package Ground;

import Creature.Creature;
import Hulu.Anno.AuthorAnno;

@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public class Position<T extends Creature>{

    private int x;
    private int y;
    private T holder;
    private T lastHolder;//记录上一个holder


    public Position(int x, int y){
        this.x = x;
        this.y = y;
        this.holder = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public T getHolder() {
        return holder;
    }

    public synchronized void setHolder(T holder) {

        lastHolder = this.holder;
        this.holder = holder;
    }


    public T getLastHolder() {
        return lastHolder;
    }

    public void cleanHolder(){
        if(holder != null)
            lastHolder = this.holder;
            this.holder = null;
    }

    public boolean isNearBy(Position position){
        if(this.distanceFrom(position) == 1) return true;
        return false;
    }

    public int distanceFrom(Position position){
        return Math.abs(this.x - position.getX())+Math.abs(this.y - position.getY());
    }

    public boolean isEmpty(){
        if(this.holder != null)
            return false;
        return true;
    }
}
