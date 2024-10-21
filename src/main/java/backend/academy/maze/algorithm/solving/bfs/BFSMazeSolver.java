package backend.academy.maze.algorithm.solving.bfs;

import backend.academy.maze.algorithm.solving.Solver;
import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import backend.academy.maze.maze.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import static backend.academy.maze.constant.MazeConstants.DIRECTIONS;
import static backend.academy.maze.validation.MazeValidator.isValidRowCol;

public class BFSMazeSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.height();
        int width = maze.width();
        Queue<BFSNode> queue = new PriorityQueue<>(Comparator.comparingInt(BFSNode::totalCost));
        queue.offer(new BFSNode(start, 0, null));

        int[][] visited = new int[height][width];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        visited[start.row()][start.col()] = 0;

        while (!queue.isEmpty()) {
            BFSNode current = queue.poll();
            Coordinate coordinate = current.coordinate();
            if (coordinate.equals(end)) {
                return editPath(current);
            }

            for (int[] direction : DIRECTIONS) {
                int newRow = coordinate.row() + direction[0];
                int newCol = coordinate.col() + direction[1];
                if (isValidRowCol(newRow, newCol, height, width)) {
                    Cell neighbor = maze.getGridElement(newRow, newCol);
                    if (neighbor.type() == Type.WALL) {
                        continue;
                    }
                    int newCost = current.totalCost() + neighbor.cost();
                    if (newCost < visited[newRow][newCol]) {
                        visited[newRow][newCol] = newCost;
                        queue.offer(new BFSNode(new Coordinate(newRow, newCol), newCost, current));
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> editPath(BFSNode node) {
        List<Coordinate> path = new ArrayList<>();
        while (node != null) {
            path.add(node.coordinate());
            node = node.previousNode();
        }
        Collections.reverse(path);
        return path;
    }
}
