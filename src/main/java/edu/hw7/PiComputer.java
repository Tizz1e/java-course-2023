package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class PiComputer {
    private PiComputer() {
    }

    @SuppressWarnings("MagicNumber")
    public static double sequentialComputing(long iterations) {
        long totalCount = 0;
        long circleCount = 0;
        float x;
        float y;
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (long i = 0; i < iterations; i++) {
            totalCount++;
            x = random.nextFloat();
            y = random.nextFloat();
            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }
        return 4 * (double) circleCount / totalCount;
    }

    @SuppressWarnings("MagicNumber")
    public static double parallelComputing(long iterations, int threads) {
        if (threads < 1) {
            throw new IllegalArgumentException("Threads must be more than 1");
        }

        AtomicLong circleCount = new AtomicLong(0);
        AtomicLong totalCount = new AtomicLong(0);

        Runnable task = () -> {
            long curTotalCount = 0;
            long curCircleCount = 0;
            float x;
            float y;
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (long i = 0; i < iterations / threads; i++) {
                curTotalCount++;
                x = random.nextFloat();
                y = random.nextFloat();
                if (x * x + y * y <= 1) {
                    curCircleCount++;
                }
            }

            totalCount.addAndGet(curTotalCount);
            circleCount.addAndGet(curCircleCount);
        };

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            threadList.add(new Thread(task));
        }
        for (int i = 0; i < threads; i++) {
            threadList.get(i).start();
        }
        try {
            for (int i = 0; i < threads; i++) {
                threadList.get(i).join();
            }
        } catch (InterruptedException ignored) {
        }

        return 4 * (double) circleCount.get() / totalCount.get();
    }
}
