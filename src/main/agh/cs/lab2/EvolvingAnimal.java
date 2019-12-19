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
    EvolvingAnimal[] parents = new EvolvingAnimal[2];
    boolean isDead = false;
    int age = 0;

    public EvolvingAnimal(Vector2d position, TurningDirection orientation, int energy, LoopingMap map){
        observerList.add(map);
        for(int i=0; i<genome.length; i++)
            genome[i] = random.nextInt(8);
        this.energy = energy;
        this.orientation=orientation;
        this.position = position;
        this.parents[0] = null;
        this.parents[1] = null;
        this.genome = validateGenome(genome);
        notifyObservers(new Vector2d(-1, -1));
    }

    public EvolvingAnimal(LoopingMap map,EvolvingAnimal parent1, EvolvingAnimal parent2){
        observerList.add(map);
        this.orientation= parent1.orientation.turn(random.nextInt(8));
        this.position = parent2.position.add(orientation.toUnitVector());
        this.parents[0] = parent1;
        this.parents[1] = parent2;
        //this.genome = inherit(this.parents);
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

    private int[] validateGenome(int[] genome){
        int[] validator = new int[8];
        for(int counter : validator)
            counter = 0;
        for(int gene : genome)
            validator[gene]++;
        for(int i=0; i<validator.length; i++){
            if(validator[i]==0){
                int temp = random.nextInt(genome.length);
                while(validator[genome[temp]] <= 1){
                    temp = random.nextInt(genome.length);
                }
                validator[genome[temp]]--;
                genome[temp] = i;
            }

        }
        return genome;
    }

    private int[] inherit(Animal[] parents){
        return null;
    }

    private void die(){
        this.isDead=true;
        for (IPositionObserver observer: observerList) {
            observer.funeralTime(this);
        }
    }
}
