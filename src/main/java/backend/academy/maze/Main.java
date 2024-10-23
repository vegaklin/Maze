package backend.academy.maze;

import backend.academy.maze.cli.MazeInterface;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.experimental.UtilityClass;
import static backend.academy.maze.factory.MazeGameFactory.createMazeGame;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MazeInterface maze = createMazeGame();
        maze.start(new Scanner(System.in, StandardCharsets.UTF_8), System.out);
    }
}
