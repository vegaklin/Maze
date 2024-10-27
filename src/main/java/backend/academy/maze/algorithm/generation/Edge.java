package backend.academy.maze.algorithm.generation;

import backend.academy.maze.maze.Coordinate;
import lombok.Getter;

/**
 * Edge class represents an edge in the maze, connecting two coordinates (cells).
 * It can be used to describe possible passages between cells.
 */
@Getter
public class Edge {
    /** The starting coordinate of the edge */
    private final Coordinate from;
    /** The ending coordinate of the edge */
    private final Coordinate to;
    /** The weight of the edge (used in weighted mazes) */
    private final int weight;

    /**
     * Creates an edge between two coordinates with a default weight of 0.
     *
     * @param from The starting coordinate.
     * @param to The ending coordinate.
     */
    public Edge(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
        this.weight = 0;
    }

    /**
     * Creates an edge between two coordinates with a specified weight.
     *
     * @param from The starting coordinate.
     * @param to The ending coordinate.
     * @param weight The weight of the edge.
     */
    public Edge(Coordinate from, Coordinate to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
