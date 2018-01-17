/**
 * Created by qin on 18.1.2.
 */
public class MonsterServent implements Creature{
    private Position position;
    private int strength=4;
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
}
