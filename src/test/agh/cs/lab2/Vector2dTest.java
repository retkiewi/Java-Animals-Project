package agh.cs.lab2;

import org.junit.Test;

import java.util.Vector;

import  static org.junit.Assert.*;

public class Vector2dTest {
    Vector2d v1 = new Vector2d(1,2);
    Vector2d v2 = new Vector2d(-2,-1);

    @Test
    public void testEquals(){
        assertTrue(v1.equals(v1));
        assertFalse(v1.equals(v2));
    }
    @Test
    public void testToString(){
        assertEquals(v1.toString(), "(1,2)");
    }
    @Test
    public void testPrecedes(){
        assertTrue(v2.precedes(v1));
        assertFalse(v1.precedes(v2));
    }
    @Test
    public void testFollows(){
        assertTrue(v1.follows(v2));
        assertFalse(v2.follows(v1));
    }
    @Test
    public void testUpperRight(){
        assertEquals(v2.upperRight(v1), v1);
    }
    @Test
    public void testLowerLeft(){
        assertEquals(v1.lowerLeft(v2), v2);
    }
    @Test
    public void testAdd(){
        v2.add(v1);
        assertEquals(v2, new Vector2d(-1,1));
    }
    @Test
    public void testSubtract(){
        assertEquals(v2.subtract(v1), new Vector2d(-3,-3));
    }
    @Test
    public void testOpposite(){
        assertEquals(v2.opposite(), new Vector2d(2,1));
    }

}
