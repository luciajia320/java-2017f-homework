public class Yeye extends Creature {
    public Yeye(Position position, Field field) {
        super(position, field);
        setPriority(3);
        dir = Direction.RIGHT;
        setCamp(Camp.JUSTICE);
        setImage("yeye.png");
        setDeadImage("tomb.png");
    }
}
