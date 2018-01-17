public class Grandpa extends Creature
{
	public String getName() 
	{
		return "Grandpa";
	}
	void sort(Hulu array[])//让葫芦兄弟摆好队形
	{
		for(int i=0;i<7;++i)
			for(int j=6;j>i;--j)
				if(array[j].getKey()<array[j-1].getKey())
				{
					Hulu tmp=array[j];
					array[j]=array[j-1];
					array[j-1]=tmp;
				}
		//for(int i=0;i<7;++i)
		//	System.out.println(array[i].getColor());
	}
}
