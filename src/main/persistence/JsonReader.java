package persistence;
/*
Represents a JsonReader
 */

import model.Tasks;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads to-do list from JSON data stored in file
public class JsonReader {
    private String source;

    // model code base on JsonSerializationDemo-JsonReader
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // model code base on JsonSerializationDemo-JsonReader
    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // model code base on JsonSerializationDemo-JsonReader
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // model code base on JsonSerializationDemo-JsonReader
    // EFFECTS: parses to-do list from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ToDoList list = new ToDoList(name);
        addTasks(list, jsonObject);
        return list;
    }

    // model code base on JsonSerializationDemo-JsonReader
    // MODIFIES: list
    // EFFECTS: parses tasks from JSON object and adds them to to-do list
    private void addTasks(ToDoList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addTask(list, nextThingy);
        }
    }

    // model code base on JsonSerializationDemo-JsonReader
    // MODIFIES: list
    // EFFECTS: parses task from JSON object and adds it to to-do list
    private void addTask(ToDoList list, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String info = jsonObject.getString("info");
        boolean status = jsonObject.getBoolean("status");
        int priority = jsonObject.getInt("priority");
        Tasks task = new Tasks(name, info, status, priority);
        list.addTask(task);
    }
}
