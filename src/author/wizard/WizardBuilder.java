package author.wizard;

import java.awt.Component;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import jsoncache.JSONReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import constants.Constants;

import util.FileChooserSingleton;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
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
        getConstructedWizard(); //As far as I can tell, this line does nothing, since its return value is not used.
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
        getConstructedWizard(); //As far as I can tell, this line does nothing, since its return value is not used.
    }

    /**
     * Iterates through all values in the key/value pairs within a JSONObject.
     * If the value is a string, passes off key/value pair to createPanel().
     * If the value is another JSONObject, passes pair off to handleJSONObject().
     * If the value is a JSONArray, passes pair off to handleJSONArray
     * @param obj the JSONObject containing one or more key/value pairs
     * @param currentPanel The current panel.
     * @return void
     */
    private void iterateOverJSONObject (JSONObject obj, JPanel currentPanel) { //EXAMPLE: obj = {"monsters": [ {"name":"list_radio_Monster.name"} ] } --> obj = {"name":"list_radio_Monster.name"}
        JSONObject tempObject = (JSONObject) obj;

        Set<?> keySet = tempObject.keySet();
        System.out.println(Constants.OPENING_MESSAGE + keySet);
        for (Object s : keySet) {
            if (tempObject.get(s) instanceof String) {
                System.out.println((String) s + Constants.STRING_STATUS_MESSAGE + tempObject.get(s) + Constants.CLOSE_PARENTHESIS);
                try {
                    currentPanel.add(createPanel((String) s, (String) tempObject.get(s)), " ");
                }
                catch (Exception e) {
                    System.out.println(Constants.FAILED_TO_CREATE_PT1 + (String) s + Constants.FAILED_TO_CREATE_PT2 +
                                       (String) tempObject.get(s) + Constants.FAILED_TO_CREATE_PT3);
                    e.printStackTrace();
                }
            }
            else if (tempObject.get(s) instanceof JSONObject) {
                System.out.println((String) s + Constants.EQUALS_JSONOBJECT);
                if (((JSONObject) tempObject.get(s)).containsKey("name")) {
	                if (((String)((JSONObject) tempObject.get(s)).get("name")).contains("list")) {
	                	for (int i=0;i<5;i++) {
	                		handleJSONObject((String) s, (JSONObject) tempObject.get(s), currentPanel);
	                	}
	                }
                }
                handleJSONObject((String) s, (JSONObject) tempObject.get(s), currentPanel);
            }
            else if (tempObject.get(s) instanceof JSONArray) {
                System.out.println((String) s + Constants.EQUALS_JSONARRAY);
                handleJSONArray((String) s, (JSONArray) tempObject.get(s), currentPanel);
            }

        }
    }

    /**
     * Iterates through all values in a JSONArray.
     * If the value is a string, passes off to createPanel().
     * If the value is another JSONObject, passes off to handleJSONObject().
     * If the value is a JSONArray, passes off to handleJSONArray.
     * @param arr the JSONArray containing one or more values
     * @param currentPanel The current panel.
     * @param label
     * @return void
     */
    private void iterateOverJSONArray (JSONArray arr, JPanel currentPanel, String label) {//EXAMPLE: [{"name":"list_radio_Monster.name"}], container, ""
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
                if (((JSONObject) genericContainer).containsKey("name")) {
                	if (((String)((JSONObject) genericContainer).get("name")).contains("list")) {
                    	for (int i=0;i<5;i++) {
                    		handleJSONObject(label, (JSONObject) genericContainer, currentPanel);
                    	}
                    }
                }
                handleJSONObject(label, (JSONObject) genericContainer, currentPanel);
            }
            else if (genericContainer instanceof JSONArray) {
                System.out.println(Constants.JSONARRAY_STRING);
                handleJSONArray(label, (JSONArray) genericContainer, currentPanel);
            }

        }
    }

    /**
     * Creates a new ContainerPanel (of type "object") and adds it within the 
     * current panel, then passes that new panel and the received JSONObject to 
     * iterateOverJSONObject().
     * @param panelLabel the label to give the new panel
     * @param object the JSONObject
     * @param currentPanel The current panel
     * @return void
     */
    private void handleJSONObject (String panelLabel, JSONObject object, JPanel currentPanel) { //EXAMPLE: "", {"name":"list_radio_Monster.name"}, currentPanel
        JPanel container = new ContainerPanel(panelLabel, Constants.OBJECT_STRING);
        currentPanel.add(container);
        iterateOverJSONObject(object, container);
    }

    /**
     * Creates a new ContainerPanel (of type "array") and adds it within the 
     * current panel, then passes that new panel and the received JSONArray to 
     * iterateOverJSONArray().
     * @param panelLabel the label to give the new panel
     * @param arr the JSONArray
     * @param currentPanel The current panel
     * @return void
     */
    private void handleJSONArray (String panelLabel, JSONArray arr, JPanel currentPanel) { //EXAMPLE: "monsters", [ {"name":"list_radio_Monster.name"} ], currentPanel
        JPanel container = new ContainerPanel(panelLabel, Constants.ARRAY_STRING);
        currentPanel.add(container);
        iterateOverJSONArray(arr, container, Constants.EMPTY_STRING);
    }

    /**
     * Creates and returns a new panel of the indicated type via reflection. In 
     * some cases this means retrieving previously-defined data from the 
     * AuthorCache to populate a list in the new panel. The method figures out 
     * if this is necessary by parsing custom-formatted strings (example: 
     * "list_radio_Monster.name") that were stored as values in the original
     * wizard-defining JSON file.
     * @param fieldName Name of field to be filled/defined by user. 
     * Examples: "name", "power"
     * @param fieldType Type/format of field: word, number, radiobutton, etc. 
     * Corresponds to a subclass of AbstractWizardPanel in author.panels
     * @return Component
     */
    private Component createPanel (String fieldName, String fieldType)
                                                                      throws ClassNotFoundException,
                                                                      NoSuchMethodException,
                                                                      SecurityException,
                                                                      InstantiationException,
                                                                      IllegalAccessException,
                                                                      IllegalArgumentException,
                                                                      InvocationTargetException {
        String[] fields = fieldType.split("_"); //EXAMPLE: fieldType="list_radio_Monster.name"
        String basicFieldType = (fields[0].equals(Constants.LIST_KEYWORD)) ? fields[1] : fields[0];
        String limitedFieldType = (fields[0].equals(Constants.LIST_KEYWORD)) ? fieldType.substring(5) : fieldType;
        String outputString = Constants.EMPTY_STRING;
        if (limitedFieldType.split("_").length > 1 && limitedFieldType.indexOf(":") == -1) { //EXAMPLE: "radio_Monster.name"
            outputString = makePartOfRadioButtonsInputParameter(limitedFieldType);
        }
        
        Class<?> classToInstantiate =
                Class.forName(Constants.AUTHOR_PANELS_PATH + KEYWORD_TO_PANEL_TYPE.get(basicFieldType)); //For fieldType="list_radio_Monster.name", class is "author.panels.RadioButtonsPanel"
        Constructor<?> ctr = classToInstantiate.getConstructor(String.class);
        
        Component output = (Component) ctr.newInstance(fieldName + outputString); //For fieldType="list_radio_Monster.name", Component output = (Component) RadioButtonsPanel("name~Bulbasaur.Squirtle.Charmander.Pidgey.") 
        
        if (fields[0].equals(Constants.LIST_KEYWORD)) {
        	
        }
        
        System.out.println(fieldName + outputString);

        return output;
    }

    /**
     * Retrieves previously-defined values from AuthorCache and generates most 
     * of the (rather idiosyncratic) parameter for constructing a 
     * RadioButtonsPanel. That constructor takes a string of the form 
     * "listLabel~listElement1.listElement2.listElement3.", of arbitrary length;
     * this method returns "~listElement1.listElement2.listElement3."
     * @param limitedFieldType String directing creation of the radio button 
     * list from cache, of format 
     * "radio_PreviouslyDefinedJSONObjectCategory.KeyWithinThatObject"
     * @return String
     */
    private String makePartOfRadioButtonsInputParameter (String limitedFieldType) {
        String[] locKeyPair = limitedFieldType.split("_")[1].split("\\."); //FIRST splits to "radio" "Monster.name", THEN splits "Monster.name" to "Monster" "Name"
        JSONArray locationArray = (JSONArray) myCache.toJSONObject().get(locKeyPair[0]); //gets "Monster"'s array out of the cache
        String outputString = "~";
        for (Object con : locationArray) {
            outputString += (String) ((JSONObject) con).get(locKeyPair[1]) + "."; //End up with something like "~Bulbasaur.Squirtle.Charmander.Pidgey."
        } 
        return outputString;
    }
    
    /**
     * Pulls JSON from a file, directs the builder to iterate over that JSON 
     * (for the purpose of generating the wizard panels for the selected 
     * category), then adds a finish panel.
     * @param filepath The filepath of a .json file.
     * @return void
     */
    public void addPanelsFromFile (String filePath) {

        JPanel currentPanel = myWizard.getCardPanel();

        SmartJsonObject json = getSmartJson(filePath);
        
	try {

		JSONArray majorArray;
		majorArray = json.getJSONArray(myCategory); //EXAMPLE: myCategory = "FightingNPC"

	        for (Object con : majorArray) { //"con" means "contained by"
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

    /**
     * Get JSON from the given file, returns as a SmartJsonObject.
     * @param filepath The filepath of a .json file.
     * @return SmartJsonObject
     */
    private SmartJsonObject getSmartJson (String filepath) {
        try {
            JSONObject obj = JSONReader.getJSON(filepath);
            return new SmartJsonObject(obj);
        } catch (NoJSONObjectJsonException e) {
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
        JFileChooser fileChooser = FileChooserSingleton.getInstance();
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
