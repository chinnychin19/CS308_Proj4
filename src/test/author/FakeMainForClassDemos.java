package test.author;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import author.ImageDisplayer;
import author.ImagePicker;
import author.panels.*;

public class FakeMainForClassDemos {

    /**
     * @param args
     */
    public static void main (String[] args) {
        //demoImageDisplayer();
        //demoImagePicker();
        //demoImagePanel();
        //demoCheckBoxes();
        //demoRadioButtons();
        testRegexSplits();
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
}
