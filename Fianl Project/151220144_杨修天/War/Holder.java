package War;

public class Holder {
	private int heng;
	private int zong;
	private int field[][];
	private Creature liver[][];
	private int zheng;
	private int fan;
	Holder()
	{
		 	this.heng=750;
	        this.zong=450;
	        field=new int[heng][zong];
	        for(int i=0;i<750;i++)
	        	for(int j=0;j<450;j++)
	        		field[i][j]=0;
	        liver=new Creature[heng][zong];
	        zheng=8;
	        fan=9;
	}
	public void setliver(int x,int y,Creature z)
	{
		liver[x][y]=z;
		field[x][y]=1;
	}
	public Creature getliver(int x,int y)
	{
		return liver[x][y];
	}
    public void moveliver(int x,int y)
    {
    	field[x][y]=0;
    	liver[x][y]=null;
    }
    public void zhengdie(int x,int y)
    {	zheng--;
    	field[x][y]=0;
    	liver[x][y]=null;
    }
    public void fandie(int x,int y)
    {	fan--;
    	field[x][y]=0;
    	liver[x][y]=null;
    }
    public boolean haspeople(int x,int y)
    {
    	if(field[x][y]==1)
    		return true;
    	else
    		return false;
    }
    public int getfan()
    {
    	return fan;
    }
    public int getzheng()
    {
    	return zheng;
    }
}
