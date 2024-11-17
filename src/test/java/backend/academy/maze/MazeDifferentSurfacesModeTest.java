package backend.academy.maze;

import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static backend.academy.maze.improvement.MazeDifferentSurfacesMode.addDifferentSurfacesInMaze;
import static backend.academy.maze.utils.MazeTestUtils.fillDefaultMaze;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeDifferentSurfacesModeTest {

    @Test
    @DisplayName("Correct addition of surfaces without changing walls")
    void checkCorrectAdditionOfSurface() {
        // given

        int height = 7;
        int width = 9;
        Maze maze = new Maze(height, width);
        fillDefaultMaze(maze);

        // when

        Maze mazeWithMode = addDifferentSurfacesInMaze(maze);

        // then

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (maze.isWallInGrid(row, col)) {
                    Cell actualIsWal = maze.getGridElement(row, col);
                    Cell expectedIsWal = mazeWithMode.getGridElement(row, col);

                    assertEquals(actualIsWal.type(), expectedIsWal.type());
                    assertEquals(actualIsWal.cost(), expectedIsWal.cost());
                }
                else {
                    Type mazeWithModeType = mazeWithMode.getGridElement(row, col).type();

                    assertTrue(mazeWithModeType == Type.COIN || mazeWithModeType == Type.EAT
                        || mazeWithModeType == Type.SWAMP || mazeWithModeType == Type.SAND
                        || mazeWithModeType == Type.PASSAGE);
                }
            }
        }
    }
}
