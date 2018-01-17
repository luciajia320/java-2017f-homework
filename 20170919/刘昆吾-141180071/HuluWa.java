

public class HuluWa {

	public static void main(String[] args){

		HuluGame game =new HuluGame();
		
		//按排行排序

		game.huluInitialize();

		System.out.println("按排行排序之前:");

		game.reportOrder();	

		System.out.println("\n"+"开始排序:");

		game.bubbleSort();

		System.out.println("按排行排序后:");

		game.reportOrder();

		System.out.print("\n************************\n"+
		                 "************************\n");
		 
		//按颜色排序
		game.huluInitialize();

		System.out.println("按颜色排序之前:");

		game.reportColor();

		System.out.println("\n"+"开始排序:");

		game.quickSort();

		System.out.println("按颜色排序之后:");

		game.reportColor();		

	}

}