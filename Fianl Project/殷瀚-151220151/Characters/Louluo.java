/**
 * Louluo:小喽啰，是一种生物,没有名字，只有代号
 */
package Characters;

import Field.Position;
import Types.TianGan;
import Types.Vector2;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Louluo extends Creature{

    private TianGan codeName;

    public Louluo(TianGan codeName){
        super();

        this.codeName = codeName;

    }
    @Override
    protected void loadImage() {
        URL loc = this.getClass().getClassLoader().getResource("Image/hama.png");
        image = new ImageIcon(loc).getImage();
        imageSizeX = 426;
        imageSizeY = 69;
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
        try{
            // 先得到要去的position
            Position destination = navigationDelegate.getPositionOfNearestHostileCreature();
            // 然后尝试往这个方向移动
            attemptToMoveTo(navigationDelegate.getpossibleNextPositionVectors(destination));

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
        return this.codeName.toString()
                + " @"
                + this.position.getX() + "," + this.position.getY()
                + ";_ ";
    }
}


