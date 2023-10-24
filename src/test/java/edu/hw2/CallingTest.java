package edu.hw2;

import edu.hw2.callingInfo.CallingInfo;
import edu.hw2.callingInfo.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingTest {
    private static final String className = "edu.hw2.CallingTest";

    private void func1() {
        assertThat(Task4.callingInfo()).isEqualTo(
            new CallingInfo(className, "func2"));
    }

    private void func2() {
        func1();
        assertThat(Task4.callingInfo()).isEqualTo(
            new CallingInfo(className, "func3"));
    }

    private void func3() {
        func2();
        assertThat(Task4.callingInfo()).isEqualTo(
            new CallingInfo(className, "check"));
    }

    @Test
    @DisplayName("Calling info #1")
    void check() {
        func3();
    }
}
