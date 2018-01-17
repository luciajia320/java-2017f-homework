package javaH2;

public class Huluwa extends creature{

	private NAME name;
	private COLOR color;
	
	
	
	
	
	public COLOR getColor() {
		return color;
	}

	public void setColor(COLOR color) {
		this.color = color;
	}


	public Huluwa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Huluwa(NAME name, COLOR color) {
		super();
		this.name = name;
		this.color = color;
	}

	public NAME getName() {
		return name;
	}

	public void setName(NAME name) {
		this.name = name;
	}
	
	
	public int solo(Huluwa A)
	{
		//if(A.getID()==this.ID) return 0;
		if(A.getColor().ordinal()==this.getColor().ordinal()) return 0;
		//else if(this.ID>A.getID()) return 1;
		else if(A.getColor().ordinal()<this.getColor().ordinal()) return 1;
		else return -1;
		
	}
	public void report(int i,int j)
	{
		System.out.println(this.name+":"+(i+1)+"->"+(j+1));
	}
	public void go(Huluwa[] list,int i)
	{
		list[i]=this;
	}
	public void swap(Huluwa A,Huluwa[] list)
	{
		int i=-1;
		int j=-1;
		for (int j2 = 0; j2 < list.length-1; j2++) {
			if(this.solo(list[j2])==0) i=j2;
			else if(A.solo(list[j2])==0) j=j2;
			else;
		}
		if (i==-1||j==-1) return;
		this.go(list,7);
		report(i,7);
		A.go(list,i);
		A.report(j, i);
		list[7].go(list, j);
		list[7].report(7, j);
		
		
	}
	
	public void getPosition(int x,int y)
	{
		Pos.setX(x);
		Pos.setY(y);
		Pos.setMaster(this);
	}
	
	@Override
	public String Tell()
	{
		return this.getColor().toString();
	}
	
}
enum COLOR
{
	赤,橙,黄,绿,青,蓝,紫
}
enum NAME
{
	一,二,三,四,五,六,七
}

