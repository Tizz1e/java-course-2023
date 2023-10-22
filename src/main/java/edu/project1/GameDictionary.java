package edu.project1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class GameDictionary implements Dictionary {
    private final ArrayList<String> words;

    private final Random random;

    public GameDictionary() {
        words = new ArrayList<>();
        random = new Random(System.currentTimeMillis());

        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("src/main/java/edu/project1/gameDictionary.txt"),
                StandardCharsets.UTF_8
            )
        )) {
            String word = reader.readLine();
            while (word != null) {
                word = word.strip();
                words.add(word);
                word = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't load Hangman dictionary", e);
        }
    }

    @Override
    public String randomWord() {
        return words.get(Math.abs(random.nextInt()) % words.size());
    }
}
