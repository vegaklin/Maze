package backend.academy.maze.algorithm.generation.prim;

import backend.academy.maze.algorithm.generation.Edge;
import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.PriorityQueue;
import static backend.academy.maze.constant.MazeConstants.PRIMS_START_COL;
import static backend.academy.maze.constant.MazeConstants.PRIMS_START_ROW;
import static backend.academy.maze.validation.MazeValidator.validateMazeSize;

/**
 * PrimsGenerator is responsible for generating mazes using Prim's algorithm.
 * This class implements the Generator interface, providing a method to generate mazes.
 * Prim's algorithm is a minimum spanning tree algorithm that starts from a random cell and
 * adds the shortest available edge connecting a new cell to the tree.
 */
public class PrimsGenerator implements Generator {
    /**
     * Random number generator for generating random weights for edges.
     * SecureRandom is used to ensure unpredictable randomness.
     */
    private final SecureRandom random;

    /**
     * Constructor to initialize the PrimsGenerator with SecureRandom for random edge weights.
     */
    public PrimsGenerator() {
        random = new SecureRandom();
    }

    /**
     * Generates a maze using Prim's algorithm.
     * It validates the provided dimensions, initializes an empty maze,
     * and applies Prim's algorithm to carve passages.
     *
     * @param height The height of the maze grid.
     * @param width  The width of the maze grid.
     * @return A Maze object representing the generated maze.
     */
    @Override
    public Maze generate(int height, int width) {
        int newHeight = validateMazeSize(height);
        int newWidth = validateMazeSize(width);

        Maze maze = new Maze(newHeight, newWidth);
        PriorityQueue<Edge> availableEdges = new PriorityQueue<>(Comparator.comparingInt(Edge::weight));

        primAlgorithmImplementation(maze, availableEdges, height, width);
        return maze;
    }

    /**
     * Implements Prim's algorithm to generate the maze.
     * This method starts from a specific cell, adds its edges to the priority queue,
     * and progressively connects cells by removing walls between them.
     *
     * @param maze           The Maze object where the passages will be carved.
     * @param availableEdges PriorityQueue that holds the edges ordered by their weights.
     * @param height         The height of the maze grid.
     * @param width          The width of the maze grid.
     */
    private void primAlgorithmImplementation(Maze maze, PriorityQueue<Edge> availableEdges, int height, int width) {
        maze.addPassageToGrid(PRIMS_START_ROW, PRIMS_START_COL);
        addEdge(maze, availableEdges, PRIMS_START_ROW, PRIMS_START_COL, height, width);
        while (!availableEdges.isEmpty()) {
            Edge edge = availableEdges.poll();
            if (maze.isWallInGrid(edge.to().col(), edge.to().row())) {
                connect(maze, edge.from(), edge.to());
                addEdge(maze, availableEdges, edge.to().col(), edge.to().row(), height, width);
            }
        }

    }

    /**
     * Adds potential edges for a given cell to the priority queue.
     * Each edge represents a connection between the current cell and its neighboring cells.
     *
     * @param maze           The Maze object where edges are being checked.
     * @param availableEdges PriorityQueue that holds the edges ordered by their weights.
     * @param y              The y-coordinate (row) of the current cell.
     * @param x              The x-coordinate (col) of the current cell.
     * @param height         The height of the maze grid.
     * @param width          The width of the maze grid.
     */
    private void addEdge(Maze maze, PriorityQueue<Edge> availableEdges, int y, int x, int height, int width) {
        if (x > 1 && maze.isWallInGrid(y, x - 2)) {
            availableEdges.add(new Edge(new Coordinate(x, y), new Coordinate(x - 2, y), random.nextInt()));
        }
        if (x < width - 2 && maze.isWallInGrid(y, x + 2)) {
            availableEdges.add(new Edge(new Coordinate(x, y), new Coordinate(x + 2, y), random.nextInt()));
        }
        if (y > 1 && maze.isWallInGrid(y - 2, x)) {
            availableEdges.add(new Edge(new Coordinate(x, y), new Coordinate(x, y - 2), random.nextInt()));
        }
        if (y < height - 2 && maze.isWallInGrid(y + 2, x)) {
            availableEdges.add(new Edge(new Coordinate(x, y), new Coordinate(x, y + 2), random.nextInt()));
        }
    }

    /**
     * Connects two adjacent cells by removing the wall between them and marking the cells as passages.
     *
     * @param maze The Maze object where the cells will be connected.
     * @param p1   The first cell to be connected.
     * @param p2   The second cell to be connected.
     */
    private void connect(Maze maze, Coordinate p1, Coordinate p2) {
        int x = (p1.row() + p2.row()) / 2;
        int y = (p1.col() + p2.col()) / 2;
        maze.addPassageToGrid(p1.col(), p1.row());
        maze.addPassageToGrid(y, x);
        maze.addPassageToGrid(p2.col(), p2.row());
    }
}
