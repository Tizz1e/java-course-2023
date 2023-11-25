package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogAnalyzerTest {
    @DisplayName("Local Test")
    @Test
    void localFileTest() {
        PrintStream prevConsole = System.out;
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));
        LogAnalyzer.main("--path src/main/resources/logs/logs.txt --format adoc".split(" "));
        System.setOut(prevConsole);
        Pattern pattern = Pattern.compile("averageSize \\| (659509)");
        Matcher matcher = pattern.matcher(newConsole.toString());
        assertTrue(matcher.find() && matcher.group(1).equals("659509"));
    }

    @DisplayName("Remote Test")
    @Test
    void remoteFileTest() {
        PrintStream prevConsole = System.out;
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));
        LogAnalyzer.main("--path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs".split(" "));
        System.setOut(prevConsole);
        Pattern pattern = Pattern.compile("averageSize \\| (659509)");
        Matcher matcher = pattern.matcher(newConsole.toString());
        assertTrue(matcher.find() && matcher.group(1).equals("659509"));
    }
}
