package agh.cs.lab2;


import javafx.util.Pair;

import java.util.*;

public class LoopingMap implements IPositionObserver{
    Vector2d topRight;
    Vector2d bottomLeft = new Vector2d(0, 0);
    Vector2d jungleBottomLeft;
    Vector2d jungleTopRight;
    List<Grass> grassList = new ArrayList<>();
    List<EvolvingAnimal> animalList = new ArrayList<>();
    HashMap<Vector2d, List<MapEntity>> hashedEntities = new HashMap<>();
    Random random = new Random();
    SwingMapVisualizer visualizer;
    HashMap<Vector2d, List<Event>> events = new HashMap<>();
    int grassEnergy;


    LoopingMap(int xSize, int ySize, int jungle_size, int grassEnergy){
        topRight = new Vector2d(xSize-1, ySize-1);
        this.grassEnergy=grassEnergy;
        jungleTopRight = new Vector2d((topRight.x + jungle_size)/2, (topRight.y + jungle_size)/2);
        jungleBottomLeft = new Vector2d((topRight.x - jungle_size)/2+1, (topRight.y - jungle_size)/2+1);
        visualizer = new SwingMapVisualizer(this, this.bottomLeft, this.topRight, this.jungleBottomLeft, this.jungleTopRight);
    }

    public List<MapEntity> objectsAt(Vector2d position){

        if(!hashedEntities.containsKey(position)){
            hashedEntities.put(position, new ArrayList<>());
        }
        return hashedEntities.get(position);
    }


    public void advanceYear(){
        growGrassJungle();
        growGrassSavanna();
        moveAnimals();
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

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, MapEntity o) {
        if(oldPosition.x >=0 && oldPosition.y >=0){
            hashedEntities.get(oldPosition).remove(o);
            if(hashedEntities.get(oldPosition)==null || hashedEntities.get(oldPosition).size()==0)
                visualizer.updateTile(oldPosition, null);
        }else{
            animalList.add((EvolvingAnimal) o);
        }
        if(newPosition.x<bottomLeft.x || newPosition.x > topRight.x || newPosition.y<bottomLeft.y || newPosition.y > topRight.y){
            newPosition = loop(newPosition);
            ((EvolvingAnimal) o).moveTo(newPosition);
        }

        if(hashedEntities.get(newPosition)==null){
            List<MapEntity> temp = new ArrayList<>();
            temp.add(o);
            hashedEntities.put(newPosition, temp);
        }else{
            if(hashedEntities.get(newPosition).get(0).getClass() != EvolvingAnimal.class)
                eatingTime(newPosition);
            else
                breedingTime(newPosition);
            hashedEntities.get(newPosition).add(o);
        }

        visualizer.updateTile(newPosition, getStrongestAnimal(hashedEntities.get(newPosition)));
    }

    @Override
    public void funeralTime(MapEntity o) {
        if(events.get(o.position) == null){
            List<Event> temp = new ArrayList<>();
            temp.add(Event.DIE);
            events.put(o.position,temp);
        }
        else{
            if(!events.get(o.position).contains(Event.DIE))
                events.get(o.position).add(Event.DIE);
        }
    }

    private void breedingTime(Vector2d position){
        if(events.get(position) == null){
            List<Event> temp = new ArrayList<>();
            temp.add(Event.BREED);
            events.put(position,temp);
        }
        else{
            if(!events.get(position).contains(Event.BREED))
                events.get(position).add(Event.BREED);
        }
    }

    private void eatingTime(Vector2d position){
        if(events.get(position) == null){
            List<Event> temp = new ArrayList<>();
            temp.add(Event.EAT);
            events.put(position,temp);
        }
        else{
            if(!events.get(position).contains(Event.EAT))
                events.get(position).add(Event.EAT);
        }
    }

    private void breed(Vector2d position){
        List<EvolvingAnimal> temp = getAnimalsByStrenght(position);
        for (EvolvingAnimal animal:temp) {
            if(animal.age < 18 || animal.energy<1.5*grassEnergy)
                temp.remove(animal);
        }
        for(int i=1; i<temp.size(); i+=2){
            new EvolvingAnimal(this, temp.get(i), temp.get(i-1));
        }
    }

    private void eat(Vector2d position){
        List<MapEntity> temp = strongestAnimals(position);
        for (MapEntity animal : temp) {
            ((EvolvingAnimal)animal).energy+=grassEnergy;
        }
        MapEntity tempGrass = hashedEntities.get(position).get(0);
        grassList.remove(tempGrass);
        hashedEntities.get(position).remove(tempGrass);
    }

    private List<EvolvingAnimal> getAnimalsByStrenght(Vector2d position){
        List<MapEntity> temp = hashedEntities.get(position);
        List<EvolvingAnimal> strongestAnimals = new ArrayList<>();
        do{
            strongestAnimals.add(((EvolvingAnimal) getStrongestAnimal(temp)));
            if(strongestAnimals.get(strongestAnimals.size()-1) == null){
                strongestAnimals.remove(strongestAnimals.size()-1);
                break;
            }
            temp = temp.subList(1, temp.size());
        }while(temp.size()>0);
        return strongestAnimals;
    }

    private List<MapEntity> strongestAnimals(Vector2d position){
        List<MapEntity> temp = hashedEntities.get(position);
        List<MapEntity> strongestAnimals = new ArrayList<>();
        strongestAnimals.add(getStrongestAnimal(temp));
        if(strongestAnimals.get(strongestAnimals.size()-1) == null){
            strongestAnimals.remove(strongestAnimals.size()-1);
            return strongestAnimals;
        }
        temp = temp.subList(1, temp.size());
        do{
            strongestAnimals.add(getStrongestAnimal(temp));
            if(strongestAnimals.get(strongestAnimals.size()-1) == null){
                strongestAnimals.remove(strongestAnimals.size()-1);
                break;
            }
            temp = temp.subList(1, temp.size());
        }while(temp.size()>0 && strongestAnimals.get(strongestAnimals.size()-1)==strongestAnimals.get(strongestAnimals.size()-2));
        return strongestAnimals;
    }

    private MapEntity getStrongestAnimal(List<MapEntity> animals){
        MapEntity temp = animals.get(0);
        for(MapEntity entity : animals){
            if(entity.getClass() == EvolvingAnimal.class){
                if(temp.getClass() != EvolvingAnimal.class || ((EvolvingAnimal) temp).energy < ((EvolvingAnimal) entity).energy){
                    temp = entity;
                }
            }
        }
        if(temp.getClass() == EvolvingAnimal.class)
            return temp;
        return null;
    }

    private void moveAnimals(){
        for(EvolvingAnimal animal : animalList){
            animal.move();
        }
    }
    private Vector2d loop(Vector2d position){
        int tempX = position.x;
        int tempY = position.y;
        if(position.x<bottomLeft.x || position.x > topRight.x){
            tempX = position.x%topRight.x;
            if(tempX < 0)
                tempX+=topRight.x+1;
            else
                tempX--;
        }
        if(position.y<bottomLeft.y || position.y > topRight.y){
            tempY = position.y%topRight.y;
            if(tempY < 0)
                tempY+=topRight.y+1;
            else
                tempY--;
        }
        return new Vector2d(tempX, tempY);
    }
}
