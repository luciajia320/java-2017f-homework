import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Grandfather extends Creature implements Runnable{//x ,y窗口位置  对应数组位置 y/50 (x-125)/50
    Grandfather(String name) {
        this.name = name;

    }
    Grandfather(String name,int x, int y, Field field,int id) {
        this.name = name;
        this.exist=true;
        setPosition(new Position(y/50,(x-125)/50));
        this.x=x;//窗口位置
        this.y=y;
        this.field = field;
        URL  loc = this.getClass().getClassLoader().getResource("爷爷.gif");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    public synchronized void run() {
        while (!Thread.interrupted()) {
            if(this.exist==false)
                break;

            Random rand = new Random(); //-1,0,1
            this.move((rand.nextInt(4)-1)*50, (rand.nextInt(4)-1)*50);

            try {
                Thread.sleep(rand.nextInt(100) + 100);
                this.field.repaint();
            } catch (Exception e) {

            }
        }
    }
    @Override
    public void move(int x,int y){
        int nx = (this.x() + x)%1000;
        int ny = (this.y() + y)%800;
        if(nx<125 ||nx>=875)
            nx=this.x();
        if(ny>=750||ny<0)
            ny=this.y();


        this.field.creatures.get(this.y() / 50).set((this.x() - 125) / 50,new Blank("空",this.x(), this.y(),this.field));
        this.field.positions.get(this.y() / 50).get((this.x() - 125) / 50).setHolder(new Blank("空",this.x(), this.y(),this.field));
        this.field.creatures.get(this.y() / 50).get((this.x() - 125) / 50).setPosition(this.field.positions.get(this.y() / 50).get((this.x() - 125) / 50));

        this.setX(nx);
        this.setY(ny);
        this.setPosition(new Position(ny,nx));

        //x ,y窗口位置  对应数组位置 y/50 (x-125)/50

        this.field.creatures.get(ny/50).set((nx-125)/50,this);
        this.field.positions.get(ny/50).get((nx-125)/50).setHolder(this);
        this.setPosition(this.field.positions.get(ny/50).get((nx-125)/50));



    }
}
