package edu.hw1;

public class Task7 {
    public static int rotateLeft(int n, int shift) {
        int binaryLength = 0;
        while ((n >> binaryLength) > 0) {
            binaryLength++;
        }
        shift %= binaryLength;
        int result = ((n << shift) | (n >> (binaryLength - shift))) & ((1 << binaryLength) - 1);
        return result;
    }

    public static int rotateRight(int n, int shift) {
        int binaryLength = 0;
        while ((n >> binaryLength) > 0) {
            binaryLength++;
        }
        shift %= binaryLength;
        int result = ((n & ((1 << shift) - 1)) << (binaryLength - shift)) | (n >> shift);
        return result;
    }
}
