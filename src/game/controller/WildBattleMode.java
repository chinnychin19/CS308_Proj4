package game.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import constants.Constants;
import game.model.GameModel;
import game.model.Monster;
import game.model.Player;
import game.model.battle.Battle;
import game.model.battle.WildMonsterParty;
import game.model.battle.WildPlayerParty;
import game.view.GameView;


public class WildBattleMode extends AbstractBattleMode {
    private Battle myBattle;
    private Graphics myOptionsBuffer;
    private Graphics myHealthBuffer;
    private Graphics myMonsterBuffer;
    private Graphics enemyHealthBuffer;
    private Graphics enemyMonsterBuffer;

    public WildBattleMode (GameModel model, GameView view) {
        super(model, view);
    }
    
    public void setEnemyMonster(Monster monster) {
        WildPlayerParty attacker = new WildPlayerParty(getModel().getController(), getModel().getPlayer());
        WildMonsterParty defender = new WildMonsterParty(getModel().getController(), monster);
        myBattle = new Battle(attacker, defender);
        attacker.setBattle(myBattle);
        defender.setBattle(myBattle);
    }

    @Override
    public void turnOff () {
        getView().removeKeyListener(this);
        closeBuffers();
    }

    @Override
    public void turnOn () {
        getView().addKeyListener(this);
        initBuffers();
    }

    private void initBuffers () {
        int opX = 0, opY = Constants.HEIGHT * 2 / 3, opW = Constants.WIDTH, opH =
                Constants.HEIGHT / 3;
        myOptionsBuffer = getGraphics().create(opX, opY, opW, opH);

        int h1X = Constants.WIDTH / 2, h1Y = Constants.HEIGHT / 3,
                h1W = Constants.WIDTH / 2, 
                h1H = Constants.HEIGHT / 3;
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

    @Override
    public void paint () {
        paintMyMonster();
        paintMyHealth();
        paintEnemyMonster();
        paintEnemyHealth();
        paintOptions();
    }

    private void paintMyHealth () {
        // TODO Auto-generated method stub
        myHealthBuffer.setColor(Color.red);
        myHealthBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                myOptionsBuffer.getClipBounds().height);
        paintHealthBuffer(myHealthBuffer, myBattle.getPlayerParty().getCurrentMonster());
    }

    private void paintEnemyHealth () {
        // TODO Auto-generated method stub
        enemyHealthBuffer.setColor(Color.green);
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

    private int selectedOption = 0;
    private String[] options = {"ATTACK", "PARTY", "ITEM"};
    private void paintOptions () {
        // TODO Auto-generated method stub
        myOptionsBuffer.setColor(Color.cyan);
        myOptionsBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                 myOptionsBuffer.getClipBounds().height);
        myOptionsBuffer.setColor(Color.black);
        myOptionsBuffer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        
        int x = 15;
        int y = 30;
        int inc = 50;
        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                myOptionsBuffer.setColor(Color.white);
            }
            myOptionsBuffer.drawString(options[i], x, y + i * inc);
            if (i == selectedOption) {
                myOptionsBuffer.setColor(Color.black);
            }
        }
    }

    @Override
    public void act () {
        boolean[] inputs = getInputs();
        if (inputs[AbstractMode.INDEX_UP] && selectedOption > 0) {
            selectedOption--;
        } else if (inputs[AbstractMode.INDEX_DOWN] && selectedOption < options.length - 1) {
            selectedOption++;
        }
//        if (inputs[AbstractMode.INDEX_INTERACT]) {
//            System.out.println("interacting in wild battle");
//            myBattle.conduct();
//        }
    }
}
