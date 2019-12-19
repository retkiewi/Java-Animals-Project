/*package agh.cs.lab2;

public class RectangularMap extends AbstractWorldMap{

    private final int height;
    private final int width;

    public RectangularMap(int height, int width){
        this.height=height;
        this.width=width;

    }



    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!isOccupied(position) && position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width, height)) ){
            return true;
        }
        return false;
    }







    @Override
    public Object objectAt(Vector2d position) {
        return animalHashMap.get(position);
    }

    @Override
    public String toString(){
        MapVisualizer mapa = new MapVisualizer(this);
        String drawing  = mapa.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
        return drawing;
    }
}
*/