

public class HuluWa {

	public static void main(String[] args){

		HuluGame game =new HuluGame();
		
		//����������

		game.huluInitialize();

		System.out.println("����������֮ǰ:");

		game.reportOrder();	

		System.out.println("\n"+"��ʼ����:");

		game.bubbleSort();

		System.out.println("�����������:");

		game.reportOrder();

		System.out.print("\n************************\n"+
		                 "************************\n");
		 
		//����ɫ����
		game.huluInitialize();

		System.out.println("����ɫ����֮ǰ:");

		game.reportColor();

		System.out.println("\n"+"��ʼ����:");

		game.quickSort();

		System.out.println("����ɫ����֮��:");

		game.reportColor();		

	}

}