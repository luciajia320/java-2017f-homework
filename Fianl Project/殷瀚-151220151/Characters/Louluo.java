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
    protected void prepareRenderDelegate() {
        URL loc = this.getClass().getClassLoader().getResource("Image/hama.png");
        renderComponent.image = new ImageIcon(loc).getImage();
        renderComponent.imageSizeX = 426;
        renderComponent.imageSizeY = 69;
        renderComponent.gestureNum = 6;
    }
    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    protected void doThreadOperations() {
        try{
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
        return this.codeName.toString()
                + " @"
                + this.position.getX() + "," + this.position.getY()
                + ";_ ";
    }
}


