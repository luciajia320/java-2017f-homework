public class CartesianPosition implements PositionInterface{

    private int[] coords;//i.e.  (x,y) or (x,y,z) or (x1,x2,x3...)
    private Creature holder;


    @Override
    public Creature getHolder() {
        return holder;
    }

    @Override
    public void setHolder(Creature holder) {
        this.holder = holder;
    }

    @Override
    public int[] getPosition(){
        assert coords != null:"Uninitialized coords";
        return coords;
    }

    @Override
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
