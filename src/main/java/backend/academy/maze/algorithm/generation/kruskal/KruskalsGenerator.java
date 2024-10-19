package backend.academy.maze.algorithm.generation.kruskal;

import backend.academy.maze.algorithm.generation.Edge;
import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.maze.Maze;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static backend.academy.maze.validation.MazeValidator.validateMazeSize;

public class KruskalsGenerator implements Generator {

    @Override
    public Maze generate(int height, int width) {
        int newHeight = validateMazeSize(height);
        int newWidth = validateMazeSize(width);

        Maze maze = new Maze(newHeight, newWidth);
        DisjointSet sets = new DisjointSet((newHeight / 2) * (newWidth / 2));
        List<Edge> edges = initializeEdges(newHeight, newWidth);

        kruskalAlgorithmImplementation(maze, edges, sets, newWidth);
        return maze;
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

    private void kruskalAlgorithmImplementation(Maze maze, List<Edge> edges, DisjointSet sets, int width) {
        for (Edge edge : edges) {
            Point from = edge.from();
            Point to = edge.to();
            int cell1 = from.y * (width / 2) + from.x;
            int cell2 = to.y * (width / 2) + to.x;
            if (sets.find(cell1) != sets.find(cell2)) {
                sets.union(cell1, cell2);
                maze.addPassageToGrid(from.y * 2 + 1, from.x * 2 + 1);
                maze.addPassageToGrid(to.y * 2 + 1, to.x * 2 + 1);
                maze.addPassageToGrid((from.y + to.y) + 1, (from.x + to.x) + 1);
            }
        }
    }
}
