package author;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImagePicker extends JPanel implements ActionListener {
    
    private ImageDisplayer myImageDisplayer;
    private JFileChooser myChooser;
    private JButton myOpenButton;
    private File myFile;

    
    public ImagePicker(){
        init();    
    }
    
    public ImagePicker(BorderLayout bl){
        super(bl);
        init();
    }
    
    private void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        myImageDisplayer = new ImageDisplayer();
        createFileChooser();
        this.add(myImageDisplayer);
        myImageDisplayer.setAlignmentX(CENTER_ALIGNMENT);
        this.add(myOpenButton);
        myOpenButton.setAlignmentX(CENTER_ALIGNMENT);
        this.setMinimumSize(new Dimension(ImageDisplayer.MIN_X_SIZE, ImageDisplayer.MIN_Y_SIZE + 100));
    }

    private void createFileChooser () {
        // TODO Auto-generated method stub
        myChooser = new JFileChooser("images");
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
    
    public File getFile(){
        return myFile;
    }
    
    public String getFilepath(){
        return myFile.getPath();
    }
}
