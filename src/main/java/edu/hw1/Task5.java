package edu.hw1;

public class Task5 {
    private static boolean isPalindromic(String number) {
        int numberLength = number.length();
        for (int i = 0; i < numberLength / 2; i++) {
            if (number.charAt(i) != number.charAt(numberLength - i - 1)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isPalindromeDescendant(long _number) {
        String number = Long.valueOf(_number).toString();
        if (isPalindromic(number)) {
            return true;
        }
        int numberLength = number.length();
        StringBuilder childNumber = new StringBuilder();
        while (numberLength > 1) {
            for (int i = 0; i < numberLength / 2; i++) {
                int digit1 = number.charAt(2 * i) - '0';
                int digit2 = number.charAt(2 * i + 1) - '0';
                childNumber.append(digit1 + digit2);
            }
            if (numberLength % 2 == 1) {
                childNumber.append(number.charAt(numberLength - 1));
            }
            number = childNumber.toString();
            numberLength = number.length();
            childNumber.setLength(0);
            if (numberLength > 1 && isPalindromic(number)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(isPalindromeDescendant(1511));
    }
}
