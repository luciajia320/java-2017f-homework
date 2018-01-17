
public class Shejing implements Creature, Comparable{

    private SENIORITY seniority;
    private Position position;


    public SENIORITY getSeniority() {
        return seniority;
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

    Shejing(SENIORITY seiority) {
        this.seniority = seiority;
    }

    @Override
    public void report() {
        //System.out.print(this.toString());
    	 System.out.print("Éß");
    }

    @Override
    public String toString(){
        return this.seniority.toString() + "@" + this.position.getX()+ " "+this.position.getY() + ";";
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }

}