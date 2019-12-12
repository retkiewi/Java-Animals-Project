package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public class Animal extends MapEntity{


    public static final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    public MapDirection orientation;

    public IWorldMap map;
    private List<IPositionObserver> observers_list = new ArrayList<>();

    public void addObserver(IPositionObserver observer){
        observers_list.add(observer);
    }

    public void removeObserver(IPositionObserver observer){
        observers_list.remove(observer);
    }

    private void notifyObservers(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionObserver observer : this.observers_list){
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }


    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    public Animal(IWorldMap map) {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toLongString() {
        return "position: (" + this.position.x + "," + this.position.y + ") orientation: " + this.orientation;
    }

    public String toString() {
        switch (this.orientation) {
            case NORTH:
                return "^";
            case SOUTH:
                return "v";
            case EAST:
                return ">";
            case WEST:
                return "<";
            default:
                return "X";
        }
    }

    public void move(MoveDirection direction) {
        if (direction == MoveDirection.RIGHT) {
            this.orientation = this.orientation.next();
        } else if (direction == MoveDirection.LEFT) {
            this.orientation = this.orientation.previous();
        } else {
            Vector2d unitvect = this.orientation.toUnitVector();

            if (direction == MoveDirection.BACKWARD) {
                unitvect = unitvect.opposite();
            }
            unitvect = unitvect.add(this.position);

            if (map.canMoveTo(unitvect)) {
                notifyObservers(this.position, unitvect);
                this.position = unitvect;
            }
        }
    }

    public void move(Vector2d newPosition){
        if(map.canMoveTo(newPosition))
            this.position = newPosition;
    }


}
