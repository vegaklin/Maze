package backend.academy.maze.cli;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.Renderer;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import static backend.academy.maze.cli.MazeUIPrinter.printMazePath;
import static backend.academy.maze.cli.MazeUIPrinter.printTitle;

public class MazeInterface {
    Renderer renderer;
    MazeProcessing mazeProcessing;

    public MazeInterface(Renderer renderer, MazeProcessing mazeProcessing) {
        this.renderer = renderer;
        this.mazeProcessing = mazeProcessing;
    }

    public void start(Scanner scanner, PrintStream out) {
        printTitle(out);

        Generator generator = chooseGeneratorAlgorithm(scanner, out);
        Maze maze = mazeProcessing.generateMaze(generator, scanner, out);
        Maze improvedMaze = mazeProcessing.improveMaze(maze);

        Coordinate start = getStartCoordinate(improvedMaze, scanner, out);
        Coordinate end = getEndCoordinate(improvedMaze, scanner, out);
        addStartAndEnd(improvedMaze, start, end);

        Solver solver = chooseSolverAlgorithm(scanner, out);
        List<Coordinate> mazePath = mazeProcessing.solveMaze(solver, improvedMaze, start, end);
        printMazePath(out, improvedMaze, mazePath, renderer);
    }

    private Generator chooseGeneratorAlgorithm(Scanner scanner, PrintStream out) {
        MazeAlgorithmProcess mazeAlgorithmProcess = new MazeAlgorithmProcess();
        return mazeAlgorithmProcess.generateAlgorithmChoosing(scanner, out);
    }

    private Solver chooseSolverAlgorithm(Scanner scanner, PrintStream out) {
        MazeAlgorithmProcess mazeAlgorithmProcess = new MazeAlgorithmProcess();
        return mazeAlgorithmProcess.solveAlgorithmChoosing(scanner, out);
    }

    private Coordinate getStartCoordinate(Maze maze,
                                        Scanner scanner,
                                        PrintStream out) {
        MazeDetailsProcess mazeDetailsProcess = new MazeDetailsProcess();
        int start = mazeDetailsProcess.inputMazeStartEndPoint(scanner, out, maze, maze.height(), renderer, true);
        return new Coordinate(start, 0);
    }

    private Coordinate getEndCoordinate(Maze maze,
                                        Scanner scanner,
                                        PrintStream out) {
        MazeDetailsProcess mazeDetailsProcess = new MazeDetailsProcess();
        int end = mazeDetailsProcess.inputMazeStartEndPoint(scanner, out, maze, maze.height(), renderer, false);
        return new Coordinate(end, maze.width() - 1);
    }

    private void addStartAndEnd(Maze maze,
                                Coordinate start,
                                Coordinate end) {
        maze.addPassageToGrid(start.row(), start.col());
        maze.addPassageToGrid(end.row(), end.col());
    }
}
