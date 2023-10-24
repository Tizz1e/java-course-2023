package edu.hw2;

import edu.hw2.remoteServer.Connection;
import edu.hw2.remoteServer.ConnectionException;
import edu.hw2.remoteServer.ConnectionManager;
import edu.hw2.remoteServer.DefaultConnectionManager;
import edu.hw2.remoteServer.FaultyConnection;
import edu.hw2.remoteServer.FaultyConnectionManager;
import edu.hw2.remoteServer.PopularCommandExecutor;
import edu.hw2.remoteServer.StableConnection;
import org.assertj.core.api.ThrowableTypeAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RemoteServerTest {
    @Test
    @DisplayName("Remote Server #1")
    void checkFaultyConnectionManager() {
        ConnectionManager manager = new FaultyConnectionManager();
        final int iterations = 10_000;
        for (int i = 0; i < iterations; i++) {
            assertThat(manager.getConnection()).isInstanceOf(FaultyConnection.class);
        }
    }

    @Test
    @DisplayName("Remote Server #2")
    void checkDefaultConnectionManager() {
        ConnectionManager manager = new DefaultConnectionManager();
        int countFaultyConnection = 0;
        final int iterations = 10_000;
        for (int i = 0; i < iterations; i++) {
            Connection connection = manager.getConnection();
            if (connection instanceof FaultyConnection) {
                countFaultyConnection++;
            }
        }
        assertThat(countFaultyConnection != 0
            && countFaultyConnection < iterations - countFaultyConnection).isTrue();
    }

    @Test
    @DisplayName("Remote Server #3")
    void checkTryExecute() {
        PopularCommandExecutor executor = new PopularCommandExecutor(2);
        Throwable connectionException = null;
        final int iterations = 10_000;
        for (int i = 0; i < iterations; i++) {
            try {
                executor.tryExecute("sudo rm -rf /");
            } catch (ConnectionException e) {
                connectionException = e;
                break;
            }
        }
        assertThat(connectionException).isNotNull();
        assertThat(connectionException.getCause()).isNotNull();
    }
}
