package nju.hulu;
import java.util.Random;
public class Judge {
	
	public boolean defeat(Type a,Type b)
	{		
	
		int s=a.ordinal()-b.ordinal();
		if (s>=2)
			return true;
		else if (s<=-2)
			return false;
		
		Random Rand=new Random();
		int x=Rand.nextInt(2);
		if(x<=1)
			return true;
		else
			return false;
		
		/*
		double partition=6+(a.ordinal()-b.ordinal()*4);		
		Random Rand=new Random();
		int x=Rand.nextInt(12);
		if (x<=partition)
		{
			return true;
		}
		else return false;*/
	}
	

}
