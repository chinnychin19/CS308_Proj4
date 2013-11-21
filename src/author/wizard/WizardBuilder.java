package author.wizard;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import constants.Constants;

import author.listeners.FinishListener;
import author.panels.ContainerPanel;
import author.panels.FinishPanel;

public class WizardBuilder {

	public static final Map<String,String> KEYWORD_TO_PANEL_TYPE;
    static {
    	Map<String,String> aMap = new HashMap<String,String>();
    	aMap.put("text", "WordPanel");
    	aMap.put("number", "NumberPanel");
    	aMap.put("fileurl", "ImagePanel");
    	aMap.put("radio", "RadioButtonsPanel");
    	aMap.put("list", "ListPanel");
    	aMap.put("checkbox", "CheckBoxPanel");
    	aMap.put("matrix","MatrixPanel");
    	KEYWORD_TO_PANEL_TYPE = Collections.unmodifiableMap(aMap);
    }
	
    private String myWizardFilePath;
    private String myCategory;
    private Wizard myWizard;

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
        myWizard = new Wizard(category);
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
        myWizard = new Wizard(category);
        myCategory = category;
        myWizardFilePath = filePath;
        addPanelsFromFile(myWizardFilePath);
        getConstructedWizard();
    }
    
    private void iterateOverJSONObject(JSONObject obj,JPanel currentPanel) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	JSONObject tempObject = ((JSONObject)obj);
		Set keySet = tempObject.keySet();
		System.out.println("Opening: " + keySet);
		for (Object s : keySet) {
			if (tempObject.get(s) instanceof String) {
				System.out.println((String)s + " = string (" + tempObject.get(s) + ")");
				Class<?> classToInstantiate = Class.forName("author.panels." + KEYWORD_TO_PANEL_TYPE.get(tempObject.get(s)) );
	            Constructor<?> ctr = classToInstantiate.getConstructor(String.class);
	            currentPanel.add((Component) ctr.newInstance((String)s));
			} else if (tempObject.get(s) instanceof JSONObject) {
				System.out.println((String)s + " = JSONObject");
				JPanel container = new ContainerPanel((String)s, "object");
				currentPanel.add(container);
				iterateOverJSONObject((JSONObject)tempObject.get(s),container);
			} else if (tempObject.get(s) instanceof JSONArray) {
				System.out.println((String)s +" = JSONArray");
				JPanel container = new ContainerPanel((String)s, "array");
				currentPanel.add(container);
				iterateOverJSONArray((JSONArray)tempObject.get(s),container,"");
			}
			
		}
    }
    
    private void iterateOverJSONArray(JSONArray arr,JPanel currentPanel, String label) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	JSONArray tempArray = ((JSONArray)arr);
    	for (Object con : tempArray) {
    		if (con instanceof String) {
    			System.out.println("string (" + con + ")");
				Class<?> classToInstantiate = Class.forName("author.panels." + KEYWORD_TO_PANEL_TYPE.get(con));
	            Constructor<?> ctr = classToInstantiate.getConstructor(String.class);
	            currentPanel.add((Component) ctr.newInstance((String)con));
			} else if (con instanceof JSONObject) {
				System.out.println("JSONObject");
				JPanel container = new ContainerPanel(label, "object");
				currentPanel.add(container);
				iterateOverJSONObject((JSONObject)con,container);
			} else if (con instanceof JSONArray) {
				System.out.println("JSONArray");
				JPanel container = new ContainerPanel(label, "array");
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
