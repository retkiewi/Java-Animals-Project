package agh.cs.lab2;

import org.junit.Test;

import  static org.junit.Assert.*;

public class IWorldMapTest {

    IWorldMap mapa = new RectangularMap(10, 10);
    Animal kot = new Animal(mapa, new Vector2d(2,7));
    Animal pies = new Animal(mapa);

    @Test
    public void testPlace(){
        mapa.place(kot);
        mapa.place(pies);
        assertEquals(mapa.objectAt(kot.getPosition()),kot);
        assertEquals(mapa.objectAt(pies.getPosition()),pies);
    }

    @Test
    public void testRun(){
        String args[] = {"f", "f"};     //"f", "b", "r", "l", "f", "l", "r", "b", "f", "f", "r", "f", "b", "l", "f", "b"
        mapa.place(kot);
        mapa.place(pies);
        assertTrue(mapa.isOccupied(kot.getPosition()));
        assertTrue(mapa.isOccupied(pies.getPosition()));
        mapa.run(OptionsParser.parseToMapDirection(args));
        assertEquals(mapa.objectAt(new Vector2d(2,8)),kot);
        assertEquals(mapa.objectAt(new Vector2d(2,3)),pies);
        String args2[] = {"b", "b"};
        mapa.run(OptionsParser.parseToMapDirection(args2));
        assertEquals(mapa.objectAt(new Vector2d(2,7)),kot);
        assertEquals(mapa.objectAt(new Vector2d(2,2)),pies);
        String args3[] = {"r", "r", "f", "f"};
        mapa.run(OptionsParser.parseToMapDirection(args3));
        assertEquals(mapa.objectAt(new Vector2d(3,7)),kot);
        assertEquals(mapa.objectAt(new Vector2d(3,2)),pies);
    }
}
