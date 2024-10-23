package backend.academy.maze.cli;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.improvement.MazeAdditionalPath;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.Renderer;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import static backend.academy.maze.cli.MazeUIPrinter.printMazePath;
import static backend.academy.maze.cli.MazeUIPrinter.printTitle;
import static backend.academy.maze.improvement.MazeDifferentSurfacesMode.addDifferentSurfacesInMaze;

public class MazeInterface {
    Renderer renderer;

    public MazeInterface(Renderer renderer) {
        this.renderer = renderer;
    }

    public void start(Scanner scanner, PrintStream out) {
        MazeDetailsProcess mazeDetailsProcess = new MazeDetailsProcess();
        MazeAlgorithmProcess mazeAlgorithmProcess = new MazeAlgorithmProcess();

        printTitle(out);

        Generator generator = mazeAlgorithmProcess.generateAlgorithmChoosing(scanner, out);
        int height = mazeDetailsProcess.inputHeight(scanner, out);
        int width = mazeDetailsProcess.inputWidth(scanner, out);

        Maze maze = generator.generate(height, width);
        MazeAdditionalPath moder = new MazeAdditionalPath();
        Maze manyPatsMaze = moder.addingPathsInMaze(maze);
        Maze differentSurfaceMaze = addDifferentSurfacesInMaze(manyPatsMaze);

        int start = mazeDetailsProcess.inputMazeStartEndPoint(scanner, out,
            differentSurfaceMaze, height, renderer, true);
        int end = mazeDetailsProcess.inputMazeStartEndPoint(scanner, out,
            differentSurfaceMaze, height, renderer, false);
        Coordinate startCoordinate = new Coordinate(start, 0);
        Coordinate endCoordinate = new Coordinate(end, width - 1);
        differentSurfaceMaze.addPassageToGrid(startCoordinate.row(), startCoordinate.col());
        differentSurfaceMaze.addPassageToGrid(endCoordinate.row(), endCoordinate.col());

        Solver solver = mazeAlgorithmProcess.solveAlgorithmChoosing(scanner, out);
        List<Coordinate> mazePath = solver.solve(differentSurfaceMaze, startCoordinate, endCoordinate);
        printMazePath(out, differentSurfaceMaze, mazePath, renderer);
    }
}
