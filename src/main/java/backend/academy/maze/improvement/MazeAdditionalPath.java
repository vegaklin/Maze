package backend.academy.maze.improvement;

import backend.academy.maze.maze.Maze;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MazeAdditionalPath {
    List<Wall> walls = new ArrayList<>();

    public void addingPathsInMaze(Maze maze) {
        int height = maze.height();
        int width = maze.width();

        for (int row = 2; row < height - 2; row += 2) {
            int col = 1;
            while(maze.isPassageInGrid(row, col) && col < width - 1) {
                col++;
            }
            int startPoint = col;
            for (; col < width - 1; col++) {
                if (maze.isPassageInGrid(row, col)) {
                    walls.add(new Wall(new Point(row, startPoint), new Point(row, col - 1), col - 1 - startPoint + 1));
                    while(maze.isPassageInGrid(row, col) && col < width - 1) {
                        col++;
                    }
                    startPoint = col;
                }
            }
            if (maze.isWallInGrid(row, width - 2)) {
                walls.add(new Wall(new Point(row, startPoint), new Point(row, width - 2), width - 2 - startPoint + 1));
            }
        }

        for (int col = 2; col < height - 2; col += 2) {
            int row = 1;
            while(maze.isPassageInGrid(row, col) && row < height - 1) {
                row++;
            }
            int startPoint = row;
            for (; row < height - 1; row++) {
                if (maze.isPassageInGrid(row, col)) {
                    walls.add(new Wall(new Point(startPoint, col), new Point(row - 1, col), row - 1 - startPoint + 1));
                    while(maze.isPassageInGrid(row, col) && col < width - 1) {
                        row++;
                    }
                    startPoint = row;
                }
            }
            if (maze.isWallInGrid(height - 2, col)) {
                walls.add(new Wall(new Point(startPoint, col), new Point(height - 2, col), height - 21 - startPoint + 1));
            }
        }

        sortingWalls();

        int maxAttempts = Math.max(height, width) / 5;
        int wallsLength = 0;
        while (maxAttempts > 0 && wallsLength < walls.size()) {
            if (walls.get(wallsLength).start().x == walls.get(wallsLength).end().x) {
                int passagePoint = walls.get(wallsLength).start().y + walls.get(wallsLength).length() / 2;
                if (passagePoint % 2 == 0) {
                    passagePoint++;
                }
                maze.addPassageToGrid(walls.get(wallsLength).start().x, passagePoint);
            }
            else {
                int passagePoint = walls.get(wallsLength).start().x + walls.get(wallsLength).length() / 2;
                if (passagePoint % 2 == 0) {
                    passagePoint++;
                }
                maze.addPassageToGrid(passagePoint, walls.get(wallsLength).start().y);
            }
            maxAttempts--;
            wallsLength++;
        }

//        return maze;

    }

    void sortingWalls() {
        walls.sort(Comparator.comparingInt(Wall::length).reversed());
    }
}
