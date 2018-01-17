package nju.java;

import java.awt.Image;
import java.io.FileOutputStream;
import java.util.Random;

import static java.lang.Thread.sleep;


abstract class Creature extends Thing2D implements Runnable {

    public boolean isDead() {
        return state == State.DEAD;
    }

    enum State{DEAD, MARCHING, FIGHTING};

    protected FileOutputStream out;
    protected Field field;
    protected State state = State.MARCHING;

    private ImagePlayer imageDeads;
    private ImagePlayer imageFightings;
    private ImagePlayer imageMarchings;

    public Creature(int x, int y, Field field, String url) {
        super(x, y);

        this.field = field;
        this.field.getTile(x, y).setHolder(this);

        imageDeads = new ImagePlayer(url+"_dead", 1);
        imageFightings = new ImagePlayer(url+"_fighting", 5);
        imageMarchings = new ImagePlayer(url+"_marching",6);

    }

    public Image getImage() throws Exception {
        switch (state){
            case DEAD:
                return imageDeads.getNextImage();
            case FIGHTING:
                return imageFightings.getNextImage();
            case MARCHING:
                return imageMarchings.getNextImage();
            default:break;
        }
        throw new Exception("No images to show");
    }

    public void setState(State state){
        LittleUtils.record(System.currentTimeMillis() + "\t" + this.toString() + "\t" + state.name() + "\n");
        this.state = state;
    }

    public void setStateWithoutRecord(State state){
        this.state = state;
    }

    public void move(int x, int y){
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    protected int norm(int n){
        int tmp = n - this.field.OFFSET;
        tmp = (tmp / this.field.SPACE) * this.field.SPACE + this.field.OFFSET;
        return tmp;
    }

    public void fight(Creature enemgy){
        if(this.state == State.DEAD || enemgy.state == State.DEAD)
            return;
        if(this.state == State.FIGHTING || enemgy.state == State.FIGHTING)
            return;
        Random random = new Random();
        int myScore = random.nextInt(100);
        int enemgyScore = random.nextInt(100);

        playFightAction(this, enemgy);

        if(myScore >= enemgyScore){
            enemgy.setState(State.DEAD);
            this.setState(State.MARCHING);
        }
        else{
            this.setState(State.DEAD);
            enemgy.setState(State.MARCHING);
        }

    }

    static public void playFightAction(Creature c1, Creature c2){
        c1.setState(State.FIGHTING);
        c2.setState(State.FIGHTING);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    abstract void oneStep() throws Exception;

    enum Direction{
        上, 下, 左, 右;
    }

    protected void oneStep(Direction direction){

        int deltaX = 0, deltaY = 0;

        final int step = 5;

        switch (direction){
            case 上: deltaY = step; break;
            case 下: deltaY = -step; break;
            case 左: deltaX = -step; break;
            case 右: deltaX = step; break;
            default: break;
        }

        int nextX = x() + deltaX;
        int nextY = y() + deltaY;
        Tile t = this.field.getTile(norm(x()), norm(y()));
        Tile nt = this.field.getTile(norm(nextX), norm(nextY));

        if(t == null || nt == null){
            //setState(State.DEAD);
            return;
        }

        synchronized (nt){

            if(nt.getHolder() == null || nt.getHolder() == this){    //下一个位置上没有东西或者是自己
                //go ahead!

                t.setHolder(null);
                move(deltaX, deltaY);
                nt.setHolder(this);
                synchronized (LittleUtils.out){
                    LittleUtils.record(System.currentTimeMillis() + "\t");
                    LittleUtils.record(this.toString() + "\t");
                    LittleUtils.record("MARCHING\t");
                    LittleUtils.record(deltaX + "\t" + deltaY + "\t");
                    LittleUtils.record("\n");
                }
            }
            else{                          //下一个位置上有东西
                //well
            }

        }


    }


    public void run() {

        while (!Thread.interrupted()) {
            Random rand = new Random();
            try {
                if(state == State.MARCHING)
                    oneStep();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

            if(this.state == State.DEAD){
                Tile tile = field.getTile(norm(x()), norm(y()));
                if(tile == null){
                    break;
                }
                tile.setHolder(null);
                break;
            }

            try {
                sleep(rand.nextInt(100) + 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}