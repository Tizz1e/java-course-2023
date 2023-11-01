package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EasyTasksTest {
    static Arguments[] atbashTests() {
        return new Arguments[] {
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("Any fool can write code that a computer can understand." +
                    " Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
                    " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi")
        };
    }

    static Arguments[] clusterizeTests() {
        return new Arguments[] {
            Arguments.of("()()()", new String[] {"()", "()", "()"}),
            Arguments.of("((()))", new String[] {"((()))"}),
            Arguments.of("((()))(())()()(()())",
                new String[] {"((()))", "(())", "()", "()", "(()())"}),
            Arguments.of("((())())(()(()()))", new String[] {"((())())", "(()(()()))"})
        };
    }

    static Arguments[] frequencyTest() {
        return new Arguments[] {
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("this", 1, "that", 1, "and", 2)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2))
        };
    }

    @DisplayName("Atbash decoder")
    @ParameterizedTest(name="{index} : {0}")
    @MethodSource("atbashTests")
    void testAtbash(String test, String answer) {
        assertThat(Homework3.atbash(test)).isEqualTo(answer);
    }

    @DisplayName("Brackets Clusterization")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("clusterizeTests")
    void testClusterize(String test, String[] ans) {
        assertThat(Homework3.clusterize(test)).isEqualTo(ans);
    }


    @DisplayName("Frequency Test")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("frequencyTest")
    <T> void testFrequencyDict(List<T> test, Map<T, Integer> answer) {
        assertEquals(Homework3.freqDict(test), answer);
    }

}
