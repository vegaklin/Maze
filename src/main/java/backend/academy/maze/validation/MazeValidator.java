package backend.academy.maze.validation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeValidator {

    public static int validateMazeSize(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Incorrect maze size.");
        }
        return (size % 2 == 0) ? size + 1 : size;
    }

    public static void validateRowCol(int row,
                                    int col,
                                    int width,
                                    int height) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IllegalArgumentException("Out of range in Maze.");
        }
    }

    public static boolean isValidRowCol(int row,
                                        int col,
                                        int height,
                                        int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
}
