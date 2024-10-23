package backend.academy.maze.cli;

import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.Renderer;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;
import static backend.academy.maze.cli.MazeUIPrinter.printMessageHeightChoosing;
import static backend.academy.maze.cli.MazeUIPrinter.printMessageWidthChoosing;
import static backend.academy.maze.constant.MazeConstants.DEFAULT_MAZE_SIZE;
import static backend.academy.maze.constant.MazeConstants.DEFAULT_MENU_NUMBER;
import static backend.academy.maze.constant.MazeConstants.INVALID_INPUT_MESSAGE;
import static backend.academy.maze.constant.MazeConstants.SELECTION_ATTEMPTS;


public class MazeDetailsProcess {
    public int inputHeight(Scanner scanner, PrintStream out) {
        printMessageHeightChoosing(out);
        return inputMazeSize(scanner, out);
    }

    public int inputWidth(Scanner scanner, PrintStream out) {
        printMessageWidthChoosing(out);
        return inputMazeSize(scanner, out);
    }

    private int inputMazeSize(Scanner scanner, PrintStream out) {
        return chooseOption(scanner, out,
            size -> size > 1,
            "The number must be greater than 1. Try again:",
            DEFAULT_MAZE_SIZE);
    }

    public int inputMazeStartEndPoint(Scanner scanner, PrintStream out,
                                    Maze maze, int height, Renderer renderer,
                                    boolean isStart) {
        String render = isStart ? renderer.renderWithNumberOfRowsFront(maze)
            : renderer.renderWithNumberOfRowsBack(maze);
        out.print(render);
        out.println(isStart ? "Enter the start line number from the left:"
            : "Enter the finish line number from the right:");
        return chooseOption(scanner, out,
            point -> point >= 1 && point <= (height - 2),
            "The number must be one of those on top. Try again:",
            DEFAULT_MENU_NUMBER);
    }

    private int chooseOption(Scanner scanner, PrintStream out,
                            Predicate<Integer> validator,
                            String errorMessage, int defaultValue) {
        int attempts = SELECTION_ATTEMPTS;
        while (attempts > 0) {
            try {
                int value = scanner.nextInt();
                if (validator.test(value)) {
                    return value;
                } else {
                    out.println(errorMessage);
                }
            } catch (Exception e) {
                out.println(INVALID_INPUT_MESSAGE);
                scanner.next();
            }
            attempts--;
        }
        return defaultValue;
    }
}
