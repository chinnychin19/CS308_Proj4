package author.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.Constants;
import util.FileChooserSingleton;
import util.FilepathReformatter;
import author.ImageDisplayer;


public class ImagePanel extends AbstractWizardPanel implements ActionListener {

	private static final long serialVersionUID = -839654482048989269L;

	private JLabel myLabel;
	private ImageDisplayer myImageDisplayer;
	private JFileChooser myChooser;
	private JButton myOpenButton;
	private File myFile;
	public static String IMG_FOLDER_FILEPATH = System.getProperty(Constants.USER_DIR) + File.separator + Constants.IMAGES;

	public ImagePanel(String label){
		super(Constants.IMAGE);
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
		myChooser.setFileFilter(new FileNameExtensionFilter(
				Constants.IMAGE_FILE_TYPES, 
				Constants.JPG_EXTENSION, 
				Constants.JPEG_EXTENSION, 
				Constants.GIF_EXTENTION, 
				Constants.PNG_EXTENSION) );

		myOpenButton = new JButton(Constants.SELECT_IMAGE_PROMPT);
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

		copyFileAndSelectCopy();

		String label = myLabel.getText();         
		FilepathReformatter fr = FilepathReformatter.getInstance();
		@SuppressWarnings("static-access")
		String localFilepath = fr.getFilepathRootedAtFolder(myFile.getAbsolutePath(), Constants.IMAGES);
		String localFilepathUnix  = fr.formatForUnix(localFilepath);

		map.put(label.substring(0, label.length()-1), localFilepathUnix);
		System.out.println(label + " --> " + localFilepathUnix);
		return map;
	}

	public void copyFileAndSelectCopy(){
		System.out.println(Constants.PARENT_FOLDER + myFile.getParent());
		System.out.println(Constants.PROJECT_IMAGES_FOLDER + (new File(IMG_FOLDER_FILEPATH)).getAbsolutePath());
		Path currentFilePath = myFile.toPath();
		if ( !currentFilePath.startsWith(IMG_FOLDER_FILEPATH) ){
			File newFile = new File(IMG_FOLDER_FILEPATH + File.separator + myFile.getName());
			System.out.println(Constants.TARGET_NEWFILE + newFile.getAbsolutePath());

			try {
				newFile.createNewFile();
				Files.copy(myFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
            myFile = newFile;            
            System.out.println(Constants.NOT_ALREADY_IN_FOLDER);
		} else { System.out.println(Constants.ALREADY_IN_FOLDER); }    
	}
}
