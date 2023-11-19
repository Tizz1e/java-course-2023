package edu.hw6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Homework6Test {
    @DisplayName("Disk Map")
    @Test
    void test1() throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("Hello", "World");
        diskMap.put("Java", "Backend");
        diskMap.put("Hochu", "Stazhu");
        diskMap.save("src/main/resources/diskMapSave.txt");

        DiskMap diskMapLoaded = new DiskMap();
        diskMapLoaded.load("src/main/resources/diskMapSave.txt");

        assertEquals(diskMap, diskMapLoaded);
    }

    @DisplayName("Copy file")
    @Test
    void test2() throws IOException {
        String dir = "src/main/resources/";
        String filename = "Tinkoff Bank Biggest Secret";
        Path pathToFile = Path.of(dir, filename);
        if (Files.notExists(pathToFile)) {
            Files.createFile(pathToFile);
        }
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(dir + filename),
                StandardCharsets.UTF_8
            )
        )) {
            writer.write("Aleksei Kalugin");
        }

        FilesHandler.cloneFile(pathToFile);

        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(dir + filename + " - копия"),
                StandardCharsets.UTF_8
            )
        )){
            String line = reader.readLine();
            assertEquals(line, "Aleksei Kalugin");
        }
    }

    @DisplayName("Custom Filters")
    @Test
    void test3() throws IOException {
        Path dir = Path.of("src/main/java/edu/hw6");
        AbstractFilter regularFile = Files::isRegularFile;

        DirectoryStream.Filter<Path> filter = regularFile
            .and(AbstractFilter.largerThan(1))
            .and(AbstractFilter.isReadable())
            .and(AbstractFilter.globMatches("*r.java"));

        List<String> result = new ArrayList<>();
        List<String> answer = List.of(
            "AbstractFilter.java",
            "FilesHandler.java",
            "NestingDollWriter.java",
            "PortScanner.java"
        );

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(e -> result.add(e.getFileName().toString()));
        }

        assertEquals(answer, result);
    }

    @DisplayName("NestedDollWriterTest")
    @Test
    void test4() {
        try {
            NestingDollWriter.createAndWrite("src/main/resources/truth.txt");
        } catch (IOException e) {
            Assertions.fail("IOException during creating file");
        }
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("src/main/resources/truth.txt"),
                StandardCharsets.UTF_8
            )
        )){
            String line = reader.readLine();
            assertEquals("Programming is learned by writing programs. ― Brian Kernighan", line);
        } catch (IOException e) {
            Assertions.fail("IOException during file checking");
        }
    }

    @DisplayName("HackerNews Test")
    @Test
    void test5() {
        assertEquals("Berlin's indoor pools", HackerNews.news(38318434).get());
    }
}
