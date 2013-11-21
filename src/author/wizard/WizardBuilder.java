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
import org.json.simple.parser.ParseException;

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
    	aMap.put("check", "CheckBoxPanel");
    	aMap.put("matrix","MatrixPanel");
    	KEYWORD_TO_PANEL_TYPE = Collections.unmodifiableMap(aMap);
    }
	
    private String myWizardFilePath;
    private String myCategory;
    private Wizard myWizard;
    private JSONObject myJSONCache;

    /**
     * Default constructor. Allows the user to retrieve a wizard file from
     * a file dialog.
     * 
     */
    public WizardBuilder (String category) {
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
     */
    public WizardBuilder (String category, String filePath) {
        myWizard = new Wizard(category);
        myCategory = category;
        myWizardFilePath = filePath;
        addPanelsFromFile(myWizardFilePath);
        getConstructedWizard();
    }
    
    private void iterateOverJSONObject(JSONObject obj,JPanel currentPanel) {
    	JSONObject tempObject = (JSONObject)obj;
		Set keySet = tempObject.keySet();
		System.out.println("Opening: " + keySet);
		for (Object s : keySet) {
			if (tempObject.get(s) instanceof String) {
				System.out.println((String)s + " = string (" + tempObject.get(s) + ")");
	            try {
					currentPanel.add( createPanel( (String) s, (String) tempObject.get(s) ) );
				} catch (Exception e) {
					System.out.println("Failed to create '" + (String)s +"'  field, of type '" + (String) tempObject.get(s) + "'.");
					e.printStackTrace();
				}
			} else if (tempObject.get(s) instanceof JSONObject) {
				System.out.println((String)s + " = JSONObject");
				handleJSONObject((String)s, (JSONObject)tempObject.get(s), currentPanel);
			} else if (tempObject.get(s) instanceof JSONArray) {
				System.out.println((String)s +" = JSONArray");
				handleJSONArray((String)s, (JSONArray) tempObject.get(s), currentPanel);
			}
			
		}
    }
    
    private void iterateOverJSONArray(JSONArray arr, JPanel currentPanel, String label) {
    	JSONArray tempArray = ((JSONArray)arr);
    	for (Object genericContainer : tempArray) {
    		if (genericContainer instanceof String) {
    			System.out.println("string (" + genericContainer + ")");
	            try {
					currentPanel.add( createPanel( "value", (String) genericContainer ) );
				} catch (Exception e) {
					System.out.println("Failed to create 'value' field, of type '" + (String) genericContainer + "'.");
					e.printStackTrace();
				}
			} else if (genericContainer instanceof JSONObject) {
				System.out.println("JSONObject");
				handleJSONObject(label, (JSONObject) genericContainer, currentPanel);
			} else if (genericContainer instanceof JSONArray) {
				System.out.println("JSONArray");
				handleJSONArray(label, (JSONArray) genericContainer, currentPanel);
			}
			
    	}
    }
    
    private void handleJSONObject(String panelLabel, JSONObject object, JPanel currentPanel) {
    	JPanel container = new ContainerPanel(panelLabel, "object");
		currentPanel.add(container);
		iterateOverJSONObject(object,container);
    }
    
    private void handleJSONArray(String panelLabel, JSONArray arr, JPanel currentPanel) {
		JPanel container = new ContainerPanel(panelLabel, "array");
		currentPanel.add(container);
		iterateOverJSONArray(arr,container,"");
    }
    
    private Component createPanel(String fieldName, String fieldType) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	String[] fields = fieldType.split("_");
    	String basicFieldType = (fields[0].equals("list")) ? fields[1] : fields[0];
    	String limitedFieldType = (fields[0].equals("list")) ? fieldType.substring(5) : fieldType;
    	String outputString = "";
    	if (limitedFieldType.split("_").length > 1 && limitedFieldType.indexOf(":") == -1) {
    		String[] locKeyPair = limitedFieldType.split("_")[1].split("\\.");
    		JSONArray locationArray = (JSONArray)myJSONCache.get(locKeyPair[0]);
    		outputString = "~";
    		for (Object con : locationArray) {
    			outputString += (String)((JSONObject)con).get(locKeyPair[1])+".";
    		}
    	}
    	
		Class<?> classToInstantiate = Class.forName("author.panels." + KEYWORD_TO_PANEL_TYPE.get(basicFieldType));
        Constructor<?> ctr = classToInstantiate.getConstructor(String.class);
        return (Component) ctr.newInstance(fieldName+outputString);
    }
    
    public void addPanelsFromFile(String filePath) {
    	
    	JPanel currentPanel = myWizard.getCardPanel();
    	
    	JSONObject json = getJSON(filePath);
    	JSONArray majorArray = (JSONArray)json.get(myCategory);
    	
    	for (Object con : majorArray) {
    		if (con instanceof JSONObject) {
    			iterateOverJSONObject((JSONObject)con,currentPanel);
    		}
    	}
    	FinishPanel finish = new FinishPanel();
        currentPanel.add(finish);
        finish.init();
    }

	private JSONObject getJSON(String filepath) {
        JSONObject json;
        JSONObject json2;
        JSONParser parser = new JSONParser();
            try {
				json = (JSONObject) parser.parse(new FileReader(filepath));
				json2 = (JSONObject) parser.parse(new FileReader(".\\games\\bogusNameOfGame\\definition.json"));
				myJSONCache = json2;
				return json;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found. Please try again.");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
            

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
