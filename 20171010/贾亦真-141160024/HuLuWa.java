package hlw;

public class HuLuWa extends Creature implements Comparable{
    private RANK rank;
    private COLOR color;

    HuLuWa(RANK rk, COLOR cl){
        rank = rk;
        color = cl;
        pos = null;
    }
    public String show(){
        return rank + " " + color + " ";
    }
    public boolean BiggerThan(Comparable another){
        if (this.rank.ordinal() < ((HuLuWa)another).get_rank().ordinal()) {
            return true;
        }
        else
        {
            return false;
        }
    }
    public String get_symbol(){
        return color.toString();
    }

    public RANK get_rank(){
        return rank;
    }
    public COLOR get_color(){
        return color;
    }
    public void echo_move(String movement){
        System.out.print(rank + ":" + movement);
    }
}
enum COLOR{
    红,橙,黄,绿,青,蓝,紫
}
enum RANK{
    老大,老二,老三,老四,老五,老六,老七
}