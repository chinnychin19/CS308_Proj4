import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import game.controller.Input;
import game.view.GameView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import constants.Constants;


public abstract class GameDriver {

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        // frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            String[] games = getGameNames(getListOfGames());
            String s = (String) JOptionPane.showInputDialog(
                                                            frame,
                                                            "Please Select A Game to Play:\n"
                                                            ,
                                                            "",
                                                            JOptionPane.PLAIN_MESSAGE,
                                                            null,
                                                            games,
                                                            "ham");
            frame.setContentPane(new GameView(s));
            // frame.setContentPane(new GameView(Constants.BOGUS_NAME_GAME));
            frame.setResizable(false);// won't accidentally change size
            frame.setVisible(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<File> getListOfGames () throws IOException {
        String path = new File(".").getCanonicalPath() + "/games";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<File> games = new ArrayList<File>();
        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                System.out.println("Directory " + file.getName());
                games.add(file);
            }
        }
        return games;
    }
    
    private static String[] getGameNames(List<File> gameFiles){
        String[] gameNames = new String[gameFiles.size()];
        for (int i = 0; i < gameFiles.size(); i++) {
            gameNames[i] = gameFiles.get(i).getName();
        }
        return gameNames;
    }
}
