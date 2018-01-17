public class Snake implements EvilCreature{

    private Position position;

    @Override
    public void report() {
        System.out.print("蛇");
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
