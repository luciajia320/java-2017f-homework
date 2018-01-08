package nju.java;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.yield;

public class Huluwa extends Player {
    public static void reInit() {
        count = 1;
    }

    private static int count = 1;
    private final int id = count;
    private final Hulu name = Hulu.newHulu(id);

    Huluwa(int x, int y, Field field) throws IOException {
        super(x,y,field);
        count++;
        path = "huluwa/wa"+id+".png";
        URL loc = this.getClass().getClassLoader().getResource(path);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }



    @Override
    public void reloadPath() {
        path = "huluwa/dwa"+id+".png";
        URL loc = this.getClass().getClassLoader().getResource(path);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    @Override
    public String toString(){
        return name.toString();
    }

    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> list = new ArrayList<String>();
        for(State s : getRoute()) {
            list.add(this.printString()+" "+s.toString());
        }
        return list;
    }

    public String printString() {
        return super.toString()+id;
    }
}

class Yeye extends Player {
    public static void reInit() {
        count = 1;
    }

    private static int count = 1;


    Yeye(int x, int y, Field field) throws Exception {
        super(x,y,field);
        full_life = 800;
        life = 800;
        if(count == 0)
            throw new Exception("Yeye exists.");
        count--;
        URL loc = this.getClass().getClassLoader().getResource("huluwa/yeye.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void reloadPath() {
        path = "huluwa/dyeye.png";
        super.reloadPath();
    }

    @Override
    public String toString() {
        return new String("爷爷");
    }

    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> list = new ArrayList<String>();
        for(State s : getRoute()) {
            list.add(this.printString()+" "+s.toString());
        }
        return list;
    }

    public String printString() {
        return super.toString();
    }

    public void randMove(){
        Random rand = new Random();
        if(!isBusy()){
            if(dir)
                this.move(0,rand.nextInt(10)-1);
            else
                this.move(0,1-rand.nextInt(10));
            dir = !dir;
        }
        else{
            if(dir)
                this.move(0,rand.nextInt(3)-1);
            else
                this.move(0,1-rand.nextInt(3));
            dir = !dir;
        }
    }
    @Override
    public void run(){
        while (!Thread.interrupted()) {
            if(isStopped())
                return;
            try {
                field.mutex.waitForBuffing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("Wax On! ");
            if(!isAlive() || isStopped()) {
                field.mutex.waxed();
                return;
            }
            if(!isBusy()) {
                Random rand = new Random();
//                if(dir)
//                    this.move(0,rand.nextInt(11)-1);
//                else
//                    this.move(0,1-rand.nextInt(11));
//                dir = !dir;
                try {
                    Thread.sleep(80+rand.nextInt(40));
                    this.field.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!isAlive()) {
                    field.mutex.waxed();
                    return;
                }
            }
            else {
                Random rand = new Random();
//                if(dir)
//                    this.move(0,2);
//                else
//                    this.move(0,-2);
//                dir = !dir;
                String result = this+" vs "+getTarget()+": ";
                result += getTarget().hurt(((int) Math.ceil(2)));
                field.takeNote(result);
                if(!getTarget().isAlive())
                    this.busy = false;
                try {
                    yield();
                    Thread.sleep(200+rand.nextInt(150));
//                    this.field.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!isAlive()) {
                    field.mutex.waxed();
                    return;
                }
                field.mutex.waxed();
            }
        }
    }
}
