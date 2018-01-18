public class Grandpa implements Creature {

    private String face = "👴";

    public Grandpa(int posX, int posY, String face) {
        this.posX = posX;
        this.posY = posY;
        face = face;
    }

    public int getPosX() {
        return posX;
    }

    private int posX;

    public int getPosY() {
        return posY;
    }

    private int posY;

    @Override
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
    public Position getPosition(){ return new Position(-1); }

    @Override
    public String getFace(){
        return face;
    }

    @Override
    public String toString() {
        return face.toString();
    }


    @Override
    public void setPosition(Position position) {
        ;
    }

    public void setPos(int x, int y){
        posX = x;
        posY = y;
    }
}
