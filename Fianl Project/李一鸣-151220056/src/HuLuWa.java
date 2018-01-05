import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class HuLuWa extends Creature implements Runnable{
    HuLuWa(String name, Position position) { ;
        this.exist=true;

        this.name =name;
        this.setPosition(position);
    }
    HuLuWa(String name,int x, int y, Field field,int id) {//x ,y窗口位置  对应数组位置 y/50 (x-125)/50
        this.name = name;
        this.exist=true;
        this.x=x;//窗口位置
        this.y=y;
        setPosition(new Position(y/50,(x-125)/50));
        this.field = field;
        URL loc= this.getClass().getClassLoader().getResource("红娃.gif");;
        if(id==1)
            loc = this.getClass().getClassLoader().getResource("红娃.gif");
        if(id==2)
            loc = this.getClass().getClassLoader().getResource("橙娃.gif");
        if(id==3)
            loc = this.getClass().getClassLoader().getResource("黄娃.gif");
        if(id==4)
            loc = this.getClass().getClassLoader().getResource("绿娃.gif");
        if(id==5)
            loc = this.getClass().getClassLoader().getResource("青娃.gif");
        if(id==6)
            loc = this.getClass().getClassLoader().getResource("蓝娃.gif");
        if(id==7)
            loc = this.getClass().getClassLoader().getResource("紫娃.gif");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    public synchronized void run() {
        while (!Thread.interrupted()) {
            if(this.exist==false)
                break;

            Random rand = new Random(); //-2,-1,0,1,2
            this.move((rand.nextInt(5)-2)*50, (rand.nextInt(5)-2)*50);
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

         int tmp1, tmp2;
         tmp1 = ny;
         tmp2 = nx;
         if (this.field.getPositions().get(tmp1 / 50).get((tmp2 - 125) / 50).getHolder() instanceof Monster) {
             Random r=new Random();
             if( (r.nextInt(4)<=2))//2/3杀掉妖精
             this.field.getCreatures().get(tmp1 / 50).get((tmp2 - 125) / 50).exist = false;
         } else {
             tmp1 = ny;
             tmp2 = nx - 50;
             if (bound(tmp1, tmp2)) {
                 tmp1 = ny;
                 tmp2 = nx + 50;
                 if (bound(tmp1, tmp2)) {
                     tmp1 = ny - 50;
                     tmp2 = nx;
                     if (bound(tmp1, tmp2)) {
                         tmp1 = ny + 50;
                         tmp2 = nx;
                         if (this.field.getPositions().get(tmp1 / 50).get((tmp2 - 125) / 50).getHolder() instanceof Monster) {
                             Random r=new Random();
                             if( (r.nextInt(4)<=2))//2/3杀掉妖精
                             this.field.getCreatures().get(tmp1 / 50).get((tmp2 - 125) / 50).exist = false;
                         }
                     } else {
                         if (this.field.getPositions().get(tmp1 / 50).get((tmp2 - 125) / 50).getHolder() instanceof Monster){
                             Random r=new Random();
                             if( (r.nextInt(4)<=2))//2/3杀掉妖精
                                 this.field.getCreatures().get(tmp1 / 50).get((tmp2 - 125) / 50).exist = false;
                         }
                     }
                 } else {
                     if (this.field.getPositions().get(tmp1 / 50).get((tmp2 - 125) / 50).getHolder() instanceof Monster){
                         Random r=new Random();
                         if( (r.nextInt(4)<=2))//2/3杀掉妖精
                             this.field.getCreatures().get(tmp1 / 50).get((tmp2 - 125) / 50).exist = false;
                     }
                 }

             } else {
                 if (this.field.getPositions().get(tmp1 / 50).get((tmp2 - 125) / 50).getHolder() instanceof Monster){
                     Random r=new Random();
                     if( (r.nextInt(4)<=2))  //2/3杀掉妖精
                         this.field.getCreatures().get(tmp1 / 50).get((tmp2 - 125) / 50).exist = false;
                 }
             }
         }
        this.setX(nx);
        this.setY(ny);
        this.setPosition(new Position(ny,nx));
//消除原来位置  一直消除不了 很难受！！！！
        Blank b=new Blank("空",this.x(), this.y(),this.field);
        this.field.creatures.get(this.y() / 50).set((this.x() - 125) / 50,b);
        this.field.positions.get(this.y() / 50).get((this.x() - 125) / 50).setHolder(b);
        this.field.creatures.get(this.y() / 50).get((this.x() - 125) / 50).setPosition(this.field.positions.get(this.y() / 50).get((this.x() - 125) / 50));
     //   b.setPosition(this.field.positions.get(this.y() / 50).get((this.x() - 125) / 50));
//移到新位置
        this.field.creatures.get(ny/50).set((nx-125)/50,this);
        this.field.positions.get(ny/50).get((nx-125)/50).setHolder(this);
        this.setPosition(this.field.positions.get(ny/50).get((nx-125)/50));

    //    System.out.println(this.field.creatures.get(ny/50).get((nx-125)/50));

    }

    public boolean bound(int tx,int ty){
        if(tx<0||tx>=750||ty<125||ty>=875)
            return true;
        return false;
    }
}
enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}