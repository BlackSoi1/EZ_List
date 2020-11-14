package ui;
//Set the Swing component outlook

import javax.swing.*;
import java.awt.*;

public class SwingComponent {
    //MODIFIES: button
    //EFFECTS: set the back button font, border, color and bounds
    public void setBackButton(JButton button) {
        button.setForeground(Color.white);
        button.setBackground(new Color(160, 82, 45));
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    //MODIFIES: button
    //EFFECTS: set the button font, border, color and bounds
    public void setButton(JButton button) {
        button.setForeground(Color.white);
        button.setBackground(new Color(68, 114, 80));
        button.setFont(new Font("Arial", Font.BOLD, 23));
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    //MODIFIES: label
    //EFFECTS: set the label font, border, color and bounds
    public void setLabel(JLabel label) {
        label = new JLabel();
        label.setForeground(new Color(122, 86, 85));
        label.setFont(new Font("Arial", Font.BOLD, 20));
    }

    //MODIFIES: JTextArea
    //EFFECTS: set the label font, border, color and bounds
    public void setTextArea(JTextArea area) {
        area.setFont(new Font("Times New Roman", Font.BOLD, 16));
        area.setBackground(new Color(222, 184, 135));
    }
}
