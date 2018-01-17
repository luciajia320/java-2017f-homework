public class Minion implements Creature{
    private Position position;
    @Override
    public Position getPosition(){
        return this.position;
    }

    @Override
    public void setPosition(Position position){
        this.position=position;
    }

    @Override
    public String toString(){
        return "\uD83D\uDC38";
    }
}
