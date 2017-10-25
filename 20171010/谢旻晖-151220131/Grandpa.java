public class Grandpa extends Creature{
    private static Grandpa ourInstance ;

    public static Grandpa getInstance(PositionInterface positionInterface) {
        if(ourInstance==null)
            ourInstance=new Grandpa(positionInterface);
        return ourInstance;
    }

    private Grandpa(PositionInterface positionInterface) {
        super("爷",positionInterface);
    }

    @Override
    public void report(){
        System.out.println(name+"@"+this.position.toString());
    }


    @Override
    public void act() {
        System.out.println("爷爷加入战场：我来了，葫芦娃们，我们上！");
    }
}
