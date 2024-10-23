package backend.academy.maze.cli;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.factory.GeneratorType;
import backend.academy.maze.factory.MazeGeneratorFactory;
import backend.academy.maze.factory.MazeSolverFactory;
import backend.academy.maze.factory.SolverType;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import static backend.academy.maze.cli.MazeUIPrinter.printMessageGeneratorChoosing;
import static backend.academy.maze.cli.MazeUIPrinter.printMessageSolverChoosing;
import static backend.academy.maze.constant.MazeConstants.INVALID_INPUT_MESSAGE;
import static backend.academy.maze.constant.MazeConstants.SELECTION_ATTEMPTS;

public class MazeAlgorithmProcess {
    public Generator generateAlgorithmChoosing(Scanner scanner, PrintStream out) {
        printMessageGeneratorChoosing(out);
        return chooseAlgorithm(scanner, out,
            this::generatorTypeFromMenu,
            GeneratorType.KRUSKAL,
            MazeGeneratorFactory::createMazeGenerator);
    }

    public Solver solveAlgorithmChoosing(Scanner scanner, PrintStream out) {
                                        printMessageSolverChoosing(out);
        return chooseAlgorithm(scanner, out,
            this::solverTypeFromMenu,
            SolverType.BFS,
            MazeSolverFactory::createMazeSolver);
    }

    private <T, U> T chooseAlgorithm(Scanner scanner, PrintStream out,
                                    Function<Integer, U> typeConverter, U defaultType,
                                    Function<U, T> algorithmFactory) {
        return chooseOption(scanner, out,
            number -> number == 1 || number == 2,
            defaultType,
            typeConverter,
            algorithmFactory);
    }

    private <T, U> T chooseOption(Scanner scanner, PrintStream out, Predicate<Integer> validator,
        U defaultValue,
        Function<Integer, U> converter,
        Function<U, T> factory) {
        int attempts = SELECTION_ATTEMPTS;
        while (attempts > 0) {
            try {
                int value = scanner.nextInt();
                if (validator.test(value)) {
                    return factory.apply(converter.apply(value));
                } else {
                    out.println("You have entered an incorrect algorithm. Try again:");
                }
            } catch (Exception e) {
                out.println(INVALID_INPUT_MESSAGE);
                scanner.next();
            }
            attempts--;
        }
        return factory.apply(defaultValue);
    }

    private GeneratorType generatorTypeFromMenu(int number) {
        return number == 1 ? GeneratorType.KRUSKAL : GeneratorType.PRIM;
    }

    private SolverType solverTypeFromMenu(int number) {
        return number == 1 ? SolverType.A_STAR : SolverType.BFS;
    }
}

