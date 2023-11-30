package edu.hw5;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class Homework {
    private Homework() {
    }

    public static Duration averageTime(List<String> data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd, HH:mm");
        List<Duration> durations = new ArrayList<>();
        for (String line : data) {
            String[] range = line.split(" - ");

            LocalDateTime begin = LocalDateTime.parse(range[0], formatter);
            LocalDateTime end = LocalDateTime.parse(range[1], formatter);

            durations.add(Duration.between(begin, end));
        }
        Duration summary = durations.stream()
            .reduce(Duration.ZERO, Duration::plus);

        return summary.dividedBy(durations.size());
    }

    public static List<LocalDate> findBlackFridays(int year) {
        LocalDate localDate = LocalDate.ofYearDay(year, 1);
        List<LocalDate> blackFridays = new ArrayList<>();
        do {
            if (localDate.getYear() != year) {
                break;
            }
            if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY && localDate.getDayOfMonth() == 13) {
                blackFridays.add(localDate);
            }
            localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (localDate.getYear() == year);

        return blackFridays;
    }

    public static LocalDate findNextBF(LocalDate curDate) {
        TemporalAdjuster adjuster = temporal -> {
            LocalDate date = LocalDate.ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
            List<LocalDate> bfs = findBlackFridays(date.getYear());
            LocalDate answer = null;
            for (LocalDate bf : bfs) {
                if (!date.isAfter(bf)) {
                    answer = bf;
                    break;
                }
            }
            if (answer == null) {
                answer = findBlackFridays(date.getYear() + 1).get(0);
            }
            return answer;
        };

        return curDate.with(adjuster);
    }

    public static Optional<LocalDate> parseDate(String string) {
        List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-d"),
            DateTimeFormatter.ofPattern("d/M/[uuuu][uu]")
        );
        for (DateTimeFormatter formatter : formatters) {
            try {
                return Optional.of(LocalDate.parse(string, formatter));
            } catch (DateTimeParseException ignored) {
            }
        }

        LocalDate today = LocalDate.now();
        Matcher matcher = Pattern.compile("(\\d+) days? ago").matcher(string);
        if (matcher.find()) {
            return Optional.of(today.minusDays(Long.parseLong(matcher.group(1))));
        }

        return switch (string.toLowerCase(Locale.ROOT)) {
            case "today" -> Optional.of(today);
            case "tomorrow" -> Optional.of(today.plusDays(1));
            case "yesterday" -> Optional.of(today.minusDays(1));
            default -> Optional.empty();
        };
    }

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        return pattern.matcher(password).find();
    }

    //Вы уж простите, но проверка корректности кода региона - упражнение для читателя :)
    public static boolean validateLicensePlate(String plate) {
        Pattern pattern = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]\\d{2}");
        return pattern.matcher(plate).find();
    }

    public static boolean checkIsSubsequence(String check, String string) {
        if (check.isEmpty()) {
            return true;
        }
        StringBuilder builder = new StringBuilder();

        for (char c : check.toCharArray()) {
            builder.append(c);
            builder.append(".*");
        }
        builder.setLength(builder.length() - 1);

        return Pattern.compile(builder.toString()).matcher(string).find();
    }

    public static boolean[] binaryCodeValidation(String code) {
        boolean[] result = new boolean[3];
        String pattern1 = "^(0|1)(0|1)0.*";
        String pattern2 = "^0(.*0)?$|^1(.*1)?$";
        String pattern3 = "^[01]{1,3}$";
        result[0] = Pattern.compile(pattern1).matcher(code).find();
        result[1] = Pattern.compile(pattern2).matcher(code).find();
        result[2] = Pattern.compile(pattern3).matcher(code).find();
        return result;
    }

    public static boolean[] binaryCodeValidationBonus(String code) {
        boolean[] result = new boolean[7];

        List<String> patterns = List.of(
            "^(0|1)([01]{2})*$",
            "(^0([01]{2})*$)|(^1([01]{2})*(0|1)$)",
            "^(1*01*01*0)*$",
            "^(0.*$|1$|10.*$|110.*$|111..*)",
            "^(1.)*1?$",
            "^0{2,}$|^10{2,}$|^010+$|^0{2,}10*$",
            "(^0(10+)*1?$)|(^1(0+1)*0*$)"
        );

        for (int i = 0; i < 7; i++) {
            result[i] = Pattern.compile(patterns.get(i)).matcher(code).find();
        }

        return result;
    }
}
