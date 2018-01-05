package Characters;
import Layout.Troop;
import Field.Position;
import Types.Vector2;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public abstract class Creature implements Paintable, Runnable{

    protected Position position;
    //protected String campName;  //  每个生物有其所属的阵营
    protected Troop troop;

    protected Image image;
    protected int imageSizeX, imageSizeY, gestureNum;
    protected NavigationDelegate navigationDelegate;// 寻路组件，在需要寻路时向其索要目的地等信息

    public abstract void report();
    public void act(){}

    public Creature() {

        position = new Position(0, 0, null);
        loadImage();
        navigationDelegate = new NavigationDelegate(this);
    }

    protected abstract void loadImage();

    public void moveTo(Position position) {

        if (!this.position.isInSomeField()) {//只有具体的position上的生物才能移动
            return;
        }
        if(this.position != null){
            this.position.setHolder(null);
        }
        if(position == null){
            // 这个生物将被移出所在的field
            // 在线程安全的前提下，不会有两个生物同时在一个position中，也就不会进入这个语句块
            // 让进入错误位置的生物被标记，并且可以继续显示出来，用于及时显示出线程不安全的问题
            this.position = new Position(this.position.getX(), this.position.getY(), null);
            return;
        }
        if(position.getHolder() != null){ //  将目标位置上原有的占有者移走
            position.getHolder().moveTo((Position) null);
        }

        this.position = position;
        position.setHolder(this);
    }

    public void moveTo(Vector2 positionCoordinate) {
        try {
            Position position = troop.getPositionAt(positionCoordinate.getX(), positionCoordinate.getY());
            if(position == null){
                throw new NullPointerException();
            }
            this.moveTo(position);
        } catch (NullPointerException e){
            System.out.println("Creature.moveTo(Vector2 ):错误的position坐标。");
            System.out.println("positionCoordinate: " + positionCoordinate);
        }

    }

    public void attemptToMoveTo(Vector2[] positionCoordinates) {
        /*
        尝试移动到某一组坐标上的position，只有找到一个没有被占据的position后，才真的会移动，否则不动。
         */
        synchronized (Creature.class) {
            for(Vector2 positionCoordinate: positionCoordinates) {
                Position position = troop.getPositionAt(positionCoordinate.getX(), positionCoordinate.getY());
                if (position.getHolder() == null) { // 候选的position没有被占据，可以移动
                    moveTo(position);
                    return;
                }
            }
        }
    }

    @Override
    public final void run() { //  template method
        try {
            while (!Thread.interrupted()) {
                doThreadOperations();
                troop.askFieldToRepaint();
            }
        } catch (NullPointerException e) {
            System.out.println("Creature.startActing(): NullPointerException.");
            e.printStackTrace();
            System.out.println(troop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected abstract void doThreadOperations();

    public final void paintInGraphics(Graphics g, int positionWidth, int positionHeight) {
        try {
            int gestureImageWidth = imageSizeX/gestureNum;
            int gestureImageHeight = imageSizeY;
            // 先计算图片右下角在field中的坐标，这样可以确定生物在图中“站立”的位置
            int rightBottomX = (int)((position.getY()+0.5)*positionWidth + 0.5*gestureImageWidth);
            int rightBottomY = (position.getX()+1)*positionHeight;
            int leftTopX = rightBottomX - gestureImageWidth;
            int leftTopY = rightBottomY - gestureImageHeight;
            g.drawImage(image,
                    leftTopX, leftTopY,
                    rightBottomX, rightBottomY,
                    0, 0,
                    gestureImageWidth, gestureImageHeight,
                    null);
            if(!this.position.isInSomeField()) {
                g.drawString("线程错误，已被移除", leftTopX, leftTopY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(Position position) {   //  只改变position的值，但是不会处理相应的holder
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void changePositionWith(Creature another){

        Position tempPosition = this.position;
        this.moveTo(another.getPosition());
        another.moveTo(tempPosition);
    }


    public String getCampName() {
        if(troop == null)
            return "";
        else
            return troop.getCampName();
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }
}
