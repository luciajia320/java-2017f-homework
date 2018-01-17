public class Scorpion implements Creature {

    private Position position;

    @Override
    public void setPosition(Position position){
        this.position=position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD82";
    }
}
