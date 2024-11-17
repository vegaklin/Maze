package backend.academy.maze.cli;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.Renderer;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import static backend.academy.maze.cli.MazeUIPrinter.printMazePath;
import static backend.academy.maze.cli.MazeUIPrinter.printMazeWithNumbers;
import static backend.academy.maze.cli.MazeUIPrinter.printTitle;

public class MazeInterface {
    private final Renderer renderer;
    private final MazeProcessing mazeProcessing;
    private final MazeDetailsProcess mazeDetailsProcess;
    private final MazeAlgorithmProcess mazeAlgorithmProcess;

    public MazeInterface(Renderer renderer,
                        MazeProcessing mazeProcessing,
                        MazeDetailsProcess mazeDetailsProcess,
                        MazeAlgorithmProcess mazeAlgorithmProcess) {
        this.renderer = renderer;
        this.mazeProcessing = mazeProcessing;
        this.mazeDetailsProcess = mazeDetailsProcess;
        this.mazeAlgorithmProcess = mazeAlgorithmProcess;
    }

    public void start(InputStream input, PrintStream out) {
        printTitle(out);

        Generator generator = chooseGeneratorAlgorithm(input, out);
        Maze maze = mazeProcessing.generateMaze(generator, input, out);
        Maze improvedMaze = mazeProcessing.improveMaze(maze);

        Coordinate start = getStartCoordinate(improvedMaze, input, out);
        Coordinate end = getEndCoordinate(improvedMaze, input, out);
        addStartAndEnd(improvedMaze, start, end);

        Solver solver = chooseSolverAlgorithm(input, out);
        List<Coordinate> mazePath = mazeProcessing.solveMaze(solver, improvedMaze, start, end);
        printMazePath(out, improvedMaze, mazePath, renderer);
    }

    private Generator chooseGeneratorAlgorithm(InputStream input, PrintStream out) {
        return mazeAlgorithmProcess.generateAlgorithmChoosing(input, out);
    }

    private Solver chooseSolverAlgorithm(InputStream input, PrintStream out) {
        return mazeAlgorithmProcess.solveAlgorithmChoosing(input, out);
    }

    private Coordinate getStartCoordinate(Maze maze,
                                        InputStream input,
                                        PrintStream out) {
        printMazeWithNumbers(out, renderer.renderWithNumberOfRowsFront(maze));
        int start = mazeDetailsProcess.inputMazeStartEndPoint(input, out, maze.height(), true);
        return new Coordinate(start, 0);
    }

    private Coordinate getEndCoordinate(Maze maze,
                                        InputStream input,
                                        PrintStream out) {
        printMazeWithNumbers(out, renderer.renderWithNumberOfRowsBack(maze));
        int end = mazeDetailsProcess.inputMazeStartEndPoint(input, out, maze.height(), false);
        return new Coordinate(end, maze.width() - 1);
    }

    private void addStartAndEnd(Maze maze,
                                Coordinate start,
                                Coordinate end) {
        maze.addPassageToGrid(start.row(), start.col());
        maze.addPassageToGrid(end.row(), end.col());
    }
}
