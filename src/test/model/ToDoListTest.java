package model;

import exception.ListSizeZeroException;
import exception.NotInTheListException;
import exception.OutOfRangeException;
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
        try {
            task.setPriority(1);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }
        toDoList = new ToDoList("list1");
    }

    @Test
    void testAddTask() {
        toDoList.addTask(task);
        assertEquals(toDoList.getTask(0), task);
        assertEquals(toDoList.size(), 1);
        assertEquals(toDoList.getTask(0).getName(), "Task2");
        assertEquals(toDoList.getTask(0).getInfo(), "Test2");
        boolean flag = toDoList.addTask(task);
        assertEquals(flag, false);
    }

    @Test
    void testDeleteTaskWhenListNotEmptyInTheList() {
        toDoList.addTask(task);
        assertEquals(toDoList.getTask(0), task);
        assertEquals(toDoList.size(), 1);
        try {
            toDoList.deleteTask(task);
        } catch (ListSizeZeroException e) {
            fail();
        } catch (NotInTheListException e) {
            fail();
        }
        assertEquals(toDoList.size(), 0);
    }

    @Test
    void testDeleteTaskWhenListNotEmptyNotInTheList() {
        toDoList.addTask(task);
        assertEquals(toDoList.getTask(0), task);
        assertEquals(toDoList.size(), 1);
        Tasks task2 = new Tasks("test2", "infotest");
        try {
            toDoList.deleteTask(task2);
        } catch (ListSizeZeroException e) {
            fail();
        } catch (NotInTheListException e) {
            //Expected
        }
        assertEquals(toDoList.size(), 1);
    }
    @Test
    void testDeleteTaskWhenListEmptyNotInTheList() {

        assertEquals(toDoList.size(), 0);
        Tasks task2 = new Tasks("test2", "infotest");
        try {
            toDoList.deleteTask(task2);
        } catch (ListSizeZeroException e) {
            //Expected
        } catch (NotInTheListException e) {
            //Expected
        }
        assertEquals(toDoList.size(), 0);
    }

    @Test
    void testDeleteTaskWhenListIsEmptyInTheList() {
        assertEquals(toDoList.size(), 0);
        try {
            toDoList.deleteTask(task);
        } catch (ListSizeZeroException e) {
            //
        } catch (NotInTheListException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testSize() {
        toDoList.addTask(task);
        assertEquals(toDoList.size(), 1);
        Tasks other2 = new Tasks("Task3", "Test3");
        toDoList.addTask(other2);
        assertEquals(toDoList.size(), 2);
    }

    @Test
    void testGetTask() {
        toDoList.addTask(task);
        Tasks other = toDoList.getTask(0);
        assertEquals(other.getName(), "Task2");
        assertEquals(other.getInfo(), "Test2");
        assertEquals(other.getStatus(), false);
        assertEquals(other.getPriority(), 1);
    }

    @Test
    void testGetListName() {
        toDoList.addTask(task);
        assertEquals(toDoList.getListName(), "list1");
    }


}
