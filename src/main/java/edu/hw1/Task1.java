package edu.hw1;

public class Task1 {
    final static int SECONDS_PER_MINUTE = 60;

    private Task1() {

    }

    public static int minutesToSeconds(String time) {
        int separatorIndex = time.indexOf(':');
        if (separatorIndex == -1) {
            return -1;
        }
        String stringMinutes = time.substring(0, separatorIndex);
        String stringSeconds = time.substring(separatorIndex + 1);
        if (stringSeconds.length() != 2 || stringMinutes.isEmpty()) {
            return -1;
        }
        int second = Integer.parseInt(stringSeconds);
        int minutes = Integer.parseInt(stringMinutes);
        if (second >= SECONDS_PER_MINUTE || minutes < 0) {
            return -1;
        }
        return SECONDS_PER_MINUTE * minutes + second;
    }
}
