import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ProjectDriver extends JFrame {
    
	private static final long serialVersionUID = 4899616470290834243L;
	
	private JPanel myJPanel;
    private JButton gameDriverButton;
    private JButton authorDriverButton;
    private String AUTHOR_DRIVER_STRING = "Create a world";
    private String GAME_DRIVER_STRING = "Play a game";

    public ProjectDriver() {
       myJPanel = new JPanel();
       
       gameDriverButton = new JButton(GAME_DRIVER_STRING);
       gameDriverButton.addActionListener(new CreateGameViewListener());
       
       authorDriverButton = new JButton(AUTHOR_DRIVER_STRING);
       authorDriverButton.addActionListener(new CreateAuthorViewListener());
       
       myJPanel.add(gameDriverButton);
       myJPanel.add(authorDriverButton);
       
       this.add(myJPanel);
       
       pack();
       this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new ProjectDriver();
    }
    
}
