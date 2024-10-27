package backend.academy.maze;

import backend.academy.maze.cli.MazeInterface;
import lombok.experimental.UtilityClass;
import static backend.academy.maze.factory.MazeGameFactory.createMazeGame;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MazeInterface maze = createMazeGame();
        maze.start(System.in, System.out);
    }
}
