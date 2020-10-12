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
    //EFFECTS: if the task is not on the to-do list add task to the to-do list
    //and return true else return false
    public boolean addTask(Tasks task) {
        if (!toDoList.contains(task)) {
            toDoList.add(task);
            return true;
        } else {
            return false;
        }

    }


    //REQUIRES:  the to-do list size is not 0
    //MODIFIES: this
    //EFFECTS: delete the task from the to-do list.
    public void deleteTask(Tasks task) {
        toDoList.remove(task);
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
