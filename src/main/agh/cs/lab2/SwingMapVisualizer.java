package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SwingMapVisualizer implements IPositionObserver{
    JFrame f = new JFrame();
    List<List<Tile>> tileSet;
    Vector2d topRight;
    LoopingMap map;
    SwingMapVisualizer(LoopingMap map, Vector2d bottomLeft, Vector2d topRight, Vector2d jungleBottomLeft, Vector2d jungleTopRight){
        this.topRight = topRight;
        f.setSize(topRight.x*15,topRight.y*15);
        GridLayout tilesGrid = new GridLayout(topRight.y+1, topRight.x+1, 1, 1);
        f.setLayout(tilesGrid);
        tileSet = new ArrayList<>();
        for(int i=0; i<=topRight.y; i++){
            tileSet.add(new ArrayList<>());
            for(int j = 0; j<=topRight.x; j++){
            boolean isJungle = false;
            if(i>=jungleBottomLeft.y && j>=jungleBottomLeft.x && i<=jungleTopRight.y && j<=jungleTopRight.x)
                isJungle = true;
            Tile tile = new Tile(isJungle);
            f.add(tile);
            tileSet.get(i).add(tile);
            }
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
            tileSet.get(oldPosition.y).get(oldPosition.x).removeTile();
        else
            drawAnimal(oldPosition, objectsAtOld.get(0));
        drawAnimal(newPosition, map.objectsAt(newPosition).get(0));
    }

    private void drawAnimal(Vector2d position, MapEntity entity){

    }

    public void grassGrown(Vector2d position){
        tileSet.get(position.y).get(position.x).growGrass();
    }

    private int vectorToInt(Vector2d position){
        return position.y*topRight.x+position.x;
    }
}
