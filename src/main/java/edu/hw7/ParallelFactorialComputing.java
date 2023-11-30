package edu.hw7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public final class ParallelFactorialComputing {
    private ParallelFactorialComputing() {
    }

    public static BigInteger calculate(long n) {
        List<BigInteger> nums = new ArrayList<>();

        for (long i = 1; i <= n; i++) {
            nums.add(BigInteger.valueOf(i));
        }

        return nums.parallelStream()
            .reduce(BigInteger::multiply).orElse(BigInteger.ONE);
    }
}
