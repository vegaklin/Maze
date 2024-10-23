package backend.academy.maze.maze;

import lombok.Getter;
import static backend.academy.maze.constant.MazeConstants.COIN_COST;
import static backend.academy.maze.constant.MazeConstants.DEFAULT_COST;
import static backend.academy.maze.constant.MazeConstants.EAT_COST;
import static backend.academy.maze.constant.MazeConstants.SAND_COST;
import static backend.academy.maze.constant.MazeConstants.SWAMP_COST;

@Getter public class Cell {
    private final Type type;
    private final int row;
    private final int col;
    private final int cost;

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.cost = calculateCost(type);
    }

    private int calculateCost(Type type) {
        return switch (type) {
            case WALL -> Integer.MAX_VALUE;
            case PASSAGE -> DEFAULT_COST;
            case SWAMP -> SWAMP_COST;
            case SAND -> SAND_COST;
            case COIN -> COIN_COST;
            case EAT -> EAT_COST;
        };
    }
}

