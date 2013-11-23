package game.controller;

import java.awt.Color;
import java.awt.Graphics;
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
    private Monster enemyMonster;
    private Graphics myOptionsBuffer;
    private Graphics myHealthBuffer;
    private Graphics myMonsterBuffer;
    private Graphics enemyHealthBuffer;
    private Graphics enemyMonsterBuffer;
    private static final Color BEIGE = new Color(245, 245, 220);
    
    public WildBattleMode (GameModel model, GameView view) {
        super(model, view);
        enemyMonster = null; //set through setMonster()
    }
    
    public void setEnemyMonster(Monster monster) {
        enemyMonster = monster;
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

    private void paintOptions () {
        // TODO Auto-generated method stub
        myOptionsBuffer.setColor(BEIGE);
        myOptionsBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                 myOptionsBuffer.getClipBounds().height);
    }

    private void paintMyHealth () {
        // TODO Auto-generated method stub
        myHealthBuffer.setColor(BEIGE);
        myHealthBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                myOptionsBuffer.getClipBounds().height);
    }

    private void paintEnemyHealth () {
        // TODO Auto-generated method stub
        enemyHealthBuffer.setColor(BEIGE);
        enemyHealthBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                   myOptionsBuffer.getClipBounds().height);
    }

    private void paintMyMonster () {
        Monster monster = myBattle.getPlayerParty().getCurrentMonster();
        myMonsterBuffer.drawImage(monster.getImage(), 0, 0, 
                                     enemyMonsterBuffer.getClipBounds().width, 
                                     enemyMonsterBuffer.getClipBounds().height, 
                                     null);
    }

    private void paintEnemyMonster () {
        enemyMonsterBuffer.drawImage(enemyMonster.getImage(), 0, 0, 
                                     enemyMonsterBuffer.getClipBounds().width, 
                                     enemyMonsterBuffer.getClipBounds().height, 
                                     null);
    }

    @Override
    public void act () {
        boolean[] inputs = getInputs();
        if (inputs[AbstractMode.INDEX_INTERACT]) {
            System.out.println("interacting in wild battle");
            myBattle.conduct();
        }
    }
}
