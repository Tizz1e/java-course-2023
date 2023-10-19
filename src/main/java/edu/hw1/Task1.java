package edu.hw1;

public class Task1 {
    final static int SECONDS_PER_MINUTE = 60;

    private Task1() {

    }

    public static int minutesToSeconds(String time) throws IllegalArgumentException {
        String[] splitTime = time.split(":");
        if (splitTime.length != 2) {
            return -1;
        }
        try {
            int minutes = Integer.parseInt(splitTime[0]);
            int seconds = Integer.parseInt(splitTime[1]);
            if (minutes < 0 || seconds < 0 || seconds >= 60) {
                return -1;
            }
            return SECONDS_PER_MINUTE * minutes + seconds;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
