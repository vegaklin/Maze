package backend.academy.maze;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.generation.kruskal.KruskalsGenerator;
import backend.academy.maze.algorithm.generation.prim.PrimsGenerator;
import backend.academy.maze.algorithm.solving.AStarMazeSolver;
import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.cli.MazeInterface;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Generator generator = new KruskalsGenerator();
////        Generator generator = new PrimsGenerator();
//        Renderer renderer = new ConsoleRenderer();
////        Solver pathFinder = new BFSMazeSolver();
//        Solver pathFinder = new AStarMazeSolver();
//
//        int height = 15;
//        int width = 65;
//        Maze maze = generator.generate(height, width);
//
//        Coordinate start = new Coordinate(1, 0);
//        Coordinate end = new Coordinate(height - 2, width - 1);
//
//        List<Coordinate> path = pathFinder.solve(maze, start, end);
//        System.out.println(renderer.render(maze, path));

        Renderer renderer = new ConsoleRenderer();
        MazeInterface maze = new MazeInterface(renderer);
        maze.start(new Scanner(System.in), System.out);
    }
}
