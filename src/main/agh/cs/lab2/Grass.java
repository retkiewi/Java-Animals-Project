package agh.cs.lab2;

public class Grass extends MapEntity{

    Grass(Vector2d initialPosition){
        this.position=initialPosition;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public String toString(){
        return "*";
    }

    public boolean equals(Object other){
        if(other == null || other.getClass() != Grass.class)
            return false;
        if(this==other)
            return true;
        Grass o = (Grass)other;

        if(this.getPosition().equals(o.getPosition())){
            return true;
        }

        return false;
    }
}
