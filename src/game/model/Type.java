package game.model;

import location.Loc;

public class Type {
    private String myType;
    public Type(String type){
        myType = type;
    }
    
    @Override
    public int hashCode() {
        return myType.hashCode();
    }
    
    
    @Override
    public boolean equals (Object o) {
        if (o == null) return false;
        if (!(o instanceof Type)) return false;
        return myType.equals(o.toString());
    }

    @Override
    public String toString () {
        return myType;
    }
}
