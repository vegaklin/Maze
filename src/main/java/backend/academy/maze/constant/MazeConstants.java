package backend.academy.maze.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeConstants {
    private static final String YAML_FILE_PATH = "application.yaml";

    private final ConfigParser configParser = new ConfigParser(YAML_FILE_PATH);

//    public static final String LINE_LONG = configParser.get("LINE_LONG", String.class);
    public static final int PRIMS_START_ROW = configParser.get("PRIMS_START_ROW", Integer.class);
    public static final int PRIMS_START_COL = configParser.get("PRIMS_START_COL", Integer.class);
}
