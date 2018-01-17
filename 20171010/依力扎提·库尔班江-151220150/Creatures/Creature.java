package Creatures;

import Position.Position;

public  abstract   class  Creature {

    protected Position position;

    public abstract void speak();

    public void setPosition(Position position) {
        if(position==null)
        {
            this.position=null;
            return;
        }
        if(position.getSomeone()!=null)
        {
            position.getSomeone().setPosition(null);
            position.setSomeone(null);
        }
        if(this.getPosition()!=null)
            this.position.setSomeone(null );
        this.position = position;
        position.setSomeone(this);
    }
    public Position getPosition() {
        return position;
    }

}
