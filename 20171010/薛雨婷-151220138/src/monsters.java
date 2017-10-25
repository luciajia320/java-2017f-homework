public class monsters implements creature{
    int number;
    Position position;

    public monsters(int num){
        number=num;
    }

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
        return "小喽啰";
    }
}
