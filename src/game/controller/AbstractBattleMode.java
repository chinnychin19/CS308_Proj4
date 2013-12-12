package game.controller;

import util.Sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.ImageIcon;
import constants.Constants;
import game.controller.optionState.AbstractBattleCompleteState;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.AbstractMainOptionState;
import game.controller.state.option.TextOptionState;
import game.model.GameModel;
import game.model.Monster;
import game.model.attack.Attack;
import game.model.battle.Battle;
import game.view.GameView;


public abstract class AbstractBattleMode extends AbstractMode {
    protected Battle myBattle;
    private Graphics myOptionsBuffer;
    private Graphics myPlayerHealthBuffer;
    private Graphics myPlayerMonsterBuffer;
    private Graphics myEnemyHealthBuffer;
    private Graphics myEnemyMonsterBuffer;
    private static final Color BEIGE = new Color(245, 245, 220);
    private boolean myPlayerMonsterIsHit;
    private boolean myEnemyMonsterIsHit;

    protected int mySelectedOption;
    protected int mySelectedAttack;

    private AbstractOptionState myOptionState;

    public AbstractBattleMode (GameModel model, GameView view) {
        super(model, view);
        myOptionState = getAMainOptionState();
        myPlayerMonsterIsHit = false;
    }
    
    public void markPlayerMonsterHit() {
        myPlayerMonsterIsHit = true;
    }
    public void markEnemyMonsterHit() {
        myEnemyMonsterIsHit = true;
    }
    public void removeHitMarkers() {
        myPlayerMonsterIsHit = false;
        myEnemyMonsterIsHit = false;

    }
    
    public abstract AbstractBattleCompleteState getBattleCompleteState(boolean didWin);
    
    public abstract AbstractMainOptionState getAMainOptionState();

    /**
     * Turns off WildBattleMode - removes keyListeners and closes the buffers
     */
    @Override
    public void turnOff () {
        super.turnOff();
        closeBuffers();
    }

    /**
     * Turns on WildBattleMode - adds keyListeners and inits the buffers
     */
    @Override
    public void turnOn () {
        super.turnOn();
        initBuffers();
    }

    /**
     * Paints the fighting monsters, their states, and the options on the screen
     * If the user has selected attack, it will paint the available attacks
     */
    @Override
    public void paint () {
        paintBackground();
        paintMyMonster();
        paintMyHealth();
        paintEnemyMonster();
        paintEnemyHealth();
        myOptionState.paint();
    }
    
    private void paintBackground() {
        getView().getBuffer().fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
    }

    /**
     * This method is called when keyPressed or keyReleased is triggered. It tells the
     * current option state to update itself and take appropriate action. For example, the attack
     * option state applies the selected attack on interaction.
     */
    @Override
    public void act () {
        Input input = getInput();
        myOptionState.act(input);
    }

    public void setOptionState (AbstractOptionState st) {
        myOptionState = st;
    }

    public void pushState (TextOptionState st) {
        st.setNextState(myOptionState);
        myOptionState = st;
    }

    public Battle getBattle () {
        return myBattle;
    }

    private void initBuffers () {
        int opX = 0, opY = Constants.HEIGHT * 2 / 3, opW = Constants.WIDTH, opH =
                Constants.HEIGHT / 3;
        myOptionsBuffer = getGraphics().create(opX, opY, opW, opH);

        int h1X = Constants.WIDTH / 3, h1Y = Constants.HEIGHT / 3, h1W = Constants.WIDTH * 2 / 3, h1H =
                Constants.HEIGHT / 3;
        myPlayerHealthBuffer = getGraphics().create(h1X, h1Y, h1W, h1H);

        int m1X = 0, m1Y = Constants.HEIGHT / 3, m1W = Constants.WIDTH / 3, m1H =
                Constants.HEIGHT / 3;
        myPlayerMonsterBuffer = getGraphics().create(m1X, m1Y, m1W, m1H);

        int h2X = Constants.WIDTH / 3, h2Y = 0, h2W = Constants.WIDTH * 2 / 3, h2H =
                Constants.HEIGHT / 3;
        myEnemyHealthBuffer = getGraphics().create(h2X, h2Y, h2W, h2H);

        int m2X = 0, m2Y = 0, m2W = Constants.WIDTH / 3, m2H = Constants.HEIGHT / 3;
        myEnemyMonsterBuffer = getGraphics().create(m2X, m2Y, m2W, m2H);
    }

