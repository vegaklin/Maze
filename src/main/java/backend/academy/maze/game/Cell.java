package backend.academy.maze.game;

public record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE }
}

