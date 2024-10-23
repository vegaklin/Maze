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

    public static boolean isValidRowCol(int row, int col, int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public static boolean isCorrectMazeSize(int number) {
        return number > 1;
    }

    public static boolean isCorrectStartEndPoint(int point, int height) {
        return point >= 1 && point <= height - 2;
    }

    public static boolean isValidMenuNumber(int input) {
        return input == 1 || input == 2;
    }
}
