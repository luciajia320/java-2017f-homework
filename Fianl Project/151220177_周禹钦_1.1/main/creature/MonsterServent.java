package main.creature;

/**
 * Created by qin on 18.1.2.
 */
import main.basicinfo.Position;
import main.myenum.PARTISAN;
import main.myenum.STATUS;
public class MonsterServent implements Creature{
    private Position position;
    private int strength=30;
    private PARTISAN partisan=PARTISAN.恶;
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
        return STATUS.青蛙;
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
