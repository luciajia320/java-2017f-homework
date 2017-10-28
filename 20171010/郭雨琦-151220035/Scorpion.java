public class Scorpion implements Creature {
    private Position position;
    @Override
    public void cheer(){}
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        if(position != null) {
            position.setHolder(this);
        }
    }

    @Override
    public String toString() { return "蝎"; }

    public void report() {
        System.out.print(this.toString());
    }

}
