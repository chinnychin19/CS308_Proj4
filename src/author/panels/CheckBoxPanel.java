package author.panels;

import java.awt.AWTEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import author.listeners.CheckBoxListener;

@SuppressWarnings("serial")
public class CheckBoxPanel extends AbstractToggleButtonPanel{

    private JLabel myCheckBoxLabel;
    private JCheckBox myCheckBox;
    private boolean myIsSelected;
        
    public CheckBoxPanel(){
        super("CheckBox", CheckBoxListener.getInstance());
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        myCheckBoxLabel = new JLabel("CheckBox:");
        myCheckBox = new JCheckBox();
        myCheckBox.addItemListener((ItemListener) myEventListener);
        myIsSelected = false;
        
        this.add(myCheckBoxLabel);
        this.add(myCheckBox);
    }
    
    public void updateSelectionState(AWTEvent e){
        myIsSelected = (((ItemEvent) e).getStateChange() == ItemEvent.SELECTED);
        //System.out.println("State is now: " + myIsSelected);
    }
    
    @Override
    public Map<String, String> getUserInput () {
        Map<String, String> map = new HashMap<String, String>();
        map.put(myCheckBoxLabel.toString(), Boolean.toString(myIsSelected));
        return map;
    }
}
