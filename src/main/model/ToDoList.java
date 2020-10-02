package model;

/*
Represent a to-do list
 */



public class ToDoList {
    public ToDoList(){

    }

    //MODIFIES: this,task
    //EFFECTS: add task to the to-do list
    public void addTask(Tasks task){

    }

    //MODIFIES: this
    //EFFECTS: delete the task from the to-do list
    public void deleteTask(Tasks task){

    }

    //MODIFIES: this
    //EFFECTS: set priorities of the task on the to-do list, finish the high priorities task first
    public void setPriorities(){

    }

    //EFFECTS:check the task one the to-do list is completed or not
    public boolean isCompleted(Tasks task) {
        return false;
    }

    //MODIFIES: this
    //EFFECTS: set deadline of the task on the to-do list
    public void setDeadline(){

    }
}
