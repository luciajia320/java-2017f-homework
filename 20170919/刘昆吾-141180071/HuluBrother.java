enum Color {

    ��ɫ,��ɫ,��ɫ,��ɫ,��ɫ,��ɫ,��ɫ

}


enum Order{

	�ϴ�,�϶�,����,����,����,����,����

}


//���嵥����«��

public class HuluBrother {

	private String name;//����

	private Order order;//����

	private Color color;//��ɫ

	private int curPos;//��ǰλ��

	

	public HuluBrother(String name,Order order,Color color,int curPos){

		this.name=name;

		this.order=order;

		this.color=color;

		this.curPos=curPos;


	}

	
	//�ƶ�λ�ò�����
	public void movePos(int newPos){

		System.out.println(order + ":" + curPos + "->" + newPos);

		curPos=newPos;

	}

	
	//��ӡ����
	public void printOrder(){

		System.out.print(order + "  ");

	}

	
	//��ӡ��ɫ
	public void printColor(){

		System.out.print(color + "  ");

	}	

	//��ȡ��«�����Եķ���
	
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