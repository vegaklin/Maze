package backend.academy.maze;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.generation.prim.PrimsGenerator;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.algorithm.solving.bfs.BFSMazeSolver;
import backend.academy.maze.cli.MazeAlgorithmProcess;
import backend.academy.maze.cli.MazeDetailsProcess;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import static backend.academy.maze.utils.MazeTestUtils.defaultMazePath;
import static backend.academy.maze.utils.MazeTestUtils.fillDefaultMaze;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MazeInterfaceTest {
    @Mock
    private MazeProcessing mazeProcessing;

    @Mock
    private MazeDetailsProcess mazeDetailsProcess;

    @Mock
    private MazeAlgorithmProcess mazeAlgorithmProcess;

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
        // given

        String inputText = "1\n7\n9\n3\n5\n1\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        Maze maze = new Maze(7, 9);
        fillDefaultMaze(maze);
        maze.addWallToGrid(3, 0);
        maze.addWallToGrid(5, 8);

        List<Coordinate> expectedPath = defaultMazePath();

        Generator primsGenerator = new PrimsGenerator();
        Solver solver = new BFSMazeSolver();

        when(mazeProcessing.generateMaze(any(Generator.class), any(InputStream.class), any(PrintStream.class))).thenReturn(maze);
        when(mazeProcessing.improveMaze(any(Maze.class))).thenReturn(maze);
        when(mazeProcessing.solveMaze(any(Solver.class), any(Maze.class), any(Coordinate.class), any(Coordinate.class))).thenReturn(expectedPath);

        when(mazeDetailsProcess.inputMazeStartEndPoint(any(InputStream.class), any(PrintStream.class), anyInt(), eq(true))).thenReturn(3);
        when(mazeDetailsProcess.inputMazeStartEndPoint(any(InputStream.class), any(PrintStream.class), anyInt(), eq(false))).thenReturn(5);

        when(mazeAlgorithmProcess.generateAlgorithmChoosing(any(InputStream.class), any(PrintStream.class))).thenReturn(primsGenerator);
        when(mazeAlgorithmProcess.solveAlgorithmChoosing(any(InputStream.class), any(PrintStream.class))).thenReturn(solver);

        // when

        mazeInterface.start(input, out);

        // then

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
        // given

        String inputText = "1\n7\n10\n3\n5\n1\n";

        InputStream input = new ByteArrayInputStream(inputText.getBytes(StandardCharsets.UTF_8));
        PrintStream out = new PrintStream(outputStream);

        Maze maze = new Maze(7, 9);
        fillDefaultMaze(maze);
        maze.addWallToGrid(3, 0);
        maze.addWallToGrid(5, 8);
        maze.addWallToGrid(3, 6);

        List<Coordinate> expectedPath = new ArrayList<>();

        Generator primsGenerator = new PrimsGenerator();
        Solver solver = new BFSMazeSolver();

        when(mazeProcessing.generateMaze(any(Generator.class), any(InputStream.class), any(PrintStream.class))).thenReturn(maze);
        when(mazeProcessing.improveMaze(any(Maze.class))).thenReturn(maze);
        when(mazeProcessing.solveMaze(any(Solver.class), any(Maze.class), any(Coordinate.class), any(Coordinate.class))).thenReturn(expectedPath);

        when(mazeDetailsProcess.inputMazeStartEndPoint(any(InputStream.class), any(PrintStream.class), anyInt(), eq(true))).thenReturn(3);
        when(mazeDetailsProcess.inputMazeStartEndPoint(any(InputStream.class), any(PrintStream.class), anyInt(), eq(false))).thenReturn(5);

        when(mazeAlgorithmProcess.generateAlgorithmChoosing(any(InputStream.class), any(PrintStream.class))).thenReturn(primsGenerator);
        when(mazeAlgorithmProcess.solveAlgorithmChoosing(any(InputStream.class), any(PrintStream.class))).thenReturn(solver);

        // when

        mazeInterface.start(input, out);

        // then

        verify(renderer).renderWithNumberOfRowsBack(maze);
        verify(renderer).renderWithNumberOfRowsFront(maze);

        assertTrue(outputStream.toString().contains("No path found between the selected points."));
    }
}
