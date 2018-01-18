//enum SENIORTY
//{
//    老大,老二,老三,老四,老五,老六,老七
//}
//
//enum COLOR
//{
//    赤,橙,黄,绿,青,蓝,紫
//}

public class Grandpa extends Creature {
    public Grandpa(){
        super("爷");
    }
    //爷爷精心照料让某个葫芦娃出生，这一切在上帝控制下进行
    Huluwa giveBirth(int i){
        Huluwa huluwa=new Huluwa(COLOR.values()[i],SENIORTY.values()[i]);
        return huluwa;
    }
}
