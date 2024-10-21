package backend.academy.maze.algorithm.solving;

import backend.academy.maze.maze.Coordinate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PathEditor {
    public static List<Coordinate> editPath(Object node) {
        List<Coordinate> path = new ArrayList<>();
        Object currentNode = node;
        while (currentNode != null) {
            try {
                Coordinate coordinate = (Coordinate) currentNode.getClass().getMethod("coordinate").invoke(currentNode);
                path.add(coordinate);
                currentNode = currentNode.getClass().getMethod("previousNode").invoke(currentNode);
            } catch (Exception e) {
                throw new RuntimeException("Error editing path", e);
            }
        }
        Collections.reverse(path);
        return path;
    }
}
