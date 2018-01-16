package tangwy.nju.edu.cn.huluwasvsdemons.things.creature;
import tangwy.nju.edu.cn.huluwasvsdemons.GUIWindow;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Creature implements Runnable{
    /*x,y属性记录单位在屏幕中的位置，而row，column记录32*20的格子化的战场中的位置。
    不将这二者统一起来的原因在于：
        一、如果将每一帧中各单位的位置格子化，那么战斗动画的连续性会很差
        二、如果放弃格子化，那么
            1、将与项目要求不符（所以这不是我的锅）
            2、每个单位将占据成百上千个像素点，为所有像素点都设计一个信号量加锁来实现避免碰撞是不现实的，因为
                1）时间开销很大
                2）极有可能造成死锁
                （这种情况各单位轮流执行比多线程靠谱得多，然而项目要求告诉我不能这么做，所以这也不是我的锅）
      因此采用这样一种设计，每个生物在移动阶段必须由一个格子移动向另一个格子(且在移动过程之中不得攻击)，从而每个单位最多同时持有两个信号量
     */
    private int x,y;
    private int row,column,rowMovement,columnMovement;
    private static final long serialVersionUID = 2L;
    private boolean died;
    private int tick=0;//用于线程同步
    protected int health=100,maxHealth=100,attackDamage;
    protected double attackRange=50.0;
    enum State{ IDLE, MOVING, PRE_ATTACK, AFTER_ATTACK};
    State state=State.IDLE;
    protected int counting=0;
    protected int preAttackCounting=50,afterAttackCounting=50;
    protected Creature attackObject;
    private final int battleNum=GUIWindow.getBattleNum();
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setPosition(int row, int column){
        this.row=row;
        this.column=column;
        updateXY();

    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getRow() {
        return row;
    }

    public int getRowMovement() {
        return rowMovement;
    }

    public int getColumnMovement() {
        return columnMovement;
    }

    public int getColumn() {
        return column;
    }
    public int getHealth() {
        return health;
    }
    public Image getImage() {
        URL loc = this.getClass().getClassLoader().getResource(imageName);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        return image;
    }
    public boolean isDied(){return died;}
    public Creature(String imageName){
        health=maxHealth;
        this.imageName=imageName;
    }


    public void run(){
        while (!Thread.interrupted()&&battleNum==GUIWindow.getBattleNum()&&!died) {
//            System.out.println(this.toString()+"    tick="+tick);
            try {
                GUIWindow.getInstance().waitForRunning(tick);
                tick++;
                act();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    protected void act(){
        if(died)return;
        switch (state){
            case IDLE:
                decideNextAction();
                break;
            case MOVING:
                move();
                break;
            case PRE_ATTACK:
                counting--;
                if(attackObject.isDied()){
                    state=State.IDLE;
                }
                else if(counting<=0){
                    attack();
                    state=State.AFTER_ATTACK;
                    counting=afterAttackCounting;
                }
                break;
            case AFTER_ATTACK:
                counting--;
                if(counting<=0)state=State.IDLE;
                break;
        }


    }

    protected Creature findNearestObject(){
        ArrayList<Creature> objectList;
        if(GUIWindow.getBattlefield().getTeam1().indexOf(this)>=0)objectList=GUIWindow.getBattlefield().getTeam2();
        else objectList=GUIWindow.getBattlefield().getTeam1();
        double minDistance=Double.POSITIVE_INFINITY;
        Creature nearestObject=null;
        for(Creature creature:objectList){
            Double distance=distanceFrom(creature);
            if(distance<minDistance){
                minDistance=distance;
                nearestObject=creature;
            }
        }
        return nearestObject;
    }

    private double distance(int x1,int y1,int x2,int y2){
        return Math.sqrt((double)(x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    private double distanceFrom(Creature creature){
        return distance(x,y,creature.x,creature.y);
    }

    private void updateXY(){
        int offset=GUIWindow.offset;
        x=column*offset;
        y=row*offset;
    }

    private void decideNextAction(){
        Creature nearestObject=findNearestObject();
        if(nearestObject==null)return;
        else if(distanceFrom(nearestObject)<=attackRange){
            attackObject=nearestObject;
            state=State.PRE_ATTACK;
            counting=preAttackCounting;
        }
        else {
            rowMovement=0;
            columnMovement=0;
            int xDistance=nearestObject.getX()-x;
            int yDistance=nearestObject.getY()-y;
            int absX=Math.abs(xDistance);
            int absY=Math.abs(yDistance);
            Random r=new Random();
            if(r.nextInt(absX+absY)<absX)
                columnMovement=xDistance/absX;
            else
                rowMovement=yDistance/absY;
 //           System.out.println(this+"trying: rm" +rowMovement+"cm"+columnMovement);
            GUIWindow.getBattlefield().getPosition(this,row+rowMovement,column+columnMovement);
            if(GUIWindow.getBattlefield().ownPosition(this)){
                state=State.MOVING;
                counting=GUIWindow.offset;
    //            System.out.println(this.getClass()+" rm" +rowMovement+"cm"+columnMovement);
            }
            else state=State.IDLE;
        }
    }

    public void sufferDamage(int damage){
        if(died)return;
        if(damage<health)health-=damage;
        else{
            health=0;
            die();
        }
    }

    private synchronized void die(){
        died=true;
        GUIWindow.getBattlefield().releasePosition(this);
        if(state==State.MOVING)GUIWindow.getBattlefield().releaseTowardsPosition(this);
        GUIWindow.getBattlefield().getTeam1().remove(this);
        GUIWindow.getBattlefield().getTeam2().remove(this);
    }

    public void getTreatment(int treatment){
        if(died)return;
        if(treatment+health<maxHealth)health+=treatment;
        else health=maxHealth;
    }

    protected void attack(){
        attackObject.sufferDamage(attackDamage);
    };

    protected void move(){
        x+=columnMovement;
        y+=rowMovement;
        counting--;
        if(counting<=0){
            GUIWindow.getBattlefield().releasePosition(this);
     //       System.out.println(this+ "release "+ row+" "+column);
            row+=rowMovement;
            column+=columnMovement;
            state=State.IDLE;

        }
    }
}
