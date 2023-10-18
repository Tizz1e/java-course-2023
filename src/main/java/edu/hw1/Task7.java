package edu.hw1;

public final class Task7 {
    private Task7() {

    }

    public static int rotateLeft(int n, int shift) {
        int binaryLength = 0;
        while ((n >> binaryLength) > 0) {
            binaryLength++;
        }
        int realShift = shift % binaryLength;
        return ((n << realShift) | (n >> (binaryLength - realShift))) & ((1 << binaryLength) - 1);
    }

    public static int rotateRight(int n, int shift) {
        int binaryLength = 0;
        while ((n >> binaryLength) > 0) {
            binaryLength++;
        }
        int realShift = shift % binaryLength;
        return ((n & ((1 << realShift) - 1)) << (binaryLength - realShift)) | (n >> realShift);
    }
}
