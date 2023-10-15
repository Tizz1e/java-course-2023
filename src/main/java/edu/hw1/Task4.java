package edu.hw1;

public class Task4 {
    public static String fixString(String input) {
        int n = input.length();
        char[] correctOrder = new char[n];
        for (int i = 0; i < n / 2; i++) {
            correctOrder[2 * i] = input.charAt(2 * i + 1);
            correctOrder[2 * i + 1] = input.charAt(2 * i);
        }
        if (n % 2 == 1) {
            correctOrder[n - 1] = input.charAt(n - 1);
        }
        return new String(correctOrder);
    }
}
