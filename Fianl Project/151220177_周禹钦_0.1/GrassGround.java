/**
 * Created by qin on 18.1.2.
 */
public class GrassGround implements Creature{
    private Position position;
    private int strength =0;
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
}
