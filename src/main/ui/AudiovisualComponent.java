package ui;
// Add the audiovisual Component to the given panel

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;

public class AudiovisualComponent {
    private static final String SUCCESS_SOUND = "./resources/Success.wav";
    private static final String ERROR_SOUND = "./resources/Error.wav";
    private static final String CLICK_SOUND = "./resources/Click.wav";
    private static final String WRONG_IMAGE = "./resources/wrong.png";
    private static final String RIGHT_IMAGE = "./resources/right.jpg";
    private JLabel pictureLabel;
    private Icon img;


    //EFFECTS: play click sound
    public void playClick() {
        try {
            FileInputStream audioFile = new FileInputStream(CLICK_SOUND);
            AudioStream as = new AudioStream(audioFile);
            AudioPlayer.player.start(as);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //EFFECTS: play success sound
    public void playSuccess() {
        try {
            FileInputStream audioFile = new FileInputStream(SUCCESS_SOUND);
            AudioStream as = new AudioStream(audioFile);
            AudioPlayer.player.start(as);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //EFFECTS: play error sound
    public void playError() {
        try {
            FileInputStream audioFile = new FileInputStream(ERROR_SOUND);
            AudioStream as = new AudioStream(audioFile);
            AudioPlayer.player.start(as);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //MODIFIES: panel
    //EFFECTS: add wrong icon to the given panel
    public void addWrongIcon(JPanel panel) {
        if (pictureLabel != null) {
            pictureLabel.setVisible(false);
        }
        img = new ImageIcon(WRONG_IMAGE);
        pictureLabel = new JLabel();
        pictureLabel.setIcon(img);
        panel.add(pictureLabel);
        panel.repaint();
    }

    //MODIFIES: panel
    //EFFECTS: add right icon to the given panel
    public void addRightIcon(JPanel panel) {
        if (pictureLabel != null) {
            pictureLabel.setVisible(false);
        }
        img = new ImageIcon(RIGHT_IMAGE);
        pictureLabel = new JLabel();
        pictureLabel.setIcon(img);
        panel.add(pictureLabel);
        panel.repaint();
    }
}
