package backend.academy.maze.algorithm.solving.bfs;

import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import static backend.academy.maze.algorithm.solving.PathEditor.editPath;
import static backend.academy.maze.constant.MazeConstants.DIRECTIONS;
import static backend.academy.maze.constant.MazeConstants.DIRECTIONS_LEFT_INDEX;
import static backend.academy.maze.constant.MazeConstants.DIRECTIONS_RIGHT_INDEX;
import static backend.academy.maze.validation.MazeValidator.isValidRowCol;

/**
 * Implements the Solver interface using the Breadth-First Search (BFS) algorithm to find the shortest path in a maze.
 */
public class BFSMazeSolver implements Solver {

    /**
     * Solves the maze using BFS to find the shortest path from the start coordinate to the end coordinate.
     *
     * @param maze   The maze object containing grid information.
     * @param start  The starting coordinate for the search.
     * @param end    The target coordinate to reach.
     * @return A list of coordinates representing the shortest path
     * from start to end, or an empty list if no path is found.
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.height();
        int width = maze.width();
        Queue<BFSNode> queue = initializeQueue(start);
        int[][] visited = initializeVisitedArray(height, width, start);

        while (!queue.isEmpty()) {
            BFSNode current = queue.poll();
            if (processCurrentBFSNode(maze, current, end, queue, visited)) {
                return editPath(current);
            }
        }
        return Collections.emptyList();
    }

    /**
     * Initializes the BFS queue with the start node.
     *
     * @param start The starting coordinate for the search.
     * @return A priority queue containing the initial BFSNode at the start position.
     */
    private Queue<BFSNode> initializeQueue(Coordinate start) {
        Queue<BFSNode> queue = new PriorityQueue<>(Comparator.comparingInt(BFSNode::totalCost));
        queue.offer(new BFSNode(start, 0, null));
        return queue;
    }

    /**
     * Initializes the visited array to track minimum costs for reaching each cell in the maze.
     * Unvisited cells are initialized with a high value.
     *
     * @param height The height of the maze grid.
     * @param width  The width of the maze grid.
     * @param start  The starting coordinate to initialize.
     * @return A 2D array representing the minimum cost to each cell, initialized for BFS traversal.
     */
    private int[][] initializeVisitedArray(int height, int width, Coordinate start) {
        int[][] visited = new int[height][width];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        visited[start.row()][start.col()] = 0;
        return visited;
    }

    /**
     * Processes the current BFS node by evaluating its neighbors.
     * If the end coordinate is reached, the search is complete.
     *
     * @param maze    The maze object containing the grid information.
     * @param current The current BFS node being processed.
     * @param end     The target end coordinate.
     * @param queue   The BFS queue for further exploration.
     * @param visited The array tracking the minimum costs to each cell.
     * @return True if the end coordinate is reached; otherwise, false.
     */
    private boolean processCurrentBFSNode(Maze maze,
                                        BFSNode current,
                                        Coordinate end,
                                        Queue<BFSNode> queue,
                                        int[][] visited) {
        Coordinate coordinate = current.coordinate();
        if (coordinate.equals(end)) {
            return true;
        }
        for (int[] direction : DIRECTIONS) {
            processBFSNeighbor(maze, current, direction, queue, visited);
        }
        return false;
    }

    /**
     * Processes each neighboring cell of the current node. If the neighbor is traversable and offers a lower cost,
     * it is added to the BFS queue for future exploration.
     *
     * @param maze     The maze object containing grid information.
     * @param current  The current BFS node.
     * @param direction The direction vector to the neighbor.
     * @param queue    The BFS queue for future exploration.
     * @param visited  The array tracking the minimum costs to each cell.
     */
    private void processBFSNeighbor(Maze maze,
                                    BFSNode current,
                                    int[] direction,
                                    Queue<BFSNode> queue,
                                    int[][] visited) {
        int newRow = current.coordinate().row() + direction[DIRECTIONS_LEFT_INDEX];
        int newCol = current.coordinate().col() + direction[DIRECTIONS_RIGHT_INDEX];
        if (isValidRowCol(newRow, newCol, maze.height(), maze.width())) {
            Cell neighbor = maze.getGridElement(newRow, newCol);
            if (neighbor.type() != Type.WALL) {
                int newCost = current.totalCost() + neighbor.cost();
                updateQueueAndVisited(current, newRow, newCol, newCost, queue, visited);
            }
        }
    }

    /**
     * Updates the BFS queue and visited array for a valid neighboring cell if it provides a shorter path.
     *
     * @param current The current BFS node.
     * @param newRow  The row of the neighboring cell.
     * @param newCol  The column of the neighboring cell.
     * @param newCost The cumulative cost to reach the neighboring cell.
     * @param queue   The BFS queue.
     * @param visited The array tracking the minimum costs to each cell.
     */
    private void updateQueueAndVisited(BFSNode current,
                                    int newRow,
                                    int newCol,
                                    int newCost,
                                    Queue<BFSNode> queue,
                                    int[][] visited) {
        if (newCost < visited[newRow][newCol]) {
            visited[newRow][newCol] = newCost;
            queue.offer(new BFSNode(new Coordinate(newRow, newCol), newCost, current));
        }
    }
}

