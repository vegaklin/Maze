package backend.academy.maze.algorithm.generation.prim;

import backend.academy.maze.algorithm.generation.Edge;
import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.maze.Maze;
import java.awt.Point;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class PrimsGenerator implements Generator {
    private final Random random;

    private static final int START_ROW = 1;
    private static final int START_COL = 1;

    public PrimsGenerator() {
        random = new Random();
    }

    @Override
    public Maze generate(int height, int width) {
        int newHeight = validateMazeSize(height);
        int newWidth = validateMazeSize(width);

        Maze maze = new Maze(newHeight, newWidth);
        PriorityQueue<Edge> availableEdges = new PriorityQueue<>(Comparator.comparingInt(Edge::weight));

        algorithmImplementation(maze, availableEdges, height, width);

        return addStartEndForMaze(maze, newHeight, newWidth);
    }

    private void algorithmImplementation(Maze maze, PriorityQueue<Edge> availableEdges, int height, int width) {
        maze.addPassageToGrid(START_ROW, START_COL);
        addEdge(maze, availableEdges, START_ROW, START_COL, height, width);

        while (!availableEdges.isEmpty()) {
            Edge edge = availableEdges.poll();
            if (maze.isWallInGrid(edge.to().y, edge.to().x)) {
                connect(maze, edge.from(), edge.to());
                addEdge(maze, availableEdges, edge.to().y, edge.to().x, height, width);
            }
        }

    }

    private void addEdge(Maze maze, PriorityQueue<Edge> availableEdges, int y, int x, int height, int width) {
        if (x > 1 && maze.isWallInGrid(y, x - 2)) {
            availableEdges.add(new Edge(new Point(x, y), new Point(x - 2, y), random.nextInt()));
        }
        if (x < width - 2 && maze.isWallInGrid(y, x + 2)) {
            availableEdges.add(new Edge(new Point(x, y), new Point(x + 2, y), random.nextInt()));
        }
        if (y > 1 && maze.isWallInGrid(y - 2, x)) {
            availableEdges.add(new Edge(new Point(x, y), new Point(x, y - 2), random.nextInt()));
        }
        if (y < height - 2 && maze.isWallInGrid(y + 2, x)) {
            availableEdges.add(new Edge(new Point(x, y), new Point(x, y + 2), random.nextInt()));
        }
    }

    private void connect(Maze maze, Point p1, Point p2) {
        int x = (p1.x + p2.x) / 2;
        int y = (p1.y + p2.y) / 2;
        maze.addPassageToGrid(p1.y, p1.x);
        maze.addPassageToGrid(y, x);
        maze.addPassageToGrid(p2.y, p2.x);
    }

    private int validateMazeSize(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Incorrect maze size.");
        }
        return (size % 2 == 0) ? size + 1 : size;
    }

    private Maze addStartEndForMaze(Maze maze, int height, int width) {
        maze.addPassageToGrid(1, 0);
        maze.addPassageToGrid(height - 2, width - 1);
        return maze;
    }
}
