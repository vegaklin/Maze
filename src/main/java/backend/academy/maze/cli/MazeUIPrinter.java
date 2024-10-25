package backend.academy.maze.cli;

import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.Renderer;
import java.io.PrintStream;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeUIPrinter {
    public static void printTitle(PrintStream out) {
        out.println("=== MAZE ===");
    }

    public static void printMessageGeneratorChoosing(PrintStream out) {
        out.print("""
            Choose an algorithm for generating an algorithm:
            1. Kruskal's algorithm;
            2. Prime's algorithm.
            """);
    }

    public static void printMessageSolverChoosing(PrintStream out) {
        out.print("""
            Choose an algorithm for solving an algorithm:
            1. A* (A-star) algorithm;
            2. BFS algorithm.
            """);
    }

    public static void printMessageHeightChoosing(PrintStream out) {
        out.println("Enter the height of the maze (greater than 1):");
    }

    public static void printMessageWidthChoosing(PrintStream out) {
        out.println("Enter the width of the maze (greater than 1):");
    }

    public static void printMazeWithNumbers(PrintStream out, String render) {
        out.print(render);
    }

    public static void printMazePath(PrintStream out, Maze maze, List<Coordinate> mazePath, Renderer renderer) {
        if (mazePath.isEmpty()) {
            out.println("No path found between the selected points.");
        } else {
            out.print(renderer.render(maze, mazePath));
        }
    }
}
