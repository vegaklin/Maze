package backend.academy.maze.factory;

import backend.academy.maze.algorithm.solving.AStarMazeSolver;
import backend.academy.maze.algorithm.solving.BFSMazeSolver;
import backend.academy.maze.algorithm.solving.Solver;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeSolverFactory {
    public static Solver createMazeSolver(SolverType type) {
        return switch (type) {
            case A_STAR -> new BFSMazeSolver();
            case BFS -> new AStarMazeSolver();
        };
    }
}
