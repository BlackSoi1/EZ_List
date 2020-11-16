package ui;

/*
Represents a to-do list application ui
 */
//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


import javax.swing.*;
import java.awt.*;

import java.io.FileNotFoundException;


public class ToDoApp extends JFrame {

    private AudiovisualComponent ac;
    private SwingComponent sc;
    private JButtonComponent jc;
    private static final int WIDTH = 550;
    private static final int HEIGHT = 700;
    private JFrame mainFrame;
    private JButton button;
    private JLabel label;


    // model code base on JsonSerializationDemo-WorkRoomApp
    // EFFECTS: run the To-Do application
    public ToDoApp() throws FileNotFoundException {
        ac = new AudiovisualComponent();
        sc = new SwingComponent();
        jc = new JButtonComponent();
        mainFrame = new JFrame("EZ List");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        initialUI();
        mainFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initial the User interface
    public void initialUI() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 230, 60));
        panel.setBackground(new Color(255, 218, 185));
        label = new JLabel(" ");
        button = jc.initialCreate(mainFrame);
        sc.setButton(button);
        panel.add(button);
        button = jc.initialShowAllTasks(mainFrame);
        sc.setButton(button);
        panel.add(button);
        button = jc.initialShowCompletedTasks(mainFrame);
        sc.setButton(button);
        panel.add(button);
        button = jc.initialEdit(mainFrame);
        sc.setButton(button);
        panel.add(button);
        button = jc.initialSave(mainFrame);
        sc.setButton(button);
        panel.add(button);
        button = jc.initialLoad(mainFrame);
        sc.setButton(button);
        panel.add(button);
        mainFrame.add(panel);
    }





}

