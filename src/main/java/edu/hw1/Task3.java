package edu.hw1;

public class Task3 {
    public static boolean isNestable(int[] internalArray, int[] externalArray) {
        int minInternal = Integer.MAX_VALUE;
        int minExternal = Integer.MAX_VALUE;
        int maxInternal = Integer.MIN_VALUE;
        int maxExternal = Integer.MIN_VALUE;
        for (var iterate : internalArray) {
            minInternal = Math.min(minInternal, iterate);
            maxInternal = Math.max(maxInternal, iterate);
        }
        for (var iterate : externalArray) {
            minExternal = Math.min(minExternal, iterate);
            maxExternal = Math.max(maxExternal, iterate);
        }
        return minInternal > minExternal && maxInternal < maxExternal;
    }
    public static boolean isNestable(long[] internalArray, long[] externalArray) {
        long minInternal = Integer.MAX_VALUE;
        long minExternal = Integer.MAX_VALUE;
        long maxInternal = Integer.MIN_VALUE;
        long maxExternal = Integer.MIN_VALUE;
        for (var iterate : internalArray) {
            minInternal = Math.min(minInternal, iterate);
            maxInternal = Math.max(maxInternal, iterate);
        }
        for (var iterate : externalArray) {
            minExternal = Math.min(minExternal, iterate);
            maxExternal = Math.max(maxExternal, iterate);
        }
        return minInternal > minExternal && maxInternal < maxExternal;
    }
}
