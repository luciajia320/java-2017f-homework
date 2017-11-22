public abstract class Creature {
    protected String name;
    protected Position position;

    public void repoName(){
        System.out.print(name);
    }

    public String getName() {
        return name;
    }

    public void setPosition(Position position){
        this.position = position;
        position.setHolder(this);
    }

    public Position getPosition(){
        return position;
    }
}
