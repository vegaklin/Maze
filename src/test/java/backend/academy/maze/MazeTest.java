package backend.academy.maze;

import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static backend.academy.maze.constant.MazeConstants.COIN_COST;
import static backend.academy.maze.constant.MazeConstants.EAT_COST;
import static backend.academy.maze.constant.MazeConstants.SAND_COST;
import static backend.academy.maze.constant.MazeConstants.SWAMP_COST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {
    @Test
    @DisplayName("New Maze with walls")
    void checkNewMazeAllWalls() {
        int height = 5;
        int width = 5;
        Maze maze = new Maze(height, width);

        boolean allWalls = true;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!maze.isWallInGrid(row, col)) {
                    allWalls = false;
                    break;
                }
            }
        }

        assertTrue(allWalls, "All cells should be initialized as walls");
    }

    @Test
    @DisplayName("Correctly add passage to maze")
    void checkAddPassageToGrid() {
        Maze maze = new Maze(5, 5);

        maze.addPassageToGrid(2, 2);

        assertTrue(maze.isPassageInGrid(2, 2), "Cell should be a passage");
    }

    @Test
    @DisplayName("Correctly add wall to maze")
    void checkAddWallToGrid() {
        Maze maze = new Maze(5, 5);

        maze.addWallToGrid(1, 1);

        assertTrue(maze.isWallInGrid(1, 1), "Cell should be a wall");
    }

    @Test
    @DisplayName("Correctly add custom type cell to maze")
    void checkAddModeToGrid() {
        Maze maze = new Maze(5, 5);

        maze.addModeToGrid(1, 1, Type.SWAMP);
        maze.addModeToGrid(2, 2, Type.SAND);
        maze.addModeToGrid(3, 3, Type.COIN);
        maze.addModeToGrid(0, 0, Type.EAT);

        assertEquals(Type.SWAMP, maze.getGridElement(1, 1).type(), "Cell should be a swamp");
        assertEquals(maze.getGridElement(1, 1).cost(), SWAMP_COST, "Swamp should have a cost of SWAMP_COST");
        assertEquals(Type.SAND, maze.getGridElement(2, 2).type(), "Cell should be a sand");
        assertEquals(maze.getGridElement(2, 2).cost(), SAND_COST, "Swamp should have a cost of SAND_COST");
        assertEquals(Type.COIN, maze.getGridElement(3, 3).type(), "Cell should be a coin");
        assertEquals(maze.getGridElement(3, 3).cost(), COIN_COST, "Swamp should have a cost of COIN_COST");
        assertEquals(Type.EAT, maze.getGridElement(0, 0).type(), "Cell should be a eat");
        assertEquals(maze.getGridElement(0, 0).cost(), EAT_COST, "Swamp should have a cost of EAT_COST");
    }

    @Test
    @DisplayName("Throw exception for invalid row and column access")
    void checkThrowIncorrectRowAndCol() {
        Maze maze = new Maze(5, 5);

        assertThrows(IllegalArgumentException.class, () -> maze.getGridElement(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> maze.getGridElement(2, -1));
        assertThrows(IllegalArgumentException.class, () -> maze.getGridElement(5, 2));
    }

    @Test
    @DisplayName("Create deep copy of maze")
    void checkCorrectDeepCopy() {
        Maze originalMaze = new Maze(5, 5);
        originalMaze.addPassageToGrid(2, 2);

        Maze copiedMaze = originalMaze.deepCopy();
        originalMaze.addPassageToGrid(1, 1);

        assertNotSame(originalMaze, copiedMaze, "Copied maze should be a new instance");
        assertEquals(originalMaze.width(), copiedMaze.width());
        assertEquals(originalMaze.height(), copiedMaze.height());
        assertEquals(originalMaze.getGridElement(2, 2).type(), copiedMaze.getGridElement(2, 2).type());
        assertNotSame(originalMaze.getGridElement(1, 1).type(), copiedMaze.getGridElement(1, 1).type());
    }
}
