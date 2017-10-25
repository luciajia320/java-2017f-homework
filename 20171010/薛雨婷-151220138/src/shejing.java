public class shejing implements creature {
    private Position position;

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        // position.setHolder(this);
    }

    @Override
    public String getName() {
        return "蛇精";
    }

}
