package javaH2;

public class SmallM extends creature{
	String name;
	boolean IsLeaded;
	//int allowed;//被允许出站的喽啰数
	public SmallM()
	{
		super();
	}
	public SmallM(String name)
	{
		this.name=name;
		IsLeaded=true;
	}
	
	public String getName()
	{
		if(IsLeaded)
			return name;
		return null;
	}
	
	public void setIsLeaded()
	{
		IsLeaded=false;
	}
	
	public void setPos(int x,int y)
	{
		Pos.setX(x);
		Pos.setY(y);
		Pos.setMaster(this);
	}
	public position getPos()
	{
		return Pos;
	}
	
	public String Tell()
	{
		return this.name;
	}
	

}
