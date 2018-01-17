import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

enum CreatureType{爷, 娃, 蝎, 蛇, 喽}

class Creature extends Thing2D implements Runnable {
    protected static FileWriter fileWriter;
    protected static BufferedWriter out = null;
    protected static int CreatrueNumber = 0;

    protected int number;
    private Field field;
    private boolean alive;
    CreatureType type;
    protected int capacity;
    protected boolean justiceCamp;

    public static void CreateRecorder(){
        try {
            fileWriter = new FileWriter("record.rcd", false);
             out= new BufferedWriter(fileWriter);
        } catch (IOException e){
            System.out.println("failed to start recorder");
            e.printStackTrace();
        }

    }

    public static void CloseRecord(){
        try {
            if(out != null) out.close();
            out = null;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*public static void setCreatureNumber(int i){
        CreatrueNumber = i;
    }*/

    public Creature(int x, int y, Field field) {
        super(x, y);
        number = ++CreatrueNumber;
        this.field = field;
        alive = true;
    }

    public Creature(int x, int y, Field field, int number){
        super(x, y);
        this.number = number;
        this.field = field;
        alive = true;
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    protected void LoadImg(){
        URL loc = this.getClass().getClassLoader().getResource(this.type.toString() + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void run() {
        int SPACE = field.getSPACE();
        int OFFSET = field.getOFFSET();
        boolean enemyRemains = true;
        while (!Thread.interrupted() && alive && enemyRemains) {
            enemyRemains = false;
            Random rand = new Random();

            int curx = x(), cury = y();
            int movx= rand.nextInt(40), movy = rand.nextInt(40);

            if(curx > OFFSET + 4 * SPACE)
                movx = -movx;

            if(cury > OFFSET + 4 * SPACE)
                movy = - movy;

            this.move(movx, movy);
            try {
                if(out != null) out.write(number + " "+ 1 + " "+ movx + " "+ movy + "\r\n");
            }catch (IOException e){
                e.printStackTrace();
            }


            try {
                Thread.sleep(rand.nextInt(200) + 200);
                this.field.repaint();

            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean splitpoint = false;
            for(Creature i : field.getCreatures()){
                //保证同步需要锁住两个对象：this和i
                //先锁住自己，查看是否存活
                synchronized (this) {
                    if(!alive) {
                        enemyRemains = true;
                        break;
                    }

                    //每个生物只会挑战creatures数组中位于自己后方的生物，以防互相挑战产生死锁，用splitpoint标识是否达到自己后方
                    if(i == this)
                        splitpoint = true;

                    //自己和自己同阵营，所以必须把这句放在i==this判断之后
                    if(justiceCamp == i.justiceCamp) continue;

                    //遍历时依然要查看是否有敌人存活，否则数组最后一个生物的enemyRemains永远为false，战斗直接结束
                    //但这个即使出现了线程不安全也没关系，最多再多循环一遍，不会有太多影响，所以无需锁住i
                    if(i.alive)
                        enemyRemains = true;

                    //找到位于数组后方的敌人后，拿锁，开打
                    if(splitpoint) {
                        synchronized (i) {
                            //之前判断的时候没锁，所以i可能死了，需要重新判断i是否存活
                            if (i.alive) {
                                enemyRemains = true;
                                if (dist(i) < 3200)
                                    i.battle(this);
                            }
                        }
                    }
                }
            }

            if(!enemyRemains){
                CloseRecord();
                field.setBattleOver();
            }
        }
    }

    public boolean battle(Creature enemy){
        Random random = new Random();
        if(random.nextInt(capacity + enemy.capacity) < capacity) {
            enemy.dead();
            return false;//调用者输，this赢
        }
        else {
            dead();
            return true;//this输
        }
    }

    public void dead(){
        alive = false;
        capacity = 0;

        try {
            if(out != null) out.write(number + " "+ 2 + "\r\n");
            //if(out != null) out.write(String.format("%d 2 this.x=%d this.y= %d,killer : %d , posx=%d posy = %d\r\n", number, x(), y(),md.number, md.x(), md.y()));
        }catch (IOException e){
            e.printStackTrace();
        }

        URL loc = this.getClass().getClassLoader().getResource("dead.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        //field.getCreatures().remove(this);
        //Thread.currentThread().interrupt();
    }

    //实际是距离的平方
    private int dist(Creature another){
        int x = x() - another.x();
        int y = y() - another.y();
        return x * x + y * y;
    }

    public int getNumber(){
        return number;
    }
}
