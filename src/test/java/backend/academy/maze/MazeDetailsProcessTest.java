package backend.academy.maze;

import backend.academy.maze.cli.MazeDetailsProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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
        // given

        String inputText = "0\n1\n1\n1\n1\n0\ng\n-1\n0\ny\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        int height = mazeDetailsProcess.inputHeight(input, out);

        assertEquals(height, DEFAULT_MAZE_SIZE);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the input height")
    void checkInputHeight() {
        // given

        String inputText = "15\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        int height = mazeDetailsProcess.inputHeight(input, out);

        assertEquals(height, 15);
    }

    @Test
    @DisplayName("Return of the default width")
    void checkDefaultWidth() {
        // given

        String inputText = "0\n1\n1\n1\n1\n0\ng\n-1\n0\ny\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        int height = mazeDetailsProcess.inputWidth(input, out);

        assertEquals(height, DEFAULT_MAZE_SIZE);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the input width")
    void checkInputWidth() {
        // given

        String inputText = "15\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        int height = mazeDetailsProcess.inputWidth(input, out);

        assertEquals(height, 15);
    }

    @Test
    @DisplayName("Return of the default start")
    void checkDefaultStart() {
        // given

        String inputText = "-1\n14\n15\n-7\nii\n0\n0\n-1\n10\ny\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        int pointStart = mazeDetailsProcess.inputMazeStartEndPoint(input, out, 10, true);

        assertEquals(pointStart, 1);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the input end")
    void checkInputEnd() {
        // given

        String inputText = "5\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        int pointEnd = mazeDetailsProcess.inputMazeStartEndPoint(input, out, 10, false);

        // then

        assertEquals(pointEnd, 5);
    }

}
