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
    private int cost;

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
        switch (type) {
            case WALL:
                this.cost = Integer.MAX_VALUE;
                break;
            case PASSAGE:
                this.cost = DEFAULT_COST;
                break;
            case SWAMP:
                this.cost = SWAMP_COST;
                break;
            case SAND:
                this.cost = SAND_COST;
                break;
            case COIN:
                this.cost = COIN_COST;
                break;
            case EAT:
                this.cost = EAT_COST;
                break;
        }
    }
}

