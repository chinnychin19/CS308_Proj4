package test.author;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
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
        demoImagePanel();
        //demoCheckBoxes();
        //demoRadioButtons();
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
