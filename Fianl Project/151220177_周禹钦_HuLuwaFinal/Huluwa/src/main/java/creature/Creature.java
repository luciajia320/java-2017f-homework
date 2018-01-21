package creature;
import  basicinfo.Position;
import  myenum.STATUS;
import  myenum.PARTISAN;
/*enum STATUS{
    大娃,二娃,三娃,四娃,五娃,六娃,七娃,爷爷,蝎子,蛇精,青蛙,草地;


    public STATUS xAt(int i) {
        for(STATUS s:STATUS.values())
            if(s.getIndex()==i)
                return s;
        return STATUS.草地;
    }
    private int getIndex(){
        return this.ordinal();
    }
}

enum PARTISAN{
    善,恶,中立
}*/

public interface Creature {

    public void report();

    public void setPosition(Position position);

    public Position getPosition();

    public STATUS getStatus();

    public int commonStrength();

    public PARTISAN getPartisan();
}