package  play;
/**
 * Created by qin on 18.1.3.
 */
import  basicinfo.Thing2D;
import  creature.*;
import  myenum.PARTISAN;
import  myenum.STATUS;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private int order;
    private boolean alive;
    private Field field;
    private Creature role;

    public Player(int x, int y, Field field,Creature c) {
        super(x, y);

        this.field = field;
        role=c;
        alive=true;
        URL loc = getHeadPic();
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    private int Strength(){
        return this.role.commonStrength();
    }
    public int ord(){return order;}
    public int distanceSqure(Player a){
        return (this.x()-a.x())*(this.x()-a.x())+(this.y()-a.y())*(this.y()-a.y());
    }
    private URL getHeadPic(){
        URL loc;
        if(role.getStatus()== STATUS.爷爷)
            loc = this.getClass().getResource("/grandfa.png");
        else if(role.getClass()==HuLuWa.class)
            switch (role.getStatus()) {
                case 大娃:loc = this.getClass().getClassLoader().getResource("red.png");break;
                case 二娃:loc = this.getClass().getClassLoader().getResource("orange.png");break;
                case 三娃:loc = this.getClass().getClassLoader().getResource("yellow.png");break;
                case 四娃:loc = this.getClass().getClassLoader().getResource("green.png");break;
                case 五娃:loc = this.getClass().getClassLoader().getResource("dark.png");break;
                case 六娃:loc = this.getClass().getClassLoader().getResource("blue.png");break;
                case 七娃:loc = this.getClass().getClassLoader().getResource("purple.png");break;
                default:loc = this.getClass().getClassLoader().getResource("red.png");break;
            }
        else if(role.getClass()==MonsterScorpion.class)
            loc = this.getClass().getClassLoader().getResource("scorpion.png");
        else if(role.getClass()==MonsterSnake.class)
            loc = this.getClass().getClassLoader().getResource("snake.png");
        else
            loc = this.getClass().getClassLoader().getResource("frog.png");
        return loc;
    }
    public PARTISAN playerPartisan(){
        return role.getPartisan();
    }
    public Creature getCreature(){return role;}

    public boolean battle(Player b){
        if(!this.Alive()||!b.Alive())
            return false;

        //Random r = new Random();
        Random r=ThreadLocalRandom.current();
        if(r.nextInt(this.Strength()+b.Strength())>=this.Strength())
            return false;
        else{
            return true;
        }
    }

    public void move(Player a) {
        int dx=Math.abs(this.x()-a.x());
        int dy=Math.abs(this.y()-a.y());
        int nx = (this.x() + a.x())/2;
        int ny = (this.y() + a.y())/2;
        if(dx*dx+dy*dy>900){
            nx=(nx+this.x())/2;
            ny=(ny+this.y())/2;
            nx=(nx+this.x())/2;
            ny=(ny+this.y())/2;
            nx=(nx+this.x())/2;
            ny=(ny+this.y())/2;
        }
        else {
            nx = nx / 2;
            ny = ny / 2;
        }
        //output action
        field.reportFromPlayer(this,nx,ny);
        this.setX(nx);
        this.setY(ny);
    }
    public void move(int x,int y){
        int dx=Math.abs(this.x()-x);
        int dy=Math.abs(this.y()-y);
        if(dx*dx+dy*dy<35*35) {
            this.setX(x);
            this.setY(y);
        }
    }

    public void setOrder(int i){
        order=i;
    }
    private Player findClosest(){
        field.freshBattle();
        /*for(int i=0;i<16;i++){
            if(Math.abs()<9)
        }
        if(Math.abs(this.distanceSqure(a))<9){
            if(battle(this,a))//true:this > a
                ;
        }*/
        if(this.Alive())
            return this.field.findClosest(this);
        else return null;
    }

    public boolean Alive(){return alive;}
    public void Dead(){alive=false;}

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            Player temp=findClosest();
            if(!Alive())
                continue;///completed;
            ///////////////////////////
            this.move(temp);
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}

