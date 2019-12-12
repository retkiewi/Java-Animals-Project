
package agh.cs.lab2;


import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

import  static org.junit.Assert.*;

public class AnimalTest {
    RectangularMap trawka = new RectangularMap(4, 4);

    @Test
    public void testOrientation(){
        Animal kotek = new Animal(trawka);
        assertTrue(kotek.orientation == MapDirection.NORTH);
        kotek.move(MoveDirection.RIGHT);
        assertTrue(kotek.orientation == MapDirection.EAST);
        kotek.move(MoveDirection.RIGHT);
        assertTrue(kotek.orientation == MapDirection.SOUTH);
        kotek.move(MoveDirection.RIGHT);
        assertTrue(kotek.orientation == MapDirection.WEST);
        kotek.move(MoveDirection.RIGHT);
        assertTrue(kotek.orientation == MapDirection.NORTH);
        kotek.move(MoveDirection.LEFT);
        assertTrue(kotek.orientation == MapDirection.WEST);
        kotek.move(MoveDirection.LEFT);
        assertTrue(kotek.orientation == MapDirection.SOUTH);
        kotek.move(MoveDirection.LEFT);
        assertTrue(kotek.orientation == MapDirection.EAST);
        kotek.move(MoveDirection.LEFT);
        assertTrue(kotek.orientation == MapDirection.NORTH);
    }

    @Test
    public void testPosition(){
        Animal piesek = new Animal(trawka);
        assertTrue(piesek.position.equals(new Vector2d(2,2)));
        piesek.move(MoveDirection.FORWARD);
        assertTrue(piesek.position.equals(new Vector2d(2,3)));
        piesek.move(MoveDirection.RIGHT);
        piesek.move(MoveDirection.FORWARD);
        assertTrue(piesek.position.equals(new Vector2d(3,3)));
        piesek.move(MoveDirection.LEFT);
        piesek.move(MoveDirection.BACKWARD);
        assertTrue(piesek.position.equals(new Vector2d(3,2)));
        piesek.move(MoveDirection.RIGHT);
        piesek.move(MoveDirection.BACKWARD);
        assertTrue(piesek.position.equals(new Vector2d(2,2)));
    }

    @Test
    public void testBorders(){
        Animal ptaszek = new Animal(trawka);
        for(int i=0; i<3; i++) {ptaszek.move(MoveDirection.FORWARD);}
        assertTrue(ptaszek.position.equals(new Vector2d(2,4)));

        for(int i=0; i<5; i++) {ptaszek.move(MoveDirection.BACKWARD);}
        assertTrue(ptaszek.position.equals(new Vector2d(2,0)));

        ptaszek.move(MoveDirection.RIGHT);
        for(int i=0; i<3; i++) {ptaszek.move(MoveDirection.FORWARD);}
        assertTrue(ptaszek.position.equals(new Vector2d(4,0)));

        for(int i=0; i<5; i++) {ptaszek.move(MoveDirection.BACKWARD);}
        assertTrue(ptaszek.position.equals(new Vector2d(0,0)));
    }

    @Test
    public void testOptionsParser(){
        String[] args = {"f", "forward", "b","backward","r","right","l","left"};
        MoveDirection[] kierunki = {MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.LEFT,MoveDirection.LEFT};
        MoveDirection[] nowe_kierunki = OptionsParser.parseToMapDirection(args);
        assertTrue(Arrays.equals(kierunki, nowe_kierunki));
    }
}
