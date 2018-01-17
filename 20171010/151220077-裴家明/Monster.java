package javaH2;
public class Monster extends creature {
	String name;
	int num;		//Monster造小喽罗的能力（小喽罗由Monster制造）
	SmallM[] List=new SmallM[20];	//小喽罗名单,小喽罗最多不超过20个
	int count;//上战场的喽啰
	public Monster(String name)
	{
		num=10;
		this.name=name;
		//List=new SmallM[num];
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setNum(int num)
	{
		this.num=num;
	}
	
	public void creatSM()//造喽啰
	{
		for (int i = 0; i < List.length; i++) {
			List[i]=new SmallM(""+i);
		}
	}
	public void addSM(SmallM A)//添加喽啰
	{
		List[num]=A;
		num++;
	}
	
	public SmallM[] getSM()//返回喽啰名单
	{
		return List;
	}
	public void setSM(SmallM[] list)//接管一批新的喽啰
	{
		List=list;
	}
	
	
	public void getPosition(int x,int y)
	{
		Pos.setX(x);
		Pos.setY(y);
		Pos.setMaster(this);
	}
	
	public void setSMpos(int i)//用参数i来表示使用那张阵图
	{
		int p[][];
		if (i==1) p=new Form().changshe(this);
		else if (i==2) p=new Form().heyi(this);
		else if (i==3) p=new Form().yanxing(this);
		else if (i==4) p=new Form().henge(this);
		else if (i==5) p=new Form().yulin(this);
		else if (i==6) p=new Form().fangyuan(this);
		else if (i==7) p=new Form().yanyue(this);
		else p=new Form().fengshi(this);
		count=p.length;
		for (int j = 0; j < p.length; j++) {
			List[j].setPos(p[j][0], p[j][1]);
		}
	}
	
	public void enterField(field A)
	{
		A.place[Pos.getX()][Pos.getY()].setMaster(this);
		int t=count;
		for (int i = 0; i < t; i++) {
			A.place[List[i].getPos().getX()][List[i].getPos().getY()].setMaster(List[i]);
		}
	}
	
	
	public String Tell()
	{
		return this.name;
	}
	
}








