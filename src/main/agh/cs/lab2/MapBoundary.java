/*package agh.cs.lab2;

import java.util.*;

public class MapBoundary implements IPositionObserver{


    public SortedSet<MapEntity> setX = new TreeSet<>(new Comparator<MapEntity>() {
        @Override
        public int compare(MapEntity o1, MapEntity o2) {
            if(o1.position.x > o2.position.x){return 1;}
            if(o1.position.x == o2.position.x && o1.position.y > o2.position.y){return 1;}
            if(o1.position.x == o2.position.x && o1.position.y == o2.position.y && o1.getClass() == Animal.class && o2.getClass() != Animal.class){return 1;}
            if(o1.position.x == o2.position.x && o1.position.y == o2.position.y && o1.getClass() == o2.getClass()){return 0;}
            return -1;
        }
    });

    public SortedSet<MapEntity> setY = new TreeSet<>(new Comparator<MapEntity>() {
        @Override
        public int compare(MapEntity o1, MapEntity o2) {
            if(o1.position.y > o2.position.y){return 1;}
            if(o1.position.y == o2.position.y && o1.position.x > o2.position.x){return 1;}
            if(o1.position.x == o2.position.x && o1.position.y == o2.position.y && o1.getClass() == Animal.class && o2.getClass() != Animal.class){return 1;}
            if(o1.position.x == o2.position.x && o1.position.y == o2.position.y && o1.getClass() == o2.getClass()){return 0;}
            return -1;
        }
    });
    Comparator comparatorX = setX.comparator();
    Comparator comparatorY = setY.comparator();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, MapEntity o) {
        if(comparatorX.compare(setX.last(), o)>0 || comparatorX.compare(setX.first(), o)<0){
            setX.remove(o);
            setX.add(o);
        }

        if(comparatorX.compare(setY.first(), o)<0 || comparatorX.compare(setY.last(), o)>0){
            setY.remove(o);
            setY.add(o);
        }
        return;
    }
    public void addToLists(MapEntity o){
        this.setX.add(o);
        this.setY.add(o);
    }
}
*/