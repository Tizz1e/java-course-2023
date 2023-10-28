package edu.hw1;

import java.util.ArrayList;
import java.util.List;

public final class Task8 {
    private static final int BOARD_SIZE = 8;

    private Task8() {
    }

    private static ArrayList<List<Integer>> knightMoves;

    private static void initialize() {
        knightMoves = new ArrayList<>();
        @SuppressWarnings("MagicNumber")
        int[] variants = new int[] {-2, -1, 1, 2};
        for (int x : variants) {
            for (int y : variants) {
                if (Math.abs(x) != Math.abs(y)) {
                    knightMoves.add(List.of(x, y));
                }
            }
        }
    }

    private static boolean isAttacking(int[][] board, int x, int y) {
        for (List<Integer> move : knightMoves) {
            int attackX = x + move.get(0);
            int attackY = y + move.get(1);
            if (0 <= attackX && attackX < BOARD_SIZE
                && 0 <= attackY && attackY < BOARD_SIZE
                && board[attackY][attackX] == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean knightBoardCapture(int[][] board) {
        initialize();
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[y][x] == 1) {
                    if (isAttacking(board, x, y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
