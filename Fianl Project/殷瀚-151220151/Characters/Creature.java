package Characters;
import Layout.Troop;
import Field.Position;
import Types.Vector2;

public abstract class Creature implements Paintable, Runnable{

    protected Position position;
    //protected String campName;  //  每个生物有其所属的阵营
    protected Troop troop;

    public abstract void report();
    public void act(){}

    public Creature() {
        position = new Position(0, 0);
    }

    public void moveTo(Position position) {

        if(this.position != null){
            this.position.setHolder(null);
        }
        if(position == null){
            this.setPosition(position);
            return;
        }
        if(position.getHolder() != null){   //  将目标位置上原有的占有者移走
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
        } catch (Exception e){
            System.out.println("Creature.moveTo(Vector2 ):错误的position坐标。");
            System.out.println("positionCoordinate: " + positionCoordinate);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doThreadOperations() {

    }

    private void setPosition(Position position) {   //  只改变position的值，但是不会处理相应的holder
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
