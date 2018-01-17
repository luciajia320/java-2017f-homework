abstract public class Creature {

    protected Position position;

    public abstract String toString();

    public void report() {
        System.out.print(this.toString());
    }

    public void setPosition(Position position){

        this.position = position;

        position.setHolder(this);
    }

    public void Moveto(Position position){
        this.position.isempty=true;
        this.position=position;
        position.setHolder(this);
    }

    public Position getPosition(){
        return position;
    }


}