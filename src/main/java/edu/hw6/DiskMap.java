package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class DiskMap extends HashMap<String, String> {
    public void save(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(filename),
                StandardCharsets.UTF_8
            )
        )) {
            for (Entry<String, String> entry : entrySet()) {
                writer.write(entry.getKey());
                writer.write(":");
                writer.write(entry.getValue());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new IOException("An error occurred during saving", e);
        }
    }

    public void load(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(
                    filename
                ),
                StandardCharsets.UTF_8
            )
        )) {
            clear();
            String line = reader.readLine();
            while (line != null) {
                String[] entry = line.split(":");
                put(entry[0], entry[1]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IOException("An error occured during loading map", e);
        }
    }
}
