
/**
 * Created by qin on 18.1.3.
 */
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Field field;
    private Creature role;

    private int Strength(){
        return this.role.commonStrength();
    }
    public void battle(Player a,Player b){
            Random r=ThreadLocalRandom.current();
            if(r.nextInt(a.Strength()+b.Strength())>=a.Strength())
                ;//a failed
            else{
                ;//b failed
            }
    }

    private URL getHeadPic(){
        URL loc;
        if(role.getStatus()==STATUS.爷爷)
            loc = this.getClass().getResource("grandfa.png");
        else if(role.getClass()==HuLuWa.class)
            switch (role.getStatus()) {
                case 大娃:loc = this.getClass().getResource("red.png");break;
                case 二娃:loc = this.getClass().getResource("orange.png");break;
                case 三娃:loc = this.getClass().getResource("yellow.png");break;
                case 四娃:loc = this.getClass().getResource("green.png");break;
                case 五娃:loc = this.getClass().getResource("dark.png");break;
                case 六娃:loc = this.getClass().getResource("blue.png");break;
                case 七娃:loc = this.getClass().getResource("purple.png");break;
                default:loc = this.getClass().getResource("red.png");break;
            }
        else if(role.getClass()==MonsterScorpion.class)
            loc = this.getClass().getResource("scorpion.png");
        else if(role.getClass()==MonsterSnake.class)
            loc = this.getClass().getResource("snake.png");
        else
            loc = this.getClass().getResource("frog.png");
        return loc;
    }

    public Player(int x, int y, Field field,Creature c) {
        super(x, y);

        this.field = field;
        role=c;

        URL loc = getHeadPic();
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    private Player findClosest(){
        return this.field.findClosest(this);
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            Player temp=findClosest();
            ////////////////////////////
            this.move(rand.nextInt(10), rand.nextInt(10));
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}

