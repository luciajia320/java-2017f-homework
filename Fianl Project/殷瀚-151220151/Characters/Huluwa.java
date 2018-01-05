package Characters;

import Field.Position;
import Types.COLOR;
import Types.SENIORITY;
import Types.Vector2;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class Huluwa extends Creature implements Comparable {

    private COLOR color;
    private SENIORITY seniority;


    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }


    public Huluwa(COLOR color, SENIORITY seiority) {
        super();

        this.color = color;
        this.seniority = seiority;


    }
    @Override
    protected void loadImage() {
        URL loc = this.getClass().getClassLoader().getResource("Image/xiaojingang.png");
        image = new ImageIcon(loc).getImage();
        imageSizeX = 426;
        imageSizeY = 96;
        gestureNum = 6;
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public Image getImage() {
        return image;
    }


    @Override
    protected void doThreadOperations() {
        try {

            if (navigationDelegate != null) {
                // 先得到要去的position
                Position destination = navigationDelegate.getPositionOfNearestHostileCreature();
                // 然后尝试往这个方向移动
                attemptToMoveTo(navigationDelegate.getpossibleNextPositionVectors(destination));
            }

            Thread.sleep(1000);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public String toString(){
        return this.seniority.toString()
                + "(" + this.color.toString() + ")@"
                + this.position.getX() + "," + this.position.getY()
                + ";";
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }

    @Override
    public void changePositionWith(Creature another){
        if(another instanceof Huluwa) {
            System.out.println(seniority + ": "
                    + this.position.getX() + "," + this.position.getY()
                    + "->"
                    + another.position.getX() + "," + another.position.getY()
            );
        }

        super.changePositionWith(another);
    }
}



