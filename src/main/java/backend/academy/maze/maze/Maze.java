package backend.academy.maze.maze;

import lombok.Getter;

public final class Maze {
    @Getter private final int height;
    @Getter private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Type.WALL);
            }
        }
    }

    public Cell getGridElement(int row, int col) {
        return grid[row][col];
    }

    public void addPassageToGrid(int row, int col) {
        grid[row][col] = new Cell(row, col, Type.PASSAGE);
    }

    public boolean isPassageInGrid(int row, int col) {
        return grid[row][col].type() == Type.PASSAGE;
    }

    public void addWallToGrid(int row, int col) {
        grid[row][col] = new Cell(row, col, Type.WALL);
    }

    public boolean isWallInGrid(int row, int col) {
        return grid[row][col].type() == Type.WALL;
    }

    public void addModeToGrid(int row, int col, Type type) {
        grid[row][col] = new Cell(row, col, type);
    }
}
