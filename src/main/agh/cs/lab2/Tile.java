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

    public void drawAnimal(EvolvingAnimal animal, int animalMaxEnergy){
        ImageIcon animalIcon;
        if(animal.energy<animalMaxEnergy/4)
            animalIcon = new ImageIcon(this.getClass().getResource("animalLowEnergy.png"));
        else if(animal.energy<animalMaxEnergy/2)
            animalIcon = new ImageIcon(this.getClass().getResource("animalMediumEnergy.png"));
        else
            animalIcon = new ImageIcon(this.getClass().getResource("animalHighEnergy.png"));

        this.setIcon(animalIcon);

    }

}
