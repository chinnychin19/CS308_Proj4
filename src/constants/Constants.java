package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;


/**
 * Defines global constants for both the game engine
 * and the authoring environment.
 * 
 * @author Team Rocket
 * 
 */

public class Constants {
    public static final int WIDTH = 800, HEIGHT = 500;

    // These both must be odd numbers
    public static final int NUM_TILES_HORIZONTAL = 15;
    public static final int NUM_TILES_VERTICAL = 9;

    public static final int MIDDLE_TILE_HORIZONTAL = NUM_TILES_HORIZONTAL / 2;
    public static final int MIDDLE_TILE_VERTICAL = NUM_TILES_VERTICAL / 2;

    public static final double TILE_WIDTH = (double) WIDTH / NUM_TILES_HORIZONTAL;
    public static final double TILE_HEIGHT = (double) HEIGHT / NUM_TILES_VERTICAL;

    public static final long KEY_DELAY_MILLISECONDS = 100;

    public static final int BORDER_THICKNESS = 15;
    public static final Color BORDER_COLOR = Color.black;

    // Order matters because certain things depend on other things already being known. e.g. Attacks
    // need to be known before Monsters are created
    public static final String[] CATEGORIES =
    { "TypeMatrix", "Status", "Attack", "Monster", "KeyItem", "Item", "FightingNPC", "Obstacle",
     "NPC", "WildRegion", "Player", "HealItem" };

    public static final String[] VIEWABLE_CATEGORIES =
    { "NPC", "Obstacle", "WildRegion", "Player", "FightingNPC", "HealItem" };

    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String RIGHT = "right";
    public static final String LEFT = "left";

    // JSON keywords
    public static final String JSON_PLAYER = "Player";
    public static final String JSON_KEYITEM = "KeyItem";
    public static final String JSON_STATUS = "Status";
    public static final String JSON_STATUS_LOWERCASE = "status";

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

    public static final String JSON_POWER = "power";
    public static final String JSON_ACCURACY = "accuracy";
    public static final String JSON_STATISTIC_EFFECT = "statisticEffect";
    public static final String JSON_STATUS_EFFECT = "statusEffect";
    public static final String JSON_TARGET = "target";

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
    public static final String JSON_EVOLUTION = "evolution";

    public static final String JSON_DEFEATEDNPCS = "defeatedNPCs";

    public static final String JSON_NPC = "NPC";
    public static final String JSON_FIGHTING_NPC = "FightingNPC";
    public static final String JSON_OBSTACLE = "Obstacle";
    public static final String JSON_HEAL_ITEM = "HealItem";
    public static final String JSON_WILD_REGION = "WildRegion";

    public static final String STATUS_OKAY = "OK";

    // Panel Type Names
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

    // Key Values for Map Creation
    public static final int ZOOM_OUT_KEY = KeyEvent.VK_X;
    public static final int ZOOM_IN_KEY = KeyEvent.VK_Z;
    public static final int UP_ARROW_KEY = KeyEvent.VK_UP;
    public static final int DOWN_ARROW_KEY = KeyEvent.VK_DOWN;
    public static final int RIGHT_ARROW_KEY = KeyEvent.VK_RIGHT;
    public static final int LEFT_ARROW_KEY = KeyEvent.VK_LEFT;

    // GUI Sizing Constants
    public static final Dimension FRAME_SIZE = new Dimension(1024, 620);
    public static final Dimension BUTTON_SIZE = new Dimension(198, 28);
    public static final Dimension TEXT_AREA_SIZE = new Dimension(400, 32);
    public static final Dimension SIDEBAR_SIZE = new Dimension(200, 600);
    public static final Dimension SIDEBAR_JLIST_SIZE = new Dimension(150, 500);
    public static final Dimension MAP_CREATOR_SIZE = new Dimension(800, 600);

    // Authoring JMenu Options Constants
    public static final String FILE_MENU = "File";
    public static final String EDIT_MENU = "Edit";
    public static final String VIEW_MENU = "View";
    public static final String NEW_ENTITY_SUBMENU = "New Entity";
    public static final String EDIT_ENTITY_SUBMENU = "Edit Existing Entity";
    public static final String CHOOSE_ALTERNATE_TEMPLATE = "Choose Alternate Template (JSON)";
    public static final String LOAD_EXISTING_GAME = "Load Existing Game (JSON)";
    public static final String CREATE_NEW_MAP = "Create New Map";
    public static final String SHOW_GENERATED_OUTPUT = "Show Generated Output";

