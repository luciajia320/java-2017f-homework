/**
 * Leader是一种生物，有名字，暂时只会站着
 * 蝎子精属于这个类
 */
package Characters;

import java.awt.*;

public class Leader extends Creature {
    private String name;

    public Leader(String name){
        super();
        this.name = name;
    }
    @Override
    protected void prepareRenderDelegate() {

    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    protected void doThreadOperations() {

    }

    @Override
    public String toString(){
        return this.name
                + "@"
                + this.position.getX() + "," + this.position.getY()
                + ";";
    }
}
