package backend.academy.maze.algorithm.generation;

import backend.academy.maze.maze.Maze;

public interface Generator {
    Maze generate(int height, int width);
}

