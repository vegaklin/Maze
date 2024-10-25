package backend.academy.maze.render;

import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import java.util.List;

public class ConsoleRenderer implements Renderer {
    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return renderMaze(maze, path, false, false);
    }

    @Override
    public String renderWithNumberOfRowsFront(Maze maze) {
        return renderMaze(maze, null, true, false);
    }

    @Override
    public String renderWithNumberOfRowsBack(Maze maze) {
        return renderMaze(maze, null, false, true);
    }

    private String renderMaze(Maze maze, List<Coordinate> path, boolean showNumbersFront, boolean showNumbersBack) {
        StringBuilder mazeImage = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            if (showNumbersFront) {
                addNumberOfRowToRenderFront(maze, row, mazeImage);
            }
            renderRow(maze, path, mazeImage, row);
            if (showNumbersBack) {
                addNumberOfRowToRenderEnd(maze, row, mazeImage);
            }
            mazeImage.append('\n');
        }
        return mazeImage.toString();
    }

    private void renderRow(Maze maze, List<Coordinate> path, StringBuilder mazeImage, int row) {
        for (int col = 0; col < maze.width(); col++) {
            Coordinate current = new Coordinate(row, col);
            Type cellType = maze.getGridElement(row, col).type();
            mazeImage.append(getCellRepresentation(cellType, path != null && path.contains(current)));
        }
    }

    private String getCellRepresentation(Type cellType, boolean isPath) {
        return switch (cellType) {
            case WALL -> "██";
            case PASSAGE -> isPath ? "**" : "  ";
            case SWAMP -> "__";
            case SAND -> "~~";
            case COIN -> "()";
            case EAT -> "[]";
        };
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

    private void addNumberOfRowToRenderEnd(Maze maze, int row, StringBuilder mazeImage) {
        if (row > 0 && row < maze.height() - 1) {
            mazeImage.append(row);
        }
    }

    private int countNumberOfDigits(int number) {
        return String.valueOf(number).length();
    }
}
