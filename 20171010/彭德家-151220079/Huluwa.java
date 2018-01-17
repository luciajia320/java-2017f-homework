package example;
public class Huluwa implements Creature, Comparable{

    private COLOR color;
    private SENIORITY seniority;
    private Position position;

    public COLOR getColor() {
        return color;
    }

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

    Huluwa(COLOR color, SENIORITY seiority) {
        this.color = color;
        this.seniority = seiority;
    }
   
    @Override
    public String toString(){
        return this.seniority.toString();
    }
    @Override
    public String report() {
		return this.seniority.toString();
	}
    @Override
    public void resetPosition(Position position) {
    	position.setHolder(null);
    }
    @Override
    public boolean biggerThan(Comparable brother){
        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }

}

enum COLOR {
    红,橙,黄,绿,蓝,靛,紫
}

enum SENIORITY {
    老大,老二,老三,老四,老五,老六,老七
}