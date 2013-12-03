package constants;

import java.awt.Color;
import java.awt.Dimension;

public class Constants {
    public static final int WIDTH = 800, HEIGHT = 500;

    //These both must be odd numbers
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

    // Order matters because certain things depend on other things already being known. e.g. Attacks
    // need to be known before Monsters are created
    public static final String[] CATEGORIES =
    { "TypeMatrix", 
      "Status", "Attack", "Monster", "KeyItem", "Item", "FightingNPC", "Obstacle",  
      "NPC",
     "WildRegion",
     "Player", };
    
    public static final String[] VIEWABLE_CATEGORIES =
        { "NPC", "Obstacle", "WildRegion", "Player", "FightingNPC" };
    
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    
    //JSON keywords
    public static final String JSON_PLAYER = "Player";
    
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
    public static final String JSON_PROB = "probability";
    public static final String JSON_LEVEL = "level";

    public static final String JSON_NAME = "name";
    public static final String JSON_X = "x";
    public static final String JSON_Y = "y";
    public static final String JSON_KEYITEMS = "keyItems";
    
    public static final String JSON_MONSTER_MAX_HP = "maxHP";
    public static final String JSON_MONSTER_CATCH_RATE = "catchRate";
    public static final String JSON_MONSTER_ALL_ATTACKS = "attacks";
    public static final String JSON_MONSTER_ATTACK = "attack";
    public static final String JSON_ATTACK_UNLOCK_LEVEL = "unlockLevel";
    
    public static final String JSON_DEFEATEDNPCS = "defeatedNPCs";

    //Panel Type Names
    public static final String LIST_PANEL = "List";
    public static final String NUMBER_PANEL = "Number";
    public static final String WORD_PANEL = "Word";
    public static final String MATRIX_PANEL = "Matrix";
    public static final String RADIOBUTTON_PANEL = "Radio Button";
    public static final String CHECKBOX_PANEL = "Checkbox";
    
    // Map Creation Values
    public static final int MIN_X_COORD = 0;
    public static final int MAX_X_COORD = 1005;
    public static final int MIN_Y_COORD = 0;
    public static final int MAX_Y_COORD = 603;
    
    //GUI Sizing Constants
    public static final Dimension FRAME_SIZE = new Dimension(800,600);
    public static final Dimension BUTTON_SIZE = new Dimension(198,28);
    public static final Dimension TEXT_AREA_SIZE = new Dimension(400, 32);
        
    // Classpaths, folder paths, and filenames
    public static final String CLASSPATH_GAME_MODEL = "game.model";
    public static final String FOLDERPATH_GAMES = "games";
    public static final String FILENAME_DEFINITION = "definition.json";
    public static final String FILENAME_WORLD = "world.json";
    public static final String FILENAME_SAVESTATE = "saveState.json";
    
    //JSON keywords for Monster stats
    public static final String EXP = "exp";
    public static final String EXP_TO_NEXT_LEVEL = "expToNextLevel";
    public static final String MAX_HP = "maxHP";
    public static final String CUR_HP = "curHP";
    public static final String ATTACK_LOWERCASE = "attack";
    public static final String DEFENSE = "defense";
    public static final String TYPE = "type";
    public static final String BASE_HP = "baseHP";
    public static final String BASE_ATTACK = "baseAttack";
    public static final String BASE_DEFENSE = "baseDefense";
    public static final String NAME = "name";
    public static final String ATTACK_UPPERCASE = "Attack";
    
    public static final String SAVE_FILE_NOT_FOUND = "Save file not found";
    public static final String UNCHECKED = "unchecked";

    public static final String MONSTERS_LOWWERCASE = "monsters";
    public static final String MONSTER_UPPERCASE = "Monster";
    
    public static final int NUM_OF_DIRECTIONS = 4;
    public static final int NUM_INPUTS = 8;
    
    public static final String TYPE_MATRIX = "TypeMatrix";
    public static final String MATRIX = "matrix";
    
    public final static String IMG_FOLDER_FILEPATH = "./images";
    
    public final static String EMPTY_STRING = "";
}
