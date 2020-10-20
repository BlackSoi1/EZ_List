package model;

/*
Represents a task
 */

import exception.OutOfRangeException;
import javafx.concurrent.Task;
import org.json.JSONObject;
import persistence.Writable;

public class Tasks implements Writable {
    private static final boolean NOTCOMPLETED = false;
    private String name;
    private boolean status;
    private String info;
    private int priority;


    public Tasks(String name, String info, boolean status, int priority) {
        this.name = name;
        this.status = status;
        this.info = info;
        this.priority = priority;
    }


    //EFFECTS:task name is set to name; task info is set to blank space;
    //task status is set to NOTCOMPLETED; deadline is set to "0"
    public Tasks(String name, String info) {
        this.name = name;
        this.info = info;
        status = NOTCOMPLETED;
        int priority = 0;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    //EFFECTS: return the priority
    public int getPriority() {
        return priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    //MODIFIES: this
    //EFFECTS: set the priority of the task. if the num is out of the range 0-5 throw OutOfRangeException
    public void setPriority(int num) throws OutOfRangeException {
        if (num < 0 || num > 5) {
            throw new OutOfRangeException("The priority number you enter is out of range");
        }
        priority = num;
    }


    //EFFECTS: return the task details in clear format
    @Override
    public String toString() {
        return "The task name is " + name
                + ". The task information is "
                + info + ".";
    }

    //MODIFIES:this, other
    //EFFECTS: swap 2 tasks' status, name, info and priority
    public void swap(Tasks other) throws OutOfRangeException {
        String tempName = name;
        String tempInfo = info;
        int tempPri = priority;
        boolean tempStatus = status;
        priority = other.getPriority();
        info = other.getInfo();
        name = other.getName();
        status = other.getStatus();
        other.setStatus(tempStatus);
        other.setName(tempName);
        other.setPriority(tempPri);
        other.setInfo(tempInfo);
    }

    // model code base on JsonSerializationDemo-Thingy
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("info", info);
        json.put("status", status);
        json.put("priority", priority);
        return json;
    }
}
