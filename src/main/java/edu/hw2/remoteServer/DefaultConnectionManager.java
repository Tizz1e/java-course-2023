package edu.hw2.remoteServer;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final Random random;

    private static final int FAULTY_CONNECTION_FREQUENCY = 5;

    public DefaultConnectionManager() {
        random = new Random();
    }

    @Override
    public Connection getConnection() {
        if (Math.abs(random.nextInt()) % FAULTY_CONNECTION_FREQUENCY == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
