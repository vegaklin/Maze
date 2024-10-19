package backend.academy.maze.render;

import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder mazeImage = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                mazeImage.append(maze.getGridElement(row, col).type() == Cell.Type.WALL ? "██" : "  ");
            }
            mazeImage.append("\n");
        }
        return mazeImage.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder mazeImage = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                Coordinate current = new Coordinate(row, col);
                if (path.contains(current)) {
                    mazeImage.append("**");
                } else if (maze.getGridElement(row, col).type() == Cell.Type.WALL) {
                    mazeImage.append("██");
                } else {
                    mazeImage.append("  ");
                }
            }
            mazeImage.append("\n");
        }
        return mazeImage.toString();
    }

    @Override
    public String renderWithNumberOfRowsFront(Maze maze) {
        StringBuilder mazeImage = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            addNumberOfRowToRenderFront(maze, row, mazeImage);
            for (int col = 0; col < maze.width(); col++) {
                mazeImage.append(maze.getGridElement(row, col).type() == Cell.Type.WALL ? "██" : "  ");
            }
            mazeImage.append("\n");
        }
        return mazeImage.toString();
    }

    @Override
    public String renderWithNumberOfRowsBack(Maze maze) {
        StringBuilder mazeImage = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                mazeImage.append(maze.getGridElement(row, col).type() == Cell.Type.WALL ? "██" : "  ");
            }
            addNumberOfRowToRenderEnd(maze, row, mazeImage);
            mazeImage.append("\n");
        }
        return mazeImage.toString();
    }

    private void addNumberOfRowToRenderFront(Maze maze, int row, StringBuilder mazeImage) {
        int maxDigitCount = countNumberOfDigits(maze.height() - 2);
        int currentDigitCount = countNumberOfDigits(row);
        int spacesToAdd = maxDigitCount - currentDigitCount;

        mazeImage.append(" ".repeat(Math.max(0, spacesToAdd)));
        mazeImage.append((row > 0 && row < maze.height() - 1) ? row : " ");
        if (row == maze.height() - 1) {
            mazeImage.append(" ".repeat(Math.max(0, maxDigitCount - 1)));
        }
    }

    private int countNumberOfDigits(int number) {
        return String.valueOf(number).length();
    }

    private void addNumberOfRowToRenderEnd(Maze maze, int row, StringBuilder mazeImage) {
        if (row > 0 && row < maze.height() - 1) {
            mazeImage.append(row);
        }
    }
}

