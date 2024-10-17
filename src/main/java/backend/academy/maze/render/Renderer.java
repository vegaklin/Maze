package backend.academy.maze.render;

import backend.academy.maze.maze.Maze;

public interface Renderer {
    String render(Maze maze);
//    String render(Maze maze, List<Coordinate> path);
}
