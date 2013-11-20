package author.wizard;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import author.listeners.FinishListener;
import author.panels.ContainerPanel;
import author.panels.FinishPanel;


public class WizardBuilder {

    private String myWizardFilePath;
    private String myCategory;
    private Wizard myWizard;
    Map<String,String> wordToPanel = new HashMap<String,String>();

    /**
     * Default constructor. Allows the user to retrieve a wizard file from
     * a file dialog.
     * 
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IOException
     */
    public WizardBuilder (String category) throws ClassNotFoundException, InstantiationException,
                           IllegalAccessException, IllegalArgumentException,
                           InvocationTargetException, NoSuchMethodException, SecurityException,
                           IOException {
        myWizard = new Wizard("");
        wordToPanel.put("text", "WordPanel");
    	wordToPanel.put("number", "NumberPanel");
    	wordToPanel.put("fileurl", "ImagePanel");
        myCategory = category;
        myWizardFilePath = getFilePath();
        addPanelsFromFile(myWizardFilePath);
        getConstructedWizard();
    }

    /**
     * Second constructor. Allows the user to pass in the file path of
     * the file from which the wizard will be constructed.
     * 
     * @param filePath
     * @throws IOException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public WizardBuilder (String category, String filePath) throws ClassNotFoundException, InstantiationException,
                                          IllegalAccessException, IllegalArgumentException,
                                          InvocationTargetException, NoSuchMethodException,
                                          SecurityException, IOException {
        myWizard = new Wizard("");
        wordToPanel.put("text", "WordPanel");
    	wordToPanel.put("number", "NumberPanel");
    	wordToPanel.put("fileurl", "ImagePanel");
        myCategory = category;
        myWizardFilePath = filePath;
        addPanelsFromFile(myWizardFilePath);
        getConstructedWizard();
    }
    
    private void iterateOverJSONObject(JSONObject obj,JPanel currentPanel) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	JSONObject tempObject = ((JSONObject)obj);
		Set keySet = tempObject.keySet();
		System.out.println(keySet);
		for (Object s : keySet) {
			if (tempObject.get(s) instanceof String) {
				System.out.println("it's a string!");
				Class<?> classToInstantiate = Class.forName("author.panels." + wordToPanel.get(tempObject.get(s)) );
	            Constructor<?> ctr = classToInstantiate.getConstructor(String.class);
	            currentPanel.add((Component) ctr.newInstance((String)s));
			} else if (tempObject.get(s) instanceof JSONObject) {
				System.out.println("it's a JSONObject!");
				JPanel container = new ContainerPanel((String)s);
				currentPanel.add(container);
				iterateOverJSONObject((JSONObject)tempObject.get(s),container);
			} else if (tempObject.get(s) instanceof JSONArray) {
				System.out.println("it's a JSONArray!");
				JPanel container = new ContainerPanel((String)s);
				currentPanel.add(container);
				iterateOverJSONArray((JSONArray)tempObject.get(s),container,"");
			}
			
		}
    }
    
    private void iterateOverJSONArray(JSONArray arr,JPanel currentPanel, String label) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	JSONArray tempArray = ((JSONArray)arr);
    	for (Object con : tempArray) {
    		if (con instanceof String) {
				System.out.println("it's a string!");
				Class<?> classToInstantiate = Class.forName("author.panels." + wordToPanel.get(con));
	            Constructor<?> ctr = classToInstantiate.getConstructor(String.class);
	            currentPanel.add((Component) ctr.newInstance((String)con));
			} else if (con instanceof JSONObject) {
				System.out.println("it's a JSONObject!");
				JPanel container = new ContainerPanel(label);
				currentPanel.add(container);
				iterateOverJSONObject((JSONObject)con,container);
			} else if (con instanceof JSONArray) {
				System.out.println("it's a JSONArray!");
				JPanel container = new ContainerPanel(label);
				currentPanel.add(container);
				iterateOverJSONArray((JSONArray)con,container,"");
			}
			
    	}
    }
    
    public void addPanelsFromFile(String filePath) {
    	
    	JPanel currentPanel = myWizard.getCardPanel();
    	
    	JSONObject json = getJSON(filePath);
    	JSONArray majorArray = (JSONArray)json.get(myCategory);
    	
    	for (Object con : majorArray) {
    		if (con instanceof JSONObject) {
    			try {
					iterateOverJSONObject((JSONObject)con,currentPanel);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
    	FinishPanel finish = new FinishPanel();
        currentPanel.add(finish);
        finish.init();
    }

	private JSONObject getJSON(String filepath) {
        JSONObject json;
        JSONParser parser = new JSONParser();
        try {
            json = (JSONObject) parser.parse(new FileReader(filepath));
            return json;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Get a file path from a file chooser dialog.
     * 
     * @return
     */
    public String getFilePath () {
        // Create a new file chooser.
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);
        String path = null;

        // If a file is approved, get the name.
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            path = f.getAbsolutePath();
        }

        return path;

    }

    /**
     * Gets the constructed wizard.
     * 
     * @return Wizard
     */
    public Wizard getConstructedWizard () {
        return myWizard;
    }

}
