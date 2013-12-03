package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import constants.Constants;
import game.controller.state.option.AbstractOptionState;
import game.controller.state.option.MainOptionState;
import game.model.GameModel;
import game.model.Monster;
import game.model.attack.Attack;
import game.model.battle.Battle;
import game.view.GameView;


public abstract class AbstractBattleMode extends AbstractMode {
    protected Battle myBattle;
    private Graphics myOptionsBuffer;
    private Graphics myHealthBuffer;
    private Graphics myMonsterBuffer;
    private Graphics enemyHealthBuffer;
    private Graphics enemyMonsterBuffer;
    private static final Color BEIGE = new Color(245, 245, 220);

    protected int mySelectedOption;
    protected String[] options = { "ATTACK", "PARTY", "ITEM" };
    protected int mySelectedAttack;

    private AbstractOptionState myOptionState;

    public AbstractBattleMode (GameModel model, GameView view) {
        super(model, view);
        myOptionState = new MainOptionState(this);

    }

    /**
     * Turns off WildBattleMode - removes keyListeners and closes the buffers
     */
    @Override
    public void turnOff () {
        getView().removeKeyListener(this);
        closeBuffers();
    }

    /**
     * Turns on WildBattleMode - adds keyListeners and inits the buffers
     */
    @Override
    public void turnOn () {
        getView().addKeyListener(this);
        initBuffers();
    }

    /**
     * Paints the fighting monsters, their states, and the options on the screen
     * If the user has selected attack, it will paint the available attacks
     */
    @Override
    public void paint () {
        paintMyMonster();
        paintMyHealth();
        paintEnemyMonster();
        paintEnemyHealth();
        myOptionState.paint();
        // if (myState == State.OPTIONS) {
        // paintOptions();
        // }
        // else if (myState == State.ATTACKS) {
        // paintAttacks();
        // } else if (myState == State.PARTY) {
        // paintParty();
        // }
    }

    // TODO: Chinmay should comment
    @Override
    public void act () {
        Input input = getInput();
        List<Attack> attacks =
                myBattle.getPlayerParty().getCurrentMonster().getAllAvailableAttacks();
        myOptionState.act(input);
    }

    public void setOptionState (AbstractOptionState st) {
        myOptionState = st;
    }

    public Battle getBattle () {
        return myBattle;
    }

    private void initBuffers () {
        int opX = 0, opY = Constants.HEIGHT * 2 / 3, opW = Constants.WIDTH, opH =
                Constants.HEIGHT / 3;
        myOptionsBuffer = getGraphics().create(opX, opY, opW, opH);

        int h1X = Constants.WIDTH / 2, h1Y = Constants.HEIGHT / 3, h1W = Constants.WIDTH / 2, h1H =
                Constants.HEIGHT / 3;
        myHealthBuffer = getGraphics().create(h1X, h1Y, h1W, h1H);

        int m1X = 0, m1Y = Constants.HEIGHT / 3, m1W = Constants.WIDTH / 2, m1H =
                Constants.HEIGHT / 3;
        myMonsterBuffer = getGraphics().create(m1X, m1Y, m1W, m1H);

        int h2X = Constants.WIDTH / 2, h2Y = 0, h2W = Constants.WIDTH / 2, h2H =
                Constants.HEIGHT / 3;
        enemyHealthBuffer = getGraphics().create(h2X, h2Y, h2W, h2H);

        int m2X = 0, m2Y = 0, m2W = Constants.WIDTH / 2, m2H = Constants.HEIGHT / 3;
        enemyMonsterBuffer = getGraphics().create(m2X, m2Y, m2W, m2H);
    }

    private void closeBuffers () {
        myOptionsBuffer.dispose();
        myHealthBuffer.dispose();
        myMonsterBuffer.dispose();
        enemyHealthBuffer.dispose();
        enemyMonsterBuffer.dispose();
    }

    private void paintMyHealth () {
        // TODO Auto-generated method stub
        myHealthBuffer.setColor(BEIGE);
        myHealthBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                myOptionsBuffer.getClipBounds().height);
        paintHealthBuffer(myHealthBuffer, myBattle.getPlayerParty().getCurrentMonster());
    }

    private void paintEnemyHealth () {
        // TODO Auto-generated method stub
        enemyHealthBuffer.setColor(BEIGE);
        enemyHealthBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                   myOptionsBuffer.getClipBounds().height);
        paintHealthBuffer(enemyHealthBuffer, myBattle.getEnemyParty().getCurrentMonster());
    }

    private void paintHealthBuffer (Graphics g, Monster m) {
        g.setColor(Color.black);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        String nameStr = String.format("Name: %s", m.getName());
        String hpStr = String.format("HP: %d/%d", m.getCurHP(), m.getMaxHP());
        String lvlStr = String.format("Level: %d", m.getLevel());
        String atkStr = String.format("Attack: %d", m.getAttack());
        String defStr = String.format("Defense: %d", m.getDefense());

        int x1 = 15, x2 = 165;
        int y1 = 30;
        int yInc = 50;

        g.drawString(nameStr, x1, y1 + 0 * yInc);
        g.drawString(hpStr, x1, y1 + 1 * yInc);
        g.drawString(lvlStr, x2, y1 + 1 * yInc);
        g.drawString(atkStr, x1, y1 + 2 * yInc);
        g.drawString(defStr, x2, y1 + 2 * yInc);
    }

    private void paintMyMonster () {
        Monster monster = myBattle.getPlayerParty().getCurrentMonster();
        myMonsterBuffer.drawImage(monster.getImage(), 0, 0,
                                  enemyMonsterBuffer.getClipBounds().width,
                                  enemyMonsterBuffer.getClipBounds().height,
                                  null);
    }

    private void paintEnemyMonster () {
        enemyMonsterBuffer.drawImage(myBattle.getEnemyParty().getCurrentMonster().getImage(), 0, 0,
                                     enemyMonsterBuffer.getClipBounds().width,
                                     enemyMonsterBuffer.getClipBounds().height,
                                     null);
    }
}
