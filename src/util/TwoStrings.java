package util;

public class TwoStrings {
    private String myFirstString;
    private String mySecondString;
    
    public TwoStrings(String one, String two){
        myFirstString = one;
        mySecondString = two;
    }
    
    public String getFirst(){
        return myFirstString;
    }
    
    public String getSecond(){
        return mySecondString;
    }
    
    public void setFirst(String str){
        myFirstString = str;
    }
    
    public void setSecond(String str){
        mySecondString = str;
    }
    
    public String toString(){
        //return "\"" + myFirstString + "\" \"" + mySecondString + "\"";
        return myFirstString + " (" + mySecondString + ")";
    }
    
    public String[] toArray(){
        return new String[]{myFirstString, mySecondString};
    }
}
