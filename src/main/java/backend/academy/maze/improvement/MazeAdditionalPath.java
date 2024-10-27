package backend.academy.maze.improvement;

import backend.academy.maze.maze.Maze;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static backend.academy.maze.constant.MazeConstants.FIXED_COORDINATE_INCREMENT;
import static backend.academy.maze.constant.MazeConstants.NUMBER_PATHS_ADD;

/**
 * The MazeAdditionalPath class is responsible for identifying and adding
 * additional paths within a given maze structure by analyzing horizontal and
 * vertical walls. Adding additional paths by inserting a Passage cell in the
 * middle of a long wall. It starts with the longest and
 * occurs Math.max(height, width) / NUMBER_PATHS_ADD times.
 */
public class MazeAdditionalPath {
    private final List<Wall> walls;

    public MazeAdditionalPath() {
        walls = new ArrayList<>();
    }

    public Maze addingPathsInMaze(Maze maze) {
        Maze newMaze = maze.deepCopy();
        int height = newMaze.height();
        int width = newMaze.width();

        processWalls(newMaze, height, width, true);
        processWalls(newMaze, height, width, false);
        sortWallsByLength();
        addPassagesInWalls(newMaze, height, width);
        return newMaze;
    }

    private void processWalls(Maze maze,
                            int height,
                            int width,
                            boolean isHorizontal) {
        int limit = isHorizontal ? height : width;
        for (int fixedCoordinate = FIXED_COORDINATE_INCREMENT;
             fixedCoordinate < limit - 2;
             fixedCoordinate += FIXED_COORDINATE_INCREMENT) {
            int variableCoordinate = 1;
            while (isPassage(maze, fixedCoordinate, variableCoordinate, isHorizontal)
                && variableCoordinate < (isHorizontal ? width : height) - 1) {
                variableCoordinate++;
            }
            int startPoint = variableCoordinate;
            for (; variableCoordinate < (isHorizontal ? width : height) - 1;
                 variableCoordinate++) {
                if (isPassage(maze, fixedCoordinate, variableCoordinate, isHorizontal)) {
                    walls.add(createWall(fixedCoordinate, startPoint,
                        variableCoordinate - 1, isHorizontal));
                    while (isPassage(maze, fixedCoordinate, variableCoordinate, isHorizontal)
                        && variableCoordinate < (isHorizontal ? width : height) - 1) {
                        variableCoordinate++;
                    }
                    startPoint = variableCoordinate;
                }
            }
            if (isWall(maze, fixedCoordinate, (isHorizontal ? width : height) - 2, isHorizontal)) {
                walls.add(createWall(fixedCoordinate, startPoint, (isHorizontal ? width : height) - 2, isHorizontal));
            }
        }
    }

    private boolean isPassage(Maze maze,
                            int fixedCoordinate,
                            int variableCoordinate,
                            boolean isHorizontal) {
        return isHorizontal
            ? maze.isPassageInGrid(fixedCoordinate, variableCoordinate)
            : maze.isPassageInGrid(variableCoordinate, fixedCoordinate);
    }

    private boolean isWall(Maze maze,
                        int fixedCoordinate,
                        int variableCoordinate,
                        boolean isHorizontal) {
        return isHorizontal
            ? maze.isWallInGrid(fixedCoordinate, variableCoordinate)
            : maze.isWallInGrid(variableCoordinate, fixedCoordinate);
    }

    private Wall createWall(int fixedCoordinate,
                            int startPoint,
                            int endPoint,
                            boolean isHorizontal) {
        return isHorizontal
            ? new Wall(new Point(fixedCoordinate, startPoint),
            new Point(fixedCoordinate, endPoint), endPoint - startPoint + 1)
            : new Wall(new Point(startPoint, fixedCoordinate),
            new Point(endPoint, fixedCoordinate), endPoint - startPoint + 1);
    }

    private void addPassagesInWalls(Maze maze,
                                    int height,
                                    int width) {
        int maxAttempts = Math.max(height, width) / NUMBER_PATHS_ADD;
        int wallsLength = 0;
        while (maxAttempts > 0 && wallsLength < walls.size()) {
            Wall wall = walls.get(wallsLength);
            if (isHorizontalWall(wall)) {
                int passagePoint = calculatePassagePoint(wall.start().y, wall.length());
                maze.addPassageToGrid(wall.start().x, passagePoint);
            } else {
                int passagePoint = calculatePassagePoint(wall.start().x, wall.length());
                maze.addPassageToGrid(passagePoint, wall.start().y);
            }
            maxAttempts--;
            wallsLength++;
        }
    }

    private boolean isHorizontalWall(Wall wall) {
        return wall.start().x == wall.end().x;
    }

    private int calculatePassagePoint(int startCoordinate, int length) {
        int passagePoint = startCoordinate + length / 2;
        return (passagePoint % 2 == 0) ? passagePoint + 1 : passagePoint;
    }

    private void sortWallsByLength() {
        walls.sort(Comparator.comparingInt(Wall::length).reversed());
    }
}
