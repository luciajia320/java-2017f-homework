public class Grandpa implements Creature{
    private String name;
    private Position position;

    Grandpa(){
        this.name = "爷爷";
        this.position = null;
    }


    Grandpa(int x, int y){
        this.name = "爷爷";
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
        return "爷爷" + "at[" + this.position.getX() + "][" + this.position.getY() + "]";
    }
}
