package javaH2;

public class Form {
	int pos[][];
	static int[] COUNT=new int[]{5,6,4,5,10,7,7,12};
	public Form()
	{
		super();
	}
	/*public int[][] GetPos(creature A,int i)
	{
		
	}*/
	public int[][] changshe(creature A)
	{
		pos=new int[COUNT[0]][2];
		for (int i=0;i<COUNT[0];i++)
		{
			pos[i][0]=A.Pos.getX()+i+1;
			pos[i][1]=A.Pos.getY();
		}
		return pos;
	}
	public int[][] heyi(creature A)
	{
		pos=new int[COUNT[1]][2];
		int t=-1;
		for (int i=0;i<COUNT[1];i++)
		{
			pos[i][1]=A.Pos.getY()+(i/2)*t+t;
			pos[i][0]=A.Pos.getX()-i/2-1;
			t=t*(-1);
		}
		return pos;
	}
	
	public int[][] yanxing(creature A)
	{
		pos=new int[COUNT[2]][2];
		for (int i=0;i<COUNT[2];i++)
		{
			pos[i][0]=A.Pos.getX()+i+1;
			pos[i][1]=A.Pos.getY()-i-1;
		}
		return pos;
	}
	
	public int[][] henge(creature A)
	{
		int t=-1;
		pos=new int[COUNT[3]][2];
		for (int i=0;i<COUNT[3];i++)
		{
			pos[i][0]=A.Pos.getX()+i+1;
			pos[i][1]=A.Pos.getY()+(t-1)/2;
			t=-t;
		}
		return pos;
	}
	
	public int[][] yulin(creature A)
	{
		pos=new int[COUNT[4]][2];
		//for (int i=0;i<COUNT[4];i++)
		//{
		//	pos[i][0]=A.Pos.getX()+i/2+1;
		//	pos[i][1]=A.Pos.getY();
		//}
		pos[0][0]=A.Pos.getX()+1;
		pos[0][1]=A.Pos.getY()-1;
		pos[1][0]=A.Pos.getX()+1;
		pos[1][1]=A.Pos.getY()+1;
		pos[2][0]=A.Pos.getX()+2;
		pos[2][1]=A.Pos.getY()-2;
		pos[3][0]=A.Pos.getX()+2;
		pos[3][1]=A.Pos.getY();
		pos[4][0]=A.Pos.getX()+2;
		pos[4][1]=A.Pos.getY()+2;
		pos[5][0]=A.Pos.getX()+3;
		pos[5][1]=A.Pos.getY()-3;
		pos[6][0]=A.Pos.getX()+3;
		pos[6][1]=A.Pos.getY()-1;
		pos[7][0]=A.Pos.getX()+3;
		pos[7][1]=A.Pos.getY()+1;
		pos[8][0]=A.Pos.getX()+3;
		pos[8][1]=A.Pos.getY()+3;
		pos[9][0]=A.Pos.getX()+4;
		pos[9][1]=A.Pos.getY();
		return pos;
	}
	
	public int[][] fangyuan(creature A)
	{
		int t=-1;
		pos=new int[COUNT[5]][2];
		for (int i=0;i<=COUNT[5]/2;i++)
		{
			pos[i][0]=A.Pos.getX()+i/2+1;
			pos[i][1]=A.Pos.getY()+(i/2+1)*t;
			t=-t;
		}
		pos[4][0]=pos[0][0]+2;
		pos[4][1]=pos[0][1];
		pos[5][0]=pos[1][0]+2;
		pos[5][1]=pos[1][1];
		pos[6][0]=A.Pos.getX()+4;
		pos[6][1]=A.Pos.getY();
		return pos;
	}
	public int[][] yanyue(creature A)
	{
		pos=new int[COUNT[6]][2];
		pos[0][0]=A.Pos.getX();
		pos[0][1]=A.Pos.getY()+1;
		pos[1][0]=A.Pos.getX()+1;
		pos[1][1]=A.Pos.getY();
		pos[2][0]=A.Pos.getX()+1;
		pos[2][1]=A.Pos.getY()+1;
		pos[3][0]=A.Pos.getX()-1;
		pos[3][1]=A.Pos.getY()+1;
		pos[4][0]=A.Pos.getX()+2;
		pos[4][1]=A.Pos.getY()+1;
		pos[5][0]=A.Pos.getX()-2;
		pos[5][1]=A.Pos.getY()+2;
		pos[6][0]=A.Pos.getX()+3;
		pos[6][1]=A.Pos.getY()+2;
		return pos;
	}
	public int[][] fengshi(creature A)
	{
		pos=new int[COUNT[7]][2];
		int t=1;
		int j=0;
		for (int i=0;i<COUNT[7]-1;i++)
		{
			t=1+i/3;
			j=i%3;
			pos[i][0]=A.Pos.getX()+i/3+1;
			pos[i][1]=A.Pos.getY()+(j-1)*t;
		}
		pos[9][0]=A.Pos.getX()+4;
		pos[9][1]=A.Pos.getY();
		pos[10][0]=A.Pos.getX()+5;
		pos[10][1]=A.Pos.getY();
		pos[11][0]=A.Pos.getX()+6;
		pos[11][1]=A.Pos.getY();
		return pos;
	}
}

enum ZHEN
{
	³¤Éß,º×Òí,ÑãÐÐ,ºâéî,ÓãÁÛ,·½ƒÒ,ÙÈÔÂ,·æÊ¸
}


