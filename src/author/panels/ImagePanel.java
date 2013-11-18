package author.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import author.FileChooserSingleton;
import author.ImageDisplayer;

public class ImagePanel extends AbstractWizardPanel implements ActionListener {
    
    private JLabel myLabel;
    private ImageDisplayer myImageDisplayer;
    private JFileChooser myChooser;
    private JButton myOpenButton;
    private File myFile;
    
    public ImagePanel(){
        super("Image");
        myLabel = new JLabel("Image:");
        createFileChooser();
        myImageDisplayer = new ImageDisplayer();   
        initLayout();  
    }

    private void initLayout(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(myImageDisplayer);    
        myImageDisplayer.setAlignmentX(CENTER_ALIGNMENT);
        this.add(myOpenButton);
        myOpenButton.setAlignmentX(CENTER_ALIGNMENT);
        this.setMinimumSize(new Dimension(ImageDisplayer.MIN_X_SIZE, ImageDisplayer.MIN_Y_SIZE + 100));
    }

    private void createFileChooser () {
        myChooser = FileChooserSingleton.getInstance();
        myChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        myChooser.setFileFilter(new FileNameExtensionFilter("Image files (JPEG, GIF, PNG)", "jpg", "jpeg", "gif", "png"));
        
        myOpenButton = new JButton("Select image...");
        myOpenButton.addActionListener(this);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == myOpenButton) {
            int returnVal = myChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = myChooser.getSelectedFile();
                myFile = file;
                myImageDisplayer.setImageAndCaption(myFile);
            }
        }
        
    }
    @Override
    public Map<String, String> getUserInput () {
        Map<String, String> map = new HashMap<String, String>();
        map.put(myLabel.toString(), myFile.getPath());
        return map;
    }

}
