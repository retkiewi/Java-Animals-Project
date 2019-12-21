package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvolvingAnimal extends MapEntity{
    TurningDirection orientation;
    List<IPositionObserver> observerList = new ArrayList<>();
    int energy;
    int[] genome = new int[32];
    Random random = new Random();
    boolean isDead = false;
    int age = 0;
    int breedingCooldown = 18;
    int numberOfChildren = 0;
    int bornAt;

    public EvolvingAnimal(Vector2d position, TurningDirection orientation, int energy, LoopingMap map){
        this.bornAt = map.epoch;
        observerList.add(map);
        for(int i=0; i<genome.length; i++)
            genome[i] = random.nextInt(8);
        this.energy = energy;
        this.orientation=orientation;
        this.position = position;
        this.genome = validateGenome(genome);
        notifyObservers(new Vector2d(-1, -1));
    }

    public EvolvingAnimal(LoopingMap map,EvolvingAnimal parent1, EvolvingAnimal parent2){
        observerList.add(map);
        this.orientation= parent1.orientation.turn(random.nextInt(8));
        this.position = parent2.position.add(orientation.toUnitVector());
        this.genome = inherit(parent1, parent2);
        parent1.energy-=map.animalMaxEnergy/4;
        parent1.breedingCooldown=18;
        parent1.numberOfChildren++;
        parent2.energy-=map.animalMaxEnergy/4;
        parent2.breedingCooldown=18;
        parent2.numberOfChildren++;
        this.energy=map.animalMaxEnergy/4 + map.animalMaxEnergy/4;
        notifyObservers(new Vector2d(-1, -1));
    }

    public void move(){
        if(this.energy==0){
            this.die();
            return;
        }
        this.orientation = this.orientation.turn(genome[random.nextInt(genome.length)]);
        this.position = position.add(orientation.toUnitVector());
        this.energy--;
        this.age++;
        this.breedingCooldown++;
        notifyObservers(this.position.subtract(orientation.toUnitVector()));
    }

    private void notifyObservers(Vector2d oldPosition){
        for (IPositionObserver observer: observerList) {
            observer.positionChanged(oldPosition, this.position, this);
        }
    }

    public void moveTo(Vector2d position){
        this.position=position;
    }

    private int[] validateGenome(int[] genome) {
        int[] validator = new int[8];
        for (int counter : validator)
            counter = 0;
        for (int gene : genome)
            validator[gene]++;
        for (int i = 0; i < validator.length; i++) {
            if (validator[i] == 0) {
                int temp = random.nextInt(genome.length);
                while (validator[genome[temp]] <= 1) {
                    temp = random.nextInt(genome.length);
                }
                validator[genome[temp]]--;
                genome[temp] = i;
            }

        }
        return genome;
    }

    private int[] inherit(EvolvingAnimal parent1, EvolvingAnimal parent2){
        int[] genome = new int[32];
        int i=0;
        for(; i<random.nextInt(8)+10; i++){
            genome[i]=parent1.genome[i];
        }
        int j=i+1;
        for(; j<32; j++){
            genome[j]=parent2.genome[j];
        }
        j=random.nextInt(5)+1;
        for(i=0; i<j; i++){
            genome[random.nextInt(32)]=random.nextInt(8);
        }
        return validateGenome(genome);
    }

    private void die(){
        this.isDead=true;
        for (IPositionObserver observer: observerList) {
            observer.funeralTime(this);
        }
    }

}
