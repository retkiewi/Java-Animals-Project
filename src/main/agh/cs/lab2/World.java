package agh.cs.lab2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class World {
    public static void main(String args[]) {
        try {
            int xSize = 80;
            int ySize = 30;
            int jungleSize = 12;
            int grassEnergy = 15;
            Random random = new Random();
            LoopingMap projectSpace = new LoopingMap(xSize, ySize, jungleSize, grassEnergy);
            for(int i=0; i<20; i++)
                new EvolvingAnimal(new Vector2d(random.nextInt(xSize),random.nextInt(ySize)), TurningDirection.NORTH, grassEnergy*5, projectSpace);
            while(true){
                projectSpace.advanceYear();
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }catch(IllegalArgumentException | InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
