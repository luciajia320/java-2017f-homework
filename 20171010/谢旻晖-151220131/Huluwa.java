public class Huluwa extends Creature implements Comparable{

    private COLOR color;
    private SENIORITY seniority;

    public COLOR getColor() {
        return color;
    }
    public SENIORITY getSeniority() {
        return seniority;
    }

    Huluwa(COLOR color, SENIORITY seniority,PositionInterface positionInterface) {
        super(seniority.toString(),positionInterface);
        this.color      = color;
        this.seniority  = seniority;
    }


    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }
    @Override
    public void act(){}

}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    大, 二, 三, 四, 五, 六, 七
}