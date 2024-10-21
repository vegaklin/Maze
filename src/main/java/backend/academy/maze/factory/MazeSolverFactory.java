package backend.academy.maze.factory;

import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.algorithm.solving.astar.AStarMazeSolver;
import backend.academy.maze.algorithm.solving.bfs.BFSMazeSolver;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeSolverFactory {
    public static Solver createMazeSolver(SolverType type) {
        return switch (type) {
            case A_STAR -> new AStarMazeSolver();
            case BFS -> new BFSMazeSolver();
        };
    }
}
