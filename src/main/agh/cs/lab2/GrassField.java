package agh.cs.lab2;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    private Vector2d bottom_left = new Vector2d(0,0);
    private Vector2d upper_right;
    private final int n;

    HashMap<Vector2d, Grass> hashed_lawn = new HashMap<>();

    GrassField(int n){
        this.n = n;
        this.upper_right = new Vector2d((int)java.lang.Math.sqrt(n*10), (int)java.lang.Math.sqrt(n*10));

        for(int i = 0; i<n; i++) {
            this.growGrassRandom();
        }
    }

    private void growGrassRandom(){
        int rand_x;
        int rand_y;
        do {
            Random losuj = new Random();
            rand_x = (java.lang.Math.abs(losuj.nextInt()) % (this.upper_right.x - this.bottom_left.x)) + this.bottom_left.x;
            rand_y = (java.lang.Math.abs(losuj.nextInt()) % (this.upper_right.y - this.bottom_left.y)) + this.bottom_left.y;
        }while (!growGrass(new Vector2d(rand_x, rand_y)));

    }

    public boolean growGrass(Vector2d position){
        if(this.canGrow(position)){
            Grass grass = new Grass(position);
            hashed_lawn.put(position, grass);
            border.addToLists(grass);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position){
        if(animalHashMap.get(position) != null)
            return animalHashMap.get(position);

        return hashed_lawn.get(position);
    }




    @Override
    public boolean place(Animal animal) {
        if(this.isOccupied(animal.position)) {
            return false;
        }
        animals.add(animal);
        animalHashMap.put(animal.position, animal);
        updateScope(animal.position);
        return true;
    }



    public boolean canGrow(Vector2d position){
        if(hashed_lawn.get(position) == null)
            return true;
        return false;
    }

    private void updateScope(Vector2d position){
        this.bottom_left = this.bottom_left.min_coordinates(position);
        this.upper_right = this.upper_right.max_coordinates(position);
    }

    public String toString(){
        MapVisualizer temp = new MapVisualizer(this);
        return temp.draw(this.bottom_left, this.upper_right);
    }
}
