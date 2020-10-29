package model;

/*
Represent a to-do list
 */

//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
import exception.ListSizeZeroException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoList implements Writable {
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

    // model code base on JsonSerializationDemo-WorkRoom
    // EFFECTS: returns an unmodifiable list of tasks in this To-Do list
    public List<Tasks> getTasks() {
        return Collections.unmodifiableList(toDoList);
    }


    //MODIFIES: this
    //EFFECTS: delete the task from the to-do list if this to-do list is not empty. throw ListSizeZeroException if
    //this list is empty
    public void deleteTask(Tasks task) throws ListSizeZeroException {
        if (toDoList.size() == 0) {
            throw new ListSizeZeroException("Cannot delete task from empty to-do list");
        }
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

    // model code base on JsonSerializationDemo-WorkRoom
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", listName);
        json.put("tasks", tasksToJson());
        return json;
    }

    // model code base on JsonSerializationDemo-WorkRoom
    // EFFECTS: returns tasks in this to-do list as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Tasks t : toDoList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
