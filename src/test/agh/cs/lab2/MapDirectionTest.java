package agh.cs.lab2;

import org.junit.Test;

import java.util.Vector;

import  static org.junit.Assert.*;

public class MapDirectionTest {
    public MapDirection n = MapDirection.NORTH;
    public MapDirection s = MapDirection.SOUTH;
    public MapDirection w = MapDirection.WEST;
    public MapDirection e = MapDirection.EAST;

    @Test
    public void next(){
        assertTrue(n.next()==e);
        assertTrue(e.next()==s);
        assertTrue(s.next()==w);
        assertTrue(w.next()==n);
    }

    @Test
    public void previous(){
        assertTrue(e.previous()==n);
        assertTrue(s.previous()==e);
        assertTrue(w.previous()==s);
        assertTrue(n.previous()==w);
    }
}
