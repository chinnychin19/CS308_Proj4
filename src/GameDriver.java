import game.view.GameView;
import javax.swing.JFrame;
import constants.Constants;


public abstract class GameDriver {

    public static void main (String[] args) {
        JFrame frame = new JFrame(Constants.BOGUS_NAME_GAME);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        // frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GameView(Constants.BOGUS_NAME_GAME));
        frame.setResizable(false);// won't accidentally change size
        frame.setVisible(true);
    }
}
