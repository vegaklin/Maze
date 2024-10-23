package backend.academy.maze.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeConstants {
    private static final String YAML_FILE_PATH = "application.yaml";

    private final ConfigParser configParser = new ConfigParser(YAML_FILE_PATH);

    public static final int PRIMS_START_ROW = configParser.get("PRIMS_START_ROW", Integer.class);
    public static final int PRIMS_START_COL = configParser.get("PRIMS_START_COL", Integer.class);

    public static final int SWAMP_COST = configParser.get("SWAMP_COST", Integer.class);
    public static final int SAND_COST = configParser.get("SAND_COST", Integer.class);
    public static final int DEFAULT_COST = configParser.get("DEFAULT_COST", Integer.class);
    public static final int EAT_COST = configParser.get("EAT_COST", Integer.class);
    public static final int COIN_COST = configParser.get("COIN_COST", Integer.class);
    public static final double SWAMP_PROBABILITY = configParser.get("SWAMP_PROBABILITY", Double.class);
    public static final double SAND_PROBABILITY = configParser.get("SAND_PROBABILITY", Double.class);
    public static final double EAT_PROBABILITY = configParser.get("EAT_PROBABILITY", Double.class);
    public static final double COIN_PROBABILITY = configParser.get("COIN_PROBABILITY", Double.class);

    public static final int NUMBER_PATHS_ADD = configParser.get("NUMBER_PATHS_ADD", Integer.class);
    public static final int SELECTION_ATTEMPTS = configParser.get("SELECTION_ATTEMPTS", Integer.class);
    public static final int DEFAULT_MENU_NUMBER = configParser.get("DEFAULT_MENU_NUMBER", Integer.class);
    public static final int DEFAULT_MAZE_SIZE = configParser.get("DEFAULT_MAZE_SIZE", Integer.class);

    public static final String INVALID_INPUT_MESSAGE = configParser.get("INVALID_INPUT_MESSAGE", String.class);

    public static final int[][] DIRECTIONS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
}
