package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.security.InvalidParameterException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class SampleTest {
    @Test
    @DisplayName("Фильтрация четных чисел")
    void filterEvenNumbers() {
        // given
        int[] numbers = new int[] {1, 2, 3, 4, 5};

        // when
        int[] evenNumbers = EvenArrayUtils.filter(numbers);

        // then
        assertThat(evenNumbers)
            .containsExactly(2, 4)
            .hasSize(2);
    }

    @Test
    @DisplayName("Длина видео в секундах")
    void minutesToSecondsTest() {
        assertThat(Task1.minutesToSeconds("01:00")).isEqualTo(60);
        assertThat(Task1.minutesToSeconds("13:56")).isEqualTo(836);
        assertThat(Task1.minutesToSeconds("10:60")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("-10:50")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Количество цифр")
    void countDigitsTest() {
        assertThat(Task2.countDigits(87654)).isEqualTo(5);
        assertThat(Task2.countDigits(-24)).isEqualTo(2);
        assertThat(Task2.countDigits(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("Вложенный массив")
    void isNestableTest() {
        assertThat(Task3.isNestable(new int[] {1, 2, 3, 4, 5}, new int[] {0, 6})).isTrue();
        assertThat(Task3.isNestable(new int[] {3, 1}, new int[] {4, 0})).isTrue();
        assertThat(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9})).isFalse();
        assertThat(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3})).isFalse();
    }

    @Test
    @DisplayName("Сломанная строка")
    void fixStringTest() {
        assertThat(Task4.fixString("123456")).isEqualTo("214365");
        assertThat(Task4.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
        assertThat(Task4.fixString("badce")).isEqualTo("abcde");
        assertThat(Task4.fixString("")).isEqualTo("");
    }

    @Test
    @DisplayName("Особый палиндром")
    void isPalindromeDescendant() {
        assertThat(Task5.isPalindromeDescendant(11211230)).isTrue();
        assertThat(Task5.isPalindromeDescendant(13001120)).isTrue();
        assertThat(Task5.isPalindromeDescendant(23336014)).isTrue();
        assertThat(Task5.isPalindromeDescendant(11)).isTrue();
        assertThat(Task5.isPalindromeDescendant(123)).isTrue();
        assertThat(Task5.isPalindromeDescendant(1511)).isFalse();
    }

    @Test
    @DisplayName("Постоянная Капрекара")
    void countK() {
        assertThat(Task6.countK(6621)).isEqualTo(5);
        assertThat(Task6.countK(6554)).isEqualTo(4);
        assertThat(Task6.countK(1234)).isEqualTo(3);
        assertThat(Task6.countK(6174)).isEqualTo(0);
    }

    @Test
    @DisplayName("Циклический битовый сдвиг")
    void rotate() {
        assertThat(Task7.rotateRight(8, 1)).isEqualTo(4);
        assertThat(Task7.rotateLeft(16, 1)).isEqualTo(1);
        assertThat(Task7.rotateLeft(17, 2)).isEqualTo(6);
    }

    @Test
    @DisplayName("Кони на доске")
    void knightBoardCapture() {
        assertThat(Task8.knightBoardCapture(
            new int[][] {
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0}
            }
        )).isTrue();
        assertThat(Task8.knightBoardCapture(
            new int[][] {
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1}
            }
        )).isFalse();
        assertThat(Task8.knightBoardCapture(
            new int[][] {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            }
        )).isFalse();
    }
}
