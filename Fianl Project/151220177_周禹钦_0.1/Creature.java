enum STATUS{
    大娃,二娃,三娃,四娃,五娃,六娃,七娃,爷爷,蝎子,蛇精,青蛙,草地
        }

public interface Creature {

    public void report();

    public void setPosition(Position position);

    public Position getPosition();

    public STATUS getStatus();

    public int commonStrength();
}