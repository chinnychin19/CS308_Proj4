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

    public static final String[] CATEGORIES =
    { "Type", "TypeMatrix", "Statistic", "Status", "Monster", "Attack", "Item", "NPC", "KeyItem",
     "Obstacle", "WildRegion", "Player", "FightingNPC"};
    
    public static final String[] VIEWABLE_CATEGORIES =
        { "NPC", "Obstacle", "WildRegion", "Player", "FightingNPC" };
    
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    
    //JSON keywords
    public static final String JSON_IMAGE = "image";
    public static final String JSON_IMAGE_UP = "image-up";
    public static final String JSON_IMAGE_DOWN = "image-down";
    public static final String JSON_IMAGE_LEFT = "image-left";
    public static final String JSON_IMAGE_RIGHT = "image-right";
    
    public static final String JSON_ORIENTATION = "orientation";
    public static final String JSON_LINE_OF_SIGHT_DISTANCE = "lineOfSightDistance";
    public static final String JSON_DIALOGUE = "dialogue";
    public static final String JSON_POST_DIALOGUE = "postDialogue";
    public static final String JSON_FIGHT = "fight";
    public static final String JSON_BET = "bet";
    
    public static final String JSON_FREQ = "frequency";
    public static final String JSON_MONSTERS = "monsters";
    
    public static final String JSON_X = "x";
    public static final String JSON_Y = "y";
    public static final String JSON_KEYITEMS = "keyItems";


    
    
}
