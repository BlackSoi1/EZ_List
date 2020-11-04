package ui;

/*
Represents a to-do list application ui
 */
//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import exception.ListSizeZeroException;
import exception.NotInTheListException;
import exception.OutOfRangeException;
import model.Tasks;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ToDoApp extends JFrame {

    private static final String JSON_STORE = "./data/myFile.txt";
    private static final String SUCCESS_SOUND = "./resources/Success.wav";
    private static final String ERROR_SOUND = "./resources/Error.wav";
    private static final String CLICK_SOUND = "./resources/Click.wav";
    private static final String WRONG_IMAGE = "./resources/wrong.png";
    private static final String RIGHT_IMAGE = "./resources/right.jpg";
    private static final String BACKGROUND_IMAGE = "./resources/background.jpg";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 700;
    private ToDoList toDoList;
    private Tasks task;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame mainFrame;
    private JLabel label;
    private JButton button;
    private JLabel pictureLabel;
    private ImageIcon img;
    private JLabel msg;

    // model code base on JsonSerializationDemo-WorkRoomApp
    // EFFECTS: run the To-Do application
    public ToDoApp() throws FileNotFoundException {
        mainFrame = new JFrame("EZ List");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        toDoList = new ToDoList("EZ List");
        pictureLabel = new JLabel();
        mainFrame.setLocationRelativeTo(null);
        msg = new JLabel();
        initialUI();
        mainFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initial the User interface
    public void initialUI() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 190, 60));
        panel.setBackground(new Color(255, 218, 185));
        label = new JLabel(" ");
        button = initialCreate();
        setButton(button);
        panel.add(button);
        button = initialShowAllTasks();
        setButton(button);
        panel.add(button);
        button = initialShowCompletedTasks();
        setButton(button);
        panel.add(button);
        button = initialEdit();
        setButton(button);
        panel.add(button);
        button = initialSave();
        setButton(button);
        panel.add(button);
        button = initialLoad();
        setButton(button);
        panel.add(button);
        mainFrame.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: create the "Create" button and set the window for create
    public JButton initialCreate() {
        button = new JButton("Create a new Task");
        setButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                playClick();
                JPanel createPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 75, 30));
                JFrame createFrame = createFrame("Create", createPanel);
                addComponent(createPanel);
                JButton backButton = backButton(createFrame, mainFrame);
                createPanel.add(backButton);
                createPanel.add(label);
            }
        });
        return button;
    }

    // MODIFIES: panel
    // EFFECTS: helper method for initialCreate() to let the panel ad the name,info JTextArea
    public void addComponent(JPanel panel) {
        JLabel nameText = new JLabel("Task name:");
        setLabel(nameText);
        panel.add(nameText);
        JTextArea name = createJTextArea(1, 30);
        panel.add(name);
        JLabel infoText = new JLabel("Task info:");
        setLabel(infoText);
        panel.add(infoText);
        JTextArea info = createJTextArea(5, 30);
        panel.add(info);
        JButton finishButton = finishButton(panel, name, info);
        panel.add(finishButton);
    }

    // MODIFIES: this
    // EFFECTS: create the "Show All Tasks" button and set the window for Show All Tasks
    public JButton initialShowAllTasks() {
        button = new JButton("Show All Tasks");
        setButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playClick();
                mainFrame.dispose();
                JPanel showAllPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 30, 30));
                JFrame showAllFrame = createFrame("Show All Tasks", showAllPanel);
                JButton backButton = backButton(showAllFrame, mainFrame);
                showAllTasks(showAllPanel);
                showAllPanel.add(backButton);
            }
        });
        return button;
    }

    // MODIFIES: this
    // EFFECTS: create the "Show Completed Tasks" button and set the window for Show Completed Tasks
    public JButton initialShowCompletedTasks() {
        button = new JButton("Show Completed Tasks");
        setButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                JPanel showCompletedPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 30));
                JFrame showCompletedFrame = createFrame("Show Completed Tasks", showCompletedPanel);
                JButton backButton = backButton(showCompletedFrame, mainFrame);
                playClick();
                showCompletedTasks(showCompletedPanel);
                showCompletedPanel.add(backButton);
            }
        });
        return button;
    }

    // MODIFIES: this
    // EFFECTS: create the "Edit" button and set the window for Edit
    public JButton initialEdit() {
        JButton editButton = new JButton("Edit");
        setButton(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playClick();
                mainFrame.dispose();
                JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 170, 30));
                JFrame editFrame = createFrame("Edit", editPanel);
                JButton completeButton = completeButton(editFrame);
                JButton setPriorityButton = setPriorityButton(editFrame);
                JButton deleteButton = deleteButton(editFrame);
                JButton backButton = backButton(editFrame, mainFrame);
                editPanel.add(completeButton);
                editPanel.add(setPriorityButton);
                editPanel.add(deleteButton);
                editPanel.add(backButton);
            }
        });
        return editButton;
    }

    //MODIFIES: this
    //EFFECTS: create the "Save" button and set the window for Save
    public JButton initialSave() {
        JButton saveButton = new JButton("Save");
        setButton(saveButton);
        setLabel(msg);
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainFrame.dispose();
                    JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 150, 30));
                    JFrame saveFrame = createFrame("Save", savePanel);
                    JButton backButton = backButton(saveFrame, mainFrame);
                    savePanel.add(msg);
                    savePanel.add(backButton);
                    saveToDoList();
                    playSuccess();
                    msg.setText("Saved " + toDoList.getListName() + " to " + JSON_STORE);
                } catch (FileNotFoundException fileNotFoundException) {
                    playError();
                    msg.setText("Unable to save " + toDoList.getListName() + " to " + JSON_STORE);
                }

            }
        });
        return saveButton;
    }


    //MODIFIES: this
    //EFFECTS: create the "Load" button and set the window for Save
    public JButton initialLoad() {
        JButton loadButton = new JButton("Load");
        setButton(loadButton);
        setLabel(msg);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainFrame.dispose();
                    JPanel loadPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 150, 30));
                    JFrame loadFrame = createFrame("Load", loadPanel);
                    JButton backButton = backButton(loadFrame, mainFrame);
                    loadPanel.add(msg);
                    loadPanel.add(backButton);
                    loadToDoList();
                    playSuccess();
                    msg.setText("Load " + toDoList.getListName() + " from " + JSON_STORE);
                } catch (IOException ioException) {
                    playError();
                    msg.setText("Unable to load " + toDoList.getListName() + " from " + JSON_STORE);
                }

            }
        });
        return loadButton;
    }

    //MODIFIES: this
    //EFFECTS: create the "Mark as completed" button and set the window for Mark as completed
    public JButton completeButton(JFrame frame) {
        JButton completeButton = new JButton("Mark the task as Completed");
        setButton(completeButton);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JPanel completePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 30));
                JFrame completeFrame = createFrame("Mark As Completed", completePanel);
                JLabel nameText = new JLabel("Please enter the task name");
                setLabel(nameText);
                completePanel.add(nameText);
                JTextArea name = new JTextArea(1, 30);
                setTextArea(name);
                completePanel.add(name);
                JButton markButton = markButton(name, completePanel);
                completePanel.add(markButton);
                JButton backButton = backButton(completeFrame, frame);
                completePanel.add(backButton);
                completePanel.add(label);
            }
        });
        return completeButton;
    }

    //MODIFIES: this
    //EFFECTS: create a "Mark this task as completed" button and set the status of the task as completed
    public JButton markButton(JTextArea name, JPanel panel) {
        JButton btn = new JButton("Mark this task as completed");
        setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameText = name.getText().trim();
                    setCompleted(nameText);
                    playSuccess();
                    addRightIcon(panel);
                    label.setText(nameText + " has been marked as completed");
                } catch (NotInTheListException notInTheListException) {
                    playError();
                    addWrongIcon(panel);
                    label.setText("The task name you enter is not on the list!");
                }
            }
        });
        return btn;
    }

    //MODIFIES: this
    //EFFECTS: create the "Set Task priority" button and set the window for Mark as completed
    public JButton setPriorityButton(JFrame frame) {
        JButton setPriorityButton = new JButton("Set Task Priority");
        setButton(setPriorityButton);
        setPriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JPanel priorityPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 100, 30));
                JFrame priorityFrame = createFrame("Set Priority", priorityPanel);
                createJTextAndJLabel(priorityPanel);
                JButton backButton = backButton(priorityFrame, frame);
                priorityPanel.add(backButton);
                priorityPanel.add(label);
            }
        });
        return setPriorityButton;
    }

    //MODIFIES: panel
    //EFFECTS: helper method for setPriority()
    public void createJTextAndJLabel(JPanel panel) {
        JLabel nameText = new JLabel("Please enter the task name");
        setLabel(nameText);
        panel.add(nameText);
        JTextArea name = new JTextArea(1, 30);
        setTextArea(name);
        panel.add(name);
        JLabel priorityText = new JLabel("Please enter the priority between 0-5");
        setLabel(priorityText);
        panel.add(priorityText);
        JTextArea priority = new JTextArea(1, 30);
        setTextArea(priority);
        panel.add(priority);
        JButton finishButton = finishButton(name, priority, panel);
        panel.add(finishButton);
    }

    //MODIFIES: this
    //EFFECTS: create the "Delete Task" button and set the window for Delete Task
    public JButton deleteButton(JFrame frame) {
        JButton deleteButton = new JButton("Delete Task");
        setButton(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playClick();
                frame.dispose();
                JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 100, 30));
                JFrame deleteFrame = createFrame("Delete Task", deletePanel);
                JLabel nameText = new JLabel("Please enter the task name");
                setLabel(nameText);
                deletePanel.add(nameText);
                JTextArea name = new JTextArea(1, 30);
                setTextArea(name);
                deletePanel.add(name);
                JButton finishButton = finishButton(name, deletePanel);
                deletePanel.add(finishButton);
                JButton backButton = backButton(deleteFrame, frame);
                deletePanel.add(backButton);
                deletePanel.add(label);
            }
        });
        return deleteButton;
    }

    //MODIFIES: this
    //EFFECTS: create a "Finish" button for "Delete Task" window
    public JButton finishButton(JTextArea name, JPanel panel) {
        JButton btn = new JButton("Finish");
        setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameText = name.getText().trim();
                    deleteTask(nameText);
                    playSuccess();
                    addRightIcon(panel);
                    label.setText(nameText + " has been deleted from the list");
                } catch (ListSizeZeroException listSizeZeroException) {
                    playError();
                    addWrongIcon(panel);
                    label.setText("The list is empty!");
                } catch (NotInTheListException notInTheListException) {
                    playError();
                    addWrongIcon(panel);
                    label.setText("The task name you enter is not on the list!");
                }

            }
        });
        return btn;
    }

    //MODIFIES: this
    //EFFECTS: create a "Finish" button for "Set Task priority" window
    public JButton finishButton(JTextArea name, JTextArea priority, JPanel panel) {
        JButton btn = new JButton("Finish");
        setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameText = name.getText().trim();
                    setTaskPriority(nameText, Integer.parseInt(priority.getText().trim()));
                    playSuccess();
                    addRightIcon(panel);
                    label.setText("Set the task " + nameText + "'s priority as " + priority.getText().trim());
                } catch (NotInTheListException notInTheListException) {
                    playError();
                    addWrongIcon(panel);
                    label.setText("The task name you enter is not on the list!");
                } catch (OutOfRangeException outOfRangeException) {
                    playError();
                    addWrongIcon(panel);
                    label.setText("The task priority you enter is our of range!");
                }

            }
        });
        return btn;
    }

    //MODIFIES: this
    //EFFECTS: create a "Add this task to list" button and add the task to the to-do list
    public JButton finishButton(JPanel panel, JTextArea name, JTextArea info) {
        JButton btn = new JButton("Add this task to list");
        setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText().trim();
                String infoText = info.getText().trim();
                createTask(nameText, infoText);
                playSuccess();
                addRightIcon(panel);
                label.setText("A new task has been created and been added to the EZ list");
            }
        });
        return btn;
    }

    //MODIFIES: this
    //EFFECTS: create a new Frame with given name and initial the frame also add a panel component to the frame
    public JFrame createFrame(String name, JPanel panel) {
        JFrame newFrame = new JFrame(name);
        newFrame.setSize(WIDTH, HEIGHT);
        panel.setBackground(new Color(255, 218, 185));
        newFrame.add(panel);
        newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
        label = new JLabel(" ");
        return newFrame;
    }

    //MODIFIES: this
    //EFFECTS: create a new JTextArea Object and set line wrap as true
    public JTextArea createJTextArea(int rows, int columns) {
        JTextArea temp = new JTextArea(rows, columns);
        temp.setLineWrap(true);
        setTextArea(temp);
        return temp;
    }


    //MODIFIES: this
    //EFFECTS: create a "back" button
    public JButton backButton(JFrame frame, JFrame backFrame) {
        JButton btn = new JButton("Back");
        setBackButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playClick();
                frame.dispose();
                backFrame.setVisible(true);
            }
        });
        return btn;
    }

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
        panel.remove(pictureLabel);
        img = new ImageIcon(WRONG_IMAGE);
        pictureLabel.setIcon(img);
        panel.add(pictureLabel);
    }

    //MODIFIES: panel
    //EFFECTS: add right icon to the given panel
    public void addRightIcon(JPanel panel) {
        panel.remove(pictureLabel);
        Icon icon = new ImageIcon(RIGHT_IMAGE);
        pictureLabel.setIcon(icon);
        panel.add(pictureLabel);
    }

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
        label.setForeground(new Color(122, 86, 85));
        label.setFont(new Font("Arial", Font.BOLD, 20));
    }

    //MODIFIES: JTextArea
    //EFFECTS: set the label font, border, color and bounds
    public void setTextArea(JTextArea area) {
        area.setFont(new Font("Times New Roman", Font.BOLD, 16));
        area.setBackground(new Color(222, 184, 135));
    }

    // MODIFIES: this
    // EFFECTS: create a new Task with given name and info
    public void createTask(String name, String info) {
        task = new Tasks(name, info);
        toDoList.addTask(task);
    }

    // EFFECTS: show all the tasks on the list to user
    public void showAllTasks(JPanel panel) {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            JTextArea taskText = createJTextArea(5, 30);
            taskText.setText(item.toString());
            panel.add(taskText);
        }
    }

    // EFFECTS: show completed tasks on the list to user
    public void showCompletedTasks(JPanel panel) {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getStatus()) {
                JTextArea taskText = createJTextArea(5, 30);
                taskText.setText(item.toString());
                panel.add(taskText);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: mark the task as completed
    public void setCompleted(String name) throws NotInTheListException {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getName().equals(name)) {
                item.setStatus(true);
                return;
            }
        }
        throw new NotInTheListException();
    }

    // MODIFIES: this
    // EFFECTS: delete the task from the to-do list
    public void deleteTask(String name) throws NotInTheListException, ListSizeZeroException {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getName().equals(name)) {

                toDoList.deleteTask(item);
                return;
            }
        }
        throw new NotInTheListException();
    }

    // MODIFIES: this
    // EFFECTS: set priority of the task on the list, high priority task will appear on the top of the list
    public void setTaskPriority(String name, int num) throws NotInTheListException, OutOfRangeException {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getName().equals(name)) {
                item.setPriority(num);
                sortList();
                return;
            }
        }
        throw new NotInTheListException();
    }

    // MODIFIES: this
    // EFFECTS: sort the to-do list from high to low priorities
    public void sortList() {
        for (int i = 0; i < toDoList.size(); i++) {
            for (int j = 1; j < toDoList.size() - i; j++) {
                Tasks taskA = toDoList.getTask(j - 1);
                Tasks taskB = toDoList.getTask(j);
                int priorityA = taskA.getPriority();
                int priorityB = taskB.getPriority();
                if (priorityA < priorityB) {
                    try {
                        taskA.swap(taskB);
                    } catch (OutOfRangeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // model code base on JsonSerializationDemo-WorkRoomApp
    // EFFECTS: saves the to-do list to file
    private void saveToDoList() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(toDoList);
        jsonWriter.close();
    }

    // model code base on JsonSerializationDemo-WorkRoomApp
    // MODIFIES: this
    // EFFECTS: loads to-do list from file
    private void loadToDoList() throws IOException {
        toDoList = jsonReader.read();
    }
}

