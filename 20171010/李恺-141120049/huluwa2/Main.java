package huluwa2;



public class Main {//������
	public static void main(String[] args) {
		Space space = new Space();//��ʼ��
		Scorpion scorpion = new Scorpion();
		Snake snake = new Snake();
		Grandpa grandpa = new Grandpa();
		
		System.out.print("��ʼ����")
		;System.out.print("���ž���");
		Huluwaqueue huluwaqueue = new Huluwaqueue();
		huluwaqueue.FormTransform(LongSnake(Space));
		space.Output();
	}

}
