package agh.cs.lab2;

import java.util.concurrent.TimeUnit;

public class World {
    public static void main(String args[]) {
        try {
            LoopingMap projectSpace = new LoopingMap(1000, 1000, 10);
            while(true){
                projectSpace.advanceYear();
                TimeUnit.MILLISECONDS.sleep(5);
            }
        }catch(IllegalArgumentException | InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
