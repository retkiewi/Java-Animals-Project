package agh.cs.lab2;

public class World {
    public static void main(String args[]) {
        try {
            LoopingMap projectSpace = new LoopingMap(new Vector2d(50, 30), 14);
            projectSpace.advanceYear();
        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }
}
