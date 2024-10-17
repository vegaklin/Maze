package backend.academy.maze.algorithm.generation;

import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.render.ConsoleRenderer;
import backend.academy.maze.render.Renderer;

import java.awt.Point;
import java.util.*;

public class PrimsGenerator implements Generator {
    private final Random random;
    private static final int START_ROW = 1;
    private static final int START_COL = 1;

    private static class Edge {
        Point from;
        Point to;
        int weight;

        Edge(Point from, Point to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    Renderer renderer = new ConsoleRenderer();

    public PrimsGenerator() {
        random = new Random();
    }

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        PriorityQueue<Edge> frontier = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        maze.addPassageToGrid(START_ROW, START_COL);
        addFrontier(maze, frontier, START_ROW, START_COL, height, width);

        // Основной цикл генерации лабиринта
        while (!frontier.isEmpty()) {
            Edge edge = frontier.poll();
            if (maze.isWallInGrid(edge.to.x, edge.to.y)) {
                connect(maze, edge.from, edge.to);
                addFrontier(maze, frontier, edge.to.x, edge.to.y, height ,width);
            }

            System.out.println(renderer.render(maze));
        }

        return maze;
    }

    private void addFrontier(Maze maze, PriorityQueue<Edge> frontier, int x, int y, int height, int width) {
        if (x > 1 && maze.isWallInGrid(x - 2, y)) frontier.add(new Edge(new Point(x, y), new Point(x - 2, y), random.nextInt()));
        if (x < width - 2 && maze.isWallInGrid(x + 2, y)) frontier.add(new Edge(new Point(x, y), new Point(x + 2, y), random.nextInt()));
        if (y > 1 && maze.isWallInGrid(x, y - 2)) frontier.add(new Edge(new Point(x, y), new Point(x, y - 2), random.nextInt()));
        if (y < height - 2 && maze.isWallInGrid(x, y + 2)) frontier.add(new Edge(new Point(x, y), new Point(x, y + 2), random.nextInt()));
    }

    private void connect(Maze maze, Point p1, Point p2) {
        int x = (p1.x + p2.x) / 2;
        int y = (p1.y + p2.y) / 2;
        maze.addPassageToGrid(p1.x, p1.y);
        maze.addPassageToGrid(x, y);
        maze.addPassageToGrid(p2.x, p2.y);
    }
}
