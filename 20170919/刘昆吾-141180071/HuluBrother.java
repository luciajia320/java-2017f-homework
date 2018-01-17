enum Color {

    红色,橙色,黄色,绿色,青色,蓝色,紫色

}


enum Order{

	老大,老二,老三,老四,老五,老六,老七

}


//定义单个葫芦娃

public class HuluBrother {

	private String name;//姓名

	private Order order;//排行

	private Color color;//颜色

	private int curPos;//当前位置

	

	public HuluBrother(String name,Order order,Color color,int curPos){

		this.name=name;

		this.order=order;

		this.color=color;

		this.curPos=curPos;


	}

	
	//移动位置并报告
	public void movePos(int newPos){

		System.out.println(order + ":" + curPos + "->" + newPos);

		curPos=newPos;

	}

	
	//打印排行
	public void printOrder(){

		System.out.print(order + "  ");

	}

	
	//打印颜色
	public void printColor(){

		System.out.print(color + "  ");

	}	

	//获取葫芦娃属性的方法
	
	public String getName(){

		return name;

	}

	public Order getOrder(){

		return order;

	}

	public Color getColor(){

		return color;

	}

	public int getPos(){

		return curPos;

	}

}