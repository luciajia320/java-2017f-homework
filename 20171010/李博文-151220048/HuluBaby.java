enum COLOR{
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}
enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}
public class HuluBaby implements Creature{
    private COLOR color;
    private SENIORITY seniority;
    private Position position;

    public HuluBaby(COLOR color, SENIORITY seniority){
        this.color = color;
        this.seniority = seniority;
    }
    @Override
    public void report(){
        System.out.print("🐵 ");
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        if(this.position != null)
            this.position.setHolder(this);
    }

    @Override
    public Position getPosition() {
        return position;
    }
    @Override
    public String toString(){
        return this.seniority.toString() + this.color.toString() + ")@" + this.position.getXY() + ";";
    }

    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }
}
