package author.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import author.FileChooserSingleton;
import author.ImageDisplayer;

@SuppressWarnings("serial")
public class ImagePanel extends AbstractWizardPanel implements ActionListener {
    
    private JLabel myLabel;
    private ImageDisplayer myImageDisplayer;
    private JFileChooser myChooser;
    private JButton myOpenButton;
    private File myFile;
    public static String IMG_FOLDER_FILEPATH = System.getProperty("user.dir") + File.separator + "images";
    
    public ImagePanel(String label){
        super("Image");
        myLabel = new JLabel(label + ":");
        createFileChooser();
        myImageDisplayer = new ImageDisplayer();   
        initLayout();  
    }

    private void initLayout(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(myLabel);
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
                copyFileAndSelectCopy();
            }
        }
        
    }
    
    @Override
    public Map<String, String> getUserInput () {
        Map<String, String> map = new HashMap<String, String>();
        copyFileAndSelectCopy();
        String label = myLabel.getText(); 
        map.put(label.substring(0, label.length()-1), myFile.getPath());
        //map.put(label.substring(0, label.length()-1), "");
        return map;
    }
    
    public void copyFileAndSelectCopy(){
        System.out.println("Parent folder: " + myFile.getParent());
        System.out.println("Project's images folder: " + (new File(IMG_FOLDER_FILEPATH)).getAbsolutePath());
        if ( !myFile.getParentFile().equals(new File(IMG_FOLDER_FILEPATH)) ){
            File newFile = new File(IMG_FOLDER_FILEPATH + "/" + myFile.getName());
            try {
                //Files.copy(myFile.toPath(), new FileOutputStream(newFile));
            } catch (Exception e){
            	
            }
            /*catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
            myFile = newFile;            
            System.out.println("Not already in folder; copy made");
        } else { System.out.println("Already in folder"); }    
    }
}
