package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {
    @Test
    @DisplayName("Stopping after MAX_ATTEMPTS")
    void test1() {
        String lineSeparator = System.lineSeparator();
        StringBuilder test = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            test.append("a");
            test.append(lineSeparator);
        }

        ByteArrayInputStream input = new ByteArrayInputStream(test.toString().getBytes());

        PrintStream stdout = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream);


        Session session = new Session();

        System.setIn(input);
        System.setOut(out);

        ConsoleHangman.run(session);

        System.setOut(stdout);

        String[] output = byteArrayOutputStream.toString().split(lineSeparator);
        String lastResult = output[output.length - 1];
        assertThat(lastResult).isEqualTo("> You lost!");
    }

    @Test
    @DisplayName("State same after incorrect input")
    void test2() {
        Session session = new Session();

        int sessionPreviousHashcode = session.hashCode();

        String lineSeparator = System.lineSeparator();
        String test = "41" + lineSeparator;

        ByteArrayInputStream input = new ByteArrayInputStream(test.getBytes());

        PrintStream stdout = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream);

        System.setIn(input);
        System.setOut(out);

        ConsoleHangman.run(session);

        System.setOut(stdout);

        assertThat(session.hashCode()).isEqualTo(sessionPreviousHashcode);
    }
}
