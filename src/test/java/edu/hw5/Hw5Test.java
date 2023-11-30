package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static edu.hw5.Homework.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Hw5Test {
    static Arguments[] timeRanges() {
        return new Arguments[] {
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                220
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 00:00 - 2022-03-12, 03:30",
                    "2022-03-12, 01:00 - 2022-03-12, 05:00",
                    "2022-03-12, 23:00 - 2022-03-13, 01:00"
                ),
                190
            )
        };
    }

    static Arguments[] dates() {
        return new Arguments[] {
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12,2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(2020, 3, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234)))
        };
    }

    static Arguments[] binary() {
        return new Arguments[] {
            Arguments.of(
                "1100001", new boolean[] {true, true, false}
            ),
            Arguments.of(
                "110", new boolean[] {true, false, true}
            ),
            Arguments.of(
                "11111", new boolean[] {false, true, false}
            ),
            Arguments.of(
                "0000001", new boolean[] {true, false, false}
            ),
            Arguments.of(
                "0", new boolean[] {false, true, true}
            ),
            Arguments.of(
                "000", new boolean[] {true, true, true}
            )
        };
    }
    @ParameterizedTest
    @DisplayName("Average time in game club")
    @MethodSource("timeRanges")
    void test1(List<String> times, int answer) {
        List<String> t = new ArrayList<>(List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        )
        );
        assertEquals(answer, averageTime(times).toMinutes());
    }

    @Test
    @DisplayName("Count black fridays in year")
    void test2() {
        assertEquals(3,  findBlackFridays(1925).size());
        assertEquals(2, findBlackFridays(2024).size());
        assertEquals(2, findBlackFridays(2023).size());
    }

    @Test
    @DisplayName("Find next black friday")
    void test3() {
        assertEquals(LocalDate.of(2024, 9, 13), findNextBF(LocalDate.now()));
    }

    @DisplayName("Parse Date")
    @ParameterizedTest
    @MethodSource("dates")
    void test4(String test, Optional<LocalDate> answer) {
        assertEquals(answer, parseDate(test));
    }

    @DisplayName("Validate password")
    @Test
    void test5() {
        assertFalse(validatePassword("141hbd139rgebv9193"));
        assertTrue(validatePassword("!r17fujsd~fghfdsagja"));
        assertTrue(validatePassword("nsg251380fs@"));
        assertFalse(validatePassword("12345678"));
    }

    @DisplayName("Validate number plates")
    @Test
    void test6() {
        assertFalse(validateLicensePlate("123АВЕ777"));
        assertFalse(validateLicensePlate("А123ВГ77"));
        assertFalse(validateLicensePlate("А123ВЕ7777"));
        assertFalse(validateLicensePlate("А123ВЕ777"));
        assertFalse(validateLicensePlate("О777ОО177"));
    }

    @DisplayName("Validate is subsequence")
    @Test
    void test7() {
        assertTrue(checkIsSubsequence("abc", "achfdbaabgbcaabg"));
        assertFalse(checkIsSubsequence("abc", "aafstiqwujbkwdfefw"));
        assertTrue(checkIsSubsequence("z", "zzzzzzzzzzzz"));
        assertFalse(checkIsSubsequence("1z", "z1"));
    }

    @DisplayName("Regexp")
    @ParameterizedTest
    @MethodSource("binary")
    void test8(String code, boolean[] answer) {
        assertArrayEquals(answer, binaryCodeValidation(code));
    }


}
