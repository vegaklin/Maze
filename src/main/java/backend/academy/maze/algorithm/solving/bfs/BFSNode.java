package backend.academy.maze.algorithm.solving.bfs;

import backend.academy.maze.maze.Coordinate;

public record BFSNode(Coordinate coordinate, int totalCost, BFSNode previousNode) {}
