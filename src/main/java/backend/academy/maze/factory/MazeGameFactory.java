package backend.academy.maze.factory;

import backend.academy.maze.cli.MazeInterface;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeGameFactory {
    public static MazeInterface createMazeGame() {
        Renderer renderer = new ConsoleRenderer();
        return new MazeInterface(renderer);
    }
}
