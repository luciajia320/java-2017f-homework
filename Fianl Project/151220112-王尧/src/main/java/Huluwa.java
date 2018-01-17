public class Huluwa extends Creature{
    public Huluwa(int rank, Field field, Position position) {
        super(position, field);
        this.setPriority(3);
        dir = Direction.RIGHT;
        this.setCamp(Camp.JUSTICE);
        this.setSpeed(3);
        String path = "huluwa" + rank + ".png";
        this.setImage(path);
        this.setDeadImage("tomb.png");
    }
}
