package model;

/*
Represent a to-do list
 */

//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import exception.DuplicateException;
import exception.InvalidInputException;
import exception.ListSizeZeroException;
import exception.NotInTheListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ToDoList implements Writable {
    private List<Tasks> toDoList;
    private String listName;

    public ToDoList(String listName) {
        toDoList = new ArrayList<Tasks>();
        this.listName = listName;
    }

    //MODIFIES: this,task
    //EFFECTS: if the task is not on the to-do list and input is not empty add task to the to-do list
    //else throw InvalidInputException
    public void addTask(Tasks task) throws InvalidInputException, DuplicateException {
        if (!toDoList.contains(task)) {
            if (task.getName().length() != 0 && task.getInfo().length() != 0) {
                toDoList.add(task);
            } else {
                throw new InvalidInputException();
            }
        } else {
            throw new DuplicateException();
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
    public void deleteTask(Tasks task) throws ListSizeZeroException, NotInTheListException {
        if (toDoList.size() == 0) {
            throw new ListSizeZeroException("Cannot delete task from empty to-do list");
        }
        if (!toDoList.contains(task)) {
            throw new NotInTheListException();
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
