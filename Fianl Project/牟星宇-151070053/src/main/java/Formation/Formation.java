package Formation;

import Creature.Creature;
import Ground.Position;
import Hulu.Anno.AuthorAnno;

import java.util.ArrayList;

@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public abstract class Formation {

    protected Position position;
    protected int width;//行数（宽度）
    protected int height;//列数（长度）
    protected ArrayList<Creature> creatures;

    public Formation(int width, int height){
        this.height = height;
        this.width = width;
        this.position = new Position(0, 0);
        this.creatures = new ArrayList<Creature>();
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
    }



    public abstract void initial();


}
