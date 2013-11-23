package game.controller;

import java.awt.Color;
import java.awt.Graphics;
import constants.Constants;
import game.model.GameModel;
import game.model.Monster;
import game.model.Player;
import game.view.GameView;


public class WildBattleMode extends AbstractBattleMode {

    private Monster myMonster;
    private Graphics myOptionsBuffer;
    private Graphics myHealthBuffer;
    private Graphics myMonsterBuffer;
    private Graphics enemyHealthBuffer;
    private Graphics enemyMonsterBuffer;

    public WildBattleMode (GameModel model, GameView view) {
        super(model, view);
        myMonster = null; //set through setMonster()
    }
    
    public void setMonster(Monster m) {
        myMonster = m;
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
        myOptionsBuffer.setColor(Color.cyan);
        myOptionsBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                 myOptionsBuffer.getClipBounds().height);
    }

    private void paintMyHealth () {
        // TODO Auto-generated method stub
        myHealthBuffer.setColor(Color.red);
        myHealthBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                myOptionsBuffer.getClipBounds().height);
    }

    private void paintEnemyHealth () {
        // TODO Auto-generated method stub
        enemyHealthBuffer.setColor(Color.green);
        enemyHealthBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                   myOptionsBuffer.getClipBounds().height);
    }

    private void paintMyMonster () {
        // TODO Auto-generated method stub
        myMonsterBuffer.setColor(Color.pink);
        myMonsterBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                 myOptionsBuffer.getClipBounds().height);
    }

    private void paintEnemyMonster () {
        // TODO Auto-generated method stub
        enemyMonsterBuffer.setColor(Color.white);
        enemyMonsterBuffer.fillRect(0, 0, myOptionsBuffer.getClipBounds().width,
                                    myOptionsBuffer.getClipBounds().height);

    }

    @Override
    public void act () {
        // TODO Auto-generated method stub
        if (Math.random() < .1) {
            getModel().getController().setWanderingMode();
        }
    }
}
