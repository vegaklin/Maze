package backend.academy.maze.algorithm.solving.astar;

import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import static backend.academy.maze.algorithm.solving.PathEditor.editPath;
import static backend.academy.maze.constant.MazeConstants.DIRECTIONS;
import static backend.academy.maze.constant.MazeConstants.DIRECTIONS_LEFT_INDEX;
import static backend.academy.maze.constant.MazeConstants.DIRECTIONS_RIGHT_INDEX;
import static backend.academy.maze.validation.MazeValidator.isValidRowCol;

/**
 * AStarMazeSolver provides an implementation of the A* pathfinding algorithm to solve mazes.
 * It computes the optimal path from a start to an end coordinate by minimizing the movement cost through cells.
 */
public class AStarMazeSolver implements Solver {
    /**
     * Solves the maze by using the A* algorithm to find the shortest path from start to end coordinates.
     *
     * @param maze The maze to be solved.
     * @param start The start coordinate.
     * @param end The end coordinate.
     * @return A list of coordinates representing the shortest path, or an empty list if no path exists.
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.height();
        int width = maze.width();
        PriorityQueue<AStarNode> open = initializeOpenSet(start, end);
        int[][] gCost = initializeGCost(height, width, start);

        boolean[][] closed = new boolean[height][width];
        while (!open.isEmpty()) {
            AStarNode current = open.poll();

            if (processCurrentsAStarNode(maze, current, end, open, gCost, closed)) {
                return editPath(current);
            }
        }
        return Collections.emptyList();
    }

    /**
     * Initializes the priority queue for open nodes, starting with the initial coordinate.
     *
     * @param start The start coordinate.
     * @param end The end coordinate.
     * @return A priority queue of open nodes.
     */
    private PriorityQueue<AStarNode> initializeOpenSet(Coordinate start, Coordinate end) {
        PriorityQueue<AStarNode> open = new PriorityQueue<>(Comparator.comparingInt(AStarNode::fCost));
        open.offer(new AStarNode(start, 0, heuristic(start, end), null));
        return open;
    }

    /**
     * Sets up the initial G-cost (movement cost) grid, with all
     * cells initialized to maximum value except the start cell.
     *
     * @param height The maze height.
     * @param width The maze width.
     * @param start The start coordinate.
     * @return A 2D array containing G-cost values.
     */
    private int[][] initializeGCost(int height, int width, Coordinate start) {
        int[][] gCost = new int[height][width];
        for (int[] row : gCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        gCost[start.row()][start.col()] = 0;
        return gCost;
    }

    /**
     * Processes the current A* node and checks if it reaches the goal.
     * Adds neighbor nodes to the open queue if they provide a lower-cost path.
     *
     * @param maze The maze object.
     * @param current The current node in the A* algorithm.
     * @param end The end coordinate.
     * @param open The queue of nodes to be processed.
     * @param gCost The G-cost grid.
     * @param closed Tracks cells that have already been evaluated.
     * @return True if the goal has been reached, false otherwise.
     */
    private boolean processCurrentsAStarNode(Maze maze, AStarNode current,
                                            Coordinate end, PriorityQueue<AStarNode> open,
                                            int[][] gCost, boolean[][] closed) {
        Coordinate currentCoordinate = current.coordinate();
        if (currentCoordinate.equals(end)) {
            return true;
        }
        closed[currentCoordinate.row()][currentCoordinate.col()] = true;
        for (int[] direction : DIRECTIONS) {
            processAStarNeighbor(maze, current, direction, end, open, gCost, closed);
        }

        return false;
    }

    /**
     * Processes a neighboring cell for the current node, adding it to the open queue if it offers a better path.
     *
     * @param maze The maze object.
     * @param current The current A* node.
     * @param direction The direction to the neighboring cell.
     * @param end The end coordinate.
     * @param open The priority queue for nodes.
     * @param gCost The G-cost grid.
     * @param closed Tracks visited cells.
     */
    private void processAStarNeighbor(Maze maze, AStarNode current,
                                    int[] direction, Coordinate end, PriorityQueue<AStarNode> open,
                                    int[][] gCost, boolean[][] closed) {
        int newRow = current.coordinate().row() + direction[DIRECTIONS_LEFT_INDEX];
        int newCol = current.coordinate().col() + direction[DIRECTIONS_RIGHT_INDEX];

        if (isValidRowCol(newRow, newCol, maze.height(), maze.width())) {
            Cell neighbor = maze.getGridElement(newRow, newCol);
            if (neighbor.cost() == Integer.MAX_VALUE || closed[newRow][newCol]) {
                return;
            }
            int tentativeGCost = current.gCost() + neighbor.cost();
            if (tentativeGCost < gCost[newRow][newCol]) {
                gCost[newRow][newCol] = tentativeGCost;
                int fCost = tentativeGCost + heuristic(new Coordinate(newRow, newCol), end);
                open.offer(new AStarNode(new Coordinate(newRow, newCol), tentativeGCost, fCost, current));
            }
        }
    }

    /**
     * Calculates the heuristic cost for A* based on Manhattan distance.
     *
     * @param a The start coordinate.
     * @param b The end coordinate.
     * @return The heuristic cost between the two coordinates.
     */
    private int heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.col() - b.col());
    }
}

