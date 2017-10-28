public class Grandpa implements Creature {
    private Position position;

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
    public String toString() { return "爷"; }

    public void report() {
        System.out.print(this.toString());
    }

    public void cheer(){ System.out.print("爷爷：孩子们加油！\n");}

}
