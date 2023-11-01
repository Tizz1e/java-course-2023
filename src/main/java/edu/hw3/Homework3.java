package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Homework3 {

    public static final String BRACKETS_NOT_CORRECT = "Given brackets line is not correct";

    private Homework3() {

    }

    public static String atbash(String line) {
        char[] symbols = line.toCharArray();

        for (int i = 0; i < symbols.length; i++) {
            char c = symbols[i];
            if (Character.isUpperCase(c)) {
                symbols[i] = (char) ('Z' - c + 'A');
            } else if (Character.isLowerCase(c)) {
                symbols[i] = (char) ('z' - c + 'a');
            }
        }

        return new String(symbols);
    }

    public static String[] clusterize(String brackets) {
        List<String> answer = new ArrayList<>();
        int balance = 0;
        int start = 0;
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
                if (balance < 0) {
                    throw new IllegalArgumentException(BRACKETS_NOT_CORRECT);
                }
            }
            if (balance == 0) {
                answer.add(brackets.substring(start, i + 1));
                start = i + 1;
            }
        }

        if (balance != 0) {
            throw new IllegalArgumentException(BRACKETS_NOT_CORRECT);
        }

        return answer.toArray(new String[0]);
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> frequency = new HashMap<>();
        for (T element : list) {
            frequency.merge(element, 1, Integer::sum);
        }
        return frequency;
    }

    public static String[] parseContacts(String[] contacts, String sortKey) {
        if (contacts == null) {
            return new String[] {};
        }
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null) {
                    return 1;
                } else if (o2 == null) {
                    return -1;
                }
                String[] tmp = o1.split(" ");
                String ob1 = tmp[tmp.length - 1];

                tmp = o2.split(" ");
                String ob2 = tmp[tmp.length - 1];
                if (sortKey.equals("ASC")) {
                    return ob1.compareTo(ob2);
                } else {
                    return ob2.compareTo(ob1);
                }
            }
        };
        Arrays.sort(contacts, comparator);
        return contacts;
    }

    public static Comparator<String> treeMapNullComparator() {
        return (String o1, String o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            } else {
                return o1.compareTo(o2);
            }
        };
    }
}
