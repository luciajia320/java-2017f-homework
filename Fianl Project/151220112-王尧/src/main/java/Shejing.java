public class Shejing extends Creature {
    public Shejing(Position position, Field field) {
        super(position, field);
        setPriority(5);
        dir = Direction.LEFT;
        setCamp(Camp.EVIL);
        setImage("shejing.png");
        setDeadImage("tomb.png");
    }

}
