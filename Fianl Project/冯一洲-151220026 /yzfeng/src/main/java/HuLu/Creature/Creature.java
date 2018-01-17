package HuLu.Creature;

import HuLu.Field.Field;
import HuLu.Field.GameState;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public abstract class Creature extends Thing2D implements Runnable, Movable {
    protected int id;
    protected Field field;
    protected boolean alive = true;
    protected CyclicBarrier cyclic;
    protected int attackRange = 1;
    protected int nextX, nextY;

    public Creature(int id ,Field field, CyclicBarrier cyclic) {
        this.id = id;
        this.field = field;
        this.cyclic = cyclic;
    }
    public int getId(){
        return id;
    }

    public synchronized void killed(){
        alive = false;
        field.getHolders()[x][y].leaveHolder();
    }

    public Image getImage() {
        if(alive){
            return super.getImage(0);
        }
        else{
            return super.getImage(1);
        }
    }

    public synchronized boolean isAlive() {
        return alive;
    }

    public synchronized int x() {
        return this.x;
    }

    public synchronized int y() {
        return this.y;
    }

    public synchronized void move(int x, int y){
        field.leaveHolder(this.x, this.y);
        this.x += x;
        this.y += y;
        field.setHolder(this.x, this.y, this);
    }

    public synchronized void moveTo(int x, int y){
        field.leaveHolder(this.x, this.y);
        this.x = x;
        this.y = y;
        field.setHolder(this.x, this.y, this);
    }

    public synchronized boolean validMove(int x, int y){
        if(this.x + x < field.getBoardWidth()
                && this.x + x >= 0
                && this.y + y< field.getBoardHeight()
                && this.y + y >= 0
                && field.getHolders()[this.x + x][this.y + y].isEmpty())
            return true;

        else return false;
    }

    public void run(){
        while (!Thread.interrupted() && field.getGameState() != GameState.END) {
            try {
                if (alive){
                    boolean move = new Random().nextBoolean();
                    if(validMove(nextX, nextY) && move)
                        move(nextX, nextY);
                    field.repaint();
                }

                cyclic.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
    }

    public abstract int meetEnemy();
    public abstract void attackAt(Creature c);
    public synchronized void attackAt(int k){
        int x = this.x;
        int y = this.y;
        switch (k) {
            case 0:     x = x + attackRange; break;
            case 1:     x = x - attackRange; break;

            default:    assert(false);
        }
        Creature c = field.getHolders()[x][y].getItem();
        attackAt(c);
    }

    public void getNext(int k){
        switch (k) {
            case 0:     nextX = 1;  nextY = 0; break;
            case 1:     nextX = -1; nextY = 0; break;
            case 2:     nextY = 1;  nextX = 0; break;
            case 3:     nextY = -1; nextX = 0; break;
            default:    assert(false);
        }
    }

    public void findnearest(){

    }
}
