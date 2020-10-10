package ui;

import model.Tasks;
import model.ToDoList;

import java.util.Scanner;

public class ToDoApp {

    private Scanner input;
    private ToDoList toDoList;
    private Tasks task;
    String command;

    //EFFECTS: run the To-Do application
    public ToDoApp() {
        input = new Scanner(System.in);
        start();
    }

    //MODIFIES: this
    //EFFECTS: process the user input
    public void start() {
        boolean flag = true;
        display();
        while (flag) {
            command = input.next().toUpperCase();
            if (command.equals("L")) {
                createList();
            } else if (command.equals("T")) {
                createTask();
                toDoList.addTask(task);
            } else if (command.equals("E")) {
                edit();
            } else if (command.equals("S")) {
                showAllTasks();
            } else if (command.equals("C")) {
                showCompletedTasks();
            } else if (command.equals("Q")) {
                System.out.println("Thanks for using EZ to-do list, goodbye!");
                flag = false;
                break;
            } else {
                System.out.println("Invalid Input");
            }
            display();
        }
    }

    //EFFECTS: show the application interface to user
    public void display() {
        System.out.println("===========================================================");
        System.out.println("Welcome to the EZ to-do list Application!");
        System.out.println("Please enter the following commands for further operation:");
        System.out.println("Enter L to create a new list");
        System.out.println("Enter T to create a new task");
        System.out.println("Enter S to show all tasks");
        System.out.println("Enter C to show all completed tasks");
        System.out.println("Enter E to edit the tasks on the list");
        System.out.println("Enter Q to quit the application");
        System.out.println("===========================================================");
    }

    //MODIFIES: this
    //EFFECTS: receive the to-do list name from user to create a new to-do list
    public void createList() {
        System.out.println("Please enter the to-do list name");
        String listName = input.next();
        toDoList = new ToDoList(listName);
        System.out.println("A new to-do list named " + listName + " has been created");
    }

    //MODIFIES: this
    //EFFECTS: receive the task name, task info and task deadline from user to create a task and return the task
    public void createTask() {
        System.out.println("Please enter the task name");
        String name = input.next();
        System.out.println("Please enter the task information");
        String info = input.next();
        task = new Tasks(name, info);
        System.out.println("A new task has been created and been added to the list" + toDoList.getListName());
    }

    //EFFECTS: show all the tasks on the list to user
    public void showAllTasks() {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            System.out.println(item);
        }
    }

    //EFFECTS: show completed tasks on the list to user
    public void showCompletedTasks() {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getStatus()) {
                System.out.println(item);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: process the command from the user and edit the task on the to do list
    public void edit() {
        System.out.println("Enter M to mark the task as completed");
        System.out.println("Enter P to set the priorities of the task");
        System.out.println("Enter D to delete the task from the list");
        command = input.next().toUpperCase();
        if (command.equals("M")) {
            System.out.println("Please enter the task name that you completed");
            String name = input.next();
            setCompleted(name);
        } else if (command.equals("P")) {
            System.out.println("Please enter the task name that you want set priority");
            String name = input.next();
            System.out.println("Please enter the priority level you want set (from 0-5)");
            int num = input.nextInt();
            setTaskPriority(name, num);
        } else if (command.equals("D")) {
            System.out.println("Please enter the task name that you want delete");
            String name = input.next();
            deleteTask(name);
        } else {
            System.out.println("Invalid Input");
            System.out.println("Edit operation end");
        }

    }

    //MODIFIES: this
    //EFFECTS: mark the task as completed
    public void setCompleted(String name) {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getName().equals(name)) {
                item.setStatus(true);
                System.out.println("The task " + item.getName() + " has been marked as completed");
                System.out.println("Edit operation end");
                return;
            }
        }
        System.out.println("The task name you enter is not on the list");
    }

    //MODIFIES: this
    //EFFECTS: delete the task from the to-do list
    public void deleteTask(String name) {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getName().equals(name)) {
                toDoList.deleteTask(item);
                System.out.println("The task " + item.getName() + " has been deleted");
                System.out.println("Edit operation end");
                return;
            }
        }
        System.out.println("The task name you enter is not on the list");
    }

    //MODIFIES: this
    //EFFECTS: set priority of the task on the list, high priority task will appear on the top of the list
    public void setTaskPriority(String name, int num) {
        for (int i = 0; i < toDoList.size(); i++) {
            Tasks item = toDoList.getTask(i);
            if (item.getName().equals(name)) {
                item.setPriority(num);
                sortList();
                System.out.println("The task " + item.getName() + " has been set priority as " + num);
                System.out.println("Edit operation end");
                return;
            }
        }
        System.out.println("The task name you enter is not on the list");
    }

    //MODIFIES: this
    //EFFECTS: sort the to-do list from high to low priorities
    public void sortList() {
        for (int i = 0; i < toDoList.size(); i++) {
            for (int j = 1; j < toDoList.size() - i; j++) {
                Tasks taskA = toDoList.getTask(j - 1);
                Tasks taskB = toDoList.getTask(j);
                int priorityA = taskA.getPriority();
                int priorityB = taskB.getPriority();
                if (priorityA < priorityB) {
                    taskA.swap(taskB);
                }
            }
        }
    }
}

