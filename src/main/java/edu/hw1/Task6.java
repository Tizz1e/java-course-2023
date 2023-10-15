package edu.hw1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
public class Task6 {
    public static int countK(int a) {
        if (a == 6174) {
            return 0;
        }
        if (a / 10000 > 0) {
            return -1;
        }
        List<Integer> digits = new ArrayList<>();
        int power = 10000;
        for (int i = 0; i < 4; ++i) {
            digits.add(a % power / (power / 10));
            power /= 10;
        }
        int countEqualPairs = 0;
        for (int i = 0; i < 3; i++) {
            if (digits.get(i) == digits.get(i + 1)) {
                countEqualPairs++;
            }
        }
        if (countEqualPairs == 3) {
            return -1;
        }
        Collections.sort(digits);
        power = 1000;
        int min = 0;
        int max = 0;
        for (int i = 0; i < 4; i++) {
            min += digits.get(i) * power;
            power /= 10;
        }
        power = 1000;
        for (int i = 3; i >= 0; i--) {
            max += digits.get(i) * power;
            power /= 10;
        }
        return 1 + countK(max - min);
    }
}
