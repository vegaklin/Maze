package backend.academy.maze;

import backend.academy.maze.improvement.MazeAdditionalPath;
import backend.academy.maze.maze.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static backend.academy.maze.utils.MazeTestUtils.fillBigDefaultMaze;
import static backend.academy.maze.utils.MazeTestUtils.fillDefaultMaze;
import static backend.academy.maze.utils.MazeTestUtils.oneWallMaze;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeAdditionalPathTest {
    MazeAdditionalPath mazeAdditionalPath;

    @BeforeEach
    public void initEach(){
        mazeAdditionalPath = new MazeAdditionalPath();
    }

    @Test
    @DisplayName("Adding a path in a vertical wall in a small maze")
    void checkAddingPathInVerticalWallInSmallMaze() {
        int height = 5;
        int width = 5;
        Maze maze = new Maze(height, width);
        oneWallMaze(maze);
        maze.addWallToGrid(1, 2);
        maze.addWallToGrid(3, 2);
        Maze expectedMaze = maze.deepCopy();
        expectedMaze.addPassageToGrid(3, 2);
        /*
            ██████████
            ██  ██  ██
            ██  ██░░██
            ██      ██
            ██████████
         */

        Maze actualMaze = mazeAdditionalPath.addingPathsInMaze(maze);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                assertEquals(expectedMaze.getGridElement(row, col).type(), actualMaze.getGridElement(row, col).type());
            }
        }
    }

    @Test
    @DisplayName("Adding a path in a horizontal wall in a small maze")
    void checkAddingPathInHorizontalWallInSmallMaze() {
        int height = 5;
        int width = 5;
        Maze maze = new Maze(height, width);
        oneWallMaze(maze);
        maze.addWallToGrid(2, 1);
        maze.addWallToGrid(2, 3);
        Maze expectedMaze = maze.deepCopy();
        expectedMaze.addPassageToGrid(2, 3);
        /*
            ██████████
            ██      ██
            ██████░░██
            ██      ██
            ██████████
         */
        Maze actualMaze = mazeAdditionalPath.addingPathsInMaze(maze);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                assertEquals(expectedMaze.getGridElement(row, col).type(), actualMaze.getGridElement(row, col).type());
            }
        }
    }

    @Test
    @DisplayName("Adding a path in the longest (vertical) wall in a regular maze")
    void checkAddingPathInVerticalWallInDefaultMaze() {
        int height = 7;
        int width = 9;
        Maze maze = new Maze(height, width);
        fillDefaultMaze(maze);
        maze.addWallToGrid(3, 6);
        Maze expectedMaze = maze.deepCopy();
        expectedMaze.addPassageToGrid(3, 6);
        /*
            ██████████████████
            ██          ██  ██
            ██  ██████  ██  ██
            ██      ██  ░░  ██
            ██████████  ██  ██
            ██          ██  ██
            ██████████████████
         */

        Maze actualMaze = mazeAdditionalPath.addingPathsInMaze(maze);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                assertEquals(expectedMaze.getGridElement(row, col).type(), actualMaze.getGridElement(row, col).type());
            }
        }
    }

    @Test
    @DisplayName("Adding a path in the longest (horizontal) wall in a regular maze")
    void checkAddingPathInHorizontalWallInDefaultMaze() {
        int height = 7;
        int width = 9;
        Maze maze = new Maze(height, width);
        fillDefaultMaze(maze);
        maze.addWallToGrid(4, 5);
        Maze expectedMaze = maze.deepCopy();
        expectedMaze.addPassageToGrid(4, 5);
        /*
            ██████████████████
            ██          ██  ██
            ██  ██████  ██  ██
            ██      ██  ██  ██
            ██████████░░██  ██
            ██          ██  ██
            ██████████████████
         */

        Maze actualMaze = mazeAdditionalPath.addingPathsInMaze(maze);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                assertEquals(expectedMaze.getGridElement(row, col).type(), actualMaze.getGridElement(row, col).type());
            }
        }
    }

    @Test
    @DisplayName("Adding 2 paths at a large size on the largest walls (vertical and horizontal)")
    void checkAddingMorePaths() {
        int height = 10;
        int width = 10;
        Maze maze = new Maze(height, width);
        fillBigDefaultMaze(maze);
        Maze expectedMaze = maze.deepCopy();
        expectedMaze.addPassageToGrid(2, 5);
        expectedMaze.addPassageToGrid(5, 2);
        /*
            ████████████████████
            ██                ██
            ██  ██████░░████████
            ██  ██            ██
            ██  ██  ████████████
            ██  ░░            ██
            ██  ██  ██        ██
            ██  ██  ██  ██    ██
            ██      ██  ██    ██
            ████████████████████
         */

        Maze actualMaze = mazeAdditionalPath.addingPathsInMaze(maze);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                assertEquals(expectedMaze.getGridElement(row, col).type(), actualMaze.getGridElement(row, col).type());
            }
        }
    }
}
