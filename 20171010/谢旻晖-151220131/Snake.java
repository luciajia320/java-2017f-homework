public class Snake extends Monster{
    private static Snake ourInstance ;

    public static Snake getInstance(PositionInterface positionInterface) {
        if(ourInstance==null)
            ourInstance=new Snake(positionInterface);
        return ourInstance;
    }

    private Snake(PositionInterface positionInterface) {
        super("蛇",positionInterface);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void act() {
        System.out.println("蛇精加入战场：如意如意,随我心意！");
    }
}
