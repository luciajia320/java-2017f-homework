import javafx.geometry.Pos;

public class Minion implements EvilCreature {

    private Position position;

    @Override
    public void report() {
        System.out.print("兵");
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
