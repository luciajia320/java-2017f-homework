public class Grandpa implements Creature {
    private Position position;

    public Grandpa(Position position){
        this.position=position;
    }
    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public String toString(){
        return "\uD83D\uDC74";
    }
}
