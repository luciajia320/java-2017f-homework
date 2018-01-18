package huluwa2;



public class Main {//主函数
	public static void main(String[] args) {
		Space space = new Space();//初始化
		Scorpion scorpion = new Scorpion();
		Snake snake = new Snake();
		Grandpa grandpa = new Grandpa();
		
		System.out.print("初始队形")
		;System.out.print("对峙局面");
		Huluwaqueue huluwaqueue = new Huluwaqueue();
		huluwaqueue.FormTransform(LongSnake(Space));
		space.Output();
	}

}
