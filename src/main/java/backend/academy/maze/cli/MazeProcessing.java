package backend.academy.maze.cli;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.improvement.MazeAdditionalPath;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import static backend.academy.maze.improvement.MazeDifferentSurfacesMode.addDifferentSurfacesInMaze;

public class MazeProcessing {
    public Maze generateMaze(Generator generator, Scanner scanner, PrintStream out) {
        MazeDetailsProcess mazeDetailsProcess = new MazeDetailsProcess();
        int height = mazeDetailsProcess.inputHeight(scanner, out);
        int width = mazeDetailsProcess.inputWidth(scanner, out);
        return generator.generate(height, width);
    }

    public Maze improveMaze(Maze maze) {
        MazeAdditionalPath moder = new MazeAdditionalPath();
        Maze mazeWithPaths = moder.addingPathsInMaze(maze);
        return addDifferentSurfacesInMaze(mazeWithPaths);
    }

    public List<Coordinate> solveMaze(Solver solver, Maze maze, Coordinate start, Coordinate end) {
        return solver.solve(maze, start, end);
    }
}
