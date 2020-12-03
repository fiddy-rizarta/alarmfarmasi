package program;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author bournot
 */
public class Music {
    private String filename;
    private static Player player;
    Thread playMusic;
    
    public Music(String filename) {
        this.filename = filename;
    }

    // play the MP3 file to the sound card
    public void play() {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (FileNotFoundException | JavaLayerException e) {
            JOptionPane.showMessageDialog(null, "Problem playing file " + filename +" Error : "+e,"Error Music",JOptionPane.ERROR_MESSAGE);        
        }
    }

    public void start() {
        play();
        playMusic = new Thread(new PlayMusic());
        playMusic.start();
    }

    public void stop() {
        close();
        playMusic = null;
    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

    class PlayMusic implements Runnable {

        @Override
        public void run() {
            try {
                player.play();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e,"Error Run Music",JOptionPane.ERROR_MESSAGE);  
            }
        }
    }
}
