package backend.academy.maze.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Predicate;
import static backend.academy.maze.cli.MazeUIPrinter.printMessageHeightChoosing;
import static backend.academy.maze.cli.MazeUIPrinter.printMessageWidthChoosing;
import static backend.academy.maze.constant.MazeConstants.DEFAULT_MAZE_SIZE;
import static backend.academy.maze.constant.MazeConstants.DEFAULT_MENU_NUMBER;
import static backend.academy.maze.constant.MazeConstants.INVALID_INPUT_MESSAGE;
import static backend.academy.maze.constant.MazeConstants.SELECTION_ATTEMPTS;
import static backend.academy.maze.validation.MazeValidator.validateMazeSize;

public class MazeDetailsProcess {

    public int inputHeight(InputStream input, PrintStream out) {
        printMessageHeightChoosing(out);
        return validateMazeSize(inputMazeSize(input, out));
    }

    public int inputWidth(InputStream input, PrintStream out) {
        printMessageWidthChoosing(out);
        return validateMazeSize(inputMazeSize(input, out));
    }

    public int inputMazeStartEndPoint(InputStream input,
                                    PrintStream out,
                                    int height,
                                    boolean isStart) {
        out.println(isStart ? "Enter the start line number from the left:"
            : "Enter the finish line number from the right:");
        return chooseSizePointOption(input, out,
            point -> point >= 1 && point <= (height - 2),
            "The number must be one of those on top. Try again:",
            DEFAULT_MENU_NUMBER);
    }

    private int inputMazeSize(InputStream input, PrintStream out) {
        return chooseSizePointOption(input, out,
            size -> size > 1,
            "The number must be greater than 1. Try again:",
            DEFAULT_MAZE_SIZE);
    }

    private int chooseSizePointOption(InputStream input,
                            PrintStream out,
                            Predicate<Integer> validator,
                            String errorMessage,
                            int defaultValue) {
        Scanner scanner = new Scanner(input, StandardCharsets.UTF_8);
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
