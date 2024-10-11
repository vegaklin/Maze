package backend.academy.maze.render;

import backend.academy.maze.game.Coordinate;
import backend.academy.maze.game.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);
//    String render(Maze maze, List<Coordinate> path);
}
