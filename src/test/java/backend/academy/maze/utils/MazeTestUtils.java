package backend.academy.maze.utils;

import backend.academy.maze.maze.Coordinate;
import backend.academy.maze.maze.Maze;
import java.util.ArrayList;
import java.util.List;

public class MazeTestUtils {

    public static List<Coordinate> upPathOneWallMaze() {
        List<Coordinate> expectedPath = new ArrayList<>();
        expectedPath.add(new Coordinate(2, 0));
        expectedPath.add(new Coordinate(2, 1));
        expectedPath.add(new Coordinate(1, 1));
        expectedPath.add(new Coordinate(1, 2));
        expectedPath.add(new Coordinate(1, 3));
        expectedPath.add(new Coordinate(2, 3));
        expectedPath.add(new Coordinate(2, 4));
        return expectedPath;
    }

    public static List<Coordinate> downPathOneWallMaze() {
        List<Coordinate> expectedPath = new ArrayList<>();
        expectedPath.add(new Coordinate(2, 0));
        expectedPath.add(new Coordinate(2, 1));
        expectedPath.add(new Coordinate(3, 1));
        expectedPath.add(new Coordinate(3, 2));
        expectedPath.add(new Coordinate(3, 3));
        expectedPath.add(new Coordinate(2, 3));
        expectedPath.add(new Coordinate(2, 4));
        return expectedPath;
    }

    public static void oneWallMaze(Maze maze) {
        /*
            ██████████
            ██  ??  ██
            A   ██   B
            ██      ██
            ██████████
         */
        maze.addPassageToGrid(2, 0);
        maze.addPassageToGrid(2, 4);

        maze.addPassageToGrid(1, 1);
        maze.addPassageToGrid(1, 2);
        maze.addPassageToGrid(1, 3);

        maze.addPassageToGrid(2, 1);
        maze.addPassageToGrid(2, 3);

        maze.addPassageToGrid(3, 1);
        maze.addPassageToGrid(3, 2);
        maze.addPassageToGrid(3, 3);
    }

    public static List<Coordinate> defaultMazePath() {
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(3, 0));

        path.add(new Coordinate(3, 1));
        path.add(new Coordinate(2, 1));
        path.add(new Coordinate(1, 1));

        path.add(new Coordinate(1, 2));
        path.add(new Coordinate(1, 3));
        path.add(new Coordinate(1, 4));
        path.add(new Coordinate(1, 5));

        path.add(new Coordinate(2, 5));
        path.add(new Coordinate(3, 5));

        path.add(new Coordinate(3, 6));
        path.add(new Coordinate(3, 7));

        path.add(new Coordinate(4, 7));
        path.add(new Coordinate(5, 7));

        path.add(new Coordinate(5, 8));
        return path;
    }

    public static void fillDefaultMaze(Maze maze) {
        /*
            ██████████████████
            ██          ██  ██
            ██  ██████  ██  ██
            A       ██      ██
            ██████████  ██  ██
            ██          ██   B
            ██████████████████
         */
        maze.addPassageToGrid(3, 0);
        maze.addPassageToGrid(5, 8);

        maze.addPassageToGrid(1, 1);
        maze.addPassageToGrid(1, 2);
        maze.addPassageToGrid(1, 3);
        maze.addPassageToGrid(1, 4);
        maze.addPassageToGrid(1, 5);
        maze.addPassageToGrid(1, 7);

        maze.addPassageToGrid(2, 1);
        maze.addPassageToGrid(2, 5);
        maze.addPassageToGrid(2, 7);

        maze.addPassageToGrid(3, 1);
        maze.addPassageToGrid(3, 2);
        maze.addPassageToGrid(3, 3);
        maze.addPassageToGrid(3, 5);
        maze.addPassageToGrid(3, 6);
        maze.addPassageToGrid(3, 7);

        maze.addPassageToGrid(4, 5);
        maze.addPassageToGrid(4, 7);

        maze.addPassageToGrid(5, 1);
        maze.addPassageToGrid(5, 2);
        maze.addPassageToGrid(5, 3);
        maze.addPassageToGrid(5, 4);
        maze.addPassageToGrid(5, 5);
        maze.addPassageToGrid(5, 7);
    }

    public static void fillBigDefaultMaze(Maze maze) {
        /*
            ████████████████████
            ██                ██
            ██  ████████████████
            ██  ██            ██
            ██  ██  ████████████
            ██  ██            ██
            ██  ██  ██        ██
            ██  ██  ██  ██    ██
            ██      ██  ██    ██
            ████████████████████
         */
        maze.addPassageToGrid(1, 1);
        maze.addPassageToGrid(1, 2);
        maze.addPassageToGrid(1, 3);
        maze.addPassageToGrid(1, 4);
        maze.addPassageToGrid(1, 5);
        maze.addPassageToGrid(1, 7);
        maze.addPassageToGrid(1, 8);

        maze.addPassageToGrid(2, 1);

        maze.addPassageToGrid(3, 1);
        maze.addPassageToGrid(3, 3);
        maze.addPassageToGrid(3, 4);
        maze.addPassageToGrid(3, 5);
        maze.addPassageToGrid(3, 6);
        maze.addPassageToGrid(3, 7);
        maze.addPassageToGrid(3, 8);

        maze.addPassageToGrid(4, 1);
        maze.addPassageToGrid(4, 3);

        maze.addPassageToGrid(5, 1);
        maze.addPassageToGrid(5, 3);
        maze.addPassageToGrid(5, 4);
        maze.addPassageToGrid(5, 5);
        maze.addPassageToGrid(5, 6);
        maze.addPassageToGrid(5, 7);
        maze.addPassageToGrid(5, 8);

        maze.addPassageToGrid(6, 1);
        maze.addPassageToGrid(6, 3);
        maze.addPassageToGrid(6, 5);
        maze.addPassageToGrid(6, 6);
        maze.addPassageToGrid(6, 7);
        maze.addPassageToGrid(6, 8);

        maze.addPassageToGrid(7, 1);
        maze.addPassageToGrid(7, 3);
        maze.addPassageToGrid(7, 5);
        maze.addPassageToGrid(7, 7);
        maze.addPassageToGrid(7, 8);

        maze.addPassageToGrid(8, 1);
        maze.addPassageToGrid(8, 2);
        maze.addPassageToGrid(8, 3);
        maze.addPassageToGrid(8, 5);
        maze.addPassageToGrid(8, 7);
        maze.addPassageToGrid(8, 8);
    }
}
