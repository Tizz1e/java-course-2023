package edu.hw6;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter other) {
        return t -> accept(t) && other.accept(t);
    }

    static AbstractFilter largerThan(long size) {
        return t -> Files.size(t) > size;
    }

    static AbstractFilter lessThan(long size) {
        return t -> Files.size(t) < size;
    }

    static AbstractFilter isDirectory() {
        return Files::isDirectory;
    }

    static AbstractFilter isExecutable() {
        return Files::isExecutable;
    }

    static AbstractFilter isHidden() {
        return Files::isHidden;
    }

    static AbstractFilter isReadable() {
        return Files::isReadable;
    }

    static AbstractFilter isRegularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter isSameFile(Path other) {
        return t -> Files.isSameFile(t, other);
    }

    static AbstractFilter isSymbolicLink() {
        return Files::isSymbolicLink;
    }

    static AbstractFilter isWritable() {
        return Files::isWritable;
    }

    static AbstractFilter globMatches(String pattern) {
        return t -> {
            PathMatcher matcher = FileSystems
                .getDefault()
                .getPathMatcher("glob:" + pattern);
            return matcher.matches(t.getFileName());
        };
    }

    static AbstractFilter regexContains(String regex) {
        return t -> {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(t.getFileName().toString()).find();
        };
    }

    static AbstractFilter magicNumber(byte... bytes) {
        return t -> {
            try (OutputStream os = Files.newOutputStream(t)) {
                byte[] buffer = new byte[bytes.length];
                os.write(buffer);
                return Arrays.equals(bytes, buffer);
            } catch (IOException e) {
                return false;
            }
        };
    }
}
