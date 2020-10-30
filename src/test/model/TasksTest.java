package model;

import exception.OutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {
    private Tasks task;
    @BeforeEach
    public void setUp() {
        task = new Tasks("Task1","Test1");
    }

    @Test
    public void testSetPriorityWithInRange(){
        try {
            task.setPriority(3);
        } catch (OutOfRangeException e) {
            fail();
        }
    }

    @Test
    public void testSetPriorityWithLessThanRange(){
        try {
            task.setPriority(-1);
        } catch (OutOfRangeException e) {
            //
        }
    }
    @Test
    public void testSetPriorityWithBiggerThanRange(){
        try {
            task.setPriority(6);
        } catch (OutOfRangeException e) {
            //
        }
    }

    @Test
    public void testToString() {
        assertEquals(task.toString(),"The task name is Task1. The task information is Test1.");
    }

    @Test
    public void testSwap() {
        Tasks other = new Tasks("Task2","Test2");
        try {
            task.setPriority(1);
            other.setPriority(2);
            task.setStatus(false);
            other.setStatus(true);
            task.swap(other);
        } catch (OutOfRangeException e) {
            e.printStackTrace();
        }

        assertEquals(task.getName(),"Task2");
        assertEquals(task.getInfo(),"Test2");
        assertEquals(task.getStatus(),true);
        assertEquals(task.getPriority(),2);
        assertEquals(other.getName(),"Task1");
        assertEquals(other.getInfo(),"Test1");
        assertEquals(other.getStatus(),false);
        assertEquals(other.getPriority(),1);
    }

    @Test
    public void testHashCode(){
        Tasks task2=new Tasks("Task1","Test1");
        assertEquals(task.hashCode(),task2.hashCode());
    }
    @Test
    public void testEqualsWhenEqual(){
        Tasks task2=new Tasks("Task1","Test1");
        assertTrue(task.equals(task2));
    }
    @Test
    public void testEqualsWhenNotEqual(){
        Tasks task2=new Tasks("Task2","Test1");
        assertFalse(task.equals(task2));
    }
}
