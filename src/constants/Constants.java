package constants;

import java.awt.Color;


public class Constants {
    public static final int WIDTH = 800, HEIGHT = 500;

    public static final int NUM_TILES_HORIZONTAL = 15;
    public static final int NUM_TILES_VERTICAL = 9;

    public static final int MIDDLE_TILE_HORIZONTAL = NUM_TILES_HORIZONTAL / 2;
    public static final int MIDDLE_TILE_VERTICAL = NUM_TILES_VERTICAL / 2;

    public static final double TILE_WIDTH = (double) WIDTH / NUM_TILES_HORIZONTAL;
    public static final double TILE_HEIGHT = (double) HEIGHT / NUM_TILES_VERTICAL;

    public static final int REFRESH_RATE = 10;
    public static final int MOVE_FRAMES = 15; // number of frames in one move

    public static final int BORDER_THICKNESS = 15;
    public static final Color BORDER_COLOR = Color.black;
}
