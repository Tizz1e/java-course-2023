package edu.hw2.remoteServer;

public class FaultyConnectionManager implements ConnectionManager {
    public FaultyConnectionManager() {
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
