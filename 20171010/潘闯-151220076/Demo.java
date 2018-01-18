package assignment3;

import assignment3.Formation.FORMATION;

public class Demo {
	
	public static void main(String[] args)
	{
		Creature[] hulu = new Creature[7];
		hulu[0] = new Hulu("老大","红色");		
		hulu[1] = new Hulu("老二","橙色");
		hulu[2] = new Hulu("老三","黄色");
		hulu[3] = new Hulu("老四","绿色");
		hulu[4] = new Hulu("老五","青色");
		hulu[5] = new Hulu("老六","蓝色");
		hulu[6] = new Hulu("老七","紫色");	
		
		Creature[] grandpa = new Creature[1];
		grandpa[0] = new Grandpa();
		
		Creature[] snake = new Creature[1];
		snake[0] = new Snake();
		
		Creature[] demon = new Creature[7];
		for(int i = 0;i < 7;++i)
		{
			if(i == 0)
				demon[i] = new Scorpion();
			else
				demon[i] = new Louluo();
		}
		
		Space space = new Space(15);
		
		space.setFormation(hulu, new Position(6,8), FORMATION.CHANGSHE);
		space.setFormation(demon, new Position(8,8), FORMATION.YANXING);		
		space.setFormation(grandpa, new Position(3,6), FORMATION.SINGLE);
		space.setFormation(snake, new Position(8,10), FORMATION.SINGLE);
		
		space.print();
		
		space.clear();
		System.out.println("变阵");
		System.out.println();
		System.out.println();
		
		space.setFormation(hulu, new Position(6,8), FORMATION.CHANGSHE);
		space.setFormation(demon, new Position(8,8), FORMATION.HEYI);		
		space.setFormation(grandpa, new Position(3,6), FORMATION.SINGLE);
		space.setFormation(snake, new Position(10
				,8), FORMATION.SINGLE);
		
		space.print();
	}
}