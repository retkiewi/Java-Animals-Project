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

    LoopingMap(int xSize, int ySize, int jungle_size){
        topRight = new Vector2d(xSize-1, ySize-1);

        jungleTopRight = new Vector2d((topRight.x + jungle_size)/2, (topRight.y + jungle_size)/2);
        jungleBottomLeft = new Vector2d((topRight.x - jungle_size)/2+1, (topRight.y - jungle_size)/2+1);
        visualizer = new SwingMapVisualizer(this, this.bottomLeft, this.topRight, this.jungleBottomLeft, this.jungleTopRight);
    }

    public List<MapEntity> objectsAt(Vector2d position){
        List<MapEntity> objects_at = new ArrayList<>();
        if(hashedEntities.containsKey(position)){
            return hashedEntities.get(position);
        }
        return objects_at;
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
        while(!growGrassAt(position)){
            position = randomSavannaPosition();
        }
        visualizer.grassGrown(position);
        return true;
    }

    private boolean growGrassJungle() {
        Vector2d position = randomJunglePosition();
        if (growGrassAt(position)){
            visualizer.grassGrown(position);
            return true;
        }
        Vector2d temp = position;
        position = iterateThroughJungle(position);
        while(!temp.equals(position)){
            if(growGrassAt(position)){
                visualizer.grassGrown(position);
                return true;
            }
            position = iterateThroughJungle(position);
        }

        return false;
    }

    private Vector2d iterateThroughJungle(Vector2d initial){
        if(initial.x+1 <= jungleTopRight.x){
            return new Vector2d(initial.x+1, initial.y);
        }
        if(initial.y-1 >= jungleBottomLeft.y){
            return new Vector2d(jungleBottomLeft.x, initial.y-1);
        }
        return new Vector2d(jungleBottomLeft.x, jungleTopRight.y);
    }



    private boolean growGrassAt(Vector2d position){
        if(objectsAt(position).size()==0){
            Grass temp = new Grass(position);
            if(hashedEntities.get(position)==null){
                List<MapEntity> temp2 = new ArrayList<>();
                temp2.add(temp);
                hashedEntities.put(position, temp2);
            }else{
                hashedEntities.get(position).add(temp);
            }
            return true;
        }
        return false;
    }

    private Vector2d randomSavannaPosition(){

        int tempX = random.nextInt(topRight.x+1);
        int tempY = random.nextInt(topRight.y+1);
        if(tempX >= jungleBottomLeft.x && tempX <= jungleTopRight.x) {
            tempY = random.nextInt(jungleBottomLeft.y);
            if (random.nextInt() % 2 == 1) {
                tempY += jungleTopRight.y+1;
            }
        }
        return new Vector2d(tempX, tempY);

    }

    private Vector2d randomJunglePosition(){
        int tempX = random.nextInt(jungleTopRight.x-jungleBottomLeft.x+1) + jungleBottomLeft.x;
        int tempY = random.nextInt(jungleTopRight.y-jungleBottomLeft.y+1) + jungleBottomLeft.y;
        return new Vector2d(tempX, tempY);
    }
}
