package author;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import author.listeners.LaunchPlayerWizardListener;


public class WizardSubMenu extends JMenu {

    private final String PLAYER = "Player";
    private final String NPC = "NPC";
    private final String ITEM = "Item";
    private final String MONSTER = "Monster";
    private final String OBSTACLE = "Obstacle";
    private final String BG_TILE = "Background Tile";
    
    public WizardSubMenu (String title) {
        
        super(title);
        
        JMenuItem playerItem = new JMenuItem(PLAYER);
        playerItem.addActionListener(new LaunchPlayerWizardListener());
        this.add(playerItem);
        
        JMenuItem NPCItem = new JMenuItem(NPC);
        this.add(NPCItem);
        
        JMenuItem itemItem = new JMenuItem(ITEM);
        this.add(itemItem);
        
        JMenuItem monsterItem = new JMenuItem(MONSTER);
        this.add(monsterItem);
        
        JMenuItem obstacleItem = new JMenuItem(OBSTACLE);
        this.add(obstacleItem);
        
        JMenuItem bgTileItem = new JMenuItem(BG_TILE);
        this.add(bgTileItem);
    }
    
}
