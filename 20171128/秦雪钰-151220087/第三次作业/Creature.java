import java.util.ArrayList;

//所有生物的抽象基类
public abstract class Creature {
    private String name;
    private Position position; //每个生物自出生起总要占位
    public Creature(String name){
        this.name=name;
        position=null; //出生时不知道自己要站哪里
    }
    String getName(){
        return name;
    }
    public Position getPos()
    {
        return  position;
    }
    public void setPos(Position position)
    {
        if(position==null){ //还没占位或者叫其去空值站
            return;
        }
//        if( this.position!=null)
//            this.position.sitterOut(); //原先的位置要宣布没人占用了。
        this.position = position;
        this.position.setSitter(this); //在这里同步pos和葫芦娃的动作！！
    }
    public void leavePos(){
        this.position.sitterOut();
        this.position=null;
    }
}

class Snake extends Creature{
    public Snake(){
        super("蛇");
    }
    //蛇精生出蝎子精
    Scorpion giveBirth(){
        Scorpion scorpion=new Scorpion();
        return scorpion;
    }
}

class Scorpion extends Creature{
    public Scorpion(){
        super("蝎");
    }
    //蝎子精生出一群小弟
    ArrayList<Minion> giveBirth(){
        ArrayList<Minion> minions=new ArrayList<Minion>();
        for(int i=0;i<7;i++){ //连续来7个小弟
            minions.add(new Minion());
        }
        return minions;
    }
}

class Minion extends Creature{
    public Minion(){
        super("喽");
    }
}
