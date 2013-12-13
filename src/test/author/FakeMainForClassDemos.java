package test.author;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.FilepathReformatter;
import util.OptionPaneSingleton;
import constants.Constants;
import author.AuthorView;
import author.ImageDisplayer;
import author.ImagePicker;
import author.model.AuthoringCache;
import author.panels.*;
import author.wizard.WizardBuilder;

public class FakeMainForClassDemos {

    /**
     * @param args
     */
    public static void main (String[] args) {
        //demoImageDisplayer();
        //demoImagePicker();
        demoImagePanel();
        //demoCheckBoxes();
        //demoRadioButtons();
        //testRegexSplits();
        //demoWizardBuilder();
        //demoOptionPanelSingleton();
        //demoFilepathReformatter();
    }

    private static void demoFilepathReformatter () {
        FilepathReformatter fr = FilepathReformatter.getInstance();
        String str0 = "C:\\Docs\\images\\fun\\pic.jpg";

        String str1 = fr.formatForUnix(str0);
        String str2 = fr.formatForWindows(str1);
        String str3 = fr.formatForCurrentSystem(str0);
        String str4 = fr.formatForCurrentSystem(str1);
        
        String str5 = fr.getFilepathRootedAtFolder(str1, "images");
        String str6 = fr.getFilepathRootedAtFolder(str2, "images");
        
        System.out.println(str0);
        System.out.println(File.separator);
        System.out.println(File.separator.equals("/"));
        System.out.println();
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
        System.out.println(str6);
    }

    private static void demoOptionPanelSingleton () {
        // TODO Auto-generated method stub
        boolean b = OptionPaneSingleton.getInstance().getOk("Do it?");
        System.out.println(b);    
        boolean c = OptionPaneSingleton.getInstance().getOk("Do it again?");
        System.out.println(c);  
    }

    private static void demoWizardBuilder () {
        //WizardBuilder wizBuild = new WizardBuilder("FightingNPC", Constants.PLAYER_JSON, new AuthoringCache(new AuthorView()));
        //String filePath = System.getProperty("user.dir") + File.separator + "test-json"+ File.separator +"test_player.json";
        //wizBuild.addPanelsFromFile(filePath);
    }

    private static void testRegexSplits () {
        String fieldType = "list_radio_Monster.name";        
        String[] fields = fieldType.split("_");
        String basicFieldType = (fields[0].equals("list")) ? fields[1] : fields[0];
        System.out.println("BASIC FIELD TYPE: " + basicFieldType);
        String limitedFieldType = (fields[0].equals("list")) ? fieldType.substring(5) : fieldType;
        System.out.println("LIMITED FIELD TYPE: " + limitedFieldType);
        String outputString = "";

        /**
         * We need this to be changed because it doens't work on a Mac
         * 
         * The parsing with the filepath strings isn't working.
         * 
         * Java has built in classes for building filepaths and file locations
         *              - We should use those so we don't get any bugs.
         * 
         * We shouldn't be parsing JSON in this class.
         *              - Should try to use util.jsonwrapper
         *              - Or use native Java methods (?)
         */
        if (limitedFieldType.split("_").length > 1 && limitedFieldType.indexOf(":") == -1) { //true EXAMPLE: "radio_Monster.name"
            String[] locKeyPair = limitedFieldType.split("_")[1].split("\\."); //FIRST splits to "radio" "Monster.name", then SUPPOSED to split "Monster.name" to "Monster" "Name"
            System.out.println("CONCEPT NEEDING ATTRIBUTE: " + locKeyPair[0]);
            System.out.println("ATTRIBUTE NEEDED: " + locKeyPair[1]);
            /*JSONArray locationArray = (JSONArray) myCache.getRawJSON().get(locKeyPair[0]);
            outputString = "~";
            for (Object con : locationArray) {
                outputString += (String) ((JSONObject) con).get(locKeyPair[1]) + ".";
            }   */
        }
        
    }

    private static void demoRadioButtons () {
        JFrame frame = new JFrame("DemoFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(800,600));
        RadioButtonsPanel panel = new RadioButtonsPanel("colors~red.green.blue");
        panel.addButtons("red","orange","yellow","green","blue","violet");
        panel.addButtons("black", "gray", "white");
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true); 
    }

    private static void demoCheckBoxes() {
        JFrame frame = new JFrame("DemoFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(800,600));
        AbstractWizardPanel panel = new CheckBoxPanel("colors~red.green.blue");
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);        
    }

    private static void demoImageDisplayer() {
        JFrame frame = new JFrame("DemoFrame");
        ImageDisplayer imgDisplay = new ImageDisplayer();
        imgDisplay.setPreferredSize(new Dimension(400,400));
        imgDisplay.setImageAndCaption("images/not_there.gif");
        frame.getContentPane().add(imgDisplay, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        
        imgDisplay.setImageAndCaption("images/test.gif");
        
    }
    
    private static void demoImagePicker() {
        JFrame frame = new JFrame("DemoFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(800,600));
        ImagePicker imgPicker = new ImagePicker();
        frame.getContentPane().add(imgPicker, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);        
    }
    
    private static void demoImagePanel () {
        JFrame frame = new JFrame("DemoFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(800,600));
        ImagePanel imgPicker = new ImagePanel("blah");
        frame.getContentPane().add(imgPicker, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);        
    }
    
    public void mjrTest () {
        // TODO Auto-generated method stub
        //for testing
        JSONObject J1 = null;
        JSONObject J2 = null;
        JSONObject J3 = null;
        JSONParser parser = new JSONParser();
        try {
            J1 = (JSONObject) parser.parse("{\"name\":\"thing1\"}");
            J2 = (JSONObject) parser.parse("{\"name\":\"thing2\"}");
            J3 = (JSONObject) parser.parse("{\"name\":\"thing3\"}");
        }
        catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //this.add("NPC", J1);
        //this.add("NPC", J2);
        //this.add("NPC", J3);
        //end test section
    }
}
