import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import game.view.GameView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import constants.Constants;


public abstract class GameDriver {

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            String[] games = getGameNames(getListOfGames());
            String gameName = getGameNameFromUser(frame, games);
            String[] sessionNames = getSessionNames(getSessionFilesForGame(gameName));
            String session = getSessionNameFromUSer(frame, sessionNames);

            if (session.equals(Constants.SESSION_NEW_SESSION_KEY)) {
                String newSession = getNewSessionName();
                session = createNewSession(gameName, newSession);

            }

            frame.setContentPane(new GameView(gameName, session));
            frame.setResizable(false); // won't accidentally change size
            frame.setVisible(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<File> getListOfGames () throws IOException {
        String path = new File(".").getCanonicalPath() + "/" + Constants.FOLDERPATH_GAMES;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<File> games = new ArrayList<File>();
        for (File file : listOfFiles) {
            if (file.isDirectory()) {
                games.add(file);
            }
        }
        return games;
    }

    private static String[] getGameNames (List<File> gameFiles) {
        String[] gameNames = new String[gameFiles.size()];
        for (int i = 0; i < gameFiles.size(); i++) {
            gameNames[i] = gameFiles.get(i).getName();
        }
        return gameNames;
    }

    private static List<String> getSessionFilesForGame (String game) throws IOException {
        List<String> sessions = new ArrayList<String>();
        sessions.add(Constants.SESSION_NEW_SESSION_KEY);
        String path =
                new File(".").getCanonicalPath() + "/" + Constants.FOLDERPATH_GAMES + "/" + game;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File f : listOfFiles) {
            System.out.println(f.getName());
            if (f.getName().startsWith(Constants.SESSION_SESSION_START_KEY)) {
                sessions.add(f.getName());
            }
        }
        return sessions;
    }

    private static String[] getSessionNames (List<String> sessions) {
        String[] sessionNames = new String[sessions.size()];
        for (int i = 0; i < sessions.size(); i++) {
            sessionNames[i] = sessions.get(i);
        }
        return sessionNames;
    }

    private static String getGameNameFromUser (JFrame frame, String[] games) {
        return (String) JOptionPane.showInputDialog(
                                                    frame,
                                                    Constants.SESSION_SELECT_GAME_TEXT
                                                    ,
                                                    "",
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null,
                                                    games,
                                                    "");
    }

    private static String getSessionNameFromUSer (JFrame frame, String[] sessionNames) {
        return (String) JOptionPane.showInputDialog(
                                                    frame,
                                                    Constants.SESSION_SELECT_SESSION_TEXT
                                                    ,
                                                    "",
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null,
                                                    sessionNames,
                                                    "");
    }

    private static String getNewSessionName () {
        return (String) JOptionPane.showInputDialog(Constants.SESSION_ENTER_NEW_SESSION_TEXT);
    }

    @SuppressWarnings("resource")
	private static String createNewSession (String gameName, String newSession) throws IOException {
        String newFileName =
                Constants.SESSION_SESSION_START_KEY + newSession.trim().replaceAll("\\s+", "-") +
                        Constants.FILETYPE;
        File newFile =
                new File(new File(".").getCanonicalPath() + "/" + Constants.FOLDERPATH_GAMES + "/" +
                         gameName + "/" +
                         newFileName);

        FileChannel src =
                new FileInputStream(new File(".").getCanonicalPath() + "/" +
                                    Constants.FOLDERPATH_GAMES + "/" +
                                    gameName + "/" + Constants.FILENAME_WORLD).getChannel();
        FileChannel dest = new FileOutputStream(newFile).getChannel();
        dest.transferFrom(src, 0, src.size());
        return newFileName;
    }
}
