package  creature;
import  basicinfo.Position;
import  myenum.*;
public class HuLuWa implements Creature, Comparable{

    private COLOR color;
    private SENIORITY seniority;
    private Position position;
    private int strength=70;
    private PARTISAN partisan=PARTISAN.善;
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

    public  HuLuWa(COLOR color, SENIORITY seniority) {
        this.color = color;
        this.seniority = seniority;
    }
    public HuLuWa(STATUS s){
        switch (s){
            case 大娃:this.color=COLOR.赤;this.seniority=SENIORITY.一;break;
            case 二娃:this.color=COLOR.橙;this.seniority=SENIORITY.二;break;
            case 三娃:this.color=COLOR.黄;this.seniority=SENIORITY.三;break;
            case 四娃:this.color=COLOR.绿;this.seniority=SENIORITY.四;break;
            case 五娃:this.color=COLOR.青;this.seniority=SENIORITY.五;break;
            case 六娃:this.color=COLOR.蓝;this.seniority=SENIORITY.六;break;
            case 七娃:this.color=COLOR.紫;this.seniority=SENIORITY.七;break;
            default:this.color=COLOR.赤;this.seniority=SENIORITY.一;break;
        }
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        return this.seniority.toString() + "(" + this.color.toString() + ")@" + this.position.getX() + ";";
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  HuLuWa)
            return this.getSeniority().ordinal()> ((HuLuWa) brother).getSeniority().ordinal();
        else
            return false;
    }
    @Override
    public STATUS getStatus(){
        switch (this.color){
            case 赤:return STATUS.大娃;
            case 橙:return STATUS.二娃;
            case 黄:return STATUS.三娃;
            case 绿:return STATUS.四娃;
            case 青:return STATUS.五娃;
            case 蓝:return STATUS.六娃;
            case 紫:return STATUS.七娃;
            default:return STATUS.爷爷;
        }
    }
    @Override
    public int commonStrength(){
        return strength;
    }
    @Override
    public PARTISAN getPartisan(){
        return partisan;
    }
}
/*
enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}*/