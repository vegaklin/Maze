package backend.academy.maze.algorithm.solving.astar;

import backend.academy.maze.maze.Coordinate;

/**
 * AStarNode represents a node in the A* algorithm, holding information about a specific point in the maze,
 * as well as costs associated with reaching this node and the path taken to it.
 *
 * @param coordinate The current position in the maze.
 * @param gCost The cost from the starting point to this node.
 * @param fCost The estimated total cost from start to goal through this node.
 * @param previousNode The previous node in the path leading to this node.
 */
public record AStarNode(Coordinate coordinate, int gCost, int fCost, AStarNode previousNode) {}
