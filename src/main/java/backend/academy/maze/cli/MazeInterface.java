package backend.academy.maze.cli;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.factory.GeneratorType;
import backend.academy.maze.factory.SolverType;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static backend.academy.maze.factory.MazeGeneratorFactory.createMazeGenerator;
import static backend.academy.maze.factory.MazeSolverFactory.createMazeSolver;

public class MazeInterface {
    Renderer renderer;

    public MazeInterface(Renderer renderer) {
        this.renderer = renderer;
    }

    public void start(Scanner scanner, PrintStream out) {
        printTitle(out);

        Generator generator = generateAlgorithmChoosing(scanner, out);
        int height = inputHeight(scanner, out);
        int width = inputWidth(scanner, out);

        Maze maze = generator.generate(height, width);
        int start = inputMazeStartPoint(scanner, out, maze, height);
        int end = inputMazeEndPoint(scanner, out, maze, height);
        Coordinate startCoordinate = new Coordinate(start, 0);
        Coordinate endCoordinate = new Coordinate(end, width - 1);
        maze.addPassageToGrid(startCoordinate.row(), startCoordinate.col());
        maze.addPassageToGrid(endCoordinate.row(), endCoordinate.col());

        Solver solver = solveAlgorithmChoosing(scanner, out);
        List<Coordinate> mazeWithPath = solver.solve(maze, startCoordinate, endCoordinate);

        out.print(renderer.render(maze, mazeWithPath));
    }

    public int inputMazeStartPoint(Scanner scanner, PrintStream out, Maze maze, int height) {
        String render = renderer.renderWithNumberOfRowsFront(maze);
        out.print(render);
        out.println("Enter the line number from the maze starting from the left:");
        return mazeStartEndChooser(scanner, out, height);
    }

    public int inputMazeEndPoint(Scanner scanner, PrintStream out, Maze maze, int height) {
        String render = renderer.renderWithNumberOfRowsBack(maze);
        out.print(render);
        out.println("Enter the line number from the maze with the end on the right:");
        return mazeStartEndChooser(scanner, out, height);
    }

    public int mazeStartEndChooser(Scanner scanner, PrintStream out, int height) {
        int selectionAttempts = 10;
        while(selectionAttempts > 0) {
            try {
                int point = scanner.nextInt();
                if (isCorrectStartEndPoint(point, height)) {
                    return point;
                } else {
                    out.println("The number must be one of those on top. Try again:");
                }
            } catch (InputMismatchException e) {
                out.println("You entered an incorrect number. Try again:");
                scanner.next();
            }
            selectionAttempts--;
        }
        return 1;
    }

    public boolean isCorrectStartEndPoint(int point, int height) {
        return point >= 1 && point <= height - 2;
    }

    public void printTitle(PrintStream out) {
        out.println("=== MAZE ===");
    }

    public Generator generateAlgorithmChoosing(Scanner scanner, PrintStream out) {
        printMessageGeneratorChoosing(out);
        return generatorChooser(scanner, out);
    }

    public Solver solveAlgorithmChoosing(Scanner scanner, PrintStream out) {
        printMessageSolverChoosing(out);
        return solverChooser(scanner, out);
    }

    public void printMessageGeneratorChoosing(PrintStream out) {
        out.print("""
            Choose an algorithm for generating an algorithm:
            1. Kruskal's algorithm;
            2. Prime's algorithm.
            """);
    }

    public void printMessageSolverChoosing(PrintStream out) {
        out.print("""
            Choose an algorithm for solving an algorithm:
            1. A* (A-star) algorithm;
            2. BFS algorithm.
            """);
    }


    public Generator generatorChooser(Scanner scanner, PrintStream out) {
        int selectionAttempts = 10;
        while(selectionAttempts > 0) {
            String input = scanner.nextLine().trim();
            if (isValidMenuNumber(input)) {
                return createMazeGenerator(generatorTypeFromMenu(input));
            }
            out.println("You have entered an incorrect generation algorithm. Try again:");
            selectionAttempts--;
        }
        return createMazeGenerator(GeneratorType.KRUSKAL);
    }

    public boolean isValidMenuNumber(String input) {
        return Objects.equals(input, "1") || Objects.equals(input, "2");
    }

    public GeneratorType generatorTypeFromMenu(String number) {
        return switch (number) {
            case "1" -> GeneratorType.KRUSKAL;
            case "2" -> GeneratorType.PRIM;
            default -> throw new IllegalArgumentException("Incorrect menu number for generator type.");
        };
    }


    public Solver solverChooser(Scanner scanner, PrintStream out) {
        int selectionAttempts = 10;
        while(selectionAttempts > 0) {
            String input = scanner.nextLine().trim();
            if (isValidMenuNumber(input)) {
                return createMazeSolver(solverTypeFromMenu(input));
            }
            out.println("You have entered an incorrect path finding algorithm. Try again:");
            selectionAttempts--;
        }
        return createMazeSolver(SolverType.BFS);
    }

    public SolverType solverTypeFromMenu(String number) {
        return switch (number) {
            case "1" -> SolverType.A_STAR;
            case "2" -> SolverType.BFS;
            default -> throw new IllegalArgumentException("Incorrect menu number for solver type.");
        };
    }

    public int inputHeight(Scanner scanner, PrintStream out) {
        printMessageHeightChoosing(out);
        int height = mazeSizeChooser(scanner, out);
        return validateMazeSize(height);
    }

    public void printMessageHeightChoosing(PrintStream out) {
        out.println("Enter the height of the maze (greater than 1):");
    }

    public int inputWidth(Scanner scanner, PrintStream out) {
        printMessageWidthChoosing(out);
        int width = mazeSizeChooser(scanner, out);
        return validateMazeSize(width);
    }

    public void printMessageWidthChoosing(PrintStream out) {
        out.println("Enter the width of the maze (greater than 1):");
    }

    public boolean isCorrectMazeSize(int number) {
        return number > 1;
    }

    public int mazeSizeChooser(Scanner scanner, PrintStream out) {
        int selectionAttempts = 10;
        while(selectionAttempts > 0) {
            try {
                int size = scanner.nextInt();
                if (isCorrectMazeSize(size)) {
                    return size;
                } else {
                    out.println("The number must be greater than 1. Try again:");
                }
            } catch (InputMismatchException e) {
                out.println("You entered an incorrect number. Try again:");
                scanner.next();
            }
            selectionAttempts--;
        }
        return 25;
    }

    private int validateMazeSize(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Incorrect maze size.");
        }
        return (size % 2 == 0) ? size + 1 : size;
    }
}
