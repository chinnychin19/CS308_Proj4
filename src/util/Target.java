package util;

public enum Target {
    SELF, OTHER;
    public static Target getTarget(String name){
        if("self".equals(name)){
            return SELF;
        }
        return OTHER;
    }
}
