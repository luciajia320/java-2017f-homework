public class LouLuo implements Creature{
    private String name;
    private Position position;
    LouLuo() {
        this.name = "💀";
    }
    @Override
    public void report(){
        System.out.print(this.name+"\t");
    }

    public void setPosition(Position position){
        this.position = position;
        position.setHolder(this);
    }

    public Position getPosition(){
        return position;
    }
}
/*
enum NAME{
    喽啰A,喽啰B,喽啰C,喽啰D,喽啰E,喽啰F
}
*/