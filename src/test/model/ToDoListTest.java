package model;

import exception.*;
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
    void testAddTaskDuplicated(){
        try {
            toDoList.addTask(task);
            toDoList.addTask(task);
            fail();
        } catch (DuplicateException e) {
           //Expected
        } catch (InvalidInputException e) {
            fail();
        }
    }

    @Test
    void testAddTaskValidNameValidInfo() {
        try {
            toDoList.addTask(task);
            assertEquals(toDoList.getTask(0), task);
            assertEquals(toDoList.size(), 1);
            assertEquals(toDoList.getTask(0).getName(), "Task2");
            assertEquals(toDoList.getTask(0).getInfo(), "Test2");
        } catch (InvalidInputException e) {
            fail();
        } catch (DuplicateException e) {
            fail();
        }

    }
    @Test
    void testAddTaskInValidNameValidInfo() {
        try {
            Tasks task2 = new Tasks("","aa");
            toDoList.addTask(task2);
            fail();
        } catch (InvalidInputException e) {
            //Expected
        } catch (DuplicateException e) {
            fail();
        }
    }
    @Test
    void testAddTaskInValidNameInValidInfo() {
        try {
            Tasks task2 = new Tasks("","");
            toDoList.addTask(task2);
            fail();
        } catch (InvalidInputException e) {
            //Expected
        } catch (DuplicateException e) {
            fail();
        }
    }
    @Test
    void testAddTaskValidNameInValidInfo() {
        try {
            Tasks task2 = new Tasks("aa","");
            toDoList.addTask(task2);
            fail();
        } catch (InvalidInputException e) {
            //Expected
        } catch (DuplicateException e) {
            fail();
        }
    }
    @Test
    void testDeleteTaskWhenListNotEmptyInTheList() {
        try {
            toDoList.addTask(task);
        } catch (InvalidInputException | DuplicateException e) {
            e.printStackTrace();
        }
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
        try {
            toDoList.addTask(task);
        } catch (InvalidInputException | DuplicateException e) {
            fail();
        }
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
        try {
            toDoList.addTask(task);
            assertEquals(toDoList.size(), 1);
            Tasks other2 = new Tasks("Task3", "Test3");
            toDoList.addTask(other2);
            assertEquals(toDoList.size(), 2);
        } catch (InvalidInputException | DuplicateException e) {
            fail();
        }

    }

    @Test
    void testGetTask() {
        try {
            toDoList.addTask(task);
            Tasks other = toDoList.getTask(0);
            assertEquals(other.getName(), "Task2");
            assertEquals(other.getInfo(), "Test2");
            assertEquals(other.getStatus(), false);
            assertEquals(other.getPriority(), 1);
        } catch (InvalidInputException | DuplicateException e) {
            fail();
        }

    }

    @Test
    void testGetListName() {
        try {
            toDoList.addTask(task);
            assertEquals(toDoList.getListName(), "list1");
        } catch (InvalidInputException | DuplicateException e) {
           fail();
        }

    }


}
