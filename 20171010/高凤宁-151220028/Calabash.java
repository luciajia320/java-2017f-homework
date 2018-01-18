enum  Color{
    红色,橙色,黄色,绿色,青色,蓝色,紫色
}
enum Order{
    老大,老二,老三,老四,老五,老六,老七
}

public class Calabash extends Creature implements Comparable{
    private Color color;
    private Order order;

    public Color GetColor(){return color;}
    public Order GetOrder(){return order;}

    Calabash(Color color,Order order,String name){
        this.color = color;
        this.order = order;
        this.name = name;
    }

    @Override
    public boolean biggerThan(Comparable brother){
        if (brother instanceof Calabash)
            return this.GetOrder().ordinal() > ((Calabash) brother).GetOrder().ordinal();
        else
            return false;
    }
}
