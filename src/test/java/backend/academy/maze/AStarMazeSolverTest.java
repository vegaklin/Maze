package backend.academy.maze;

import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.algorithm.solving.astar.AStarMazeSolver;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static backend.academy.maze.utils.MazeTestUtils.defaultMazePath;
import static backend.academy.maze.utils.MazeTestUtils.downPathOneWallMaze;
import static backend.academy.maze.utils.MazeTestUtils.fillDefaultMaze;
import static backend.academy.maze.utils.MazeTestUtils.oneWallMaze;
import static backend.academy.maze.utils.MazeTestUtils.upPathOneWallMaze;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AStarMazeSolverTest {
    private final Solver solver = new AStarMazeSolver();
    List<Coordinate> expectedPath;

    @BeforeEach
    public void setUp(){
        expectedPath = new ArrayList<>();
    }

    @Test
    @DisplayName("Finding the way in the perfect maze")
    void checkFindingPathInPerfectMaze() {
        // given

        int height = 7;
        int width = 9;
        Maze maze = new Maze(height, width);
        fillDefaultMaze(maze);

        Coordinate start = new Coordinate(3, 0);
        Coordinate end =  new Coordinate(5, width - 1);

        expectedPath = defaultMazePath();

        // when

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("There is no way in Maze")
    void checkNotSolveMaze() {
        // given

        int height = 7;
        int width = 9;
        Maze maze = new Maze(height, width);
        fillDefaultMaze(maze);
        maze.addWallToGrid(3, 6);

        Coordinate start = new Coordinate(3, 0);
        Coordinate end =  new Coordinate(5, width - 1);

        // when

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        // then

        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("There are 2 paths and a choice of a short")
    void checkFindingPathInNotPerfectMaze() {
        // given

        int height = 7;
        int width = 9;
        Maze maze = new Maze(height, width);
        fillDefaultMaze(maze);
        maze.addPassageToGrid(1,6);

        Coordinate start = new Coordinate(3, 0);
        Coordinate end =  new Coordinate(5, width - 1);

        expectedPath = defaultMazePath();

        // when

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        // then

        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("Finding a short cut with the SWAMP type")
    void checkFindingBestPathWithSwampType() {
        // given

        int height = 5;
        int width = 5;
        Maze maze = new Maze(height, width);
        oneWallMaze(maze);
        maze.addModeToGrid(1, 2, Type.SWAMP);

        Coordinate start = new Coordinate(2, 0);
        Coordinate end =  new Coordinate(2, width - 1);

        expectedPath = downPathOneWallMaze();

        // when

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        // then

        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("Finding a short cut with the SAND type")
    void checkFindingBestPathWithSandType() {
        // given

        int height = 5;
        int width = 5;
        Maze maze = new Maze(height, width);
        oneWallMaze(maze);
        maze.addModeToGrid(1, 2, Type.SAND);

        Coordinate start = new Coordinate(2, 0);
        Coordinate end =  new Coordinate(2, width - 1);

        expectedPath = downPathOneWallMaze();

        // when

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        // then

        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("Finding a short cut with the COIN type")
    void checkFindingBestPathWithCoinType() {
        // given

        int height = 5;
        int width = 5;
        Maze maze = new Maze(height, width);
        oneWallMaze(maze);
        maze.addModeToGrid(1, 2, Type.COIN);

        Coordinate start = new Coordinate(2, 0);
        Coordinate end =  new Coordinate(2, width - 1);

        expectedPath = upPathOneWallMaze();

        // when

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        // then

        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("Finding a short cut with the EAT type")
    void checkFindingBestPathWithEatType() {
        // given

        int height = 5;
        int width = 5;
        Maze maze = new Maze(height, width);
        oneWallMaze(maze);
        maze.addModeToGrid(1, 2, Type.EAT);

        Coordinate start = new Coordinate(2, 0);
        Coordinate end =  new Coordinate(2, width - 1);

        expectedPath = upPathOneWallMaze();

        // when

        List<Coordinate> actualPath = solver.solve(maze, start, end);

        // then

        assertEquals(expectedPath, actualPath);
    }
}
