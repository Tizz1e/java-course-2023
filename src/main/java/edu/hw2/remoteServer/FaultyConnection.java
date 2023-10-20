package edu.hw2.remoteServer;

import java.util.Random;

public class FaultyConnection extends AbstractConnection {
    private final Random random;

    private static final int CONNECTION_EXCEPTION_FREQUENCY = 3;

    public FaultyConnection() {
        random = new Random();
    }

    public void execute(String command) {
        if (isOpen) {
            if (Math.abs(random.nextInt()) % CONNECTION_EXCEPTION_FREQUENCY == 0) {
                throw new ConnectionException("Connection lost");
            }
        }
    }
}
