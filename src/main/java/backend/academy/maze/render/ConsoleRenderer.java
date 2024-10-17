package backend.academy.maze.render;

import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Maze;

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
}

