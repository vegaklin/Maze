package backend.academy.maze.algorithm.generation;

import backend.academy.maze.game.Maze;

public interface Generator {
    Maze generate(int height, int width);
}

