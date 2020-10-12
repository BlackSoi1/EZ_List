package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ToDoListTest {

    private ToDoList toDoList;
    private Tasks task;

    @BeforeEach
    void setUp() {
        task = new Tasks("Task2", "Test2");
        task.setStatus(false);
        task.setPriority(1);
        toDoList = new ToDoList("list1");
        toDoList.addTask(task);
    }

    @Test
    void testAddTask() {
        assertEquals(toDoList.getTask(0), task);
        assertEquals(toDoList.size(), 1);
        assertEquals(toDoList.getTask(0).getName(),"Task2");
        assertEquals(toDoList.getTask(0).getInfo(),"Test2");
    }

    @Test
    void testDeleteTask() {
        assertEquals(toDoList.getTask(0), task);
        assertEquals(toDoList.size(), 1);
        toDoList.deleteTask(task);
        assertEquals(toDoList.size(), 0);
    }

    @Test
    void testSize() {
        assertEquals(toDoList.size(), 1);
        Tasks other2 = new Tasks("Task3", "Test3");
        toDoList.addTask(task);
        assertEquals(toDoList.size(), 2);
    }

    @Test
    void testGetTask() {
        Tasks other = toDoList.getTask(0);
        assertEquals(other.getName(),"Task2");
        assertEquals(other.getInfo(),"Test2");
        assertEquals(other.getStatus(),false);
        assertEquals(other.getPriority(),1);
    }

    @Test
    void testGetListName() {
        assertEquals(toDoList.getListName(),"list1");
    }
}
