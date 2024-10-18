package backend.academy.maze.algorithm.solving;

import backend.academy.maze.maze.Cell;
import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;

import java.util.*;

public class AStarMazeSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.getHeight();
        int width = maze.getWidth();

        // gCost - расстояние от старта до текущей клетки
        Map<Coordinate, Integer> gCost = new HashMap<>();
        // fCost = gCost + hCost - общее предполагаемое расстояние
        Map<Coordinate, Integer> fCost = new HashMap<>();

        // Очередь с приоритетом, которая выбирает клетки с минимальной fCost
        PriorityQueue<Coordinate> openSet = new PriorityQueue<>(Comparator.comparingInt(fCost::get));
        // Словарь для восстановления пути
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();

        // Инициализация стартовой клетки
        gCost.put(start, 0);
        fCost.put(start, heuristic(start, end));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Coordinate current = openSet.poll();

            // Если достигли целевой клетки, восстанавливаем путь
            if (current.equals(end)) {
                return reconstructPath(cameFrom, start, end);
            }

            // Обрабатываем соседей текущей клетки
            for (Coordinate neighbor : getNeighbors(maze, current)) {
                int tentativeGCost = gCost.get(current) + 1; // Каждый шаг на один сосед стоит 1

                // Если путь через текущую клетку короче
                if (tentativeGCost < gCost.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gCost.put(neighbor, tentativeGCost);
                    fCost.put(neighbor, tentativeGCost + heuristic(neighbor, end));

                    // Если соседняя клетка не в openSet, добавляем её
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        // Если путь не найден
        return Collections.emptyList();
    }

    // Метод для вычисления эвристики (Манхэттенское расстояние)
    private int heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.row() - b.row()) + Math.abs(a.col() - b.col());
    }

    // Получение соседних клеток, которые являются проходами
    private List<Coordinate> getNeighbors(Maze maze, Coordinate current) {
        List<Coordinate> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Верх, низ, влево, вправо

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

    // Восстановление пути
    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        for (Coordinate at = end; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Путь восстанавливается в обратном порядке
        return path;
    }
}
