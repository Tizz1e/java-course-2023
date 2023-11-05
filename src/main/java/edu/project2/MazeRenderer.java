package edu.project2;

import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public final class MazeRenderer implements Renderer {
    private final static String WALL = "\033[46m  \033[0m";
    private final static String PATH = "\033[42m  \033[0m";
    private final static String EDGE = "\033[41m  \033[0m";
    private final static String PASSAGE = "\033[40m  \033[0m";

    private String[][] parseMaze(Maze maze) {
        String[][] out = new String[maze.height()][maze.width()];
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                out[i][j] = maze.grid()[i][j].type().equals(Cell.Type.WALL) ? WALL : PASSAGE;
            }
        }
        return out;
    }

    private void print(String[][] out, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(out[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void render(Maze maze) {
        String[][] out = parseMaze(maze);
        print(out, maze.height(), maze.width());
    }

    @Override
    public void render(Maze maze, List<Coordinate> path) {
        String[][] out = parseMaze(maze);

        for (Coordinate coord : path) {
            out[coord.row()][coord.col()] = PATH;
        }

        Coordinate start = path.get(path.size() - 1);
        Coordinate end = path.get(0);

        out[start.row()][start.col()] = EDGE;
        out[end.row()][end.col()] = EDGE;

        print(out, maze.height(), maze.width());
    }
}
