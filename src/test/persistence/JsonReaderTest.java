package persistence;

import model.Tasks;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderTest extends JsonTest {

    // model code base on JsonSerializationDemo-JsonReaderTest
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.txt");
        try {
            ToDoList toDoList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // model code base on JsonSerializationDemo-JsonReaderTest
    @Test
    void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.txt");
        try {
            ToDoList toDoList = reader.read();
            assertEquals("EZ List", toDoList.getListName());
            assertEquals(0, toDoList.size());
        } catch (IOException e) {
            fail("Exception shouldn't be thrown");
        }
    }

    // model code base on JsonSerializationDemo-JsonReaderTest
    @Test
    void testReaderGeneralToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.txt");
        try {
            ToDoList toDoList = reader.read();
            assertEquals("EZ List", toDoList.getListName());
            List<Tasks> tasks = toDoList.getTasks();
            assertEquals(2, tasks.size());
            checkTask("task 2","work",false,3,tasks.get(0));
            checkTask("task 1","study",true,0,tasks.get(1));
        } catch (IOException e) {
            fail("Exception shouldn't be thrown");
        }
    }
}
