package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SwingMapVisualizer{
    JFrame f = new JFrame("Map");
    List<List<Tile>> tileSet;
    Vector2d topRight;
    LoopingMap map;
    SwingMapVisualizer(LoopingMap map, Vector2d bottomLeft, Vector2d topRight, Vector2d jungleBottomLeft, Vector2d jungleTopRight){
        this.topRight = topRight;
        f.setSize(topRight.x*17-1,topRight.y*17-1);
        GridLayout tilesGrid = new GridLayout(topRight.y+2, topRight.x+2, 1, 1);
        f.setLayout(tilesGrid);
        tileSet = new ArrayList<>();
        for(int i=-1; i<=topRight.y; i++){
            tileSet.add(new ArrayList<>());
            for(int j = -1; j<=topRight.x; j++){
                if(i<0){
                    f.add(new JLabel(String.valueOf(j+1)));
                }else if(j<0){
                    f.add(new JLabel(String.valueOf(i+1)));
                }else {
                    boolean isJungle = false;
                    if (i >= jungleBottomLeft.y && j >= jungleBottomLeft.x && i <= jungleTopRight.y && j <= jungleTopRight.x)
                        isJungle = true;
                    Tile tile = new Tile(isJungle);
                    f.add(tile);
                    tileSet.get(i).add(tile);
                }
            }
        }
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        this.map = map;
    }

    public void updateTile(Vector2d position, MapEntity objectAt){
        if(objectAt == null)
            tileSet.get(position.y).get(position.x).clearTile();
        else{
            tileSet.get(position.y).get(position.x).drawAnimal(((EvolvingAnimal) objectAt),map.animalMaxEnergy); }
    }


    public void grassGrown(Vector2d position){
        tileSet.get(position.y).get(position.x).growGrass();
    }

    private int vectorToInt(Vector2d position){
        return position.y*topRight.x+position.x;
    }


}
