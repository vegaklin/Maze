package backend.academy.maze.algorithm.solving;

import backend.academy.maze.game.Coordinate;
import backend.academy.maze.game.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}

