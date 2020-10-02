package model;

/*
Represents a task
 */

public class Tasks {
    private static final boolean COMPLETED = true;
    private static final boolean NOTCOMPLETED = false;
    private String name;
    private boolean status;
    private String info;
    private String deadline;

    //REQUIRES:name have non-zero length
    //EFFECTS:task name is set to name; task info is set to blank space;
    //task status is set to NOTCOMPLETED; deadline is set to "0"
    public Tasks(String name, String info, String deadline) {
        this.name = name;
        this.info = info;
        status = NOTCOMPLETED;
        this.deadline = deadline;
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

    //MODIFIERS: this
    //EFFECTS: set task status to be Completed
    public void setStatusIsCompleted() {
        status = COMPLETED;
    }





}
