package agh.cs.lab2;

public class World {
    public static void main(String args[]) {
        try {
            MoveDirection[] directions = new OptionsParser().parseToMapDirection(args);
            IWorldMap map = new GrassField(20);

            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3, 4)));
            System.out.println(map.toString());
            map.run(directions);
            System.out.println(map.toString());
        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }
}
