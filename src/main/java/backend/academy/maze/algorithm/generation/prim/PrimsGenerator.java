package backend.academy.maze.algorithm.generation.prim;

import backend.academy.maze.algorithm.generation.Edge;
import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.maze.Maze;
import java.awt.Point;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import static backend.academy.maze.constant.MazeConstants.PRIMS_START_COL;
import static backend.academy.maze.constant.MazeConstants.PRIMS_START_ROW;
import static backend.academy.maze.validation.MazeValidator.validateMazeSize;

public class PrimsGenerator implements Generator {
    private final Random random;

    public PrimsGenerator() {
        random = new Random();
    }

    @Override
    public Maze generate(int height, int width) {
        int newHeight = validateMazeSize(height);
        int newWidth = validateMazeSize(width);

        Maze maze = new Maze(newHeight, newWidth);
        PriorityQueue<Edge> availableEdges = new PriorityQueue<>(Comparator.comparingInt(Edge::weight));

        primAlgorithmImplementation(maze, availableEdges, height, width);
        return maze;
    }

    private void primAlgorithmImplementation(Maze maze, PriorityQueue<Edge> availableEdges, int height, int width) {
        maze.addPassageToGrid(PRIMS_START_ROW, PRIMS_START_COL);
        addEdge(maze, availableEdges, PRIMS_START_ROW, PRIMS_START_COL, height, width);
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
}
