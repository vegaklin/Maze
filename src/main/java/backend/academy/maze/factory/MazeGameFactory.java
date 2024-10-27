package backend.academy.maze.factory;

import backend.academy.maze.cli.MazeAlgorithmProcess;
import backend.academy.maze.cli.MazeDetailsProcess;
import backend.academy.maze.cli.MazeInterface;
import backend.academy.maze.cli.MazeProcessing;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeGameFactory {

    public static MazeInterface createMazeGame() {
        Renderer renderer = new ConsoleRenderer();
        MazeProcessing mazeProcessing = new MazeProcessing();
        MazeDetailsProcess mazeDetailsProcess = new MazeDetailsProcess();
        MazeAlgorithmProcess mazeAlgorithmProcess = new MazeAlgorithmProcess();
        return new MazeInterface(renderer, mazeProcessing, mazeDetailsProcess, mazeAlgorithmProcess);
    }
}
