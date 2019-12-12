package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SwingMapVisualizer implements IPositionObserver{
    JFrame f = new JFrame();
    List<Tile> tileSet;
    Vector2d topRight;
    LoopingMap map;
    SwingMapVisualizer(LoopingMap map, Vector2d bottomLeft, Vector2d topRight, Vector2d jungleBottomLeft, Vector2d jungleTopRight){
        this.topRight = topRight;
        f.setSize(topRight.x*15,topRight.y*15);
        GridLayout tilesGrid = new GridLayout(topRight.y, topRight.x, 1, 1);
        f.setLayout(tilesGrid);
        tileSet = new ArrayList<>();
        for(int i=0; i<topRight.x*topRight.y; i++){
            boolean isJungle = false;
            if(i%topRight.x >= jungleBottomLeft.x && i%topRight.x <= jungleTopRight.x && i/topRight.x >= jungleBottomLeft.y && i/topRight.x <= jungleTopRight.y)
                isJungle = true;
            Tile tile = new Tile(isJungle);
            f.add(tile);
            tileSet.add(tile);
        }
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        this.map = map;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, MapEntity o) {
        List<MapEntity> objectsAtOld = map.objectsAt(oldPosition);
        if(objectsAtOld.isEmpty())
            tileSet.get(vectorToInt(oldPosition)).removeTile();
        else
            drawAnimal(oldPosition, objectsAtOld.get(0));
        drawAnimal(newPosition, map.objectsAt(newPosition).get(0));
    }

    private void drawAnimal(Vector2d position, MapEntity entity){

    }

    public void grassGrown(Vector2d position){
        tileSet.get(vectorToInt(position)).growGrass();
    }

    private int vectorToInt(Vector2d position){
        return position.y*topRight.x+position.x;
    }
}
