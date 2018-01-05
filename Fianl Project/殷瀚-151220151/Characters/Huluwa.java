package Characters;

import Field.Position;
import Types.COLOR;
import Types.SENIORITY;
import Types.Vector2;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

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
    protected void prepareRenderDelegate() {
        URL loc = this.getClass().getClassLoader().getResource("Image/xiaojingang.png");
        renderComponent.image = new ImageIcon(loc).getImage();
        renderComponent.imageSizeX = new ImageIcon(loc).getIconWidth();
        renderComponent.imageSizeY = new ImageIcon(loc).getIconHeight();
        renderComponent.gestureNum = 6;
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }


    @Override
    protected void doThreadOperations() {
        try {


            if (timerComponent.timesCount == 0) { // per second
                if (navigationDelegate != null) {
                    // 先得到要去的position
                    Position destination = navigationDelegate.getPositionOfNearestHostileCreature();
                    // 然后尝试往这个方向移动
                    Vector2 currentCoordinate = this.position.getCoordinate();
                    isMoving = attemptToMoveTo(navigationDelegate.getPossibleNextPositionVectors(destination));
                    if (isMoving) {
                        remainMoveAnimationTimes = 10; // 移动动画持续500毫秒
                        renderComponent.startMovingProgressWithDuration(remainMoveAnimationTimes, currentCoordinate);
                    }

                }
            }

            if (timerComponent.timesCount%2 == 0) {
                if (isMoving) {
                    renderComponent.changeToNextMovingGesture();
                }
            }


            //Thread.sleep(1000);
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



