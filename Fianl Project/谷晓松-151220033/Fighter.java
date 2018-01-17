package com.gxs;



import java.util.Random;
enum Camp
{
	GOOD,EVIL
}
public abstract class Fighter extends Creature
{
	private static Random rand=new Random();
	private Camp camp;
	
	Fighter(String name,int speed,int power,Camp camp)
	{
		super(name,speed,power);
		this.camp=camp;
		//register this fighter
		Judge.getAcess().register(this);
	}
	final public Camp getCamp()
	{
		return camp;
	}
	
	public static boolean Fight(Fighter c1,Fighter c2)
	{
		int p1=c1.getPower(),p2=c2.getPower();
		int sum=p1+p2-1,r=rand.nextInt(sum);
		if(r<=p1-1)
		{
			c2.KIA();
			Judge.getAcess().recordMove(new InfoNode(c2.getCid(),c2.getPositon(),false));
			return true;
		}
		else
		{
			c1.KIA();
			Judge.getAcess().recordMove(new InfoNode(c1.getCid(),c1.getPositon(),false));
			return false;
		}
	}
	
	abstract protected void KIA();
	
	//may override
	protected Fighter target;
	protected void engageEnemy()
	{
		Judge j=Judge.getAcess();
		int dis=Constant.LOGIC_HEIGHT+Constant.LOGIC_WIDTH;
		int coin=rand.nextInt();
		if(target==null || !target.isAlive())
		{
			Fighter f;
			if(coin%2==0)
			{
				while(true)
				{
					int t=rand.nextInt(j.enemyIdNum(camp));
					f=j.getEnemy(t, camp);
					if(f.isAlive())
						break;
				}
				target=f;
			}
			else
			{
				for(int i=0;i<j.enemyIdNum(camp);i++)
				{
					f=j.getEnemy(i, camp);
					if(f.isAlive()&&Position.getDistance(this.getPositon(), f.getPositon())<dis)
						target=f;
				}
			}
		}
		this.moveTo(target.getPositon());
	}
}