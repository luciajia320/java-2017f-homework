package main.creature;

/**
 * Created by qin on 18.1.2.
 */
import main.basicinfo.Position;
import main.myenum.PARTISAN;
import main.myenum.STATUS;
public class GrassGround implements Creature{
    private Position position;
    private int strength =0;
    private PARTISAN partisan=PARTISAN.中立;
    @Override
    public void report(){

    }
    @Override
    public void setPosition(Position position){
        this.position=position;
        position.setHolder(this);
    }
    @Override
    public Position getPosition(){
        return position;
    }

    @Override
    public STATUS getStatus(){
        return STATUS.草地;
    }
    @Override
    public int commonStrength(){
        return strength;
    }
    @Override
    public PARTISAN getPartisan(){
        return partisan;
    }
}
