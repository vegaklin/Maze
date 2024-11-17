package backend.academy.maze.improvement;

import java.awt.Point;

public record Wall(Point start, Point end, int length) {}
