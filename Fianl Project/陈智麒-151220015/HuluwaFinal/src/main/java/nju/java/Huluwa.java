package nju.java;


public class Huluwa extends GoodMan implements Runnable {

    enum Color{
        红色,橙色,黄色,绿色,青色,蓝色,紫色;
    }

    enum Order {
        老大, 老二, 老三, 老四, 老五, 老六, 老七
    }

    private Color color;

    private Order order;

    public Huluwa(int x, int y, Field field, Color color, Order order){

        super(x, y, field,"huluwa");

        this.color = color;
        this.order = order;

    }

    public String toString(){
        return "Huluwa\t" + order.ordinal();
    }

}
