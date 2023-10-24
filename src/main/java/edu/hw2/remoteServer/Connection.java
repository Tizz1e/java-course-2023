package edu.hw2.remoteServer;

public interface Connection extends AutoCloseable {
    void execute(String command);

    @Override
    void close();
}
