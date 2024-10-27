package backend.academy.maze;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.generation.kruskal.KruskalsGenerator;
import backend.academy.maze.algorithm.generation.prim.PrimsGenerator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.algorithm.solving.astar.AStarMazeSolver;
import backend.academy.maze.algorithm.solving.bfs.BFSMazeSolver;
import backend.academy.maze.cli.MazeAlgorithmProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static backend.academy.maze.constant.MazeConstants.INVALID_INPUT_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeAlgorithmProcessTest {
    private final MazeAlgorithmProcess mazeAlgorithmProcess = new MazeAlgorithmProcess();
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    @DisplayName("Return of the default generator")
    void checkDefaultGenerator() {
        // given

        String inputText = "0\ne\ne\n-1\n3\n12\ng\n-1\n0\ny\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        Generator generator = mazeAlgorithmProcess.generateAlgorithmChoosing(input, out);

        // then

        assertInstanceOf(KruskalsGenerator.class, generator);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the Prims Generator")
    void checkPrimsGenerator() {
        // given

        String inputText = "2\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        Generator generator = mazeAlgorithmProcess.generateAlgorithmChoosing(input, out);

        // then

        assertInstanceOf(PrimsGenerator.class, generator);
    }

    @Test
    @DisplayName("Return of the Kruskals Generator")
    void checkKruskalsGenerator() {
        // given

        String inputText = "1\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        Generator generator = mazeAlgorithmProcess.generateAlgorithmChoosing(input, out);

        // then

        assertInstanceOf(KruskalsGenerator.class, generator);
    }

    @Test
    @DisplayName("Return of the default solver")
    void checkDefaultSolver() {
        // given

        String inputText = "-1\ne\ne\n-1\n3\n12\ng\n-1\n0\ny\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        Solver solver = mazeAlgorithmProcess.solveAlgorithmChoosing(input, out);

        // then

        assertInstanceOf(BFSMazeSolver.class, solver);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the BFS Maze Solver")
    void checkBFSMazeSolver() {
        // given

        String inputText = "2\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        Solver solver = mazeAlgorithmProcess.solveAlgorithmChoosing(input, out);

        // then

        assertInstanceOf(BFSMazeSolver.class, solver);
    }

    @Test
    @DisplayName("Return of the A Star MazeSolver")
    void checkAStarMazeSolver() {
        // given

        String inputText = "1\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        // when

        Solver solver = mazeAlgorithmProcess.solveAlgorithmChoosing(input, out);

        // then

        assertInstanceOf(AStarMazeSolver.class, solver);
    }
}
