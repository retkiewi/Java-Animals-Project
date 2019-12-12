package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SwingMapVisualizer {
    JFrame f = new JFrame();
    ArrayList<Tile> tileSet;
    SwingMapVisualizer(LoopingMap map, Vector2d bottomLeft, Vector2d topRight, Vector2d jungleBottomLeft, Vector2d jungleTopRight){
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
    }

}
