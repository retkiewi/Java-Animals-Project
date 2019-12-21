package agh.cs.lab2;

import javax.swing.*;

public class AnimalAdvancedData extends JPanel {

    Box verticalBox;
    int initialChildren;
    int initialAge;
    //Wszystkie te wartości odnoszą się do zmian które zaszły podczas obserwacji osobnika.
    LabelWithNameTag increaseInChildren;
    LabelWithNameTag age;
    LabelWithNameTag epochOfDeath;
    LabelWithNameTag energy;
    JLabel position;
    JLabel genome;
    EvolvingAnimal animal;
    boolean diedFlag = false;
    //LabelWithNameTag increaseinChildrenAncestors;        -Nie jest używany ze względu na problemy jakie wywołało by jego generowanie(musielibyśmy przechowywać w pamięci wszystkie zwierzęta, nawet te martwe).
    public AnimalAdvancedData(EvolvingAnimal animal){
        if(animal==null){
            return;
        }
        this.animal = animal;
        this.setSize(650, 400);
        verticalBox = Box.createVerticalBox();
        initialAge = animal.age;
        initialChildren = animal.numberOfChildren;
        genome = new JLabel("Animal Genome:  " + buildStringFromGenome(animal.genome));
        genome.setSize(650,20);
        increaseInChildren = new LabelWithNameTag(animal.numberOfChildren-initialChildren, "Increase in Children: ");
        age = new LabelWithNameTag(animal.age-initialAge, "Increase in age: ");
        energy = new LabelWithNameTag(animal.energy, "Animals Energy: ");
        position = new JLabel("Position of animal: " + animal.position.toString());
        verticalBox.add(genome);
        verticalBox.add(position);
        verticalBox.add(age);
        verticalBox.add(increaseInChildren);
        verticalBox.add(energy);

        this.add(verticalBox);
        this.setVisible(true);
    }
    
    public void update(){
        if(this.animal.isDead && !diedFlag){
            epochOfDeath = new LabelWithNameTag(animal.bornAt+animal.age, "Epoch of Death: ");
            verticalBox.add(epochOfDeath);
            diedFlag=true;
        }
        age.setValue(animal.age-initialAge);
        increaseInChildren.setValue(animal.numberOfChildren-initialChildren);
        energy.setValue(animal.energy);
        position.setText("Position of animal: " + animal.position.toString());
    }
    
    private String buildStringFromGenome(int[] genome){
        String stringGenome = "|";
        for(int i : genome){
            stringGenome +=" " + i + " |";
        }
        return stringGenome;
    }
}
