enum COLOR
{
    赤,橙,黄,绿,青,蓝,紫
}

enum SENIORTY
{
    老大,老二,老三,老四,老五,老六,老七
}

public class Huluwa extends Creature implements Comparable {
    public COLOR color;
    private SENIORTY seniorty;
    public Huluwa(COLOR color,SENIORTY seniorty)
    {
        super("娃");
        this.color=color;
        this.seniorty=seniorty;
    }
    @Override
    String getName(){ //葫芦娃报的是自己更精细的属性
        return color.toString();
    }
    public boolean isBiggerThan(Comparable other)
    {
        if(other instanceof Huluwa) //可能会有别的类型要和葫芦娃比较,数值小的辈分大
        {
            //  System.out.print(myseniorty.ordinal()+" "+((Huluwa) other).myseniorty.ordinal());
            return (seniorty.ordinal()) > (((Huluwa) other).seniorty.ordinal());
        }
        else
            return false;
    }
}
