package backend.academy.maze.maze;

import lombok.Getter;
import static backend.academy.maze.validation.MazeValidator.isCorrectRowCol;

@Getter public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                addWallToGrid(row, col);
            }
        }
    }

    public Maze deepCopy() {
        Maze copy = new Maze(this.height, this.width);
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                Cell originalCell = this.getGridElement(row, col);
                copy.grid[row][col] = new Cell(originalCell.row(), originalCell.col(), originalCell.type());
            }
        }
        return copy;
    }

    public Cell getGridElement(int row, int col) {
        isCorrectRowCol(row, col, width, height);
        return grid[row][col];
    }

    public void addPassageToGrid(int row, int col) {
        isCorrectRowCol(row, col, width, height);
        grid[row][col] = new Cell(row, col, Type.PASSAGE);
    }

    public boolean isPassageInGrid(int row, int col) {
        isCorrectRowCol(row, col, width, height);
        return grid[row][col].type() == Type.PASSAGE;
    }

    public void addWallToGrid(int row, int col) {
        isCorrectRowCol(row, col, width, height);
        grid[row][col] = new Cell(row, col, Type.WALL);
    }

    public boolean isWallInGrid(int row, int col) {
        isCorrectRowCol(row, col, width, height);
        return grid[row][col].type() == Type.WALL;
    }

    public void addModeToGrid(int row, int col, Type type) {
        isCorrectRowCol(row, col, width, height);
        grid[row][col] = new Cell(row, col, type);
    }
}
