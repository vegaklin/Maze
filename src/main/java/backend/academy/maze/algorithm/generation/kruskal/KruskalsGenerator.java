package backend.academy.maze.algorithm.generation.kruskal;

import backend.academy.maze.algorithm.generation.Edge;
import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.maze.Maze;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalsGenerator implements Generator {

    @Override
    public Maze generate(int height, int width) {
        int newHeight = validateMazeSize(height);
        int newWidth = validateMazeSize(width);

        Maze maze = new Maze(newHeight, newWidth);
        List<Edge> edges = initializeEdges(newHeight, newWidth);

        DisjointSet ds = new DisjointSet((newHeight / 2) * (newWidth / 2));

        kruskalAgorithmImplementation(maze, edges, ds, newWidth);

        addEntranceAndExit(maze, newHeight, newWidth);

        return maze;
    }

    private int validateMazeSize(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Incorrect maze size.");
        }
        return (size % 2 == 0) ? size + 1 : size;
    }

    private List<Edge> initializeEdges(int height, int width) {
        List<Edge> edges = new ArrayList<>();
        for (int y = 0; y < height / 2; y++) {
            for (int x = 0; x < width / 2; x++) {
                Point cell = new Point(x, y);
                if (x < (width / 2) - 1) {
                    edges.add(new Edge(cell, new Point(x + 1, y)));
                }
                if (y < (height / 2) - 1) {
                    edges.add(new Edge(cell, new Point(x, y + 1)));
                }
            }
        }
        Collections.shuffle(edges);
        return edges;
    }

    private void kruskalAgorithmImplementation(Maze maze, List<Edge> edges, DisjointSet ds, int width) {
        for (Edge edge : edges) {
            Point from = edge.from();
            Point to = edge.to();

            int cell1 = from.y * (width / 2) + from.x;
            int cell2 = to.y * (width / 2) + to.x;

            if (ds.find(cell1) != ds.find(cell2)) {
                ds.union(cell1, cell2);

                maze.addPassageToGrid(from.y * 2 + 1, from.x * 2 + 1);
                maze.addPassageToGrid(to.y * 2 + 1, to.x * 2 + 1);
                maze.addPassageToGrid((from.y + to.y) + 1, (from.x + to.x) + 1);
            }
        }
    }

    private void addEntranceAndExit(Maze maze, int height, int width) {
        maze.addPassageToGrid(1, 0);
        maze.addPassageToGrid(height - 2, width - 1);
    }
}
