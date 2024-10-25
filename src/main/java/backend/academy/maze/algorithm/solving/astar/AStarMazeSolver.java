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

public class AStarMazeSolver implements Solver {
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

    private PriorityQueue<AStarNode> initializeOpenSet(Coordinate start, Coordinate end) {
        PriorityQueue<AStarNode> open = new PriorityQueue<>(Comparator.comparingInt(AStarNode::fCost));
        open.offer(new AStarNode(start, 0, heuristic(start, end), null));
        return open;
    }

    private int[][] initializeGCost(int height, int width, Coordinate start) {
        int[][] gCost = new int[height][width];
        for (int[] row : gCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        gCost[start.row()][start.col()] = 0;
        return gCost;
    }

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

    private int heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.col() - b.col());
    }
}

