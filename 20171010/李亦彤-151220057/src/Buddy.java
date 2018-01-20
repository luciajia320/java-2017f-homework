public class Buddy implements Creature {
    private Position<Creature> position;
    private int index;
    private String name;
    @Override
    public void setPosition(Position<Creature> position) {
        this.position = position;
        position.setHolder(this);
    }
    @Override
    public Position<Creature> getPosition() {
        return position;
    }
    @Override
    public void report() {
        System.out.print(this.toString());
    }
    @Override
    public String toString(){
        return "小喽啰"+this.index+"@" + this.position.getX() + ","+this.position.getY()+";";
    }
    @Override
    public void show() {
        System.out.print(name);
    }

    Buddy(int i) {
        this.index = i;
        this.name="🐶";
    }
}

