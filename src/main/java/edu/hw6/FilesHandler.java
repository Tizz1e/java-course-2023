package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FilesHandler {
    private FilesHandler() {
    }

    public static void cloneFile(Path sourcePath) throws IOException {
        if (Files.notExists(sourcePath)) {
            throw new IllegalArgumentException("No such file to copy");
        }
        try {
            int attempts = 1;
            Path targetPath;

            do {
                targetPath = sourcePath.resolveSibling(sourcePath.getFileName()
                    + " - копия" + (attempts > 1 ? " (" + attempts + ")" : ""));
                attempts++;
            } while (Files.exists(targetPath));
            Files.createFile(targetPath);
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Can't copy file", e);
        }
    }
}
