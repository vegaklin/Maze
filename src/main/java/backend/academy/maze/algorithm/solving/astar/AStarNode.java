package backend.academy.maze.algorithm.solving.astar;

import backend.academy.maze.maze.Coordinate;

public record AStarNode(Coordinate coordinate, int gCost, int fCost, AStarNode previousNode) {}
