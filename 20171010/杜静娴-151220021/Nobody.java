public class Nobody implements Creature {

    private final String face = "👤";
    private Position position;
    private int posX = 0;
    private int posY = 0;

    public void report() {;}

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public void setPos(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public String getFace(){
        return face;
    }

    @Override
    public String toString() {
        return face.toString();
    }
}
