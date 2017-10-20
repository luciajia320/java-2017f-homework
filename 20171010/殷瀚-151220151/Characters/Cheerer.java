/**
 * Cheerer是一种生物，可以为自己所属的势力加油助威
 * 爷爷和蛇精属于这个类
 */
package Characters;
import Position.Position;

public class Cheerer extends Creature implements CheeringGroup {
    private String name;
    private Position position;
    private String campName;

    Cheerer(String campName){
        this.campName = campName;
    }

    @Override
    public void cheerUp(){
        System.out.println(name + " : " + campName + "牛逼！");
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        return this.name
                + "@"
                + this.position.getX() + this.position.getY()
                + ";";
    }
}
