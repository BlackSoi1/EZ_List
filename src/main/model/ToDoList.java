package model;

/*
Represent a to-do list
 */


import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<Tasks> toDoList;
    private String listName;

    public ToDoList(String listName) {
        toDoList = new ArrayList<Tasks>();
        this.listName = listName;
    }

    //MODIFIES: this,task
    //EFFECTS: add task to the to-do list
    public void addTask(Tasks task) {
        toDoList.add(task);
    }

    //MODIFIES: this
    //EFFECTS: If the to-do list size is 0 return false, else delete the task from the to-do list and return true.
    public boolean deleteTask(Tasks task) {
        if (toDoList.size() == 0) {
            return false;
        } else {
            toDoList.remove(task);
            return true;
        }

    }

    //EFFECTS: return the size of the to do list
    public int size() {
        return toDoList.size();
    }

    //EFFECTS: return the size of the to do list
    public Tasks getTask(int index) {
        return toDoList.get(index);
    }

    //EFFECTS: return the name of the to do list
    public String getListName() {
        return listName;
    }


}
