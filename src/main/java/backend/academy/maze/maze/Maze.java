package backend.academy.maze.maze;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getGridElement(int row, int col) {
        return grid[row][col];
    }

    public void addPassageToGrid(int row, int col) {
        grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
    }

    public boolean isPassageInGrid(int row, int col) {
        return grid[row][col].type() == Cell.Type.PASSAGE;
    }

    public void addWallToGrid(int row, int col) {
        grid[row][col] = new Cell(row, col, Cell.Type.WALL);
    }

    public boolean isWallInGrid(int row, int col) {
        return grid[row][col].type() == Cell.Type.WALL;
    }
}
