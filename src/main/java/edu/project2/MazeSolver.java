package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeSolver implements Solver {
    private final List<Coordinate> neighbor;

    public MazeSolver() {
        neighbor = new ArrayList<>(List.of(
            new Coordinate(-1, 0),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(0, 1)
        ));
    }

    private void dfs(Coordinate v, Coordinate parent, Maze maze, int[][] dp, boolean[][] isVisited) {
        int row = v.row();
        int col = v.col();
        if (row < 0 || row >= maze.height()
            || col < 0 || col >= maze.width()
            || isVisited[row][col] || maze.grid()[row][col].type().equals(Cell.Type.WALL)) {
            return;
        }
        isVisited[row][col] = true;
        dp[row][col] = Math.min(dp[row][col], dp[parent.row()][parent.col()] + 1);
        for (Coordinate move : neighbor) {
            Coordinate newCoords = new Coordinate(
                row + move.row(), col + move.col()
            );
            if (!newCoords.equals(parent)) {
                dfs(newCoords, v, maze, dp, isVisited);
            }
        }
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (maze == null || start == null || end == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        if (maze.width() < Math.max(start.col(), end.col())
            || maze.height() < Math.max(start.row(), end.row())
            || 0 > Math.min(start.col(), start.row())
            || 0 > Math.min(end.col(), end.row())) {
            throw new IllegalArgumentException("Incorrect start/end coordinates");
        }
        if (maze.grid()[start.row()][start.col()].type().equals(Cell.Type.WALL)
            || maze.grid()[end.row()][end.col()].type().equals(Cell.Type.WALL)) {
            throw new IllegalArgumentException("Start and End points can not be walls");
        }

        List<Coordinate> path = new ArrayList<>();

        int[][] dp = new int[maze.height()][maze.width()];
        boolean[][] isVisited = new boolean[maze.height()][maze.width()];

        for (int row = 0; row < maze.height(); row++) {
            Arrays.fill(dp[row], Integer.MAX_VALUE);
            Arrays.fill(isVisited[row], false);
        }

        dp[start.row()][start.col()] = 0;

        dfs(start, start, maze, dp, isVisited);

        if (dp[end.row()][end.col()] == Integer.MAX_VALUE) {
            return path;
        }

        path.add(end);

        Coordinate currentCoordinate = end;
        while (!currentCoordinate.equals(start)) {
            for (Coordinate move : neighbor) {
                int newRow = currentCoordinate.row() + move.row();
                int newCol = currentCoordinate.col() + move.col();
                if (newRow < 0 || newRow >= maze.height() || newCol < 0 || newCol >= maze.width()) {
                    continue;
                }
                if (dp[newRow][newCol] == dp[currentCoordinate.row()][currentCoordinate.col()] - 1) {
                    currentCoordinate = new Coordinate(newRow, newCol);
                    path.add(currentCoordinate);
                    break;
                }
            }
        }
        return path;
    }
}
