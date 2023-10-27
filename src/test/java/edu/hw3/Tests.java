package edu.hw3;

import edu.hw3.StockMarket.Stock;
import edu.hw3.StockMarket.StockMarketImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.linesOf;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

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

    @DisplayName("Romanian Test")
    @Test
    void convertToRoman() {
        assertEquals("II", ArabicToRomanianConverter.convertToRoman(2));
        assertEquals("XII", ArabicToRomanianConverter.convertToRoman(12));
        assertEquals("XVI", ArabicToRomanianConverter.convertToRoman(16));
        assertEquals("MMMDI", ArabicToRomanianConverter.convertToRoman(3501));
        assertEquals("MMMCMXLVI", ArabicToRomanianConverter.convertToRoman(3946));
    }

    @DisplayName("Parse Contacts Test")
    @Test
    void parseTest() {
        assertArrayEquals(Homework3.parseContacts(new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"),
            new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"});
        assertArrayEquals(Homework3.parseContacts(new String[]{}, "DESC"), new String[] {});
        assertArrayEquals(Homework3.parseContacts(null, "DESC"), new String[] {});
    }

    @DisplayName("Stock Market Test")
    @Test
    void stockTest() {
        StockMarketImplementation market = new StockMarketImplementation();
        Stock s1 = new Stock(10), s2 = new Stock(20), s3 = new Stock(5);
        market.add(s1);
        market.add(s2);
        market.add(s3);
        assertEquals(s2, market.mostValuableStock());
        market.remove(s1);
        assertEquals(s2, market.mostValuableStock());
        market.remove(s2);
        assertEquals(s3, market.mostValuableStock());
        market.add(s1);
        assertEquals(s1, market.mostValuableStock());

    }

    @DisplayName("Дерево и null")
    @Test
    void treeMapComparatorTest() {
        TreeMap<String, String> tree = new TreeMap<>(
            Homework3.treeMapNullComparator()
        );
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @DisplayName("Обратный итератор")
    @Test
    void backwardIteratorTest() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));
        assertEquals(iterator.next(), 3);
        assertEquals(iterator.next(), 2);
        assertEquals(iterator.next(), 1);
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
