package Characters;
import Layout.Troop;
import Field.Position;
import Types.Vector2;

import java.awt.*;

public abstract class Creature implements Paintable, Runnable{

    protected Position position;
    //protected String campName;  //  每个生物有其所属的阵营
    protected Troop troop;
    protected RenderComponent renderComponent;
    protected NavigationDelegate navigationDelegate;// 寻路组件，在需要寻路时向其索要目的地等信息
    protected TimerComponent timerComponent;

    protected int remainMoveAnimationTimes = 0;
    protected boolean isMoving = false; // 用于实现连续移动动画

    public abstract void report();
    public void act(){}

    public Creature() {

        position = new Position(0, 0, null);
        renderComponent = new RenderComponent(this);
        prepareRenderDelegate();
        navigationDelegate = new NavigationDelegate(this);
        timerComponent = new TimerComponent(this);
    }

    protected abstract void prepareRenderDelegate();

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

    public boolean attemptToMoveTo(Vector2[] positionCoordinates) {
        /*
        尝试移动到某一组坐标上的position，只有找到一个没有被占据的position后，才真的会移动，否则不动。
         */
        synchronized (Creature.class) {
            for(Vector2 positionCoordinate: positionCoordinates) {
                Position position = troop.getPositionAt(positionCoordinate.getX(), positionCoordinate.getY());
                if (position.getHolder() == null) { // 候选的position没有被占据，可以移动
                    moveTo(position);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public final void run() { //  template method
        try {
            while (!Thread.interrupted()) {
                doThreadOperations();
                if (remainMoveAnimationTimes > 0) {
                    remainMoveAnimationTimes --;
                    renderComponent.updateMovingProgress();
                    if (remainMoveAnimationTimes == 0)
                        isMoving = false;
                }
                troop.askFieldToRepaint();

                timerComponent.tick();
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
        if (renderComponent != null) {
            renderComponent.paintInGraphics(g, positionWidth, positionHeight);
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
