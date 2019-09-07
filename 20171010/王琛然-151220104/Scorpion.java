public class Scorpion implements Creature {
    private String name;
    private Position position;

    Scorpion(){
        this.name = "蝎子精";
        this.position = null;
    }


    Scorpion(int x, int y){
        this.name = "蝎子精";
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
        return "蝎子精" + "at[" + this.position.getX() + "][" + this.position.getY() + "]";
    }
}
