package agh.cs.lab2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class World {
    public static void main(String args[]) {
        try {
            Menu mainMenu = new Menu(toInts(args));

            while(true){
                while(mainMenu.isPaused){
                    TimeUnit.MILLISECONDS.sleep(50);
                }
                mainMenu.projectSpace.advanceYear();
                mainMenu.statistics.update();
                if(mainMenu.selector!=null && mainMenu.selector.animalData!=null && mainMenu.selector.animalData.animal!=null)
                    mainMenu.selector.animalData.update();
                TimeUnit.MILLISECONDS.sleep(50);
            }
        }catch(IllegalArgumentException | InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public static Integer[] toInts(String args[]){
        int size = args.length;
        Integer [] arr = new Integer[size];
        for(int i=0; i<size; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        return arr;
    }
}
