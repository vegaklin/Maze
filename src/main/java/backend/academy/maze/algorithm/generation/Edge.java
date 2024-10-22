package backend.academy.maze.algorithm.generation;

import backend.academy.maze.maze.Coordinate;
import lombok.Getter;

@Getter public class Edge {
    private final Coordinate from;
    private final Coordinate to;
    private final int weight;

    public Edge(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
        this.weight = 0;
    }

    public Edge(Coordinate from, Coordinate to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
