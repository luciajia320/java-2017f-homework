/**
 * Cheerer是一种生物，可以为自己所属的势力加油助威
 * 爷爷和蛇精属于这个类
 */
package Characters;

import java.awt.*;

public class Cheerer extends Creature implements CheeringGroup {
    private String name;

    public Cheerer(String name){
        this.name = name;
    }


    @Override
    public void act(){
        cheerUp();
    }

    @Override
    public void cheerUp(){
        System.out.println(name + " : " + troop.getCampName() + "牛逼！");
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void paintInGraphics(Graphics g, int positionWidth, int positionHeight) {

    }


    @Override
    public String toString(){
        return this.name
                + "  "
                + "@"
                + this.position.getX() + "," + this.position.getY()
                + ";";
    }
}
