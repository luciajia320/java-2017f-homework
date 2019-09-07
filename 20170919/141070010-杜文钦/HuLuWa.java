package java

/**
 * Created by Administrator on 2017/10/17.
 */
public class HuLuWa {
    public static void main(String[] args) {

    }
    private int rank;
    private String name;
    private int pos;
    private String color;

    public HuLuWa(int rank, String name, String color,int pos) {
        this.rank=rank;
        this.name=name;
        this.pos=pos;
        this.color=color;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        reportPositionChange(pos);
        this.pos=pos;
    }

    public void countOffByName() {
        System.out.print(name+" ");
    }

    public void countOffByColor(){
        System.out.print(color+" ");
    }

    public void reportPositionChange(int endPos) {
        System.out.printf("%s:%d->%d\n", name, pos, endPos);
    }
}
