package  creature;
import Battle.Queue;
import UI.Field;


import UI.Thing2D;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Creature extends Thing2D implements Runnable {

    //用于判断是否运行中途退出
    boolean is_interupt=false;

    //一下两项用于分辨加载的图像
    //0为向左，1为像右，2为死亡
    private int currentState;
    private final Name id;

    //用于表示移动速度快慢
    private final int moveBounds;

    //所在战场，作为唯一的战场，成为线程之间争夺的资源
    private Field field;

    //判断死亡
    private boolean is_dead=false;

    public void setIs_dead(boolean is_dead) {
        this.is_dead = is_dead;
    }

    public boolean getIs_dead(){
        return is_dead;
    }

    public void setIs_interupt(boolean is_interupt) {
        this.is_interupt = is_interupt;
    }

    //用于输出到文件使用
    public String speak() {
        return getId().toString();
    }

    public Name getId() {
        return id;
    }

    public int getCurrentState() {
        return currentState;
    }

    public Field getField() {
        return field;
    }

    public Creature(int x, int y, Field field, Name id,int moveBounds,int direction) {
        super(x, y, field.getScenery()[x][y]);

        currentState=direction;

        this.field = field;

        this.moveBounds=moveBounds;

        this.id = id;


        getCurrentImage();
    }

    //此函数用于加载不同状态下不同生物的图像
    public void getCurrentImage() {
        URL loc;
        switch (id) {
            case 一: {
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("hongZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("hongYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("hongSi.png");
                break;
            }
            case 二:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("chengZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("chengYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("chengSi.png");
                break;
            }
            case 三:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("huangZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("huangYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("huangSi.png");
                break;
            }
            case 四:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("lvZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("lvYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("lvSi.png");
                break;
            }
            case 五:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("qingZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("qingYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("qingSi.png");
                break;
            }
            case 六:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("lanZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("lanYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("lanSi.png");
                break;
            }
            case 七:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("ziZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("ziYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("ziSi.png");
                break;
            }
            case 蛇:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("sheZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("sheYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("sheSi.png");
                break;
            }
            case 蝎:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("xieZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("rxieYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("xieSi.png");
                break;
            }
            case 爷:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("yeZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("yeYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("yeSi.png");
                break;
            }
            case 蜈:{
                if(currentState==0)
                    loc = this.getClass().getClassLoader().getResource("wuZuo.png");
                else if(currentState==1)
                    loc = this.getClass().getClassLoader().getResource("wuYou.png");
                else
                    loc = this.getClass().getClassLoader().getResource("wuSi.png");
                break;
            }
            default:
                return;
        }
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    //此方法表示沿向量（x,y）移动
    public void move(int x, int y) {

        if (field.getRow() > (this.x + y) && field.getCol() > (this.y + x)&&(this.x+y)>=0&&this.y+x>=0) {
            this.getPosition().setSomeone(null);
            field.getScenery()[this.x + y][this.y + x].setSomeone(this);
            this.setPosition(field.getScenery()[this.x + y][this.y + x]);
            this.x += y;
            this.y += x;
        }
    }

    //此方法表示移动到点Scenery[x][y]
    public void moveto(int x, int y) {
        if (x < field.getRow() && y < field.getCol()) {
            this.getPosition().setSomeone(null);
            field.getScenery()[x][y].setSomeone(this);
            this.setPosition(field.getScenery()[x][y]);
            this.x = x;
            this.y = y;
        }
    }

    //对象的寻路并移动算法
    void nextMove(Queue myQueue, Queue enemyQueue){
        int enemyX = -1;
        int enemyY = -1;
        int moveX = 0;
        int moveY = 0;
        int index = -1;
        Random rand = new Random();
        Random randmove=new Random();

        L1:
        for(int i=this.x;i<field.getRow();i++)
            for(int j=0;j<field.getCol();j++)
            {
                Creature creature=field.getScenery()[i][j].getSomeone();
                if(creature!=null&&creature.getIs_dead()==false)
                {
                    index=enemyQueue.isThere(creature);
                    if(index!=-1)
                    {
                        enemyX=enemyQueue.getCreatures()[index].getX();
                        enemyY=enemyQueue.getCreatures()[index].getY();
                        break L1;
                    }
                }
            }

        if (this.x > enemyX && this.y > enemyY) {
            currentState=0;
            moveX = -randmove.nextInt(moveBounds);
            moveY = -randmove.nextInt(moveBounds);
        } else if (this.x > enemyX && this.y < enemyY) {
            currentState=1;
            moveX = randmove.nextInt(moveBounds);
            moveY = -randmove.nextInt(moveBounds);
        } else if (this.x < enemyX && this.y < enemyY) {
            currentState=1;
            moveX = randmove.nextInt(moveBounds);
            moveY = randmove.nextInt(moveBounds);
        } else if (this.x < enemyX && this.y > enemyY) {
            currentState=0;
            moveX = -randmove.nextInt(moveBounds);
            moveY = randmove.nextInt(moveBounds);
        } else if (this.x == enemyX && this.y > enemyY) {
            currentState=0;
            moveX = -randmove.nextInt(moveBounds);
            moveY = 0;
        } else if (this.x == enemyX && this.y < enemyY) {
            currentState=1;
            moveX = randmove.nextInt(moveBounds);
            moveY = 0;
        } else if (this.x > enemyX && this.y == enemyY) {
            moveX = 0;
            moveY = -randmove.nextInt(moveBounds);
        } else if (this.x < enemyX && this.y == enemyY) {
            moveX = 0;
            moveY = randmove.nextInt(moveBounds);
        } else
            ;

        //移动时需要对战场进行操作，所以战场作为锁出现
        synchronized (getField().getScenery()) {
            if(x + moveY<field.getRow()&&y+moveX<field.getCol()&&x+moveY>=0&&y+moveX>=0) {
                if (getField().getScenery()[x + moveY][y + moveX].getSomeone() != null) {
                    try {
                        getField().getScenery().wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.move(moveX, moveY);
            this.field.getBattle().FightCheck();
            getField().getScenery().notifyAll();
        }
        try {
            Thread.sleep(rand.nextInt(100) + 100);
            this.field.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void outputToFile(){
        synchronized (field.getFile()){
            FileWriter fw=null;
            BufferedWriter bw=null;
            try{
                fw=new FileWriter(field.getFile().getAbsoluteFile(),true);
                bw=new BufferedWriter(fw);
                for(int i=0;i<field.getRow();i++){
                    for(int j=0;j<field.getCol();j++) {
                        if (field.getScenery()[i][j].getSomeone() != null)
                        {
                            bw.write(field.getScenery()[i][j].getSomeone().speak());
                            bw.write(((Integer)field.getScenery()[i][j].getSomeone().getCurrentState()).toString());
                        }
                        else
                            bw.write('*');
                    }
                    bw.write("\r\n");
                }
                bw.write("\r\n");

                bw.close();
                fw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void run() {

        while (!Thread.interrupted()) {

            if(is_interupt)
                break;

            //最后剩下的一方的所有线程保存战斗结果并退出线程
            if(field.getCompleted()!=0) {
                outputToFile();
                break;
            }

            //根据阵营移动
            if (this.getClass().equals(Huluwa.class)||this.getClass().equals(Yeye.class))
                nextMove(field.getBattle().getHuluwa(),field.getBattle().getEnemy());
            else
                nextMove(field.getBattle().getEnemy(),field.getBattle().getHuluwa());

            //移动完后需要输出到文件，由于文件也只有一个，所以此处文件也成为了竞争资源，写完文件之后才会释放锁
            outputToFile();

            //移动完成后可能发生状态的改变，可能死亡，要做处理
            getCurrentImage();
            this.field.repaint();
            if(is_dead==true) {
                currentState=2;
                getCurrentImage();
                this.field.repaint();
                break;
            }
        }
    }
}