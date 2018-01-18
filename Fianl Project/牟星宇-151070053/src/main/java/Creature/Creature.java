package Creature;

import ENUM.NAME;
import ENUM.STATE;
import Ground.Position;
import Ground.BattleGround;
import Hulu.Anno.AuthorAnno;

import java.awt.*;


@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public abstract class Creature implements Runnable{

    protected Position position;

    protected NAME name;

    protected static int count=0;

    protected int basic_Hp;
    protected int current_Hp;

    protected int basic_damage;

    protected static int deathNum = 0;

    protected transient Image img_right;//面朝右侧
    protected transient Image img_left;//面朝左侧
    protected transient Image img_dead;//尸体

    protected int direction;//方向（左/右）

    protected BattleGround battleGround;//所在ground

    protected boolean pause = false;

    public int id;//生物标识


    public static void setDeathNum(int deathNum) {
        Creature.deathNum = deathNum;
    }

    public Creature(){
        id = count;
        count++;
        position = new Position(0,0);
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            //判断暂停
            synchronized (this){
                while(this.isPause()){
                    try{
                        wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            //判断自己是否已死亡
            if (this.isDead()) {
                this.position.cleanHolder();
                synchronized ((Object) deathNum){
                    deathNum++;
                    if(deathNum >= 16){
                        battleGround.setState(STATE.OVER);
                    }
                }
                return;
            }

            //找到最近敌人
            Creature creature = battleGround.getClosestEnemy(this);
            //无敌人,结束
            if(creature == this) {
                battleGround.setState(STATE.OVER);
                battleGround.repaint();
                return;
            }
            //相遇后开始战斗
            if (this.position.isNearBy(creature.getPosition())) {
                synchronized (this) {
                    if (creature.isDead()) continue;//该位置敌人已死
                    synchronized (creature) {
                        try
                        {
                            Thread.sleep(500);
                            this.battleGround.battling(this, creature);
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            }


            //移动+延时
            try
            {
                this.moveTo(creature.getPosition());
                Thread.sleep(1200);

            } catch (Exception e) {
                break;
            }
        }

    }




    public void move(int x, int y) {
        if(this.isDead()) return;
        int nx = this.position.getX() + x;
        int ny = this.position.getY() + y;
        if(nx < 0 || ny < 0) return;
        if(nx >= battleGround.getW() || ny >= battleGround.getH()) return;
        synchronized (position){
            if(!battleGround.getPositions()[nx][ny].isEmpty()) return;
            synchronized (battleGround.getPositions()[nx][ny]){
                position.cleanHolder();
                this.setPosition(battleGround.getPositions()[nx][ny]);
            }
        }
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void reversePause(){
        this.pause = !this.pause;
    }


    public void moveTo(Position position){
        if(this.isDead()) return;
        if (position.getX() > this.position.getX()) {
            this.move(1, 0);
        } else if (position.getX() < this.position.getX()) {
            this.move(-1, 0);
        } else {
            if (position.getY() > this.position.getY()) {
                this.move(0, 1);
            } else if (position.getY() < this.position.getY()) {
                this.move(0, -1);
            }
        }
    }


    public void setImg_left(Image img_left) {
        this.img_left = img_left;
    }

    public void setImg_right(Image img_right) {
        this.img_right = img_right;
    }

    public void setImg_dead(Image img_dead) {
        this.img_dead = img_dead;
    }

    public void setBasic_damage(int basic_damage) {
        this.basic_damage = basic_damage;
    }

    public void setBasic_Hp(int basic_Hp) {
        this.basic_Hp = basic_Hp;
    }

    public int getBasic_Hp() {
        return basic_Hp;
    }

    public int getCurrent_Hp() {
        return current_Hp;
    }

    public void setCurrent_Hp(int current_Hp) {
        this.current_Hp = current_Hp;
    }

    public void setDirection(int dir){
        this.direction = dir;
     }

    public int getDirection() {
        return direction;
    }

    public void setBattleGround(BattleGround battleGround) {
        this.battleGround = battleGround;
    }

    public BattleGround getBattleGround() {
        return battleGround;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    public Image getImage(){
        if(this.isDead()) {
            return this.img_dead;
        }
        if(this.direction == 0){
             return this.img_left;
         }
         else{
             return this.img_right;
         }
    }

    public synchronized void wounded(int damage){
        if(damage >= this.current_Hp){
            current_Hp = 0;
        }
        else {
            current_Hp -=damage;
        }
    }

    public boolean isDead(){
        if(current_Hp > 0)
            return false;
        return true;
    }

    public void initial(){

        pause = false;
        current_Hp = basic_Hp;
    }


    public abstract int damage();

    public NAME getNAME(){
        return this.name;
    }

    public int getCreatureId() {
        return id;
    }
}

