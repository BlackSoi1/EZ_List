package ui;
//The task UI

import exception.*;
import model.Tasks;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TaskUI {
    private static final String JSON_STORE = "./data/myFile.txt";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Tasks task;
    private ToDoList toDoList;
    private SwingComponent sc;

    public TaskUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        toDoList = new ToDoList("EZ List");
        sc = new SwingComponent();
    }

    // MODIFIES: this
    // EFFECTS: create a new Task with given name and info
    public void createTask(String name, String info) throws InvalidInputException, DuplicateException {
        task = new Tasks(name, info);
        toDoList.addTask(task);
    }

    // EFFECTS: show all the tasks on the list to user
    public void showAllTasks(JPanel panel) {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            JTextArea taskText = new JTextArea(5,30);
            taskText.setLineWrap(true);
            sc.setTextArea(taskText);
            taskText.setText(item.toString());
            panel.add(taskText);
        }
    }

    // EFFECTS: show completed tasks on the list to user
    public void showCompletedTasks(JPanel panel) {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getStatus()) {
                JTextArea taskText = new JTextArea(5,30);
                taskText.setLineWrap(true);
                sc.setTextArea(taskText);
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
    public void saveToDoList() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(toDoList);
        jsonWriter.close();
    }

    // model code base on JsonSerializationDemo-WorkRoomApp
    // MODIFIES: this
    // EFFECTS: loads to-do list from file
    public void loadToDoList() throws IOException, InvalidInputException, DuplicateException {
        toDoList = jsonReader.read();
    }
}
