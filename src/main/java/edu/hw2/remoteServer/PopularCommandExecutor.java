package edu.hw2.remoteServer;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    private final static int DEFAULT_MAX_ATTEMPTS = 10;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public PopularCommandExecutor(int maxAttempts) {
        this(new DefaultConnectionManager(), maxAttempts);
    }

    public PopularCommandExecutor() {
        this(new DefaultConnectionManager(), DEFAULT_MAX_ATTEMPTS);
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                if (attempt == maxAttempts - 1) {
                    throw new ConnectionException("Can not execute command after attempts: "
                        + maxAttempts, e);
                }
            }
        }
    }
}
