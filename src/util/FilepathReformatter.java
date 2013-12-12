package util;

import java.io.File;

public class FilepathReformatter {
    
    private static FilepathReformatter instance;

    private FilepathReformatter () {
        
    }

    public static synchronized FilepathReformatter getInstance ()
    {
        if (instance == null)
            instance = new FilepathReformatter();

        return instance;
    }

    public static String getFilepathRootedAtFolder(String filepath, String folder) {
        String formattedPath = FilepathReformatter.getInstance().formatForCurrentSystem(filepath);
        int i = formattedPath.lastIndexOf(File.separator + folder);
        String truncatedFilepath = filepath.substring(i+1);
        return truncatedFilepath;
    }
    
    public String formatForWindows (String filepath) {
        filepath = filepath.replace("/", "\\");
        return filepath;
    }
    
    public String formatForUnix (String filepath) {
        filepath = filepath.replace("\\", "/");
        return filepath;
    }
    
    
    public String formatForCurrentSystem(String filepath){
        if (File.separator.equals("\\")){
            filepath = filepath.replace("/", File.separator);
        }
        if (File.separator.equals("/")){
            filepath = filepath.replace("\\", File.separator);
        }
        return filepath;
    }

}
