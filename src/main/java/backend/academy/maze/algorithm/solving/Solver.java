package backend.academy.maze.algorithm.solving;

import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.List;

/**
 * Solver interface that defines a method for solving a maze to find a path from a start to an end point.
 */
public interface Solver {
    /**
     * Solves the maze to find a path between the given start and end coordinates.
     *
     * @param maze The maze to be solved.
     * @param start The starting coordinate within the maze.
     * @param end The ending coordinate within the maze.
     * @return A list of coordinates representing the path from start to end, or an empty list if no path is found.
     */
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}

