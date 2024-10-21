package backend.academy.maze.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeConstants {
    private static final String YAML_FILE_PATH = "application.yaml";

    private final ConfigParser configParser = new ConfigParser(YAML_FILE_PATH);

//    public static final String LINE_LONG = configParser.get("LINE_LONG", String.class);
    public static final int PRIMS_START_ROW = configParser.get("PRIMS_START_ROW", Integer.class);
    public static final int PRIMS_START_COL = configParser.get("PRIMS_START_COL", Integer.class);
    public static final int SWAMP_COST = configParser.get("SWAMP_COST", Integer.class);
    public static final int SAND_COST = configParser.get("SAND_COST", Integer.class);
    public static final int DEFAULT_COST = configParser.get("DEFAULT_COST", Integer.class);
    public static final int EAT_COST = configParser.get("EAT_COST", Integer.class);
    public static final int COIN_COST = configParser.get("COIN_COST", Integer.class);
    public static final int[][] DIRECTIONS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
}
