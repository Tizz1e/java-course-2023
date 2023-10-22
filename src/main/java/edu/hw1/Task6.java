package edu.hw1;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public final class Task6 {
    private static final int KAPREKAR = 6174;
    private static final int NUMBER_LENGTH = 4;
    private static final int BASE = 10;

    private Task6() {

    }

    public static int countK(int number) throws InvalidParameterException {
        if (number == KAPREKAR) {
            return 0;
        }
        if (number <= 0 || number / (int) Math.pow(BASE, NUMBER_LENGTH) > 0) {
            throw new InvalidParameterException("countK takes only  positive number < 10000");
        }

        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            digits.add(number % (int) Math.pow(BASE, i + 1) / (int) Math.pow(BASE, i));
        }

        boolean allEquals = true;
        for (int i = 1; i < NUMBER_LENGTH; i++) {
            if (digits.get(i - 1) != digits.get(i)) {
                allEquals = false;
                break;
            }
        }
        if (allEquals) {
            throw new InvalidParameterException("countK does not works with single digit numbers");
        }

        digits.sort(Integer::compareTo);

        int max = 0;
        int min = 0;

        for (int i = 0; i < digits.size(); i++) {
            max += digits.get(i) * (int) Math.pow(BASE, i);
            min += digits.get(i) * (int) Math.pow(BASE, NUMBER_LENGTH - 1 - i);
        }

        return 1 + countK(max - min);

    }

}
