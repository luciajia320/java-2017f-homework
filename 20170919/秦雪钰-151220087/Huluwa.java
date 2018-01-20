
enum COLOR
{
    赤,橙,黄,绿,青,蓝,紫
}

enum SENIORTY
{
    老大,老二,老三,老四,老五,老六,老七
}

enum SPEAKKINDS
{
    color,seniorty,pos
}

public class Huluwa implements Creature,Comparable{
    private COLOR mycolor;
    private SENIORTY myseniorty;
    private Position myposition;
    private SPEAKKINDS words; //根据不同场景说不同的话
    public Huluwa(COLOR color,SENIORTY seniorty)
    {
        mycolor=color;
        myseniorty=seniorty;
        words=SPEAKKINDS.color;
    }
    public COLOR getColor()
    {
        return mycolor;
    }
    public Position getPos()
    {
        return  myposition;
    }
    public void setPos(Position position)
    {
        myposition=position;
        myposition.setSitter(this); //在这里同步pos和葫芦娃的动作！！
    }
    public void setWords(SPEAKKINDS akind) //此时葫芦娃要说的话
    {
        words=akind;
    }
    public void speak() //报告位置
    {
        switch(words) {
            case pos:speakPos();break;
            case color:speakColor();break;
            case seniorty:speakSeniorty();break;
        }
    }
    public void speakPos()
    {
        System.out.print(myseniorty.toString() + "(" + myposition.getNumber() + ")"+" ");
    }
    public void speakColor() //报告颜色
    {
        System.out.print(mycolor.toString()+" ");
    }
    public void speakSeniorty() //报告辈分
    {
        System.out.print(myseniorty.toString()+" ");
    }
    public void changeHereThere(Position a,Position b)
    {
        System.out.println(myseniorty.toString()+":"+a.getNumber()+"->"+b.getNumber());
    }

    public boolean isBiggerThan(Comparable other)
    {
        if(other instanceof Huluwa) //可能会有别的类型要和葫芦娃比较,数值小的辈分大
        {
          //  System.out.print(myseniorty.ordinal()+" "+((Huluwa) other).myseniorty.ordinal());
            return (myseniorty.ordinal()) > (((Huluwa) other).myseniorty.ordinal());
        }
        return false;
    }

}



