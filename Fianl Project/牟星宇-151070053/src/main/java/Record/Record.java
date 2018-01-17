package Record;

import Creature.Creature;
import Hulu.Anno.AuthorAnno;

import java.io.Serializable;

@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public class Record implements Serializable {


    //ID信息
    private int id;
    //坐标信息
    private int x;
    private int y;
    private int current_Hp;
    private int direction;//当前方向


    public Record(Creature creature){
        this.id = creature.getCreatureId();
        this.x = creature.getPosition().getX();
        this.y = creature.getPosition().getY();
        current_Hp = creature.getCurrent_Hp();
        direction = creature.getDirection();
    }

    public int getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCurrent_Hp() { return current_Hp; }

    public int getId() {
        return id;
    }
}
