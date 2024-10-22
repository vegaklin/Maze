package backend.academy.maze.improvement;

import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import java.util.Random;
import lombok.experimental.UtilityClass;
import static backend.academy.maze.constant.MazeConstants.COIN_PROBABILITY;
import static backend.academy.maze.constant.MazeConstants.EAT_PROBABILITY;
import static backend.academy.maze.constant.MazeConstants.SAND_PROBABILITY;
import static backend.academy.maze.constant.MazeConstants.SWAMP_PROBABILITY;

@UtilityClass
public class MazeDifferentSurfacesMode {
    public static Maze addDifferentSurfacesInMaze(Maze maze) {
        Maze newMaze = maze.deepCopy();
        int height = newMaze.height();
        int width = newMaze.width();
        Random random = new Random();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (newMaze.isPassageInGrid(row, col)) {
                    if (random.nextDouble() < SWAMP_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.SWAMP);
                    } else if (random.nextDouble() < SAND_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.SAND);
                    } else if (random.nextDouble() < COIN_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.COIN);
                    } else if (random.nextDouble() < EAT_PROBABILITY) {
                        newMaze.addModeToGrid(row, col, Type.EAT);
                    }
                }
            }
        }
        return newMaze;
    }
}
