package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class NestingDollWriter {
    private NestingDollWriter() {
    }

    @SuppressWarnings("NestedTryDepth")
    public static void createAndWrite(String path) throws IOException {
        try {
            Path file = Files.createFile(Path.of(path));
            try (OutputStream os = Files.newOutputStream(file)) {
                try (CheckedOutputStream cos = new CheckedOutputStream(os, new Adler32())) {
                    try (BufferedOutputStream bos = new BufferedOutputStream(cos)) {
                        try (OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8)) {
                            try (PrintWriter pw = new PrintWriter(osw)) {
                                pw.write("Programming is learned by writing programs. ― Brian Kernighan");
                            }
                        } catch (IOException e) {
                            throw new IOException(" Can't create Output Stream Writer");
                        }
                    } catch (IOException e) {
                        throw new IOException("Can't create Buffered Output Stream");
                    }
                } catch (IOException e) {
                    throw new IOException("An error during work with CheckedOutputStream");
                }
            } catch (IOException e) {
                throw new IOException("Can't open output stream", e);
            }
        } catch (IOException e) {
            throw new IOException("Can't create new file with given path", e);
        }
    }
}
