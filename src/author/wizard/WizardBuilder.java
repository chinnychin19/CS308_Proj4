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
import jsoncache.JSONReader;
//import javax.swing.SwingUtilities;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import author.FileChooserSingleton;
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
        aMap.put("text", "WordPanel");
        aMap.put("number", "NumberPanel");
        aMap.put("fileurl", "ImagePanel");
        aMap.put("radio", "RadioButtonsPanel");
        aMap.put("list", "ListPanel");
        aMap.put("check", "CheckBoxPanel");
        aMap.put("matrix", "MatrixPanel");
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

    private void iterateOverJSONObject (JSONObject obj, JPanel currentPanel) { //EXAMPLE: obj = {"monsters": [ {"name":"list_radio_Monster.name"} ] } --> obj = {"name":"list_radio_Monster.name"}
        JSONObject tempObject = (JSONObject) obj;
        Set<?> keySet = tempObject.keySet();// ["monsters"] --> "name"
        System.out.println("Opening: " + keySet);
        for (Object s : keySet) {
            if (tempObject.get(s) instanceof String) { //no --> yes
                System.out.println((String) s + " = string (" + tempObject.get(s) + ")"); 
                try {
                    currentPanel.add(createPanel((String) s, (String) tempObject.get(s)));
                }
                catch (Exception e) {
                    System.out.println("Failed to create '" + (String) s + "'  field, of type '" +
                            (String) tempObject.get(s) + "'.");
                    e.printStackTrace();
                }
            }
            else if (tempObject.get(s) instanceof JSONObject) { //no
                System.out.println((String) s + " = JSONObject");
                handleJSONObject((String) s, (JSONObject) tempObject.get(s), currentPanel);
            }
            else if (tempObject.get(s) instanceof JSONArray) { //yes
                System.out.println((String) s + " = JSONArray");
                handleJSONArray((String) s, (JSONArray) tempObject.get(s), currentPanel);
            }

        }
    }

    private void iterateOverJSONArray (JSONArray arr, JPanel currentPanel, String label) {//[{"name":"list_radio_Monster.name"}], container, ""
        JSONArray tempArray = ((JSONArray) arr);
        for (Object genericContainer : tempArray) {
            if (genericContainer instanceof String) { //no
                System.out.println("string (" + genericContainer + ")");
                try {
                    currentPanel.add(createPanel("value", (String) genericContainer));
                }
                catch (Exception e) {
                    System.out.println("Failed to create 'value' field, of type '" +
                            (String) genericContainer + "'.");
                    e.printStackTrace();
                }
            }
            else if (genericContainer instanceof JSONObject) { //yes
                System.out.println("JSONObject");
                handleJSONObject(label, (JSONObject) genericContainer, currentPanel);
            }
            else if (genericContainer instanceof JSONArray) {
                System.out.println("JSONArray");
                handleJSONArray(label, (JSONArray) genericContainer, currentPanel);
            }

        }
    }

    private void handleJSONObject (String panelLabel, JSONObject object, JPanel currentPanel) { //"", {"name":"list_radio_Monster.name"}, currentPanel
        JPanel container = new ContainerPanel(panelLabel, "object");
        currentPanel.add(container);
        iterateOverJSONObject(object, container);
    }

    private void handleJSONArray (String panelLabel, JSONArray arr, JPanel currentPanel) { //"monsters", [ {"name":"list_radio_Monster.name"} ], currentPanel
        JPanel container = new ContainerPanel(panelLabel, "array");
        currentPanel.add(container);
        iterateOverJSONArray(arr, container, "");
    }

    private Component createPanel (String fieldName, String fieldType)//EXAMPLE: "name", "list_radio_Monster.name"
            throws ClassNotFoundException,
            NoSuchMethodException,
            SecurityException,
            InstantiationException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {
        String[] fields = fieldType.split("_"); //EXAMPLE: fieldType="list_radio_Monster.name"
        String basicFieldType = (fields[0].equals("list")) ? fields[1] : fields[0];
        String limitedFieldType = (fields[0].equals("list")) ? fieldType.substring(5) : fieldType;
        String outputString = "";

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
        if (limitedFieldType.split("_").length > 1 && limitedFieldType.indexOf(":") == -1) { //EXAMPLE: "radio_Monster.name"
            outputString = makePartOfRadioButtonsInputParameter(limitedFieldType);
        }

        Class<?> classToInstantiate =
                Class.forName("author.panels." + KEYWORD_TO_PANEL_TYPE.get(basicFieldType));//For fieldType="list_radio_Monster.name", class is "author.panels.RadioButtonsPanel"
        Constructor<?> ctr = classToInstantiate.getConstructor(String.class);


        Component output = (Component) ctr.newInstance(fieldName + outputString);//For fieldType="list_radio_Monster.name", Component output = (Component) RadioButtonsPanel("name~Bulbasaur.Squirtle.Charmander.Pidgey.")

        if (fields[0].equals("list")) {

        }

        System.out.println(fieldName + outputString);

        return output;


    }

    private String makePartOfRadioButtonsInputParameter (String limitedFieldType) {
        String[] locKeyPair = limitedFieldType.split("_")[1].split("\\."); //FIRST splits to "radio" "Monster.name", THEN splits "Monster.name" to "Monster" "Name"
        JSONArray locationArray = (JSONArray) myCache.getRawJSON().get(locKeyPair[0]); //gets "Monster"'s array out of the cache
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
            majorArray = json.getJSONArray(myCategory);//EXAMPLE: myCategory = "FightingNPC"

            for (Object con : majorArray) { //"con" means "contained by"
                if (con instanceof JSONObject) {
                    iterateOverJSONObject((JSONObject) con, currentPanel);
                }
            }
            FinishPanel finish = new FinishPanel(myCache);
            currentPanel.add(finish);
            finish.init();
        } catch (NoJSONArrayJsonException e) {
            System.out.println("Category of '" + myCategory + "' not found.");
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
        /*  BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line, results = "";
            while( ( line = reader.readLine() ) != null) {
                results += line;
            }
            reader.close();
            return new SmartJsonObject(results);        */
            JSONObject obj = JSONReader.getJSON(filepath);
            return new SmartJsonObject(obj);
    /*  } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();        */
        } catch (NoJSONObjectJsonException e) {
            e.printStackTrace();
            System.out.println("Malformed JSON String");
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
