package huluwa3;

public class Huluwa  extends basicHuman implements Creature {
	
	COLOR color;
	NUMBER num;
	
	@Override
	public void report()
	{
		System.out.print(num.toString()+"("+color.toString()+")");
	}
	
	
	@Override
	public boolean smallerThan(Creature creature,int type)
	{
		Huluwa second=(Huluwa)creature;
		if (type==0)
		{
			if (this.color.ordinal()<second.color.ordinal())
				return true;
			else
				return false;
		}
		else if (type==1)
		{
			if (this.num.ordinal()<second.num.ordinal())
				return true;
			else
				return false;
		}
		return true;
		
	}
	
	
	@Override
	public void printCreature()
	{
		System.out.print("😃");
	}
	Huluwa(COLOR colorToSet,NUMBER numToSet)
	{
		this.color=colorToSet;
		this.num=numToSet;
		this.position=new Position<Creature>(0,0);
	}
	
	
	
}

enum COLOR
{
	
	RED,ORANGE,YELLOW,GREEN,QING,BLUE,PURPLE
	//��,��,��,��,��,��,��
};

enum NUMBER
{
	FIRST,SECOND,THIRD,FORTH,FIFTH,SIXTH,SEVENTH
	//�ϴ�,�϶�,����,����,����,����,����
}
