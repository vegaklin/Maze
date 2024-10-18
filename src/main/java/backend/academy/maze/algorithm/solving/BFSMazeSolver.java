package backend.academy.maze.algorithm.solving;

import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSMazeSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.getHeight();
        int width = maze.getWidth();

        boolean[][] visited = new boolean[height][width];
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(start);
        visited[start.row()][start.col()] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(cameFrom, start, end);
            }

            for (Coordinate neighbor : getNeighbors(maze, current)) {
                if (!visited[neighbor.row()][neighbor.col()]) {
                    queue.add(neighbor);
                    visited[neighbor.row()][neighbor.col()] = true;
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return Collections.emptyList(); // Если путь не найден
    }

    private List<Coordinate> getNeighbors(Maze maze, Coordinate current) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Верх, низ, лево, право

        for (int[] direction : directions) {
            int newRow = current.row() + direction[0];
            int newCol = current.col() + direction[1];

            if (newRow >= 0 && newRow < maze.getHeight() &&
                newCol >= 0 && newCol < maze.getWidth() &&
                maze.isPassageInGrid(newRow, newCol)) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }

        return neighbors;
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        for (Coordinate at = end; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Путь в обратном порядке
        return path;
    }
}
