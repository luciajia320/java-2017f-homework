package nju.java;


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player extends Thing2D implements Runnable {
    public static Random random = new Random(20);
    private Field field;
    private boolean isLive;
    private ArrayList<Player> enemys=null;
    private ArrayList<Player> friends=null;
    private boolean running;
    public Player(int x, int y, Field field) {
        super(x, y);

        this.field = field;
        isLive=true;
        running=true;
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void run() {
        while (running) {
            Random rand = new Random();

            this.move();
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
        System.out.println(this.getClass().getSimpleName()+" over");
    }


    public boolean conflict(Player p) {
        //synchronized (p) {
            if (p.isLive == true && (Math.abs(x() - p.x()) <= SPACE*5/4 && Math.abs(y() - p.y()) <= SPACE*5/4))
                return true;
            return false;
        //}
    }

    public boolean conflict(Player p,int x,int y) {
        //synchronized (p) {
        if (p.isLive == true && (Math.abs(x - p.x()) <= SPACE && Math.abs(y - p.y()) <= SPACE))
            return true;
        return false;
        //}
    }

    public int battle(Player p) {
        int x = random.nextInt();
        if (x %2==0) {
            return 1;
        }
        else {

            return 2;
        }
    }

    public int distance(Player p) {
        //synchronized (p) {
            int deltaX = x() - p.x();
            int deltaY = y() - p.y();
            return (deltaX * deltaX) + (deltaY * deltaY);
        //}
    }

    public boolean canMove(int x,int y) {
        boolean flag = true;
        for (Player p : friends) {
            if(p!=this) {
                synchronized (Player.class) {
                    if (p.isLive == true && conflict(p, x, y) == true) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (flag) {
            for (Player p : enemys) {
                synchronized (Player.class) {
                    if (p.isLive == true && conflict(p,x,y) == true) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    protected void move() {
        if (isLive == true) {
            boolean flag = false;
            for (Player p : enemys) {
                synchronized (Player.class) {
                    if (p.isLive == true && conflict(p) == true) {
                        int lose=battle(p);
                        if(lose==1){
                            isLive=false;
                            running=false;
                            setNewImage();
                            System.out.println(this.getClass().getSimpleName()+" lose");
                            if(this instanceof Huluwa){
                                Huluwa temp=(Huluwa)this;
                                FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" lose"+" \r\n");
                            }
                            else if(this instanceof Xiaolouluo){
                                Xiaolouluo temp=(Xiaolouluo)this;
                                FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" lose"+" \r\n");
                            }
                            else{
                                FileOperation.output(this.getClass().getSimpleName()+" lose"+" \r\n");
                            }
                        }
                        else{
                            p.isLive=false;
                            p.setRunning();
                            p.setNewImage();
                            System.out.println(p.getClass().getSimpleName()+" lose");
                            if(p instanceof Huluwa){
                                Huluwa temp=(Huluwa)p;
                                FileOperation.output(p.getClass().getSimpleName()+temp.getNo()+" lose"+" \r\n");
                            }
                            else if(p instanceof Xiaolouluo){
                                Xiaolouluo temp=(Xiaolouluo)p;
                                FileOperation.output(p.getClass().getSimpleName()+temp.getNo()+" lose"+" \r\n");
                            }
                            else{
                                FileOperation.output(p.getClass().getSimpleName()+" lose"+" \r\n");
                            }
                        }
                        flag = true;
                        break;
                    }
                }
            }
            if (flag == true)
                return;

            int min = 100000000;
            Player goal = null;
            //synchronized (enemys) {
                for (Player p : enemys) {
                    synchronized (Player.class){
                        int dis = distance(p);
                        if (p.isLive == true && dis <= min) {
                            goal = p;
                            min = dis;
                        }
                    }
                }

            if(goal==null){
                field.setCompleted();
                System.out.println(this.getClass().getSimpleName()+"win");
                running=false;
                return;
            }
            if(goal!=null) {
                synchronized (Player.class) {
                    if (goal.x() < x() && canMove(x() - SPACE / 4, y())) {
                        //ground.content[getY()][getX()] = -1;
                        setPoint(x() - SPACE / 4, y());
                        if(this instanceof Huluwa){
                            Huluwa temp=(Huluwa)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+ " "+y()+" \r\n");
                        }
                        else if(this instanceof Xiaolouluo){
                            Xiaolouluo temp=(Xiaolouluo)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+ " "+y()+" \r\n");
                        }
                        else{
                            FileOperation.output(this.getClass().getSimpleName()+" "+x()+ " "+y()+" \r\n");
                        }
                    } else if (goal.x() > x() && canMove(x() + SPACE / 4, y())) {
                        //ground.content[getY()][getX()] = -1;
                        setPoint(x() + SPACE / 4, y());
                        if(this instanceof Huluwa){
                            Huluwa temp=(Huluwa)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+ " "+y()+" \r\n");
                        }
                        else if(this instanceof Xiaolouluo){
                            Xiaolouluo temp=(Xiaolouluo)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+ " "+y()+" \r\n");
                        }
                        else{
                            FileOperation.output(this.getClass().getSimpleName()+" "+x()+ " "+y()+" \r\n");
                        }
                    } else if (goal.y() < y() && canMove(x(), y() - SPACE / 4)) {
                        //ground.content[getY()][getX()] = -1;
                        setPoint(x(), y() - SPACE / 4);
                        if(this instanceof Huluwa){
                            Huluwa temp=(Huluwa)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+" "+ y()+" \r\n");
                        }
                        else if(this instanceof Xiaolouluo){
                            Xiaolouluo temp=(Xiaolouluo)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+" "+ y()+" \r\n");
                        }
                        else{
                                FileOperation.output(this.getClass().getSimpleName()+" "+x()+" "+ y()+" \r\n");
                        }
                    } else if (canMove(x(), y() + SPACE / 4)) {
                        //ground.content[getY()][getX()] = -1;
                        setPoint(x(), y() + SPACE / 4);
                        if(this instanceof Huluwa){
                            Huluwa temp=(Huluwa)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+" "+ y()+" \r\n");
                        }
                        else if(this instanceof Xiaolouluo){
                            Xiaolouluo temp=(Xiaolouluo)this;
                            FileOperation.output(this.getClass().getSimpleName()+temp.getNo()+" "+x()+" "+ y()+" \r\n");
                        }
                        else{
                            FileOperation.output(this.getClass().getSimpleName()+" "+x()+" "+ y()+" \r\n");
                        }
                    }
                }
            }
        }
        return;
    }

    public void setEnemys(ArrayList<Player> enemys){
        this.enemys=enemys;
    }
    public void setFriends(ArrayList<Player> friends){
        this.friends=friends;
    }

    public void setNewImage(){
        URL loc = this.getClass().getClassLoader().getResource("lose.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public boolean getIsLive(){return isLive;}
    public void setRunning(){running=false;}
    public void setIsLive(){isLive=false;}
    public void myRepaint(){field.repaint();}
}