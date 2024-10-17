package backend.academy.maze.maze;

public record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE }
}

