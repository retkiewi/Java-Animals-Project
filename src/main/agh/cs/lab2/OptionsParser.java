package agh.cs.lab2;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parseToMapDirection(String[] args){
        MoveDirection[] temp = new MoveDirection[args.length];
        int errorcount=0;
        for(int i=0; i<args.length; i++){
            switch (args[i]){
                case "f": temp[i-errorcount]=MoveDirection.FORWARD; break;
                case "forward": temp[i-errorcount]=MoveDirection.FORWARD; break;
                case "b": temp[i-errorcount]=MoveDirection.BACKWARD; break;
                case "backward": temp[i-errorcount]=MoveDirection.BACKWARD; break;
                case "r": temp[i-errorcount]=MoveDirection.RIGHT; break;
                case "right": temp[i-errorcount]=MoveDirection.RIGHT; break;
                case "l": temp[i-errorcount]=MoveDirection.LEFT; break;
                case "left": temp[i-errorcount]=MoveDirection.LEFT; break;
                default: {errorcount++; throw new IllegalArgumentException(args[i] + " is not legal move specification"); }
            }
        }
        return Arrays.copyOfRange(temp,0,temp.length-errorcount);
    }
}
