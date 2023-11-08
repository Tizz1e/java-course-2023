package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator implements Generator {
    private final Random random;
    private final List<Coordinate> neighbor;

    public MazeGenerator() {
        random = new Random();
        neighbor = new ArrayList<>(List.of(
            new Coordinate(-1, 0),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(0, 1)
        ));
    }

    private boolean isInside(int value, int edge) {
        return 0 < value && value < edge - 1;
    }

    private Coordinate findEnter(Cell[][] maze, int height) {
        for (int row = 0; row < height; row++) {
            if (maze[row][1].type().equals(Cell.Type.PASSAGE)) {
                maze[row][0] = new Cell(Cell.Type.PASSAGE);
                return new Coordinate(row, 0);
            }
        }
        throw new RuntimeException("Can't find enter in maze");
    }

    private Coordinate findExit(Cell[][] maze, int height, int width) {
        for (int row = height - 1; row >= 0; row--) {
            if (maze[row][width - 2].type().equals(Cell.Type.PASSAGE)) {
                maze[row][width - 1] = new Cell(Cell.Type.PASSAGE);
                return new Coordinate(row, width - 1);
            }
        }
        throw new RuntimeException("Can't find exit from maze");
    }

    @Override
    public Maze recursiveBackTracker(int width, int height) {
        if (width < 0 || height < 0 || width % 2 == 0 || height % 2 == 0) {
            throw new IllegalArgumentException("Width and height must be odd numbers");
        }


        Cell[][] maze = new Cell[height][width];
        boolean[][] isVisited = new boolean[height][width];
        Cell wall = new Cell(Cell.Type.WALL);
        for (int row = 0; row < height; row++) {
            Arrays.fill(maze[row], wall);
        }

        int tmp = random.nextInt(width - 2) + 1;

        Coordinate start = new Coordinate(
            tmp % 2 == 1 ? tmp : tmp - 1,
            1
        );

        Stack<Coordinate> stack = new Stack<>();
        stack.push(start);

        maze[start.row()][start.col()] = new Cell(Cell.Type.PASSAGE);
        isVisited[start.row()][start.col()] = true;

        while (!stack.empty()) {
            Coordinate coords = stack.peek();
            Collections.shuffle(neighbor);
            boolean hasNext = false;
            for (Coordinate move : neighbor) {
                Coordinate newCoords = new Coordinate(
                    coords.row() + 2 * move.row(),
                    coords.col() + 2 * move.col()
                );
                Coordinate betweenCoords = new Coordinate(
                    coords.row() + move.row(),
                    coords.col() + move.col()
                );
                if (isInside(newCoords.row(), height)
                    && isInside(newCoords.col(), width)
                    && !isVisited[newCoords.row()][newCoords.col()]) {
                    maze[newCoords.row()][newCoords.col()] = new Cell(Cell.Type.PASSAGE);
                    maze[betweenCoords.row()][betweenCoords.col()] = new Cell(Cell.Type.PASSAGE);
                    isVisited[newCoords.row()][newCoords.col()] = true;
                    stack.push(newCoords);
                    hasNext = true;
                    break;
                }
            }
            if (!hasNext) {
                stack.pop();
            }
        }

        Coordinate enter = findEnter(maze, height);
        Coordinate exit = findExit(maze, height, width);


        for (int row = height - 1; row >= 0; row--) {
            if (maze[row][width - 2].type().equals(Cell.Type.PASSAGE)) {
                maze[row][width - 1] = new Cell(Cell.Type.PASSAGE);
                exit = new Coordinate(row, width - 1);
                break;
            }
        }

        return new Maze(maze, height, width, enter, exit);
    }
}
