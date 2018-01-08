package creature;

import UI.Field;

import java.util.Random;

public class Snake extends Creature{

    public Snake(int x, int y, Field field, Name id,int moveBounds,int direction){
        super(x,y,field,id,moveBounds,direction);
    }

}
