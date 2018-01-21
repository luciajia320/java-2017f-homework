package huluwa3;

import java.util.ArrayList;

public class Fight<T1 extends Creature,T2 extends Creature> {
	
	private Queue<T1> huluQueue;
	private Queue<T2> scorpionQueue;	
	private Position<Creature>[][] positions;
	ArrayList<Position<Creature>[][]> positionlist;
	private int cols=50;
	private int rows=10;
	
	@SuppressWarnings("unchecked")
	public Fight(T1[] groupA,T2[] groupB)
	{
		huluQueue=new Queue<T1>(groupA);
		scorpionQueue=new Queue<T2>(groupB);		
		positions=(Position<Creature>[][]) new Position<?>[rows][cols];
		
		for (int i=0;i<rows;i++)
			for (int j=0;j<cols;j++)
				positions[i][j]=new Position<>(i,j);
		
		huluQueue.setFirst(positions[2][10]);
		scorpionQueue.setFirst(positions[2][(cols-10)/2]);
		
		
		
	}
	
	public Position<Creature>[][] getPositions()
	{
		return positions;
	}
	
	public Queue<? extends Creature> getQueue(int n)
	{
		if (n==0)
			return huluQueue;
		else
			return scorpionQueue;
	}
	
	public void setOneCreature(Creature creature,int a,int b )
	{
		creature.setPosition(positions[a][b]);
	}

	public void printFight()
	{
		for (int i=0;i<rows;i++)
		{
			for (int j=0;j<cols;j++)
			{
				positions[i][j].printHolder();
			}
			System.out.println();
		}
	}
	
	
	
	public static void main(String[] args)
	{
		Huluwa[] brothers=new Huluwa[7];
		for (int i=0;i<brothers.length;i++)
		{
			brothers[i]=new Huluwa(COLOR.values()[i],NUMBER.values()[i]);
		}

		Servant[] Servants=new Servant[13];				
		for (int i=0;i<Servants.length;i++)
		{
			Servants[i]=new Servant();
		}		
		Scorpion scorpion=new Scorpion();
		Snake snake=new Snake();
		Grandpa grandpa=new Grandpa();
		//创建原始对象：葫芦娃，蝎子精，小喽啰，蛇精，爷爷
		
		Fight<? extends Creature,? extends Creature> fight=new Fight<Huluwa,Servant>(brothers,Servants);
		//对象放入战场
		
		positionSetter changSheSetter=new ChangShe();		
		changSheSetter.setPosition(fight.huluQueue,fight.getPositions());
		fight.getQueue(0).shuffle();
		
		
		positionSetter heyiSetter=new HeYi();
		heyiSetter.setPosition(fight.scorpionQueue,fight.getPositions());//蝎子精一方站队
		
		
		
		fight.setOneCreature(grandpa,5,5);
		fight.setOneCreature(snake,5,35);
		fight.setOneCreature(scorpion,1,20);//放置蝎子精，蛇精，爷爷
		fight.printFight();//打印战场
		System.out.println("\n\n");
		
		positionSetter fengShiSetter=new FengShi();
		fengShiSetter.setPosition(fight.scorpionQueue,fight.getPositions());//蝎子精一方站队重新站队
		fight.setOneCreature(grandpa,5,5);
		fight.setOneCreature(snake,5,35);
		fight.setOneCreature(scorpion,1,23);
		fight.printFight();//打印战场
		
		
		
		
	}

}
