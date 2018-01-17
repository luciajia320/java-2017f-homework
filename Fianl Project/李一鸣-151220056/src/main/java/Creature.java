import java.awt.*;

public  class Creature implements Runnable{    //x ,y窗口位置  对应数组位置 y/50 (x-125)/50
    public String name;
    public Position position;//数组中位置
    protected Field field;
    public int x;//窗口位置
    public int y;
    public boolean exist;

    public void setPosition(Position position){
        this.position=position;
        position.setHolder(this);
    }
    public void setexist(boolean exist){
        this.exist=exist;
    }
    public Position getPosition(){
        return position;
    }
    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
    public void report(){
        System.out.print(name+"\t");
    }
    public String getname(){
        return name;
    }
    private Image image;
    public Image getImage() {
        return this.image;
    }
    public void setImage(Image img) {
        image = img;
    }
    public void run() {
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int x, int y) {
    }
}
