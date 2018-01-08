package HL;
public class Huluwa implements Creature{

    private COLOR color;
    private SENIORITY seniority;
    private Position position;

    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    Huluwa(COLOR color, SENIORITY seiority) {
        this.color = color;
        this.seniority = seiority;
    }

    @Override
    public void report() {
        System.out.print("🍐");
    }


}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}