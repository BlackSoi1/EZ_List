package ui;

import java.io.FileNotFoundException;

/*
provide a simple use of the to-do list App "EZ"
 */
public class Main {
    public static void main(String[] args) {
        // model code base on JsonSerializationDemo-Main
        try {
            ToDoApp toDoApp = new ToDoApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
