package javaH2;
public class Monster extends creature {
	String name;
	int num;		//Monster��С��޵�������С�����Monster���죩
	SmallM[] List=new SmallM[20];	//С�������,С�����಻����20��
	int count;//��ս����ආ�
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
	
	public void creatSM()//��ආ�
	{
		for (int i = 0; i < List.length; i++) {
			List[i]=new SmallM(""+i);
		}
	}
	public void addSM(SmallM A)//���ආ�
	{
		List[num]=A;
		num++;
	}
	
	public SmallM[] getSM()//����ආ�����
	{
		return List;
	}
	public void setSM(SmallM[] list)//�ӹ�һ���µ�ආ�
	{
		List=list;
	}
	
	
	public void getPosition(int x,int y)
	{
		Pos.setX(x);
		Pos.setY(y);
		Pos.setMaster(this);
	}
	
	public void setSMpos(int i)//�ò���i����ʾʹ��������ͼ
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








