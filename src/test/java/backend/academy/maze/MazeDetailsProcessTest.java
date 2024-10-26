package backend.academy.maze;

import backend.academy.maze.cli.MazeDetailsProcess;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static backend.academy.maze.constant.MazeConstants.DEFAULT_MAZE_SIZE;
import static backend.academy.maze.constant.MazeConstants.INVALID_INPUT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeDetailsProcessTest {
    private final MazeDetailsProcess mazeDetailsProcess = new MazeDetailsProcess();
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    @DisplayName("Return of the default height")
    void checkDefaultHeight() {
        String input = "0\n1\n1\n1\n1\n0\ng\n-1\n0\ny\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        int height = mazeDetailsProcess.inputHeight(scanner, out);

        assertEquals(height, DEFAULT_MAZE_SIZE);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the input height")
    void checkInputHeight() {
        String input = "15\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        int height = mazeDetailsProcess.inputHeight(scanner, out);

        assertEquals(height, 15);
    }

    @Test
    @DisplayName("Return of the default width")
    void checkDefaultWidth() {
        String input = "0\n1\n1\n1\n1\n0\ng\n-1\n0\ny\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        int height = mazeDetailsProcess.inputWidth(scanner, out);

        assertEquals(height, DEFAULT_MAZE_SIZE);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the input width")
    void checkInputWidth() {
        String input = "15\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        int height = mazeDetailsProcess.inputWidth(scanner, out);

        assertEquals(height, 15);
    }

    @Test
    @DisplayName("Return of the default start")
    void checkDefaultStart() {
        String input = "-1\n14\n15\n-7\nii\n0\n0\n-1\n10\ny\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);
        Maze maze = new Maze(10, 10);
        Renderer renderer = new ConsoleRenderer();

        int pointStart = mazeDetailsProcess.inputMazeStartEndPoint(scanner, out, maze, 10, renderer, true);

        assertEquals(pointStart, 1);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the input end")
    void checkInputEnd() {
        String input = "5\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);
        Maze maze = new Maze(10, 10);
        Renderer renderer = new ConsoleRenderer();

        int pointEnd = mazeDetailsProcess.inputMazeStartEndPoint(scanner, out, maze, 10, renderer, false);

        assertEquals(pointEnd, 5);
    }

}
