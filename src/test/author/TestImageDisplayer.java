package test.author;

import static org.junit.Assert.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import author.ImageDisplayer;

public class TestImageDisplayer {

    private JFrame myFrame;
    
    @Before
    public void setUp () throws Exception {
        myFrame = new JFrame("TestFrame");
        myFrame.pack();
        myFrame.setVisible(true);
    }

    @After
    public void tearDown () throws Exception {
    }

    @Test
    public void Test001() {
        ImageDisplayer imgDisplay = new ImageDisplayer();
        imgDisplay.setPreferredSize(new Dimension(100,100));
        imgDisplay.setImageAndCaption("../images/test.gif");
        myFrame.getContentPane().add(imgDisplay, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setVisible(true);
        fail("Not yet implemented");
    }

}
