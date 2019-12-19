package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel{
    boolean isJungle;
    ImageIcon biome;

    Tile(boolean isJungle){

        if(isJungle){
            biome = new ImageIcon(this.getClass().getResource("JungleEmpty.png"));

        }
        else{
            biome = new ImageIcon(this.getClass().getResource("SavannaEmpty.png"));

        }
        setIcon(biome);
    }
    public void growGrass(){
        ImageIcon grass = new ImageIcon(this.getClass().getResource("GrownGrass.png"));
        setIcon(grass);
    }
    public void clearTile(){
        setIcon(biome);
    }

    public void drawAnimal(){
        ImageIcon animalIcon = new ImageIcon(this.getClass().getResource("animal.png"));
        this.setIcon(animalIcon);
    }
}
