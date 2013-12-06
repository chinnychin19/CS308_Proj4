package location;

import constants.Constants;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    
    public static Direction opposite(Direction d) {
        if (d.equals(UP)) {
            return DOWN;
        }
        if (d.equals(DOWN)) {
            return UP;
        }
        if (d.equals(LEFT)) {
            return RIGHT;
        }
        if (d.equals(RIGHT)) {
            return LEFT;
        }
        return DOWN;
    }
    
    public static Direction constructFromString (String s) {
        if (s.equalsIgnoreCase(Constants.UP)) {
            return Direction.UP;
        }
        else if (s.equalsIgnoreCase(Constants.LEFT)) {
            return Direction.LEFT;
        }
        else if (s.equalsIgnoreCase(Constants.DOWN)) {
            return Direction.DOWN;
        }
        else if (s.equalsIgnoreCase(Constants.RIGHT)) {
            return Direction.RIGHT;
        }
        else {
            return null;
        }
    }
    
    public static String getString (Direction d) {
        switch(d){
            case UP:
                return Constants.UP;
            case DOWN:
                return Constants.DOWN;
            case LEFT:
                return Constants.LEFT;
            case RIGHT:
                return Constants.RIGHT;
            default:
                return null;
        }
    }
    

}
