package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private static String rawPath = null;
    private static String rawFrom = null;
    private static String rawTo = null;
    private static String format = null;

    private static List<LogRecord> logs = new ArrayList<>();

    private LogAnalyzer() {
    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        try {
            parseArgs(args);

            try {
                var uri = new URI(rawPath);
                logs = parseLogs(getRemoteLogs(uri));
            } catch (URISyntaxException | IllegalArgumentException e) {
                if (simplePath(rawPath)) {
                    logs = parseLogs(getLocalLogs(Path.of(rawPath)));
                } else {
                    PathMatcher matcher = FileSystems.getDefault()
                        .getPathMatcher("glob:" + rawPath);
                    try (var dirStream = Files.walk(Paths.get("").toAbsolutePath())) {
                        logs = dirStream
                            .filter(matcher::matches)
                            .flatMap(file -> parseLogs(getLocalLogs(file)).stream())
                            .toList();
                    }
                }
            }

            final LocalDate from = rawFrom == null ? LocalDate.MIN
                : LocalDate.parse(rawFrom, DateTimeFormatter.ISO_LOCAL_DATE);
            final LocalDate to = rawTo == null ? LocalDate.MAX
                : LocalDate.parse(rawTo, DateTimeFormatter.ISO_LOCAL_DATE);

            logs = logs
                .stream()
                .filter(t -> !t.localTime().isBefore(from) && !t.localTime().isAfter(to))
                .collect(Collectors.toList());

            LogReport report = new LogReport(rawPath, from, to, format, logs);
            report.makeReport();

        } catch (InterruptedException | UnsupportedAddressTypeException e) {
            System.err.println("Can't get logs from given URL");
            System.err.println(e.getMessage());
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Can't get logs from given path");
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Given file doesn't looks like log file");
            System.err.println(e.getMessage());
        }
    }

    private static boolean simplePath(String path) {
        try {
            Path.of(path);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @SuppressWarnings("MagicNumber")
    private static List<LogRecord> parseLogs(String[] rawLogs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss xxxx", Locale.ENGLISH);
        List<LogRecord> result = new ArrayList<>();

        String regexp = "([^ ]+) - ([^ ]+) \\[([^ ]+ [^ \\]]+)] \"([^\"]+)\" (\\d+) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regexp);

        for (String log : rawLogs) {
            Matcher matcher = pattern.matcher(log);
            if (matcher.find()) {
                LogRecord logRecord = new LogRecord(
                    matcher.group(1),
                    matcher.group(2),
                    OffsetDateTime.parse(matcher.group(3), formatter).toLocalDate(),
                    matcher.group(4),
                    matcher.group(5),
                    Integer.parseInt(matcher.group(6)),
                    matcher.group(7),
                    matcher.group(8)
                );

                result.add(logRecord);
            }
        }

        return result;
    }

    private static String[] getLocalLogs(Path path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException ignored) {
        }

        return lines.toArray(new String[0]);
    }

    @SuppressWarnings("MagicNumber")
    private static String[] getRemoteLogs(URI uri) throws InterruptedException, IOException {
        var request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .timeout(Duration.of(20, ChronoUnit.SECONDS))
            .build();

        var response = HttpClient.newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().split("\n");
    }

    @SuppressWarnings({"MissingSwitchDefault", "InnerAssignment"})
    private static void parseArgs(String[] args) {
        InputType next = InputType.UNKNOWN;
        for (String param : args) {
            switch (next) {
                case PATH -> rawPath = param;
                case FROM -> rawFrom = param;
                case TO -> rawTo = param;
                case FORMAT -> format = param;
            }

            if (next != InputType.UNKNOWN) {
                next = InputType.UNKNOWN;
                continue;
            }

            switch (param) {
                case "--path" -> next = InputType.PATH;
                case "--from" -> next = InputType.FROM;
                case "--to" -> next = InputType.TO;
                case "--format" -> next = InputType.FORMAT;
            }
        }
    }
}
