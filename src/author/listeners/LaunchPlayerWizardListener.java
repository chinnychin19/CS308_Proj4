package author.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import author.wizard.PlayerWizard;
import author.wizard.WizardBuilder;


public class LaunchPlayerWizardListener implements ActionListener {

    @Override
    public void actionPerformed (ActionEvent e) {
        try {
            new WizardBuilder("player.json");
        }
        catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
