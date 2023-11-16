package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Homework {
    public static Duration averageTime(List<String> data) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        List<Duration> durations = new ArrayList<>();
        for (String line : data) {
            String[] range = line.split(" - ");
            LocalDateTime begin = LocalDateTime.parse(range[0].replace(", ", "T"));
            LocalDateTime end = LocalDateTime.parse(range[1].replace(", ", "T"));
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
        Matcher matcher1 = Pattern.compile("(\\d{4})-(\\d+)-(\\d+)").matcher(string);
        Matcher matcher2 = Pattern.compile("(\\d+)/(\\d+)/(\\d{2,})").matcher(string);
        Matcher matcher3 = Pattern.compile("(\\d+) days? ago").matcher(string);
        LocalDate today = LocalDate.now();

        LocalDate answer = null;

        if (matcher1.find()) {
            int year = Integer.parseInt(matcher1.group(1));
            int month = Integer.parseInt(matcher1.group(2));
            int day = Integer.parseInt(matcher1.group(3));
            answer = LocalDate.of(year, month, day);

        } else if (matcher2.find()) {
            String tmpYear = matcher2.group(3);
            int year = Integer.parseInt(tmpYear);
            year += tmpYear.length() == 2 ? today.getYear() / 100 * 100 : 0;
            int month = Integer.parseInt(matcher2.group(2));
            int day = Integer.parseInt(matcher2.group(1));
            answer = LocalDate.of(year, month, day);

        } else if (matcher3.find()) {
            int days = Integer.parseInt(matcher3.group(1));
            answer = today.minusDays(days);

        } else {
            switch (string.toLowerCase(Locale.ROOT)) {
                case "today" -> answer = today;
                case "tomorrow" -> answer = today.plusDays(1);
                case "yesterday" -> answer = today.minusDays(1);
            }
        }

        return Optional.of(answer);
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
            builder.append("*");
        }
        builder.setLength(builder.length() - 1);

        return Pattern.compile(builder.toString()).matcher(string).find();
    }

    boolean[] binaryCodeValidation(String code) {
        boolean[] result = new boolean[3];
        String pattern1 = "^(0|1)(0|1)0.*";
        String pattern2 = "^0*0$|^1*1$";
        String pattern3 = "^[01]{1,3}$";
        result[0] = Pattern.compile(pattern1).matcher(code).find();
        result[1] = Pattern.compile(pattern2).matcher(code).find();
        result[2] = Pattern.compile(pattern3).matcher(code).find();
        return result;
    }

    boolean[] binaryCodeValidationBonus(String code) {
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

    public static void main(String[] args) {
    }
}
