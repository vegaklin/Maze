package backend.academy.maze.algorithm.generation.kruskal;

import backend.academy.maze.algorithm.generation.Edge;
import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static backend.academy.maze.validation.MazeValidator.validateMazeSize;


/**
 * KruskalsGenerator class implements the maze generation algorithm based on Kruskal's algorithm.
 * It generates a perfect maze using disjoint sets to ensure no loops and walls between connected cells.
 */
public class KruskalsGenerator implements Generator {

    /**
     * Generates a maze with specified dimensions using Kruskal's algorithm.
     *
     * @param height The height of the maze.
     * @param width The width of the maze.
     * @return A Maze object representing the generated maze.
     */
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

    /**
     * Initializes the list of edges between adjacent cells in the grid.
     * Each edge connects two neighboring cells, either horizontally or vertically.
     *
     * @param height The height of the maze grid.
     * @param width The width of the maze grid.
     * @return A shuffled list of edges representing possible passages.
     */
    private List<Edge> initializeEdges(int height, int width) {
        List<Edge> edges = new ArrayList<>();
        for (int y = 0; y < height / 2; y++) {
            for (int x = 0; x < width / 2; x++) {
                Coordinate cell = new Coordinate(x, y);
                // Add horizontal edge to the right neighbor
                if (x < (width / 2) - 1) {
                    edges.add(new Edge(cell, new Coordinate(x + 1, y)));
                }
                // Add vertical edge to the bottom neighbor
                if (y < (height / 2) - 1) {
                    edges.add(new Edge(cell, new Coordinate(x, y + 1)));
                }
            }
        }
        // Shuffle the edges to ensure random passage creation
        Collections.shuffle(edges);
        return edges;
    }

    /**
     * Implements Kruskal's algorithm for maze generation.
     * It iterates over the edges and connects cells that belong to different sets,
     * ensuring no loops are created, until all cells are connected.
     *
     * @param maze The maze being generated.
     * @param edges The list of edges between adjacent cells.
     * @param sets The disjoint set used to track connected cells.
     * @param width The width of the maze grid.
     */
    private void kruskalAlgorithmImplementation(Maze maze, List<Edge> edges, DisjointSet sets, int width) {
        for (Edge edge : edges) {
            Coordinate from = edge.from();
            Coordinate to = edge.to();
            int cell1 = from.col() * (width / 2) + from.row();
            int cell2 = to.col() * (width / 2) + to.row();
            // If the two cells are not connected, union them and create a passage between them
            if (sets.find(cell1) != sets.find(cell2)) {
                sets.union(cell1, cell2);
                // Create passages in the maze grid for the two cells and the wall between them
                maze.addPassageToGrid(from.col() * 2 + 1, from.row() * 2 + 1);
                maze.addPassageToGrid(to.col() * 2 + 1, to.row() * 2 + 1);
                maze.addPassageToGrid((from.col() + to.col()) + 1, (from.row() + to.row()) + 1);
            }
        }
    }
}
