public class Xiaolouluo extends Creature {
    public Xiaolouluo(Position position, Field field) {
        super(position, field);
        setPriority(1);
        setCamp(Camp.EVIL);
        dir = Direction.LEFT;
        setImage("xiaolouluo.png");
        setDeadImage("tomb.png");
    }
}
