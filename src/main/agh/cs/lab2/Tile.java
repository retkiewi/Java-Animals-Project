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
    public void removeTile(){
        setIcon(biome);
    }
    public void animalShowedUp(Animal animal){
        switch (animal.orientation){
            case NORTH: {
                ImageIcon animalNorth = new ImageIcon(this.getClass().getResource("animalNorth.png"));
                setIcon(animalNorth);
            }
            case SOUTH: {
                ImageIcon animalSouth = new ImageIcon(this.getClass().getResource("animalSouth.png"));
                setIcon(animalSouth);
            }
            case WEST: {
                ImageIcon animalWest = new ImageIcon(this.getClass().getResource("animalWest.png"));
                setIcon(animalWest);
            }
            case EAST: {
                ImageIcon animalEast = new ImageIcon(this.getClass().getResource("animalEast.png"));
                setIcon(animalEast);
            }
        }
    }
}
