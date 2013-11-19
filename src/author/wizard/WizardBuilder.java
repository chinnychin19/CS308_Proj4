package author.wizard;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFileChooser;

import author.panels.FinishPanel;


public class WizardBuilder {

    private String myWizardFilePath;
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
    public WizardBuilder () throws ClassNotFoundException, InstantiationException,
                           IllegalAccessException, IllegalArgumentException,
                           InvocationTargetException, NoSuchMethodException, SecurityException,
                           IOException {
        myWizard = new Wizard();
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
    public WizardBuilder (String filePath) throws ClassNotFoundException, InstantiationException,
                                          IllegalAccessException, IllegalArgumentException,
                                          InvocationTargetException, NoSuchMethodException,
                                          SecurityException, IOException {
        myWizard = new Wizard();
        myWizardFilePath = filePath;
        addPanelsFromFile(myWizardFilePath);
        getConstructedWizard();
    }

    /**
     * Uses reflection to instantiate panel classes from given strings
     * in a text file. Adds these panels to the wizard.
     * 
     * @param filePath
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IOException
     */
    public void addPanelsFromFile (String filePath) throws ClassNotFoundException,
                                                   InstantiationException, IllegalAccessException,
                                                   IllegalArgumentException,
                                                   InvocationTargetException,
                                                   NoSuchMethodException, SecurityException,
                                                   IOException {
        // Begin try-catch block!
        try {
            // Create a new reader and a variable in which to store lines.
            BufferedReader reader = new BufferedReader(new FileReader(myWizardFilePath));
            String line = null;
            Class[] args = new Class[0];

            // Iterate through the lines in the file.
            while ((line = reader.readLine()) != null) {
                
                

                /*
                 * This is where it gets tricky. Get the string of the preference
                 * and use reflection to initialize the matching sub-class of that string
                 * which will then handle the value string and load the correct preference
                 * in its constructor.
                 */
                Class<?> classToInstantiate = Class.forName("author.panels." + line);
                Constructor<?> ctr = classToInstantiate.getConstructor();
                myWizard.getMyCardPanel().add((Component) ctr.newInstance());
            }
            FinishPanel finish = new FinishPanel();
            myWizard.getMyCardPanel().add(finish);
            finish.init();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
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