    // Classpaths, folder paths, and filenames
    public static final String WRITE_JSON_TO_FILE = "Write JSON to file";
    public static final String PLAYER_JSON = "player.json";
    public static final String CLASSPATH_GAME_MODEL = "game.model";
    public static final String FOLDERPATH_GAMES = "games";
    public static final String FILENAME_DEFINITION = "definition.json";
    public static final String FILENAME_WORLD = "world.json";
    public static final String FILENAME_SAVESTATE = "saveState2.json";

    // JSON keywords for Monster stats
    public static final String STAT_EXP = "exp";
    public static final String STAT_EXP_TO_NEXT_LEVEL = "expToNextLevel";
    public static final String STAT_MAX_HP = "maxHP";
    public static final String STAT_CUR_HP = "curHP";
    public static final String STAT_ATTACK = "attack";
    public static final String STAT_DEFENSE = "defense";
    public static final String STAT_TYPE = "type";
    public static final String STAT_BASE_HP = "baseHP";
    public static final String STAT_BASE_ATTACK = "baseAttack";
    public static final String STAT_BASE_DEFENSE = "baseDefense";
    public static final String NAME = "name";
    public static final String ATTACK_UPPERCASE = "Attack";

    // JSON Keywords for Item Stats
    public static final String MODE_KEYWORD = "mode";
    public static final String CONSCIOUSNESS = "consciousness";
    public static final String STATISTIC_EFFECT = "statisticEffect";
    public static final String STATISTIC_EFFECTS = "statisticEffects";
    public static final String STAT_NAME = "statName";
    public static final String CHANGE = "change";

    public static final String SAVE_FILE_NOT_FOUND = "Save file not found";
    public static final String UNCHECKED = "unchecked";

    public static final String FILE_NOT_FOUND_ERROR = "Error: File not found.";

    public static final String MONSTERS_LOWERCASE = "monsters";
    public static final String MONSTER_UPPERCASE = "Monster";

    public static final int NUM_OF_DIRECTIONS = 4;
    public static final int NUM_INPUTS = 8;

    public static final String TYPE_MATRIX = "TypeMatrix";
    public static final String MATRIX = "matrix";

    public final static String IMG_FOLDER_FILEPATH = "./images";

    public final static String SHORTGRASS_PNG_FILEPATH = "images/background/shortGrass.png";
    public final static String TEST_FILE = "images/ground/wildGrass.png";

    public final static String EMPTY_STRING = "";

    // MODE STRINGS
    public final static String MODE_CATCH = "CATCH";
    public static final String MODE_RUN_AWAY = "RUN AWAY";
    public final static String MODE_DEFAULT = "";
    public final static String MODE_BATTLE_OVER = "BATTLE OVER!";
    public final static String MODE_MONSTER_CAUGHT = "You caught the monster!";
    public final static String MODE_MONSTER_NOT_CAUGHT = "You failed to catch the wild monster!";
    public final static String MODE_PARTY_FULL =
            "Your party is full.  Choose a monster to release.";
    public static final String MODE_MONSTER_RELEASE_1 = "Goodbye ";
    public static final String MODE_MONSTER_RELEASE_2 = "!  We had some great memories together.";

    // Dialogue Strings
    public final static String PROMPT_INVALID_KEYITEM = "";
    public final static String PROMPT_MONSTER_DEAD = "Monster Died.  Choose a new Monster";
    public final static String PROMPT_PRESS_TO_CATCH = "Press interact to attempt to catch";
    public static final String PROMPT_PRESS_TO_RUN = "Press interact to run away from this battle";
    public final static String PROMPT_MONSTERS_HEALED = "All Monsters Healed";
    public final static String PROMPT_MISSING_ITEM = "MISSING ITEM: ";
    public final static String PROMPT_AQUIRE_MISSING_ITEM = ".  Acquire this item and try again";

    // Author Message Strings
    public final static String SIDEBAR_PROMPT_TEXT =
            "Please select the elements you would like to " +
                    "add to your map, and then click on the map to add:";

