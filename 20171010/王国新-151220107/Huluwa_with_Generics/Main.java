import java.util.ArrayList;
public class Main {
	public static void main(String[] args) {
		//Version 2 update: Use ArrayList
		ArrayList<Creature> brothers = new ArrayList<Creature>();
		for(int i=0;i<7;i++) {
			Huluwa huluwa = new Huluwa(COLOR.values()[i], SENIORITY.values()[i],"😀");
			brothers.add(huluwa);
		}
		ArrayList<Creature> demons = new ArrayList<Creature>();
		demons.add(new Scorpion("👼"));
		for(int i=0;i<6;i++) {
			demons.add(new XiaoLouluo("🐸"));
		}
		Creature grandpa = new Grandfather("🎅");
		Creature snake = new Snake("🐍");
		
		Queue BrosQueue = new Queue(brothers);
		Ground ground = new Ground(15);
		
		//step1:初始乱序的七个兄弟按(老大至老七）站队
		BrosQueue.shuffle();
		new BubbleSort().sort(BrosQueue);
		
		//step2:将葫芦兄弟的长蛇阵营和蝎子精小喽啰阵营放置于二位空间中 
		ChangsheFormation chanegshe = new ChangsheFormation(brothers);
		HeyiFormation heyi = new HeyiFormation(demons);
		
		ground.layoutFormation(chanegshe, new Location(5,6));
		ground.layoutFormation(heyi, new Location(2,0));
		
		//step3:将老爷爷和蛇精放置于空间中，为各自一方加油助威；
		OneCreatureFormation g = new OneCreatureFormation(grandpa); 
		OneCreatureFormation s = new OneCreatureFormation(snake); 

		ground.layoutFormation(g, new Location(0,14));
		ground.layoutFormation(s, new Location(0,0));
		
		//step4:将对峙局面打印输出
		System.out.println("--葫芦娃(长蛇) VS 蝎子精(鹤翼)--");
		System.out.print(ground);

		//step5:蝎子精小喽啰阵营变换成“衡轭”阵形
		ground.clearFormation(heyi);
		YulinFormation yulin = new YulinFormation(demons);
		ground.layoutFormation(yulin, new Location(3,0));
		System.out.println("--葫芦娃(长蛇) VS 蝎子精(鱼鳞)--");
		System.out.print(ground);
	}
}
