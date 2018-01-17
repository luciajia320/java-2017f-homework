package  creature;

/**
 * Created by qin on 18.1.2.
 */
import  myenum.PARTISAN;
import  myenum.STATUS;
import  basicinfo.Position;
public class GrandFa implements Creature{
    private Position position;
    private int strength=10;
    private PARTISAN partisan=PARTISAN.善;
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
        return STATUS.爷爷;
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