    // Fighting NPC intro speech
    public final static String PROMPT_FIGHTING_NPC_BEFORE_BATTLE =
            "Hey you, let's battle! Come here.";

    // Dialogue Box Placement
    public final static int DIALOGUE_HEIGHT = 80;
    // public final static int DIALOGUE_PADDING = 20;

    public static final int MAX_PARTY_SIZE = 6;

    // Type Matrix
    public static final String TYPE_MATRIX_KEY = "matrix";

    // Battle Text
    public final static String BATTLE_LEVEL_UP = "You Leveled Up!";
    public final static String BATTLE_EVOLVE = "You Evolved!";
    public final static String BATTLE_KILLED_ENEMY_MONSTER = "You Killed Da Monster!";

    // State Text Constants
    public final static int TEXT_START_X = 15;
    public final static int TEXT_START_Y = 30;
    public final static int TEXT_START_INC = 35;
    public static final int TEXT_CHARS_PER_LINE = 60;

    // Main Menu
    public final static int MAIN_MENU_X = 0;
    public final static int MAIN_MENU_Y = 0;
    public final static int MAIN_MENU_WIDTH = WIDTH;
    public final static int MAIN_MENU_HEIGHT = HEIGHT;
    public final static String MAIN_MENU_PARTY = "PARTY";
    public final static String MAIN_MENU_ITEM = "ITEMS";
    public final static String MAIN_MENU_KEY_ITEM = "KEY ITEMS";

    public final static String MAIN_MENU_SAVE = "SAVE";
    public final static String MAIN_MENU_EXIT = "EXIT";
    public static final int REVIVE_TO_FULL = -1;

    public static final int INT_ONE = 1;

    public static final String BOGUS_NAME_GAME = "bogusNameOfGame";

    // AuthorView Constants
    public static final String AUTHOR_TITLE = "Authoring View";
    public static final String AUTHOR_LAUNCH_WIZARD = "Launch Wizard";
    public static final String ERROR_LOADING_GAME = "Error loading game data.";

    // Music
    public static final String GAME_MUSIC = "music/nfl.wav";

    // Post Battle Mode
    public static final String YOU_LOST = "You Lost :(";
    public static final String YOU_WON = "You won :)";
    public static final String YOU_RECEIVED = "\tYou recieved ";
    public static final String DIALOGUE_KEY_ITEMS = " key items";
    public static final String BLANK_STRING = "";
    public static final String SPACE_STRING = " ";
    public static final String SPLIT_SLASH_S = "\\s+";
    public static final String TOGGLE_MUSIC = "TOGGLE MUSIC";
    public static final String MAIN_TEXT = "MAIN";
    public static final int ZERO = 0;

    public static final String TEXT_ATTACK = "ATTACK";
    public static final String TEXT_ITEMS = "ITEMS";
    public static final String TEXT_PARTY = "PARTY";
    public static final String TEXT_DIALOGUE = "DIALOGUE";

    public static final String TEXT_SWITCH_TO = "You switch to %s";
    public static final String TEXT_YOU_USED_ON_BLANK = "You used %s on %s";

    public static final String TEXT_DEFEATED = "defeated";

    public static final String HEAL_ITEM_IMAGE = "images/items/healPokeBall.png";

    public static final String TEXT_STAT_EFFECT = "statisticEffect";

    public static final String TEXT_KEY_ITEM = "KeyItem";
    public static final String TEXT_ITEMS_LOWERCASE = "items";
    public static final String TEXT_ITEM = "Item";

    public static final String MATCHUP_EFFECTIVE = "The matchup is super effective! ";
    public static final String MATCHUP_NOT_EFFECTIVE = "The matchup is not very effective. ";
    public static final String PROMPT_ATTACK_DAMAGE_DID = "The attack did %.0f damage. ";
    public static final String PROMPT_ATTACK_MISSED = "The attack missed! ";
    public static final String TEXT_HANDLING_USER_MOSNTER = "Handling user monster";
    public static final String TEXT_STAT_NAME = "statName";

    public static final String FILE_NAME_SHORT_GRASS = "images/ground/shortGrass.png";

    public static final String NAME_LOWERCASE = "name";

    public static final String ERROR_WITH_JSON = "There was an error with your JSON!";

    public static final String ALIVE_STRING = "alive";
    public static final String DEAD_STRING = "dead";

}
