package backend.academy.maze.algorithm.generation.prim;

import java.awt.Point;

public class Edge {
    Point from;
    Point to;
    int weight;

    Edge(Point from, Point to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
