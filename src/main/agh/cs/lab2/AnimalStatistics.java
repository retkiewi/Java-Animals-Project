package agh.cs.lab2;

import javax.swing.*;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;

public class AnimalStatistics extends JPanel {

    LabelWithNameTag averageNumberOfChildren;
    LabelWithNameTag averageEnergy;
    LabelWithNameTag averageAgeOfDeath;
    LabelWithNameTag numberOfAnimals;
    LabelWithNameTag quantityOfGrass;
    LabelWithNameTag epoch;
    LoopingMap dataSet;

    public AnimalStatistics(LoopingMap dataSet){
        GridLayout layout = new GridLayout(3,2,10,10);
        this.setLayout(layout);
        this.dataSet = dataSet;
        this.setSize(new Dimension(300, 600));

        averageEnergy = new LabelWithNameTag(calculateAverageEnergy(), "Avg. Energy:");
        averageAgeOfDeath = new LabelWithNameTag(((float)Math.round(100*dataSet.sumOfAgeOfDeath/(dataSet.deathCounter==0?1:dataSet.deathCounter)))/100, "Avg. Age of Death:");
        averageNumberOfChildren = new LabelWithNameTag(calculateAverageNumberOfChildren(), "Avg. Num. of Children:");
        numberOfAnimals = new LabelWithNameTag(dataSet.animalList.size(), "Num. of Animals");
        quantityOfGrass = new LabelWithNameTag(dataSet.grassList.size(), "Quantity of Grass:");
        epoch = new LabelWithNameTag(dataSet.epoch, "Epoch: ");

        this.add(numberOfAnimals);
        this.add(quantityOfGrass);
        this.add(averageAgeOfDeath);
        this.add(averageEnergy);
        this.add(averageNumberOfChildren);
        this.add(epoch);
        this.setVisible(true);
    }

    public void update(){
        averageEnergy.setValue(calculateAverageEnergy());
        averageAgeOfDeath.setValue(((float)Math.round(100*dataSet.sumOfAgeOfDeath/(dataSet.deathCounter==0?1:dataSet.deathCounter)))/100);
        averageNumberOfChildren.setValue(calculateAverageNumberOfChildren());
        numberOfAnimals.setValue(dataSet.animalList.size());
        quantityOfGrass.setValue(dataSet.grassList.size());
        epoch.setValue(dataSet.epoch);
    }

    private float calculateAverageNumberOfChildren(){
        float temp = 0;
        for(EvolvingAnimal animal : dataSet.animalList){
            temp+=animal.numberOfChildren;
        }
        return ((float)Math.round(100*temp/dataSet.animalList.size()))/100;
    }

    private float calculateAverageEnergy(){
        float temp = 0;
        for(EvolvingAnimal animal : dataSet.animalList){
            temp+=animal.energy;
        }
        return ((float)Math.round(100*temp/dataSet.animalList.size()))/100;
    }
}
