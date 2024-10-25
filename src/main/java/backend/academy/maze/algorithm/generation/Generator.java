package backend.academy.maze.algorithm.generation;

import backend.academy.maze.maze.Maze;

/**
 * Generator interface defines the method for generating a maze.
 */
public interface Generator {
    /**
     * Generates a maze with the specified dimensions.
     *
     * @param height The height of the maze.
     * @param width The width of the maze.
     * @return A Maze object representing the generated maze.
     */
    Maze generate(int height, int width);
}

