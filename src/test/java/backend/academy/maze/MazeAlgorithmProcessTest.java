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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
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
        String input = "0\ne\ne\n-1\n3\n12\ng\n-1\n0\ny\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        Generator generator = mazeAlgorithmProcess.generateAlgorithmChoosing(scanner, out);

        assertInstanceOf(KruskalsGenerator.class, generator);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the Prims Generator")
    void checkPrimsGenerator() {
        String input = "2\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        Generator generator = mazeAlgorithmProcess.generateAlgorithmChoosing(scanner, out);

        assertInstanceOf(PrimsGenerator.class, generator);
    }

    @Test
    @DisplayName("Return of the Kruskals Generator")
    void checkKruskalsGenerator() {
        String input = "1\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        Generator generator = mazeAlgorithmProcess.generateAlgorithmChoosing(scanner, out);

        assertInstanceOf(KruskalsGenerator.class, generator);
    }

    @Test
    @DisplayName("Return of the default solver")
    void checkDefaultSolver() {
        String input = "-1\ne\ne\n-1\n3\n12\ng\n-1\n0\ny\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        Solver solver = mazeAlgorithmProcess.solveAlgorithmChoosing(scanner, out);

        assertInstanceOf(BFSMazeSolver.class, solver);
        assertTrue(outputStream.toString().contains(INVALID_INPUT_MESSAGE));
    }

    @Test
    @DisplayName("Return of the BFS Maze Solver")
    void checkBFSMazeSolver() {
        String input = "2\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        Solver solver = mazeAlgorithmProcess.solveAlgorithmChoosing(scanner, out);

        assertInstanceOf(BFSMazeSolver.class, solver);
    }

    @Test
    @DisplayName("Return of the A Star MazeSolver")
    void checkAStarMazeSolver() {
        String input = "1\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);

        Solver solver = mazeAlgorithmProcess.solveAlgorithmChoosing(scanner, out);

        assertInstanceOf(AStarMazeSolver.class, solver);
    }
}
