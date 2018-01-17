


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;


public class Player extends Thing2D implements Runnable {
    private Field field;

    public Player(int x, int y, Field field,int id,Role r) {
        super(x, y,r);

        this.field = field;


        URL loc = this.getClass().getClassLoader().getResource("./img/p"+id+".png");
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
    
    public boolean war(Player enemy){
    	boolean win=true;
    	Random rand = new Random();
    	int r=rand.nextInt(10);
    	if(r<=5){
    		this.setL(true);
    		enemy.setL(false);
    		enemy.dead();
    		win=true;
    	}
    	else{
    		this.setL(false);
    		this.dead();
    		enemy.setL(true);
    		win=false;
    	}
    	return win;	
    }
    
    public void dead(){
    	URL loc = this.getClass().getClassLoader().getResource("./img/dead.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        this.setL(false);
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            this.move(rand.nextInt(10), rand.nextInt(10));
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}