package edu.hw3;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("magicNumber")
public class ArabicToRomanianConverter {
    private ArabicToRomanianConverter() {
    }

    public static final String POSITIVE_LOWER_4000 =
        "Converter works only with positive numbers lower than 4000";
    private static final Map<Integer, String> CONVERT_MAP;

    static {
        CONVERT_MAP = new HashMap<>();
        CONVERT_MAP.put(1, "I");
        CONVERT_MAP.put(4, "IV");
        CONVERT_MAP.put(5, "V");
        CONVERT_MAP.put(9, "IX");
        CONVERT_MAP.put(10, "X");
        CONVERT_MAP.put(40, "XL");
        CONVERT_MAP.put(50, "L");
        CONVERT_MAP.put(90, "XC");
        CONVERT_MAP.put(100, "C");
        CONVERT_MAP.put(400, "CD");
        CONVERT_MAP.put(500, "D");
        CONVERT_MAP.put(900, "CM");
        CONVERT_MAP.put(1000, "M");
    }

    public static String convertToRoman(int number) {
        if (number >= 4000 || number <= 0) {
            throw new IllegalArgumentException(POSITIVE_LOWER_4000);
        }
        StringBuilder builder = new StringBuilder();
        int currentPower = 1000;
        int currentNumber = number;
        while (currentNumber != 0) {
            int digit = currentNumber / currentPower;
            if (CONVERT_MAP.containsKey(digit * currentPower)) {
                builder.append(CONVERT_MAP.get(digit * currentPower));
                currentNumber -= digit * currentPower;
            } else if (digit > 5) {
                builder.append(CONVERT_MAP.get(currentPower * 5));
                for (int i = 0; i < digit - 5; i++) {
                    builder.append(CONVERT_MAP.get(currentPower));
                }
                currentNumber -= digit * currentPower;
            } else if (digit > 0) {
                for (int i = 0; i < digit; i++) {
                    builder.append(CONVERT_MAP.get(currentPower));
                }
                currentNumber -= digit * currentPower;
            }
            currentPower /= 10;
        }
        return builder.toString();
    }
}
