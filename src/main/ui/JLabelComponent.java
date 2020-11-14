package ui;
//Create different JLabel in the panel

import exception.*;
import model.Tasks;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JLabelComponent {
    private static final String JSON_STORE = "./data/myFile.txt";
    private AudiovisualComponent ac;
    private SwingComponent sc;
    private static final int WIDTH = 550;
    private static final int HEIGHT = 700;
    private ToDoList toDoList;

    private JLabel label;
    private JButton button;
    private JLabel msg;
    private TaskUI taskUI;


    public JLabelComponent() {
        sc = new SwingComponent();
        ac = new AudiovisualComponent();
        msg = new JLabel();
        button = new JButton();
        taskUI = new TaskUI();
        toDoList = new ToDoList("EZ List");
    }


    // MODIFIES: this
    // EFFECTS: create the "Create" button and set the window for create
    public JButton initialCreate(JFrame mainFrame) {
        button = new JButton("Create a new Task");
        sc.setButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                ac.playClick();
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
        sc.setLabel(nameText);
        panel.add(nameText);
        JTextArea name = createJTextArea(1, 30);
        panel.add(name);
        JLabel infoText = new JLabel("Task info:");
        sc.setLabel(infoText);
        panel.add(infoText);
        JTextArea info = createJTextArea(5, 30);
        panel.add(info);
        JButton finishButton = finishButton(panel, name, info);
        panel.add(finishButton);
    }


    // MODIFIES: this
    // EFFECTS: create the "Show All Tasks" button and set the window for Show All Tasks
    public JButton initialShowAllTasks(JFrame mainFrame) {
        button = new JButton("Show All Tasks");
        sc.setButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ac.playClick();
                mainFrame.dispose();
                JPanel showAllPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 30, 30));
                JFrame showAllFrame = createFrame("Show All Tasks", showAllPanel);
                JButton backButton = backButton(showAllFrame, mainFrame);
                taskUI.showAllTasks(showAllPanel);
                showAllPanel.add(backButton);
            }
        });
        return button;
    }

    // MODIFIES: this
    // EFFECTS: create the "Show Completed Tasks" button and set the window for Show Completed Tasks
    public JButton initialShowCompletedTasks(JFrame mainFrame) {
        button = new JButton("Show Completed Tasks");
        sc.setButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                JPanel showCompletedPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 30));
                JFrame showCompletedFrame = createFrame("Show Completed Tasks", showCompletedPanel);
                JButton backButton = backButton(showCompletedFrame, mainFrame);
                ac.playClick();
                taskUI.showCompletedTasks(showCompletedPanel);
                showCompletedPanel.add(backButton);
            }
        });
        return button;
    }

    // MODIFIES: this
    // EFFECTS: create the "Edit" button and set the window for Edit
    public JButton initialEdit(JFrame mainFrame) {
        JButton editButton = new JButton("Edit");
        sc.setButton(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ac.playClick();
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
    public JButton initialSave(JFrame mainFrame) {
        JButton saveButton = new JButton("Save");
        sc.setButton(saveButton);
        sc.setLabel(msg);
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
                    taskUI.saveToDoList();
                    ac.playSuccess();
                    msg.setText("Saved " + toDoList.getListName() + " to " + JSON_STORE);
                } catch (FileNotFoundException fileNotFoundException) {
                    ac.playError();
                    msg.setText("Unable to save " + toDoList.getListName() + " to " + JSON_STORE);
                }

            }
        });
        return saveButton;
    }


    //MODIFIES: this
    //EFFECTS: create the "Load" button and set the window for Save
    public JButton initialLoad(JFrame mainFrame) {
        JButton loadButton = new JButton("Load");
        sc.setButton(loadButton);
        sc.setLabel(msg);
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
                    taskUI.loadToDoList();
                    ac.playSuccess();
                    msg.setText("Load " + toDoList.getListName() + " from " + JSON_STORE);
                } catch (IOException | InvalidInputException | DuplicateException ioException) {
                    ac.playError();
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
        sc.setButton(completeButton);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JPanel completePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 30));
                JFrame completeFrame = createFrame("Mark As Completed", completePanel);
                JLabel nameText = new JLabel("Please enter the task name");
                sc.setLabel(nameText);
                completePanel.add(nameText);
                JTextArea name = new JTextArea(1, 30);
                sc.setTextArea(name);
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
        sc.setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameText = name.getText().trim();
                    taskUI.setCompleted(nameText);
                    ac.playSuccess();
                    ac.addRightIcon(panel);
                    label.setText(nameText + " has been marked as completed");
                } catch (NotInTheListException notInTheListException) {
                    ac.playError();
                    ac.addWrongIcon(panel);
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
        sc.setButton(setPriorityButton);
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
        sc.setLabel(nameText);
        panel.add(nameText);
        JTextArea name = new JTextArea(1, 30);
        sc.setTextArea(name);
        panel.add(name);
        JLabel priorityText = new JLabel("Please enter the priority between 0-5");
        sc.setLabel(priorityText);
        panel.add(priorityText);
        JTextArea priority = new JTextArea(1, 30);
        sc.setTextArea(priority);
        panel.add(priority);
        JButton finishButton = finishButton(name, priority, panel);
        panel.add(finishButton);
    }

    //MODIFIES: this
    //EFFECTS: create the "Delete Task" button and set the window for Delete Task
    public JButton deleteButton(JFrame frame) {
        JButton deleteButton = new JButton("Delete Task");
        sc.setButton(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ac.playClick();
                frame.dispose();
                JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 100, 30));
                JFrame deleteFrame = createFrame("Delete Task", deletePanel);
                JLabel nameText = new JLabel("Please enter the task name");
                sc.setLabel(nameText);
                deletePanel.add(nameText);
                JTextArea name = new JTextArea(1, 30);
                sc.setTextArea(name);
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
        sc.setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameText = name.getText().trim();
                    taskUI.deleteTask(nameText);
                    ac.playSuccess();
                    ac.addRightIcon(panel);
                    label.setText(nameText + " has been deleted from the list");
                } catch (ListSizeZeroException listSizeZeroException) {
                    ac.playError();
                    ac.addWrongIcon(panel);
                    label.setText("The list is empty!");
                } catch (NotInTheListException notInTheListException) {
                    ac.playError();
                    ac.addWrongIcon(panel);
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
        sc.setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameText = name.getText().trim();
                    taskUI.setTaskPriority(nameText, Integer.parseInt(priority.getText().trim()));
                    ac.playSuccess();
                    ac.addRightIcon(panel);
                    label.setText("Set the task " + nameText + "'s priority as " + priority.getText().trim());
                } catch (NotInTheListException notInTheListException) {
                    ac.playError();
                    ac.addWrongIcon(panel);
                    label.setText("The task name you enter is not on the list!");
                } catch (OutOfRangeException outOfRangeException) {
                    ac.playError();
                    ac.addWrongIcon(panel);
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
        sc.setButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    taskUI.createTask(name.getText().trim(), info.getText().trim());
                    ac.playSuccess();
                    ac.addRightIcon(panel);
                    label.setText("A new task has been created and been added to the EZ list");
                } catch (InvalidInputException invalidInputException) {
                    ac.playError();
                    ac.addWrongIcon(panel);
                    label.setText("Task name or Task info cannot be empty!");
                } catch (DuplicateException duplicateException) {
                    ac.playError();
                    ac.addWrongIcon(panel);
                    label.setText("The task already in the list!");
                }

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
        sc.setTextArea(temp);
        return temp;
    }


    //MODIFIES: this
    //EFFECTS: create a "back" button
    public JButton backButton(JFrame frame, JFrame backFrame) {
        JButton btn = new JButton("Back");
        sc.setBackButton(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ac.playClick();
                frame.dispose();
                backFrame.setVisible(true);
            }
        });
        return btn;
    }

}
