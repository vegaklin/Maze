package backend.academy.maze.render;

import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);

    String renderWithNumberOfRowsFront(Maze maze);

    String renderWithNumberOfRowsBack(Maze maze);
}
