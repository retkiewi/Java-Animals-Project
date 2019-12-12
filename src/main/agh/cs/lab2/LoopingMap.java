package agh.cs.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LoopingMap extends AbstractWorldMap{
    Vector2d topRight;
    Vector2d bottomLeft = new Vector2d(0, 0);
    Vector2d jungleBottomLeft;
    Vector2d jungleTopRight;
    List<Grass> grassList = new ArrayList<>();
    HashMap<Vector2d, List<MapEntity>> hashedEntities = new HashMap<>();
    Random random = new Random();
    SwingMapVisualizer visualizer;

    LoopingMap(Vector2d size, int jungle_size){
        topRight = size;
        jungleTopRight = new Vector2d((topRight.x + jungle_size)/2-1, (topRight.y + jungle_size)/2-1);
        jungleBottomLeft = new Vector2d((topRight.x - jungle_size)/2, (topRight.y - jungle_size)/2);
        visualizer = new SwingMapVisualizer(this, this.bottomLeft, this.topRight, this.jungleBottomLeft, this.jungleTopRight);
    }

    public List<MapEntity> objectsAt(Vector2d position){
        List<Object> objects_at = new ArrayList<>();
        if(hashedEntities.containsKey(position)){
            return hashedEntities.get(position);
        }
        return null;
    }

    @Override
    public void run(MoveDirection[] directions) {
        for(int i=0; i < directions.length; i++){
            Animal current_animal = animals.get(Math.floorMod(i, animals.size()));
            current_animal.move(directions[i]);
            Vector2d animal_position = current_animal.getPosition();
            if(animal_position.x > topRight.x)
                current_animal.move(new Vector2d(bottomLeft.x, animal_position.y));
            if(animal_position.x < bottomLeft.x)
                current_animal.move(new Vector2d(topRight.x, animal_position.y));
            if(animal_position.y > topRight.y)
                current_animal.move(new Vector2d(animal_position.x, bottomLeft.y));
            if(animal_position.y < bottomLeft.y)
                current_animal.move(new Vector2d(animal_position.x, topRight.y));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }


    public void advanceYear(){
        growGrassJungle();
        growGrassSavanna();
        //moveAnimals();
    }

    

    private boolean growGrassSavanna(){
        Vector2d position = randomSavannaPosition();
        if (growGrassAt(position)){
            return true;
        }
        Vector2d temp = position;
        position = iterateThroughSavanna(position);
        while(position != temp){
            if(growGrassAt(position))
                return true;
            position = iterateThroughSavanna(position);
        }

        return false;
    }

    private boolean growGrassJungle() {
        Vector2d position = randomJunglePosition();
        if (growGrassAt(position)){
            return true;
        }
        Vector2d temp = position;
        position = iterateThroughJungle(position);
        while(position != temp){
            if(growGrassAt(position))
                return true;
            position = iterateThroughJungle(position);
        }

        return false;
    }

    private Vector2d iterateThroughJungle(Vector2d initial){
        if(initial.x+1 <= jungleTopRight.x){
            return new Vector2d(initial.x+1, initial.y);
        }
        if(initial.y+1 >= jungleBottomLeft.y){
            return new Vector2d(jungleBottomLeft.x, initial.y+1);
        }
        return new Vector2d(jungleBottomLeft.x, jungleTopRight.y);
    }

    private Vector2d iterateThroughSavanna(Vector2d initial){
        if(initial.x >= topRight.x && initial.y >= bottomLeft.y)
            return new Vector2d(bottomLeft.x, topRight.y);
        if(initial.x >= topRight.x)
            return new Vector2d(bottomLeft.x, initial.y+1);
        if(initial.x+1 >= jungleBottomLeft.x && initial.x+1 <= jungleTopRight.x && (initial.y <= jungleTopRight.y && initial.y >= jungleBottomLeft.y))
            return new Vector2d(jungleTopRight.x+1, initial.y);
        return new Vector2d(initial.x+1, initial.y);
    }

    private boolean growGrassAt(Vector2d position){
        if(objectAt(position) == null){
            Grass temp = new Grass(position);
            return true;
        }
        return false;
    }

    private Vector2d randomSavannaPosition(){
        int tempX = random.nextInt(topRight.x);
        int tempY = random.nextInt(topRight.y);
        if(tempX >= jungleBottomLeft.x && tempX <= jungleTopRight.x) {
            tempY = random.nextInt(jungleBottomLeft.y);
            if (random.nextInt() % 2 == 1) {
                tempY += jungleTopRight.y;
            }
        }
        return new Vector2d(tempX, tempY);
    }

    private Vector2d randomJunglePosition(){
        int tempX = random.nextInt(jungleTopRight.x-jungleBottomLeft.x) + jungleBottomLeft.x;
        int tempY = random.nextInt(jungleTopRight.y-jungleBottomLeft.y) + jungleBottomLeft.y;
        return new Vector2d(tempX, tempY);
    }
}
