public class Huluwa implements Creature{
    private COLOR color;
    private SENIORITY seniority;
    private Position position;

    public Huluwa(COLOR color,SENIORITY seniority){
        this.color=color;
        this.seniority=seniority;
    }

    public void report(){
        System.out.println(color.toString()+seniority.toString()+",");
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public String toString() {
        return "\uD83C\uDF50";
    }
}
