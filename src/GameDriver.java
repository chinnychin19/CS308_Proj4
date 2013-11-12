import game.view.GameView;
import javax.swing.JFrame;
import constants.Constants;


public abstract class GameDriver {

    public static void main (String[] args) {
        String nameOfGame = "bogusNameOfGame";
        JFrame frame = new JFrame(nameOfGame);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
//        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GameView(nameOfGame));
        frame.setResizable(false);// won't accidentally change size
        frame.setVisible(true);
    }
}
