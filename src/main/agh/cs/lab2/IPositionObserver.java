package agh.cs.lab2;


public interface IPositionObserver {

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, MapEntity o);
        // Observes changes in position of observed objects
    void funeralTime(MapEntity o);
        // Observes DEATH :o
}
