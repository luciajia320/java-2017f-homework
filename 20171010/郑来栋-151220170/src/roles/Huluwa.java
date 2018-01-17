package roles;


public class Huluwa implements Creature,Comparable<Huluwa>{

    /*name 和 color都是一个外部可读但不可写的属性*/
    private String name;
    public String getName() {
        return name;
    }

    private COLOR color;
    public COLOR getColor() {
        return color;
    }

    private int priority;

    private Huluwa(String name, COLOR color, int priority)
    {
        this.name = name;
        this.color = color;
        this.priority = priority;
    }

    @Override
    public int compareTo(Huluwa o) {
        if (priority > o.priority)
            return 1;
        else if (priority == o.priority)
            return 0;
        else return -1;
    }

    enum COLOR{
        RED,ORANGE,YELLOW,GREEN,CYAN,BLUE,PURPLE
    }

    public static class HuluBrothers
    {
        private HuluBrothers(){};
        public final Huluwa first = new Huluwa("老大",COLOR.RED,1);
        public final Huluwa second = new Huluwa("老二",COLOR.ORANGE,2);
        public final Huluwa third = new Huluwa("老三",COLOR.YELLOW,3);
        public final Huluwa fourth = new Huluwa("老四",COLOR.GREEN,4);
        public final Huluwa fifth = new Huluwa("老五",COLOR.CYAN,5);
        public final Huluwa sixth = new Huluwa("老六",COLOR.BLUE,6);
        public final Huluwa seventh = new Huluwa("老七",COLOR.PURPLE,7);
    }

    private static HuluBrothers huluBrothers = new HuluBrothers();

    public static HuluBrothers getHuluBrothers()
    {
        return huluBrothers;
    }
}
