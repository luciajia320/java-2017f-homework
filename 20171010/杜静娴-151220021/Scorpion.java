public class Scorpion implements Creature {

    private String face = "🦂";

    public Scorpion(String face) {
        face = face;
    }

    public int getPosX() {
        return posX;
    }

    private int posX = 0;

    public int getPosY() {
        return posY;
    }

    private int posY = 0;

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
