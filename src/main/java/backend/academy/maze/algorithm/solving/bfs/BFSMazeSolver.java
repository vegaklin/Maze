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

public class BFSMazeSolver implements Solver {
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

    private Queue<BFSNode> initializeQueue(Coordinate start) {
        Queue<BFSNode> queue = new PriorityQueue<>(Comparator.comparingInt(BFSNode::totalCost));
        queue.offer(new BFSNode(start, 0, null));
        return queue;
    }

    private int[][] initializeVisitedArray(int height, int width, Coordinate start) {
        int[][] visited = new int[height][width];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        visited[start.row()][start.col()] = 0;
        return visited;
    }

    private boolean processCurrentBFSNode(Maze maze, BFSNode current, Coordinate end,
                                        Queue<BFSNode> queue, int[][] visited) {
        Coordinate coordinate = current.coordinate();
        if (coordinate.equals(end)) {
            return true;
        }
        for (int[] direction : DIRECTIONS) {
            processBFSNeighbor(maze, current, direction, queue, visited);
        }
        return false;
    }

    private void processBFSNeighbor(Maze maze, BFSNode current, int[] direction,
                                    Queue<BFSNode> queue, int[][] visited) {
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

    private void updateQueueAndVisited(BFSNode current, int newRow, int newCol,
                                    int newCost, Queue<BFSNode> queue, int[][] visited) {
        if (newCost < visited[newRow][newCol]) {
            visited[newRow][newCol] = newCost;
            queue.offer(new BFSNode(new Coordinate(newRow, newCol), newCost, current));
        }
    }
}

