public class Minion implements Creature {
    private String name;
    private Position position;

    Minion(int index){
        String s = String.valueOf(index);
        this.name = "喽啰" + s;
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
        return this.name + "at[" + this.position.getX() + "][" + this.position.getY() + "]";
    }
}
