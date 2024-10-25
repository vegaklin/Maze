package backend.academy.maze.algorithm.solving.bfs;

import backend.academy.maze.maze.Coordinate;

/**
 * Represents a node in the BFS search process for pathfinding within a maze.
 * Each BFSNode holds a coordinate, the total cost to reach this node from the start,
 * and a reference to the previous node in the path.
 * Used for tracing the path once the end coordinate is reached.
 *
 * @param coordinate  The coordinate location of this node within the maze.
 * @param totalCost   The total cumulative cost to reach this node from the start coordinate.
 * @param previousNode The previous BFSNode in the path, used for backtracking the shortest path.
 */
public record BFSNode(Coordinate coordinate, int totalCost, BFSNode previousNode) {}
