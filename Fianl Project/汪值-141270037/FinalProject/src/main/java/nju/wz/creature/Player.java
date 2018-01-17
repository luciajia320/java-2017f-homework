package nju.wz.creature;

import nju.wz.position.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public abstract class Player extends Thing2D implements Runnable, Creature {
    public Field field;
    private double fightPower;
    private int ID;
    private String name;
    private boolean die;

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFightPower(double fightPower) {
        this.fightPower = fightPower;
    }

    public double getFightPower() {
        return fightPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int index2() {
        return (this.x() - Field.OFFSET) / Field.SPACE;
    }

    public int index1() {
        return (this.y() - Field.OFFSET) / Field.SPACE;
    }

    public int index2(int posX) {
        return (posX - Field.OFFSET) / Field.SPACE;
    }

    public int index1(int posY) {
        return (posY - Field.OFFSET) / Field.SPACE;
    }

    public int getX(int index2) {
        return index2 * Field.OFFSET + Field.SPACE;
    }

    public int getY(int index1) {
        return index1 * Field.OFFSET + Field.SPACE;
    }

    public Player(Field field) {
        super(0, 0);
        this.field = field;
        setFightPower(1.0);
    }

    public void repaint() {
        this.field.repaint();
    }

    public void setImageName(String picName) {
        if(picName == null || picName.equals("")) {
            picName = "1.jpg";
        }
        URL loc = this.getClass().getClassLoader().getResource(picName);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;

        int idx2 = index2(nx);
        int idx1 = index1(ny);
        if(idx2 < 0 || idx2 > 7 || idx1 < 0 || idx1 > 7) {
            System.out.println(this.getName() + "越界");
            return;
        }
        if(!field.isExist(this, idx1, idx2)) {
            //field.resetMap();
            field.setMap(index1(this.y()), index2(this.x()), 0);
            this.setX(nx);
            this.setY(ny);
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {

                Random rand = new Random();
                Thread.sleep(rand.nextInt(2000) + 10000 / (int)getFightPower());
                if(this.isDie()) {
                    break;
                }
                field.findEnemy(this);
                field.repaint();
            }
        }
        catch(InterruptedException e) {
        }
    }

    @Override
    public int compareTo(Creature a) {
        if(a instanceof Player) {
            Random rand = new Random();
            double rnd = rand.nextDouble() * (this.getFightPower() + ((Player)a).getFightPower());
            if(rnd < getFightPower()) {
                return 1;
            }
            else {
                return -1;
            }
        }
        return 0;
    }
}