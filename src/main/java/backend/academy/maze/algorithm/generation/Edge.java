package backend.academy.maze.algorithm.generation;

import java.awt.Point;
import lombok.Getter;

@Getter public class Edge {
    private final Point from;
    private final Point to;
    private final int weight;

    public Edge(Point from, Point to) {
        this.from = from;
        this.to = to;
        this.weight = 0;
    }

    public Edge(Point from, Point to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
