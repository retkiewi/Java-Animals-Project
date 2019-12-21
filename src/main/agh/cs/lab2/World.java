package agh.cs.lab2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class World {
    public static void main(String args[]) {
        try {
            int xSize = 80;
            int ySize = 30;
            int jungleSize = 10;
            int grassEnergy = 5;
            int animalMaxEnergy= 100;
            Random random = new Random();
            LoopingMap projectSpace = new LoopingMap(xSize, ySize, jungleSize, grassEnergy, animalMaxEnergy);
            for(int i=0; i<40; i++)
                new EvolvingAnimal(new Vector2d(random.nextInt(xSize),random.nextInt(ySize)), TurningDirection.NORTH, animalMaxEnergy/2, projectSpace);
            while(true){
                projectSpace.advanceYear();
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(IllegalArgumentException | InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
