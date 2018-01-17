import java.util.Random;

class HuluBrothers {
	
	Huluwa[] brothers = {
			
			new Huluwa("老大","红色"),
			new Huluwa("老二","橙色"),
			new Huluwa("老三","黄色"),
			new Huluwa("老四","绿色"),
			new Huluwa("老五","青色"),
			new Huluwa("老六","蓝色"),
			new Huluwa("老七","紫色"),		
	};
	
	HuluBrothers()
	{
		for(int i = 0;i < brothers.length;++i)
			brothers[i].setPos(i);		
	}
	
	void randomSort()
	{
		Random rand = new Random();
		int j;
		for(int i = 0;i < brothers.length;++i)
		{
			j = rand.nextInt(6) % 7;
			if(i != j)
			{
				swap(i,j);
			}
		}
	}
	
	void bubbleSortByName()
	{
		for(int i = 0;i < brothers.length-1;++i)
		{
			for(int j = 0;j < brothers.length - i - 1;++j)
			{
				if(brothers[j].getNid() > brothers[j+1].getNid())
				{
					reportSwap(j,j+1);
					swap(j,j+1);
				}
			}
		}
	}
	
	void binarySortByColor()
	{
		int first,last,mid;
				
		for(int i = 1;i < brothers.length;++i)
		{
			first = 0; last = i - 1; mid = (first + last) / 2;
			while(first < last)
			{
				if(brothers[i].getCid() < brothers[mid].getCid())
				{
					last = mid - 1;
					mid = (first + last) / 2;
				}
				else
				{
					first = mid + 1;
					mid = (first + last) / 2;
				}
			}
			if(brothers[i].getCid() < brothers[mid].getCid())
			{
				for(int j = mid;j < i;++j)
				{
					reportSwap(i,j);
					swap(i,j);
				}
			}
			else
			{
				for(int j = mid+1;j < i;++j)
				{
					reportSwap(i,j);
					swap(i,j);
				}
			}
		}
	}
	
	void reportSwap(int i,int j)
	{
		String name = brothers[i].getName();
		System.out.println(name + " : " + i + "->" + j);
	}
	
	
	void reportName()
	{
		for(int i = 0;i < brothers.length;++i)
			System.out.print(brothers[i].getName() + " ");
		System.out.println();
	}
	void reportColor()
	{
		for(int i = 0;i < brothers.length;++i)
			System.out.print(brothers[i].getColor() + " ");
		System.out.println();
	}
	
	void swap(int i,int j)
	{
		Huluwa temp = brothers[i];
		brothers[i] = brothers[j];
		brothers[j] = temp;
	}
}
