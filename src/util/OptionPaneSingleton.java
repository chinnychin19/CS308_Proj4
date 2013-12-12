package util;

import javax.swing.JOptionPane;

public class OptionPaneSingleton extends JOptionPane {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7390035204506669294L;
    private static OptionPaneSingleton instance;
    
    private OptionPaneSingleton () {
        super();
    }

    public static synchronized OptionPaneSingleton getInstance ()
    {
        if (instance == null)
            instance = new OptionPaneSingleton();

        return instance;
    }
    
    @SuppressWarnings("static-access")
    public boolean getOk(String message){
        int response = 
                super.showConfirmDialog(null, message, 
                                        "Please confirm", 
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.WARNING_MESSAGE );
        return (response == super.OK_OPTION);
    }

}
