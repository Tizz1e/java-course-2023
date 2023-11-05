package edu.project2;

public record Maze(Cell[][] grid, int height, int width, Coordinate enter, Coordinate exit) {
}
