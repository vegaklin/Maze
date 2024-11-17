package backend.academy.maze.algorithm.solving;

import backend.academy.maze.maze.Coordinate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.experimental.UtilityClass;


/**
 * Utility class for editing and reconstructing paths in the maze from linked nodes.
 * Provides a method to trace a path by following a node chain backwards, constructing a path from start to end.
 */
@UtilityClass
public class PathEditor {

    /**
     * Constructs a list of coordinates representing the path by tracing backwards from the given node.
     * Uses reflection to access the coordinate and previous node fields of each node object.
     *
     * @param node The final node in the path to trace back from.
     * @return A list of coordinates in order from start to end.
     * @throws RuntimeException if an error occurs during reflection.
     */
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
