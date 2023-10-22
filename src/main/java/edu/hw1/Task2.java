package edu.hw1;

public final class Task2 {
    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {
        int num = number;
        int counter = 0;
        do {
            counter++;
            num /= 10;
        } while (num != 0);
        return counter;
    }
}
