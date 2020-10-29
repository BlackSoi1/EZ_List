package persistence;

import model.Tasks;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {

    // model code base on JsonSerializationDemo-JsonWriterTest
    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDoList = new ToDoList("EZ List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.txt");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Expected
        }
    }

    // model code base on JsonSerializationDemo-JsonWriterTest
    @Test
    void testWriterEmptyToDoList() {
        try {
            ToDoList toDoList = new ToDoList("EZ List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.txt");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.txt");
            toDoList = reader.read();
            assertEquals("EZ List", toDoList.getListName());
            assertEquals(0, toDoList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // model code base on JsonSerializationDemo-JsonWriterTest
    @Test
    void testWriterGeneralToDoList() {
        try {
            ToDoList toDoList = new ToDoList("EZ List");
            toDoList.addTask(new Tasks("task 2","work",false,3));
            toDoList.addTask(new Tasks("task 1","study",true,0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.txt");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.txt");
            toDoList = reader.read();
            assertEquals("EZ List", toDoList.getListName());
            List<Tasks>  tasks = toDoList.getTasks();
            assertEquals(2, tasks.size());
            checkTask("task 2","work",false,3,tasks.get(0));
            checkTask("task 1","study",true,0,tasks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
