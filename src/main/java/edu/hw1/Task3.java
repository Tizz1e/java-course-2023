package edu.hw1;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] internalArray, int[] externalArray) {
        int minInternal = Integer.MAX_VALUE;
        int minExternal = Integer.MAX_VALUE;
        int maxInternal = Integer.MIN_VALUE;
        int maxExternal = Integer.MIN_VALUE;
        for (int iterate : internalArray) {
            minInternal = Math.min(minInternal, iterate);
            maxInternal = Math.max(maxInternal, iterate);
        }
        for (int iterate : externalArray) {
            minExternal = Math.min(minExternal, iterate);
            maxExternal = Math.max(maxExternal, iterate);
        }
        return minInternal > minExternal && maxInternal < maxExternal;
    }
}
