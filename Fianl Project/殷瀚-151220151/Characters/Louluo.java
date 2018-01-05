/**
 * Louluo:小喽啰，是一种生物,没有名字，只有代号
 */
package Characters;

import Types.TianGan;

import java.awt.*;

public class Louluo extends Creature{

    private TianGan codeName;

    public Louluo(TianGan codeName){
        this.codeName = codeName;
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
        return this.codeName.toString()
                + " @"
                + this.position.getX() + "," + this.position.getY()
                + ";_ ";
    }
}


