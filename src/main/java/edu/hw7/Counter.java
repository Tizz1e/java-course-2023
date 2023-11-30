package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger count;

    public Counter() {
        this.count = new AtomicInteger(0);
    }

    public void increment() {
        count.incrementAndGet();
    }

    public void incrementKTimes(int k) {
        Runnable task = this::increment;

        try {
            for (int it = 0; it < k; it++) {
                Thread th = new Thread(task);
                th.start();
                th.join();
            }
        } catch (InterruptedException ignored) {

        }
    }

    public int get() {
        return count.get();
    }
}
