import javafx.geometry.Pos;

public class Snake implements Creature {
    private Position position;
    public Snake(Position position){
        this.position=position;
    }

    @Override
    public void setPosition(Position position){
        this.position=position;
    }

    @Override
    public Position getPosition(){
        return this.position;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0D";
    }
}
