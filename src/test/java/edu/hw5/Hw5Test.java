package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.hw5.Homework.averageTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hw5Test {
    @Test
    @DisplayName("Average time in game club")
    void test1() {
        List<String> t = new ArrayList<>(List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        )
        );
        assertEquals(averageTime(t).toMinutes(), 220);
    }
}
