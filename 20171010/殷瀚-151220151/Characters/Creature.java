package Characters;
import Position.Position;

public abstract class Creature {

    protected Position position;
    protected String campName;  //  每个生物有其所属的阵营

    public abstract void report();

    public void act(){}


    public void moveTo(Position position) {

        if(this.position != null){
            this.position.setHolder(null);
        }
        if(position == null){
            this.setPosition(position);
            return;
        }
        if(position.getHolder() != null){   //  将目标位置上原有的占有者移走
            position.getHolder().moveTo(null);
        }

        this.position = position;
        position.setHolder(this);
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

    public void setCampName(String campName){
        this.campName = campName;
    }

    public String getCampName() {
        return campName;
    }
}
