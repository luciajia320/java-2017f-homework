class HuluRanks
{
	Position huluLine[];

	HuluRanks()
	{
		huluLine = new Position[8];
		for(int i = 0;i < 8;i++)
			huluLine[i] = new Position(i);
	}

	void changePosition(int position_var1,int position_var2,boolean ifPrint)
	{
		HuluBoy temp;
		temp = huluLine[position_var1].holder;
		huluLine[position_var1].holder = huluLine[position_var2].holder;
		huluLine[position_var2].holder = temp;
		if(ifPrint)
		{
			System.out.println(huluLine[position_var2].holder.name + " :" + position_var1 + "->" + position_var2);
		}
	}

	void randomRanks()
	{
		int roil;
		System.out.println("葫芦娃随机排队：");
		for(int i = 1;i < 8;i++)
		{
			roil = (int)(Math.random() * 7 + 1);
			for(int j = 1;j < 8;j++)
			{
				if(roil == huluLine[j].holder.rank & i != j)
				{
					changePosition(i,j,false);
					break;
				}
			}
		}
	}

	void bubbleSort()
	{
		System.out.println("冒泡排序:");
		for(int i = 1;i < 8;i++)
		{
			for(int j = 1;j < 8 - i;j++)
			{
				if(huluLine[j].holder.rank > huluLine[j + 1].holder.rank)
				{
					changePosition(j,0,true);
					changePosition(j + 1,j,true);
					changePosition(0,j + 1,true);
					System.out.println("");
				}
			}
		}
		System.out.println("");
	}

	void quickSort(int left_var,int right_var)
	{
		int left = left_var;
		int right = right_var;
		if(left >= right)
			return;
		while(left < right)
		{
			for(;left < right & huluLine[left].holder.rank < huluLine[right].holder.rank;right--)
				;
			if(huluLine[left].holder.rank > huluLine[right].holder.rank)
			{
				changePosition(left,0,true);
				changePosition(right,left,true);
				changePosition(0,right,true);
				System.out.println("");
			}
			for(;left < right & huluLine[left].holder.rank < huluLine[right].holder.rank;left++)
				;
			if(huluLine[left].holder.rank > huluLine[right].holder.rank)
			{
				changePosition(left,0,true);
				changePosition(right,left,true);
				changePosition(0,right,true);
				System.out.println("");
			}
		}
		quickSort(left_var,left - 1);
		quickSort(left + 1,right_var);
	}

	void numberOff()
	{
		System.out.println("Position 0\tnull for changing the queue.");
		for(int i = 1;i < 8;i++)
			huluLine[i].numberOff();
		System.out.println("");
	}
}