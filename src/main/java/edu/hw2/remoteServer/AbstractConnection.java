package edu.hw2.remoteServer;

public abstract class AbstractConnection implements Connection {
    protected boolean isOpen;

    public AbstractConnection() {
        isOpen = true;
    }

    @Override
    public abstract void execute(String command);

    @Override
    public void close() {
        isOpen = false;
    }
}
