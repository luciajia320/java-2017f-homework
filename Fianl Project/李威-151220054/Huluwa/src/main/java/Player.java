import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.activation.FileDataSource;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Field field;

    public Player(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("hong.png");
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

    public void run() {
        while (!Thread.interrupted()) {
          
        
        		// this.move(rand.nextInt(10), rand.nextInt(10));
                try {

                    Thread.sleep(sleepTime);
                    //field.getBuffer().append("battle: louluo"+rank+" lose hulu"+huluRank+"\r\n");
                    for(int i=0;i<7;i++)
                     field.getMyBuffer().append("hulu"+(i+1)+" "+field.getHuLuWas()[i].x()+" "+field.getHuLuWas()[i].y()+" "+field.getHuLuWas()[i].isAlive()+"\r\n");
                    for(int i=0;i<6;i++)
                        field.getMyBuffer().append("louluo"+(i+1)+" "+field.getXiaolouluos()[i].x()+" "+field.getXiaolouluos()[i].y()+" "+field.getXiaolouluos()[i].isAlive()+"\r\n");
                    
                    field.getMyBuffer().append("shejing "+field.getShejing().x()+" "+field.getShejing().y()+" "+field.getShejing().isAlive()+"\r\n");
                    field.getMyBuffer().append("xiezijing "+field.getXiezijing().x()+" "+field.getXiezijing().y()+" "+field.getXiezijing().isAlive()+"\r\n");
                    field.getMyBuffer().append("grandpa "+field.getGrandpa().x()+" "+field.getGrandpa().y()+" "+field.getGrandpa().isAlive()+"\r\n");
                    
                    this.field.repaint();

                } catch (Exception e) {

                }
        	}
        	
        	
        }
}

