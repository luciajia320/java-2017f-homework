package example;

public class Position {

    private int x = -1, y = -1;

    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
        //System.out.println("fuck");
    }

    private Creature holder = null;

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
    public Position(int x,int y){
        super();
        this.x = x;
        this.y = y;
    }
}