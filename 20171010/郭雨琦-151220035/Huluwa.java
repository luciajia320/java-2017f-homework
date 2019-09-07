public class Huluwa implements Creature, Comparable{

    private COLOR color;
    private SENIORITY seniority;
    private Position position;
    String icon;

    @Override
    public String getIcon(){
        return icon;
    }
    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }

    @Override
    public void cheer(){}
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        if(position != null) {
            position.setHolder(this);
        }
    }

    Huluwa(COLOR color, SENIORITY seiority, String icon) {
        this.color = color;
        this.seniority = seiority;
        this.icon = icon;
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        return this.seniority.toString();
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }

}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}