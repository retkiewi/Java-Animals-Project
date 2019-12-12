package agh.cs.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap, IPositionObserver{

    int width;
    int height;
    List<Animal> animals = new ArrayList<>();
    HashMap<Vector2d, Animal> animalHashMap = new HashMap<>();
    MapBoundary border = new MapBoundary();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, MapEntity o){
        Animal current_animal = animalHashMap.get(oldPosition);
        animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition, current_animal);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.position)){
            animals.add(animal);
            animalHashMap.put(animal.position, animal);
            border.addToLists(animal);
            animal.addObserver(this);
            animal.addObserver(border);
            return true;
        }
        throw new IllegalArgumentException("Position on the map is allredy occupied!");
    }

    @Override
    public void run(MoveDirection[] directions) {
        for(int i=0; i < directions.length; i++){
            Animal current_animal = animals.get(Math.floorMod(i, animals.size()));
            current_animal.move(directions[i]);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(animalHashMap.get(position) == null)
            return false;
        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        if(isOccupied(position)){
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
        MapVisualizer mapa = new MapVisualizer(this);
        String drawing  = mapa.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
        return drawing;
    }
}
