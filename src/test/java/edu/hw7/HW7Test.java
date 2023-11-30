package edu.hw7;

import edu.hw7.personDatabase.Database;
import edu.hw7.personDatabase.Person;
import edu.hw7.personDatabase.PersonDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HW7Test {
    @DisplayName("Многопоточный счетчик")
    @Test
    void testCounter() {
        Counter counter1 = new Counter();
        counter1.incrementKTimes(100);
        assertEquals(100, counter1.get());

        Counter counter2 = new Counter();
        counter2.incrementKTimes(1000);
        assertEquals(1000, counter2.get());

        counter1.incrementKTimes(100);
        assertEquals(200, counter1.get());

    }

    @DisplayName("Параллельный факториал")
    @Test
    void factorialTest() {
        BigInteger factorial1 = ParallelFactorialComputing.calculate(6);
        assertEquals(BigInteger.valueOf(720L), factorial1);
        BigInteger factorial2 = ParallelFactorialComputing.calculate(10000);
        BigInteger ans = BigInteger.ONE;
        for (long i = 2; i <= 10000; i++) {
            ans = ans.multiply(BigInteger.valueOf(i));
        }
        assertEquals(ans, factorial2);
    }

    @DisplayName("База данных")
    @Test
    void databaseTest() {
        // i have no idea how to test multithreaded code
    }

    @DisplayName("Число Pi")
    @Test
    void piTest() {
        /*
        Speed up per threads count
            2 threads - 1.48
            4 threads - 3.12
            6 threads - 4.00
            8 threads - 6.48
         */

        /*
        (digit) means that on this digit some answers was pretty close, but wrong
        [digit] means that on this digit answers most answers was wrong

        10_000_000 iters = 3.14(1)
        100_000_000 iters = 3.141[5]
        1_000_000_000 iters = 3.1415(9)
         */

        Logger logger = LogManager.getLogger();

        Thread seq = new Thread(() -> {
            logger.info("Begin seq");
            logger.info("Seq: " + PiComputer.sequentialComputing(1_000_000_000L));
        });
        Thread par = new Thread(() -> {
            logger.info("Begin par");
            logger.info("Par: " + PiComputer.parallelComputing(1_000_000_000L, 8));
        });
        seq.start();
        par.start();
        try {
            seq.join();
            par.join();
        } catch (InterruptedException e) {
        }
    }


}
