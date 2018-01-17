public class Xiezijing extends Creature {
    public Xiezijing(Position position, Field field) {
        super(position, field);
        setCamp(Camp.EVIL);
        dir = Direction.LEFT;
        setPriority(4);
        setImage("xiezijing.png");
        setDeadImage("tomb.png");
    }
}
