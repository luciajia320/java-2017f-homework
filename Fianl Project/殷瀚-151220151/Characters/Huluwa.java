package Characters;

import Types.COLOR;
import Types.SENIORITY;
import Types.Vector2;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Huluwa extends Creature implements Comparable {

    private COLOR color;
    private SENIORITY seniority;

    private Image image;
    private int imageSizeX = 426, imageSizeY = 96, gestureNum = 6;
    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }


    public Huluwa(COLOR color, SENIORITY seiority) {
        this.color = color;
        this.seniority = seiority;
        URL loc = this.getClass().getClassLoader().getResource("Image/xiaojingang.png");
        image = new ImageIcon(loc).getImage();
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
    public void paintInGraphics(Graphics g, int positionWidth, int positionHeight) {
        try {
            g.drawImage(image,
                    position.getY()*positionWidth, position.getX()*positionHeight,
                    position.getY()*positionWidth+imageSizeX/gestureNum, position.getX()*positionHeight+imageSizeY,
                    0, 0,
                    imageSizeX/gestureNum, imageSizeY,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doThreadOperations() {

        this.moveTo(new Vector2(position.getX()+1, position.getY()+1));
        try {
            troop.askFieldToRepaint();
            Thread.sleep(100);
        } catch (NullPointerException e) {
            System.out.println("");
        } catch (Exception e) {

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



