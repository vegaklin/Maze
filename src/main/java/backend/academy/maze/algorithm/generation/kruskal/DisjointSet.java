package backend.academy.maze.algorithm.generation.kruskal;

/**
 * DisjointSet class implements the union-find (disjoint-set) data structure.
 * It is used to track and manage which maze cells are connected to prevent loops in the maze generation.
 */
public class DisjointSet {
    /** Array representing the parent of each set */
    private final int[] parent;
    /** Array representing the rank of each set (to optimize union operation) */
    private final int[] rank;

    /**
     * Constructs a DisjointSet of the specified size.
     * Each element is initially its own parent (i.e., each cell is its own set).
     *
     * @param size The number of sets to initialize.
     */
    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    /**
     * Finds the representative (root) of the set containing the given point.
     * Uses path compression to flatten the structure for faster future queries.
     *
     * @param point The point to find.
     * @return The root of the set containing the point.
     */
    public int find(int point) {
        if (parent[point] != point) {
            parent[point] = find(parent[point]);
        }
        return parent[point];
    }

    /**
     * Unions the sets containing two points.
     * Uses union by rank to attach the smaller set under the root of the larger set.
     *
     * @param x The first point.
     * @param y The second point.
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
