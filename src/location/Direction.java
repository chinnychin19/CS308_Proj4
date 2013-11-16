package location;

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
}
