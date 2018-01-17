package  creature;

/**
 * Created by qin on 18.1.2.
 */
import  basicinfo.Position;
import  myenum.PARTISAN;
import  myenum.STATUS;
public class MonsterSnake implements Creature{
    private Position position;
    private int strength=90;
    private PARTISAN partisan =PARTISAN.恶;
    @Override
    public void report(){

    }
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
        position.setHolder(this);
    }
    @Override
    public STATUS getStatus(){
        return STATUS.蛇精;
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
