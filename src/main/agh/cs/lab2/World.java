package agh.cs.lab2;

import java.util.concurrent.TimeUnit;

public class World {
    public static void main(String args[]) {
        try {
            LoopingMap projectSpace = new LoopingMap(100, 30, 10, 20);
            EvolvingAnimal animal1 = new EvolvingAnimal(new Vector2d(5,5), TurningDirection.NORTH, 100, projectSpace);
            EvolvingAnimal animal2 = new EvolvingAnimal(new Vector2d(14,25), TurningDirection.NORTH, 100, projectSpace);
            while(true){
                projectSpace.advanceYear();
                TimeUnit.MILLISECONDS.sleep(50);
            }
        }catch(IllegalArgumentException | InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
