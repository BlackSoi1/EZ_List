package persistence;


import model.ToDoList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
/*
Represents a writer that reads to-do list from JSON data stored in file
 */

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // model code base on JsonSerializationDemo-JsonWriter
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // model code base on JsonSerializationDemo-JsonWriter
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(ToDoList list) {
        JSONObject json = list.toJson();
        saveToFile(json.toString(TAB));
    }

    // model code base on JsonSerializationDemo-JsonWriter
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // model code base on JsonSerializationDemo-JsonWriter
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
