/**
 * Cheerer是一种生物，可以为自己所属的势力加油助威
 * 只能站在原地
 * 爷爷和蛇精属于这个类
 */
package Characters;

import java.awt.*;

public class Cheerer extends Creature implements CheeringGroup {
    protected String name;

    public Cheerer(String name){
        super();
        this.name = name;
    }


    @Override
    protected void prepareRenderComponent() {

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
    protected void doThreadOperations() {
        if (timerComponent.timesCount == 0) { // per second
            cheerUp();
        }
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
