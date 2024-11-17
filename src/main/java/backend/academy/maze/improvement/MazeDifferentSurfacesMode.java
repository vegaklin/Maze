package backend.academy.maze.improvement;

import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import java.security.SecureRandom;
import lombok.experimental.UtilityClass;
import static backend.academy.maze.constant.MazeConstants.COIN_PROBABILITY;
import static backend.academy.maze.constant.MazeConstants.EAT_PROBABILITY;
import static backend.academy.maze.constant.MazeConstants.SAND_PROBABILITY;
import static backend.academy.maze.constant.MazeConstants.SWAMP_PROBABILITY;

/**
 * Utility class to add diverse surface types to passages in a maze.
 * Each type influences maze traversal cost.
 */
@UtilityClass
public class MazeDifferentSurfacesMode {
    private final SecureRandom random = new SecureRandom();

    public static Maze addDifferentSurfacesInMaze(Maze maze) {
        Maze newMaze = maze.deepCopy();
        int height = newMaze.height();
        int width = newMaze.width();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (newMaze.isPassageInGrid(row, col)) {
                    double randomValue = random.nextDouble();
                    if (randomValue < SWAMP_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.SWAMP);
                    } else if (randomValue < SAND_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.SAND);
                    } else if (randomValue < COIN_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.COIN);
                    } else if (randomValue < EAT_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.EAT);
                    }
                }
            }
        }
        return newMaze;
    }
}
