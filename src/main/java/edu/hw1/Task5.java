package edu.hw1;

public final class Task5 {
    private Task5() {

    }

    private static boolean isPalindromic(String number) {
        int numberLength = number.length();
        for (int i = 0; i < numberLength / 2; i++) {
            if (number.charAt(i) != number.charAt(numberLength - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeDescendant(long number) {
        String num = String.valueOf(number);
        if (isPalindromic(num)) {
            return true;
        }
        int numberLength = num.length();
        StringBuilder childNumber = new StringBuilder();
        while (numberLength > 1) {
            for (int i = 0; i < numberLength / 2; i++) {
                int digit1 = num.charAt(2 * i) - '0';
                int digit2 = num.charAt(2 * i + 1) - '0';
                childNumber.append(digit1 + digit2);
            }
            if (numberLength % 2 == 1) {
                childNumber.append(num.charAt(numberLength - 1));
            }
            num = childNumber.toString();
            numberLength = num.length();
            childNumber.setLength(0);
            if (numberLength > 1 && isPalindromic(num)) {
                return true;
            }
        }
        return false;
    }
}
