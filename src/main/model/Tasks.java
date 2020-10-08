package model;

/*
Represents a task
 */

import javafx.concurrent.Task;

public class Tasks {
    private static final boolean NOTCOMPLETED = false;
    private String name;
    private boolean status;
    private String info;
    private int priority;

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

    //REQUIRES: the range of num should between 0-5
    //MODIFIES: this
    //EFFECTS: set the priority of the task
    public void setPriority(int num) {
        priority = num;
    }


    //REQUIRES:name have non-zero length
    //EFFECTS:task name is set to name; task info is set to blank space;
    //task status is set to NOTCOMPLETED; deadline is set to "0"
    public Tasks(String name, String info) {
        this.name = name;
        this.info = info;
        status = NOTCOMPLETED;
        int priority = 0;
    }

    //EFFECTS:check the task is completed or not
    public boolean isCompleted(Tasks task) {
        return task.getStatus();
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
    public void swap(Tasks other) {
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
}
