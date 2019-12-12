
package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

public class GrassFieldTest {

    public Vector2d test_vect = new Vector2d(4, 4);
    public int n = 10;
    public Vector2d large_vector = new Vector2d(10*n, 10*n);
    public GrassField trawnik = new GrassField(n);
    public Animal krowa = new Animal(trawnik, test_vect);
    public Grass trawa = new Grass(large_vector);

    @Test
    public void isOccupiedTest() {
        try {
            Assert.assertFalse(trawnik.isOccupied(test_vect));
            trawnik.place(krowa);
            Assert.assertTrue(trawnik.isOccupied(test_vect));
        }catch(IllegalArgumentException ex){}
    }

    @Test
    public void canMoveToTest() {
        try {
            trawnik.place(krowa);
            Assert.assertTrue(trawnik.canMoveTo(large_vector));
            Assert.assertFalse(trawnik.canMoveTo(krowa.getPosition()));
        }catch(IllegalArgumentException ex){}
    }

    @Test
    public void objectAtTest(){
        trawnik.place(krowa);
        Assert.assertEquals(krowa, trawnik.objectAt(krowa.getPosition()));
        trawnik.growGrass(large_vector);
        Assert.assertTrue(trawa.equals(trawnik.objectAt(large_vector)));
    }

}
