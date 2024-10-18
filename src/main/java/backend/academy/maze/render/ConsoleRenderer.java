package backend.academy.maze.render;

import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                sb.append(maze.getGridElement(row, col).type() == Cell.Type.WALL ? "██" : "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                Coordinate current = new Coordinate(row, col);
                if (path.contains(current)) {
                    sb.append("**"); // Путь
                } else if (maze.getGridElement(row, col).type() == Cell.Type.WALL) {
                    sb.append("██"); // Стена
                } else {
                    sb.append("  "); // Проход
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String renderWithNumberOfRowsFront(Maze maze) {
        int maxDigitCount = countNumberOfDigits(maze.getHeight() - 2);
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            int currentDigitCount = countNumberOfDigits(row);
            int spacesToAdd = maxDigitCount - currentDigitCount;
            sb.append(" ".repeat(Math.max(0, spacesToAdd)));
            sb.append((row > 0 && row < maze.getHeight() - 1) ? row : " ");
            if(row == maze.getHeight() - 1) {
                sb.append(" ".repeat(Math.max(0, maxDigitCount - 1)));
            }
            for (int col = 0; col < maze.getWidth(); col++) {
                sb.append(maze.getGridElement(row, col).type() == Cell.Type.WALL ? "██" : "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int countNumberOfDigits(int number) {
        return String.valueOf(number).length();
    }

    @Override
    public String renderWithNumberOfRowsBack(Maze maze) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                sb.append(maze.getGridElement(row, col).type() == Cell.Type.WALL ? "██" : "  ");
            }
            if (row > 0 && row < maze.getHeight() - 1) {
                sb.append(row);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

