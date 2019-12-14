package agh.cs.lab2;

import java.util.HashMap;

public class
Vector2d {
    public final int x;
    public final int y;



    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null || other.getClass() != this.getClass())
            return false;
        Vector2d o = (Vector2d) other;
        return this.x == o.x && this.y == o.y;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean precedes(Vector2d other){
        if(this.x <= other.x && this.y <= other.y)
            return true;
        else
            return false;
    }

    public boolean follows(Vector2d other){
        if(this.x >= other.x && this.y >= other.y)
            return true;
        else
            return false;
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other){
        int new_x;
        int new_y;

        if(this.x<=other.x)
            new_x = this.x;
        else
            new_x = other.x;

        if(this.y<=other.y)
            new_y = this.y;
        else
            new_y = other.y;

        return new Vector2d(new_x, new_y);
    }

    public Vector2d add(Vector2d other){

        return new Vector2d(this.x+ other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x, this.y-other.y);
    }

    public Vector2d opposite(){

        return new Vector2d(-this.x,-this.y);

    }

    public Vector2d min_coordinates(Vector2d other){
        int new_x= this.x;
        int new_y= this.y;
        if(this.x > other.x)
            new_x=other.x;
        if(this.y > other.y)
            new_y = other.y;

        return new Vector2d(new_x, new_y);
    }

    public Vector2d max_coordinates(Vector2d other){
        int new_x= this.x;
        int new_y= this.y;
        if(this.x < other.x)
            new_x=other.x;
        if(this.y < other.y)
            new_y = other.y;

        return new Vector2d(new_x, new_y);
    }

    @Override
    public int hashCode(){
        int hash=13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
