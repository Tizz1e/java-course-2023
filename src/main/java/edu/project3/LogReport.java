package edu.project3;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings({"MagicNumber", "RegexpSinglelineJava", "MultipleStringLiterals"})
public class LogReport {
    private final String path;
    private final LocalDate from;
    private final LocalDate to;
    private final String format;
    private final List<LogRecord> logs;

    public LogReport(String path, LocalDate from, LocalDate to, String format, List<LogRecord> logs) {
        this.path = path;
        this.from = from;
        this.to = to;
        this.format = format;
        this.logs = logs;
    }

    public void makeReport() {
        Map<String, String> general = new LinkedHashMap<>();
        general.put("file", path);
        general.put("from", from.equals(LocalDate.MIN) ? "-" : from.toString());
        general.put("to", to.equals(LocalDate.MAX) ? "-" : to.toString());
        general.put("amount", String.valueOf(logs.size()));

        general.put(
            "averageSize",
            String.valueOf((long) logs.stream()
                .mapToLong(LogRecord::bodyBytes)
                .average().orElse(0))
        );
        Map<String, Long> resources = logs.stream()
            .collect(
                Collectors.groupingBy(
                    t -> t.request().split(" ")[1],
                    Collectors.counting()
                )
            );

        Map<String, String> topResources = resources.keySet().stream()
            .sorted(Comparator.comparingLong(resources::get).reversed())
            .limit(Math.min(resources.size(), 5))
            .collect(Collectors.toMap(
                t -> t,
                t -> String.valueOf(resources.get(t)),
                (x, y) -> y,
                LinkedHashMap::new
            ));

        Map<String, Long> statusCodes = logs.stream()
            .collect(
                Collectors.groupingBy(
                    LogRecord::status,
                    Collectors.counting()
                )
            );

        Map<String, String> topStatusCodes = statusCodes.keySet().stream()
            .sorted(Comparator.comparingLong(statusCodes::get).reversed())
            .limit(Math.min(statusCodes.size(), 5))
            .collect(Collectors.toMap(
                t -> t,
                t -> String.valueOf(statusCodes.get(t)),
                (x, y) -> y,
                LinkedHashMap::new
            ));
        print(general, topResources, topStatusCodes);
    }

    private void print(Map<String, String> generalInfo, Map<String, String> topRes, Map<String, String> topStatCodes) {
        String headerFormat = format != null && format.equals("adoc") ? "====" : "####";
        System.out.println(headerFormat + " Общая информация");
        System.out.println();
        printTable("Метрика", "Значение", generalInfo);
        System.out.println();
        System.out.println(headerFormat + " Запрашиваемые ресурсы");
        System.out.println();
        printTable("Ресурс", "Количество", topRes);
        System.out.println();
        System.out.println(headerFormat + " Коды ответа");
        System.out.println();
        printTable("Код", "Количество", topStatCodes);

    }

    private void printTable(String keyHeader, String valueHeader, Map<String, String> data) {
        int keyCellLength = data.keySet().stream().mapToInt(String::length).max().orElse(5);
        keyCellLength = Math.max(keyCellLength, keyHeader.length()) + 1;

        int valueCellLength = data.values().stream().mapToInt(String::length).max().orElse(5);
        valueCellLength = Math.max(valueCellLength, valueHeader.length()) + 1;

        System.out.print("| " + keyHeader + " ".repeat(keyCellLength - keyHeader.length() - 1) + " | ");
        System.out.println(valueHeader + " ".repeat(valueCellLength - valueHeader.length() - 1) + " |");

        System.out.println("|:" + "-".repeat(keyCellLength - 1) + ":|" + "-".repeat(valueCellLength) + ":|");

        for (var entry : data.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            System.out.println("| " + key + " ".repeat(keyCellLength - key.length() - 1)
            + " | " + value + " ".repeat(valueCellLength - value.length() - 1) + " |");
        }
    }
}
