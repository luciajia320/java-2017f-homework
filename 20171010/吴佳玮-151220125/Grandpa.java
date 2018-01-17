public class Grandpa implements JusticeCreature {

    Position position;

    @Override
    public void report() {
        System.out.print("爷");
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
