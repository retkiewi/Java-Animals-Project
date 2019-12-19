package agh.cs.lab2;

enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT,
    INVALID_DIRECTION;

    public MapDirection toMapDirection(){
        switch (this){
            case FORWARD: return MapDirection.NORTH;
            case BACKWARD: return MapDirection.SOUTH;
            case RIGHT: return MapDirection.EAST;
            case LEFT: return MapDirection.WEST;
            case INVALID_DIRECTION: return MapDirection.INVALID_DIRECTION;
        }
        return null;
    }
}

enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    INVALID_DIRECTION;

    public String toString(){
        switch (this){
            case NORTH: return "North";
            case SOUTH: return "South";
            case WEST: return "West";
            case EAST: return "East";
            default: return "Invalid direction";
        }
    }

    public MapDirection next(){
        switch (this){
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            case EAST: return SOUTH;
            default: return INVALID_DIRECTION;
        }
    }

    public MapDirection previous(){
        switch (this){
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case EAST: return NORTH;
            default: return INVALID_DIRECTION;
        }
    }

    public Vector2d toUnitVector(){
        Vector2d temp = new Vector2d(0,0);
        switch (this){
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case EAST: return new Vector2d(1,0);
            case WEST: return new Vector2d(-1,0);
            default: return new Vector2d(0,0);
        }
    }
}

enum TurningDirection{
    NORTH,
    NORTHEAST,
    EAST,
    EASTSOUTH,
    SOUTH,
    SOUTHWEST,
    WEST,
    WESTNORTH,
    INVALID_DIRECTION;

    public TurningDirection turn(int i){
        TurningDirection[] vals = values();
        return vals[(this.ordinal()+i)%(vals.length-1)];
    }

    public Vector2d toUnitVector(){
        switch (this){
            case NORTH: return new Vector2d(0,1);
            case NORTHEAST: return new Vector2d(1,1);
            case EAST: return new Vector2d(1,0);
            case EASTSOUTH: return new Vector2d(1,-1);
            case SOUTH: return new Vector2d(0,-1);
            case SOUTHWEST: return new Vector2d(-1,-1);
            case WEST: return new Vector2d(-1,0);
            case WESTNORTH: return new Vector2d(-1,1);
            default: return new Vector2d(0,0);
        }
    }
}