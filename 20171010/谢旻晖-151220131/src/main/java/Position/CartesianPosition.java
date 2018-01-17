package Position;

import Creatures.Creature;

public class CartesianPosition implements PositionInterface{

    private int[] coords;//i.e.  (x,y) or (x,y,z) or (x1,x2,x3...)
    private Creature holder;


    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
    }

    public int[] getPosition(){
        assert coords != null:"Uninitialized coords";
        return coords;
    }

    public void setPosition(int ...pos) {
        this.coords=pos;
    }

    public CartesianPosition(int ...pos)   {
        holder=null;
        setPosition(pos);
    }

    @Override
    public String toString(){
        String temp=new String("(");
        for (int coord:coords)
        {
            temp+=coord+',';
        }//(x,y,z,
        char[] tempCharArray=temp.toCharArray();
        tempCharArray[tempCharArray.length-1]=')';
        return  tempCharArray.toString();
    }
}
