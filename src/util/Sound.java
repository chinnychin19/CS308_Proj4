package util;
import game.controller.GameController;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


public class Sound {
    private final static int BUFFER_SIZE = 12800000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private Thread myThread;
    private GameController myController;

    public Sound(String filename, GameController controller) {
        myController = controller;
        try {
            soundFile = new File(filename);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void setVolume(boolean muted) {
        FloatControl volume = (FloatControl) sourceLine.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(muted ? 0 : volume.getMinimum()); // 0 is default value
    }
    
    private Thread getALoopingThread() {
        return new Thread(){
            @Override
            public void run() {
                loopForever();
            }
        };
    }
    
    /**
     * 
     * @param filename the name of the file that is going to be played
     * 
     */
    private void playSound () {
        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }
    
    private void loopForever() {
        while (true) {
            playSound();
        }
    }
    
    public void start() {
        myThread = getALoopingThread();
        myThread.start();
        setVolume(myController.isVolumeOn());
        
    }
    
    public void stop() {
        if (myThread != null) {
            myThread.stop();
        }
    }
}
