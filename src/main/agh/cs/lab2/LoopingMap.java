package agh.cs.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LoopingMap extends AbstractWorldMap{
    Vector2d top_right;
    Vector2d bottom_left = new Vector2d(0, 0);
    Vector2d jungle_bottom_left;
    Vector2d jungle_top_right;
    List<Grass> grassList = new ArrayList<>();
    HashMap<Vector2d, List<MapEntity>> hashedEntities = new HashMap<>();
    Random random = new Random();

    public void LoopingMap(Vector2d size, int jungle_size){
        top_right = size;
        jungle_top_right = new Vector2d((top_right.x + jungle_size)/2, (top_right.y + jungle_size)/2);
        jungle_bottom_left = new Vector2d((top_right.x - jungle_size)/2, (top_right.y - jungle_size)/2);
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
            if(animal_position.x > top_right.x)
                current_animal.move(new Vector2d(bottom_left.x, animal_position.y));
            if(animal_position.x < bottom_left.x)
                current_animal.move(new Vector2d(top_right.x, animal_position.y));
            if(animal_position.y > top_right.y)
                current_animal.move(new Vector2d(animal_position.x, bottom_left.y));
            if(animal_position.y < bottom_left.y)
                current_animal.move(new Vector2d(animal_position.x, top_right.y));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }


    private void advanceYear(){

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
        if(initial.x+1 <= jungle_top_right.x){
            return new Vector2d(initial.x+1, initial.y);
        }
        if(initial.y+1 >= jungle_bottom_left.y){
            return new Vector2d(jungle_bottom_left.x, initial.y+1);
        }
        return new Vector2d(jungle_bottom_left.x, jungle_top_right.y);
    }

    private Vector2d iterateThroughSavanna(Vector2d initial){
        if(initial.x >= top_right.x && initial.y >= bottom_left.y)
            return new Vector2d(bottom_left.x, top_right.y);
        if(initial.x >= top_right.x)
            return new Vector2d(bottom_left.x, initial.y+1);
        if(initial.x+1 >= jungle_bottom_left.x && initial.x+1 <= jungle_top_right.x && (initial.y <= jungle_top_right.y && initial.y >= jungle_bottom_left.y))
            return new Vector2d(jungle_top_right.x+1, initial.y);
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
        int tempX = random.nextInt(top_right.x);
        int tempY = random.nextInt(top_right.y);
        if(tempX >= jungle_bottom_left.x && tempX <= jungle_top_right.x) {
            tempY = random.nextInt(jungle_bottom_left.y);
            if (random.nextInt() % 2 == 1) {
                tempY += jungle_top_right.y;
            }
        }
        return new Vector2d(tempX, tempY);
    }

    private Vector2d randomJunglePosition(){
        int tempX = random.nextInt(jungle_top_right.x-jungle_bottom_left.x) + jungle_bottom_left.x;
        int tempY = random.nextInt(jungle_top_right.y-jungle_bottom_left.y) + jungle_bottom_left.y;
        return new Vector2d(tempX, tempY);
    }
}
