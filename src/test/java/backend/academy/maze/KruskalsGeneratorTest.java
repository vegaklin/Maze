package backend.academy.maze;

import backend.academy.maze.algorithm.generation.Generator;
import backend.academy.maze.algorithm.generation.kruskal.KruskalsGenerator;
import backend.academy.maze.maze.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KruskalsGeneratorTest {
    private final Generator generator = new KruskalsGenerator();

    @Test
    @DisplayName("Generating a maze with the right dimensions")
    void checkGenerationCorrectWithCorrectSize() {
        // given

        int height = 5;
        int width = 15;

        // when

        Maze maze = generator.generate(height, width);

        // then

        assertEquals(height, maze.height(), "Maze height should be correct");
        assertEquals(width, maze.width(), "Maze width should be correct");
    }

    @Test
    @DisplayName("Generating a maze with adjusted dimensions if even dimensions are specified")
    void checkEvenSizeWillBeOddAfterGeneration() {
        // given

        int evenHeight = 4;
        int evenWidth = 6;

        // when

        Maze maze = generator.generate(evenHeight, evenWidth);

        // then

        assertEquals(5, maze.height(), "Maze height should be adjusted to 5");
        assertEquals(7, maze.width(), "Maze width should be adjusted to 7");
    }

    @Test
    @DisplayName("Have walls along all the edges of the maze")
    void checkWallsAlongMaze() {
        // given

        Maze maze = generator.generate(5, 5);

        // when // then

        for (int row = 0; row < maze.height(); row++) {
            assertTrue(maze.isWallInGrid(row, 0), "Left edge should be all walls");
            assertTrue(maze.isWallInGrid(row, maze.width() - 1), "Right edge should be all walls");
        }
        for (int col = 0; col < maze.width(); col++) {
            assertTrue(maze.isWallInGrid(0, col), "Top edge should be all walls");
            assertTrue(maze.isWallInGrid(maze.height() - 1, col), "Bottom edge should be all walls");
        }
    }

    @Test
    @DisplayName("Throw exception for too small maze size")
    void checkThrowInMazeSizeSmallerThanNull() {
        // given // when // then

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            generator.generate(-4, -14);
            generator.generate(4, -14);
            generator.generate(-4, 14);
        });
        assertEquals("Incorrect maze size.", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception of size 1")
    void checkThrowInMazeSizeOne() {
        // given // when // then

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> generator.generate(1, 1));
        assertEquals("Incorrect maze size.", exception.getMessage());
    }
}
