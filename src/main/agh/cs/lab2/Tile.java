package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel{
    boolean isJungle;
    ImageIcon biome;
    ImageIcon grass = new ImageIcon(this.getClass().getResource("GrownGrass.png"));
    Tile(boolean isJungle){

        if(isJungle){
            biome = new ImageIcon(this.getClass().getResource("JungleEmpty.png"));

        }
        else{
            biome = new ImageIcon(this.getClass().getResource("SavannaEmpty.png"));

        }
        setIcon(biome);
    }
    public void GrowGrass(){
        setIcon(grass);
    }
    public void RemoveGrass(){
        setIcon(biome);
    }
}
