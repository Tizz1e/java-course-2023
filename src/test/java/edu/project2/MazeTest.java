package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeTest {
    @DisplayName("Correctly find way")
    @Test
    void test1() {
        String[] profile = new String[] {
            "1111111",
            "0000001",
            "1111101",
            "1000001",
            "1111101",
            "1000000",
            "1111111"
        };
        Cell[][] grid = new Cell[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = (profile[i].charAt(j) == 1 ? new Cell(Cell.Type.WALL) : new Cell(Cell.Type.PASSAGE));
            }
        }
        Maze maze = new Maze(grid, 7, 7, new Coordinate(1, 0), new Coordinate(5, 6));
        Solver solver = new MazeSolver();
        List<Coordinate> path = solver.solve(maze, maze.enter(), maze.exit());
        assertThat(path.size()).isNotEqualTo(0);
    }

    @DisplayName("Can't create maze with negative sides")
    @Test
    void test2() {
        Generator gen = new MazeGenerator();
        assertThrows(
            IllegalArgumentException.class,
            ()->{
                gen.recursiveBackTracker(-5, -5);
            }
        );
    }

    @DisplayName("Can't create maze with even sides")
    @Test
    void test3() {
        Generator gen = new MazeGenerator();
        assertThrows(
            IllegalArgumentException.class,
            ()->{
                gen.recursiveBackTracker(6, 4);
            }
        );
    }

    @DisplayName("Solver, coordinates must be in maze")
    @Test
    void test4() {
        Generator gen = new MazeGenerator();
        Maze maze = gen.recursiveBackTracker(7, 7);
        Solver solver = new MazeSolver();
        assertThrows(
            IllegalArgumentException.class,
            ()->{
                solver.solve(maze, new Coordinate(11, 11), new Coordinate(1, 1));
            }
        );
    }
}
