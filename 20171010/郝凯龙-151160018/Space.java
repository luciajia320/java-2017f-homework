public class Space
{
	private int N;
	private Creature matrix[][];
	public Space()
	{
		N=0;
		matrix=null;
	}
	public Space(int _N)
	{
		N=_N;
		matrix=new Creature[N][N];
		for(int i=0;i<N;++i)
			for(int j=0;j<N;++j)
				matrix[i][j]=null;
	}
	public void VFormation(Creature head,Creature array[],int n,Creature encourage)//鹤翼阵
	{
		final int line=N-3;
		final int row=N/2;
		matrix[line][row]=head;
		int i=0;
		int index=1;
		while(i<n)
		{
			matrix[line-index][row-index]=array[i++];
			if(i>=n) break;
			matrix[line-index][row+index]=array[i++];
			++index;
		}
		matrix[line][row+3]=encourage;//助阵
		show();//显示战况
		matrix[line][row]=null;
		i=0; index=1;
		while(i<n)
		{
			matrix[line-index][row-index]=null; ++i;
			matrix[line-index][row+index]=null; ++i;
			++index;
		}
		matrix[line][row+3]=null;
	}
	public void AFormation(Creature head,Creature array[],int n,Creature encourage)//锋矢阵
	{
		final int line=N/2+1;
		final int row=N/2;
		matrix[line][row]=head;
		int i=0;
		int index=1;
		while(i<n)
		{
			matrix[line+index][row]=array[i++];
			if(i<3*n/4 && i<n)
			{
				matrix[line+index][row-index]=array[i++];
				if(i>=n) break;
				matrix[line+index][row+index]=array[i++];
			}
			++index;
		}
		matrix[line][row+3]=encourage;
		show();//显示战况
		matrix[line][row]=null;
		i=0; index=1;
		while(i<n)
		{
			matrix[line+index][row]=null; ++i;
			if(i<3*n/4 && i<n)
			{
				matrix[line+index][row-index]=null; ++i;
				if(i>=n) break;
				matrix[line+index][row+index]=null; ++i;
			}
			++index;
		}
		matrix[line][row+3]=null;
	}
	public void IFormation(Creature array[],int n,Creature encourage)//长蛇阵
	{
		final int line=N/2-1;
		final int row=N/2;
		for(int i=0;i<n;++i)
			matrix[line-i][row]=array[i];
		matrix[line][row+3]=encourage;
	/*	show();//显示战况
		for(int i=0;i<n;++i)
			matrix[line-i][row]=null;
		matrix[line][row+3]=null;
	*/
	}
	public void show()
	{
		for(int i=0;i<N;++i)
		{
			for(int j=0;j<N;++j)
				if(matrix[i][j]==null)
					System.out.print("~~");
				else if(matrix[i][j].getName()=="Hulu")
					System.out.print(((Hulu)matrix[i][j]).getColor());
				else if(matrix[i][j].getName()=="Soldier")
					System.out.print("兵");
				else if(matrix[i][j].getName()=="Snake")
					System.out.print("蛇");
				else if(matrix[i][j].getName()=="Grandpa")
					System.out.print("爷");
				else if(matrix[i][j].getName()=="Scorpion")
					System.out.print("蝎");
				else
					System.out.print("~~");
			System.out.println();
		}
	}
}
