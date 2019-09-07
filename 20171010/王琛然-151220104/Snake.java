public class Snake implements Creature{
    private String name;
    private Position position;

    Snake(){
        this.name = "蛇精";
        this.position = null;
    }

    Snake(int x, int y){
        this.name = "蛇精";
        this.position = new Position(x, y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }


    @Override
    public void report() {
        System.out.println(this.toString());
    }

    @Override
    public void reportName(){
        System.out.print(name);
    }


    @Override
    public String toString(){
        return "蛇精" + "at[" + this.position.getX() + "][" + this.position.getY() + "]";
    }
}
