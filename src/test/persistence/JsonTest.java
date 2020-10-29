package persistence;

import model.Tasks;
import static org.junit.jupiter.api.Assertions.*;
//model code base on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    // model code base on JsonSerializationDemo-JsonTest
    protected void checkTask(String name, String info, boolean status, int priority, Tasks task) {
        assertEquals(name,task.getName());
        assertEquals(info,task.getInfo());
        assertEquals(status,task.getStatus());
        assertEquals(priority,task.getPriority());
    }
}
