package author.panels;

import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckBoxPanel extends AbstractWizardPanel {

    private JLabel myCheckBoxLabel;
    private JCheckBox myCheckBox;
    private boolean myIsSelected;
    
    public void itemStateChanged(ItemEvent e) {
        if (this == e.getItemSelectable()){
            myIsSelected = (e.getStateChange() == ItemEvent.SELECTED);
        }
    }
    
    public void updateSelectionState(ItemEvent e){
        myIsSelected = (e.getStateChange() == ItemEvent.SELECTED);
    }
    
    @Override
    public Map<String, String> getUserInput () {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<String, String>();
        map.put(myCheckBoxLabel.toString(), Boolean.toString(myIsSelected));
        return map;
    }

}
