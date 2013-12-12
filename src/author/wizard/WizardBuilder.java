package author.wizard;

import java.awt.Component;
import java.io.BufferedReader;
//import java.io.BufferedReader;
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
//import javax.swing.SwingUtilities;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import constants.Constants;

import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
//import constants.Constants;
//import author.listeners.FinishListener;
import author.model.AuthoringCache;
import author.panels.ContainerPanel;
import author.panels.FinishPanel;

/**
 * This class provides methods to dynamically 
 * generate a Wizard based on data given in the
 * JSON template provided by the user.
 * 
 * @author Michael Marion and Robert Ansel
 *
 */
public class WizardBuilder {

    public static final Map<String, String> KEYWORD_TO_PANEL_TYPE;
    static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put(Constants.TEXT_KEYWORD, Constants.WORD_PANEL_CLASS);
        aMap.put(Constants.NUMBER_KEYWORD, Constants.NUMBER_PANEL_CLASS);
        aMap.put(Constants.FILE_URL_KEYWORD, Constants.IMAGE_PANEL_CLASS);
        aMap.put(Constants.RADIO_KEYWORD, Constants.RADIOBUTTON_PANEL_CLASS);
        aMap.put(Constants.LIST_KEYWORD, Constants.LIST_PANEL_CLASS);
        aMap.put(Constants.CHECKBOX_KEYWORD, Constants.CHECKBOX_PANEL_CLASS);
        aMap.put(Constants.MATRIX_KEYWORD, Constants.MATRIX_PANEL_CLASS);
        KEYWORD_TO_PANEL_TYPE = Collections.unmodifiableMap(aMap);
    }

    private String myWizardFilePath;
    private String myCategory;
    private Wizard myWizard;
    private AuthoringCache myCache;

    /**
     * Default constructor. Allows the user to retrieve a wizard file from
     * a file dialog.
     * 
     */
    public WizardBuilder (String category, AuthoringCache cache) {
        myWizard = new Wizard(category);
        myCategory = category;
        myWizardFilePath = getFilePath();
        myCache = cache;
        addPanelsFromFile(myWizardFilePath);
        getConstructedWizard();
    }

    /**
     * Second constructor. Allows the user to pass in the file path of
     * the file from which the wizard will be constructed.
     * 
     */
    public WizardBuilder (String category, String filePath, AuthoringCache cache) {
        myWizard = new Wizard(category);
        myCategory = category;
        myWizardFilePath = filePath;
        myCache = cache;
        addPanelsFromFile(myWizardFilePath);
        getConstructedWizard();
    }

    private void iterateOverJSONObject (JSONObject obj, JPanel currentPanel) {
        JSONObject tempObject = (JSONObject) obj;
        Set<?> keySet = tempObject.keySet();
        System.out.println(Constants.OPENING_MESSAGE + keySet);
        for (Object s : keySet) {
            if (tempObject.get(s) instanceof String) {
                System.out.println((String) s + Constants.STRING_STATUS_MESSAGE + tempObject.get(s) + Constants.CLOSE_PARENTHESIS);
                try {
                    currentPanel.add(createPanel((String) s, (String) tempObject.get(s)));
                }
                catch (Exception e) {
                    System.out.println(Constants.FAILED_TO_CREATE_PT1 + (String) s + Constants.FAILED_TO_CREATE_PT2 +
                                       (String) tempObject.get(s) + Constants.FAILED_TO_CREATE_PT3);
                    e.printStackTrace();
                }
            }
            else if (tempObject.get(s) instanceof JSONObject) {
                System.out.println((String) s + Constants.EQUALS_JSONOBJECT);
                handleJSONObject((String) s, (JSONObject) tempObject.get(s), currentPanel);
            }
            else if (tempObject.get(s) instanceof JSONArray) {
                System.out.println((String) s + Constants.EQUALS_JSONARRAY);
                handleJSONArray((String) s, (JSONArray) tempObject.get(s), currentPanel);
            }

        }
    }

    private void iterateOverJSONArray (JSONArray arr, JPanel currentPanel, String label) {
        JSONArray tempArray = ((JSONArray) arr);
        for (Object genericContainer : tempArray) {
            if (genericContainer instanceof String) {
                System.out.println(Constants.STRING_OPEN_PARENTHESIS + genericContainer + Constants.CLOSE_PARENTHESIS);
                try {
                    currentPanel.add(createPanel("value", (String) genericContainer));
                }
                catch (Exception e) {
                    System.out.println(Constants.FAILED_TO_CREATE_VALUE +
                                       (String) genericContainer + Constants.FAILED_TO_CREATE_PT3);
                    e.printStackTrace();
                }
            }
            else if (genericContainer instanceof JSONObject) {
                System.out.println(Constants.JSONOBJECT_STRING);
                handleJSONObject(label, (JSONObject) genericContainer, currentPanel);
            }
            else if (genericContainer instanceof JSONArray) {
                System.out.println(Constants.JSONARRAY_STRING);
                handleJSONArray(label, (JSONArray) genericContainer, currentPanel);
            }

        }
    }

    private void handleJSONObject (String panelLabel, JSONObject object, JPanel currentPanel) {
        JPanel container = new ContainerPanel(panelLabel, Constants.OBJECT_STRING);
        currentPanel.add(container);
        iterateOverJSONObject(object, container);
    }

    private void handleJSONArray (String panelLabel, JSONArray arr, JPanel currentPanel) {
        JPanel container = new ContainerPanel(panelLabel, Constants.ARRAY_STRING);
        currentPanel.add(container);
        iterateOverJSONArray(arr, container, Constants.EMPTY_STRING);
    }

    private Component createPanel (String fieldName, String fieldType)
                                                                      throws ClassNotFoundException,
                                                                      NoSuchMethodException,
                                                                      SecurityException,
                                                                      InstantiationException,
                                                                      IllegalAccessException,
                                                                      IllegalArgumentException,
                                                                      InvocationTargetException {
        String[] fields = fieldType.split("_");
        String basicFieldType = (fields[0].equals(Constants.LIST_KEYWORD)) ? fields[1] : fields[0];
        String limitedFieldType = (fields[0].equals(Constants.LIST_KEYWORD)) ? fieldType.substring(5) : fieldType;
        String outputString = Constants.EMPTY_STRING;

        /**
         * We need this to be changed because it doens't work on a Mac
         * 
         * The parsing with the filepath strings isn't working.
         * 
         * Java has built in classes for building filepaths and file locations
         * 		- We should use those so we don't get any bugs.
         * 
         * We shouldn't be parsing JSON in this class.
         * 		- Should try to use util.jsonwrapper
         * 		- Or use native Java methods (?)
         */
        if (limitedFieldType.split("_").length > 1 && limitedFieldType.indexOf(":") == -1) {
            String[] locKeyPair = limitedFieldType.split("_")[1].split("\\.");
            JSONArray locationArray = (JSONArray) myCache.getRawJSON().get(locKeyPair[0]);
            outputString = "~";
            for (Object con : locationArray) {
                outputString += (String) ((JSONObject) con).get(locKeyPair[1]) + ".";
            }
        }

        Class<?> classToInstantiate =
                Class.forName(Constants.AUTHOR_PANELS_PATH + KEYWORD_TO_PANEL_TYPE.get(basicFieldType));
        Constructor<?> ctr = classToInstantiate.getConstructor(String.class);

        
        Component output = (Component) ctr.newInstance(fieldName + outputString); 
        
        if (fields[0].equals(Constants.LIST_KEYWORD)) {
        	
        }
        
        System.out.println(fieldName + outputString);
        
        return output;
        

    }

    public void addPanelsFromFile (String filePath) {

        JPanel currentPanel = myWizard.getCardPanel();

        
        SmartJsonObject json = getJSON(filePath);
        
		try {

			JSONArray majorArray;
			majorArray = json.getJSONArray(myCategory);

	        for (Object con : majorArray) {
	            if (con instanceof JSONObject) {
	                iterateOverJSONObject((JSONObject) con, currentPanel);
	            }
	        }
	        FinishPanel finish = new FinishPanel(myCache);
	        currentPanel.add(finish);
	        finish.init();
		} catch (NoJSONArrayJsonException e) {
			System.out.println(Constants.CATEGORY_NOT_FOUND_MESSAGE + myCategory + Constants.NOT_FOUND_MESSAGE);
			e.printStackTrace();
		}
    }

    private SmartJsonObject getJSON (String filepath) {
        try {
	        BufferedReader reader = new BufferedReader(new FileReader(filepath));
	        String line, results = Constants.EMPTY_STRING;
	        while( ( line = reader.readLine() ) != null) {
	            results += line;
	        }
	        reader.close();
	        return new SmartJsonObject(results);
        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_NOT_FOUND);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoJSONObjectJsonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(Constants.MALFORMED_JSON_MESSAGE);
		}
        return null;
    }

    /**
     * Get a file path from a file chooser dialog.
     * 
     * @return file path of selected file.
     */
    public String getFilePath () {
        // Create a new file chooser.
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);
        
        String path = null;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
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
