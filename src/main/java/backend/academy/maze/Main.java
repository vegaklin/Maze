package backend.academy.maze;

import backend.academy.maze.cli.MazeInterface;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Renderer renderer = new ConsoleRenderer();
        MazeInterface maze = new MazeInterface(renderer);
        maze.start(new Scanner(System.in), System.out);
    }
}
