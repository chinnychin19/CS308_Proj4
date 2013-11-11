package util;

public class Loc {
    private int x, y;

    public Loc (int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public boolean equals (Object o) {
        if (o == null) return false;
        if (!(o instanceof Loc)) return false;
        return x == ((Loc) o).getX() && y == ((Loc) o).getY();
    }

    @Override
    public int hashCode () {
        return toString().hashCode();
    }

    @Override
    public String toString () {
        return "(" + x + ", " + y + ")";
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public void setX (int a) {
        x = a;
    }

    public void setY (int b) {
        y = b;
    }

    public Loc adjacentLoc (Direction d) {
        switch (d) {
            case UP:
                return up();
            case DOWN:
                return down();
            case LEFT:
                return left();
            case RIGHT:
                return right();
        }
        return null;
    }

    private Loc left () {
        return new Loc(x - 1, y);
    }

    private Loc right () {
        return new Loc(x + 1, y);
    }

    // Assumes (0,0) is top left corner
    private Loc up () {
        return new Loc(x, y - 1);
    }

    // Assumes (0,0) is top left corner
    private Loc down () {
        return new Loc(x, y + 1);
    }
}
