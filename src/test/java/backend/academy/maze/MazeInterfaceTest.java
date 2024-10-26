package backend.academy.maze;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.cli.MazeInterface;
import backend.academy.maze.cli.MazeProcessing;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static backend.academy.maze.utils.MazeTestUtils.defaultMazePath;
import static backend.academy.maze.utils.MazeTestUtils.fillDefaultMaze;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MazeInterfaceTest {
    @Mock
    private MazeProcessing mazeProcessing;

    @Spy
    private Renderer renderer = new ConsoleRenderer();

    @InjectMocks
    private MazeInterface mazeInterface;

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    @DisplayName("Correct output of a matrix with a path")
    void checkCorrectMazeOutput() {
        String input = "1\n7\n9\n3\n5\n1\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);
        Maze maze = new Maze(7, 9);
        fillDefaultMaze(maze);
        maze.addWallToGrid(3, 0);
        maze.addWallToGrid(5, 8);
        List<Coordinate> expectedPath = defaultMazePath();
        when(mazeProcessing.generateMaze(any(Generator.class), any(Scanner.class), any(PrintStream.class))).thenReturn(maze);
        when(mazeProcessing.improveMaze(any(Maze.class))).thenReturn(maze);
        when(mazeProcessing.solveMaze(any(Solver.class), any(Maze.class), any(Coordinate.class), any(Coordinate.class))).thenReturn(expectedPath);

        mazeInterface.start(scanner, out);

        verify(renderer).renderWithNumberOfRowsBack(maze);
        verify(renderer).renderWithNumberOfRowsFront(maze);
        verify(renderer).render(maze, expectedPath);
        assertTrue(outputStream.toString().contains("""
            ██████████████████
            ██          ██  ██1
            ██  ██████  ██  ██2
            ██      ██      ██3
            ██████████  ██  ██4
            ██          ██  ██5
            ██████████████████
            """));
        assertTrue(outputStream.toString().contains("""
             ██████████████████
            1██          ██  ██
            2██  ██████  ██  ██
            3██      ██      ██
            4██████████  ██  ██
            5██          ██  ██
             ██████████████████
            """));
        assertTrue(outputStream.toString().contains("""
            ██████████████████
            ██**********██  ██
            ██**██████**██  ██
            ****    ██******██
            ██████████  ██**██
            ██          ██****
            ██████████████████
            """));
    }

    @Test
    @DisplayName("Message output if there is no path")
    void checkNotPathOutput() {
        String input = "1\n7\n10\n3\n5\n1\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);
        Maze maze = new Maze(7, 9);
        fillDefaultMaze(maze);
        maze.addWallToGrid(3, 0);
        maze.addWallToGrid(5, 8);
        maze.addWallToGrid(3, 6);
        List<Coordinate> expectedPath = new ArrayList<>();
        when(mazeProcessing.generateMaze(any(Generator.class), any(Scanner.class), any(PrintStream.class))).thenReturn(maze);
        when(mazeProcessing.improveMaze(any(Maze.class))).thenReturn(maze);
        when(mazeProcessing.solveMaze(any(Solver.class), any(Maze.class), any(Coordinate.class), any(Coordinate.class))).thenReturn(expectedPath);

        mazeInterface.start(scanner, out);

        verify(renderer).renderWithNumberOfRowsBack(maze);
        verify(renderer).renderWithNumberOfRowsFront(maze);
        assertTrue(outputStream.toString().contains("No path found between the selected points."));
    }

    @Test
    @DisplayName("Correct output of a interface output without mock")
    void checkMazeInterfaceWithoutMock() {
        String input = "1\n7\n10\n1\n5\n1\n";
        Scanner scanner = new Scanner(input);
        PrintStream out = new PrintStream(outputStream);
        MazeProcessing realMazeProcessing = new MazeProcessing();
        mazeInterface = new MazeInterface(renderer, realMazeProcessing);

        mazeInterface.start(scanner, out);

        assertTrue(outputStream.toString().contains("""
            Choose an algorithm for generating an algorithm:
            1. Kruskal's algorithm;
            2. Prim's algorithm.
            """));
        assertTrue(outputStream.toString().contains("Enter the height of the maze (greater than 1):"));
        assertTrue(outputStream.toString().contains("Enter the width of the maze (greater than 1):"));
        assertTrue(outputStream.toString().contains("Enter the start line number from the left:"));
        assertTrue(outputStream.toString().contains("Enter the finish line number from the right:"));
        assertTrue(outputStream.toString().contains("""
            Choose an algorithm for solving an algorithm:
            1. A* (A-star) algorithm;
            2. BFS algorithm.
            """));
        assertTrue(outputStream.toString().contains("██████████████████████"), "Wall length is 10");
    }
}