    private void closeBuffers () {
        myOptionsBuffer.dispose();
        myPlayerHealthBuffer.dispose();
        myPlayerMonsterBuffer.dispose();
        myEnemyHealthBuffer.dispose();
        myEnemyMonsterBuffer.dispose();
    }

    private void paintMyHealth () {
        paintHealthBuffer(myPlayerHealthBuffer, myBattle.getPlayerParty().getCurrentMonster());
    }

    private void paintEnemyHealth () {
        paintHealthBuffer(myEnemyHealthBuffer, myBattle.getEnemyParty().getCurrentMonster());
    }

    private void paintHealthBuffer (Graphics g, Monster m) {
        g.setColor(BEIGE);
        g.fillRect(0, 0, g.getClipBounds().width,
                                   g.getClipBounds().height);
        g.setColor(Color.black);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));  
        
        String nameStr = String.format("Name: %s", m.getName());
        String statusStr = String.format("Status: %s", m.getStatus().toString());
        String hpStr = String.format("HP: %d/%d", m.getStat(Constants.STAT_CUR_HP), m.getStat(Constants.STAT_MAX_HP));
        String lvlStr = String.format("Level: %d", m.getStat(Constants.JSON_LEVEL));
        String atkStr = String.format("Attack: %d", m.getStat(Constants.STAT_ATTACK));
        String defStr = String.format("Defense: %d", m.getStat(Constants.STAT_DEFENSE));

        int bufferWidth = g.getClipBounds().width, bufferHeight = g.getClipBounds().height;
        
        int x1 = Constants.BUFFER_START_X;
        int x2 = bufferWidth / 2 + Constants.BUFFER_START_X;
        int y1 = Constants.BUFFER_START_Y;
        int yInc = Constants.BUFFER_Y_INC;

        g.drawString(nameStr,   x1,     y1 + 0 * yInc);
        g.drawString(lvlStr,    x2,     y1 + 0 * yInc);
        g.drawString(atkStr,    x1,     y1 + 1 * yInc);
        g.drawString(defStr,    x2,     y1 + 1 * yInc);
        g.drawString(hpStr,     x1,     y1 + 2 * yInc);
        g.drawString(statusStr, x2,     y1 + 2 * yInc);
        paintHealthBar(g, m,    x1,     y1 + 3 * yInc - Constants.HEALTH_BAR_HEIGHT);
    }
    
    private void paintHealthBar(Graphics g, Monster m, int startX, int startY){
        Color origColor = g.getColor();
        g.setColor(Color.red);
        int barWidth = g.getClipBounds().width / 2 - Constants.BORDER_THICKNESS;
        int currentHealth = m.getStat(Constants.STAT_CUR_HP);
        int maxHealth = m.getStat(Constants.STAT_MAX_HP);
        double ratio = ((double)currentHealth)/maxHealth;
        int toFillIn = (int)Math.round(ratio*barWidth);
        g.drawRect(startX, startY, barWidth, Constants.HEALTH_BAR_HEIGHT);
        g.fillRect(startX, startY, toFillIn, Constants.HEALTH_BAR_HEIGHT);
        g.setColor(origColor);
    }

    private void paintAMonster(Graphics monsterBuffer, Monster m, boolean isHit) {
        monsterBuffer.drawImage(m.getImage(), 0, 0,
                                monsterBuffer.getClipBounds().width,
                                monsterBuffer.getClipBounds().height,
                                null);
        
        if (isHit) {
            ImageIcon boom = new ImageIcon("images/boom.png");
            int width = monsterBuffer.getClipBounds().width;
            int startX = width / 5;
            width = width * 3 / 5;
            monsterBuffer.drawImage(boom.getImage(), startX, 0,
                                      width,
                                      monsterBuffer.getClipBounds().height,
                                      null);            
        }
    }

    private void paintMyMonster () {
        Monster monster = myBattle.getPlayerParty().getCurrentMonster();
        paintAMonster(myPlayerMonsterBuffer, monster, myPlayerMonsterIsHit);
    }

    private void paintEnemyMonster () {
        Monster monster = myBattle.getEnemyParty().getCurrentMonster();
        paintAMonster(myEnemyMonsterBuffer, monster, myEnemyMonsterIsHit);
    }
}
