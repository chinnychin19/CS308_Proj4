package author.panels;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractWizardPanel extends JPanel {

	protected UserInputDimension myDimensions;
	protected JLabel myTextLabel;
	private String myType;
	
	public AbstractWizardPanel(String type){
		// Default dimension size is 1x1
		myDimensions = new UserInputDimension(1, 1);
		myType = type;
	}
	
	public AbstractWizardPanel(String type, int rows, int columns){
		myDimensions = new UserInputDimension(rows, columns);
		myType = type;
	}
	
    public abstract Map<String, String> getUserInput();
    
	public String getMyType(){
		return myType;
	}
	
	public void addRow(){
		myDimensions.addRow();
	}
	
	public void addColumn(){
		myDimensions.addColumn();
	}
	
	public void increaseMatrixSize(){
		addRow();
		addColumn();
	}
	
	protected class UserInputDimension {
		
		private int rowDimension;
		private int columnDimension;
		
		public UserInputDimension(int rows, int columns){
			rowDimension = rows;
			columnDimension = columns;
		}
		
		public void addRow(){ rowDimension += 1; }
		
		public void addColumn(){ columnDimension += 1; }
		
		public int getRowDimension(){ return rowDimension; }
		
		public int getColumnDimension(){ return columnDimension; }
		
	}

}
