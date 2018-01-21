public class Snake implements Creature {
    private Position<Creature> position;
    private String name;
    @Override
    public void setPosition(Position<Creature> position) {
        this.position = position;
        this.name="🐍";
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
        return "蛇精@" + this.position.getX() + ","+this.position.getY()+";";
    }
    @Override
    public void show() {
        System.out.print(name);
    }
}
