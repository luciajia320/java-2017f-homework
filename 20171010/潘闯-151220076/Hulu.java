package assignment3;

class Hulu extends Creature{
	
	private String name;
	private String color;
	private int nid;
	private int cid;
	
	Hulu(String name,String color)
	{
		super();
		this.name = name;
		this.color = color;
		switch(name)
		{
		case "�ϴ�": nid = 0; break;
		case "�϶�": nid = 1; break;
		case "����": nid = 2; break;
		case "����": nid = 3; break;
		case "����": nid = 4; break;
		case "����": nid = 5; break;
		case "����": nid = 6; break;
		default: {System.out.println("name: " + name + " don't exist!"); nid = -1;} 
		}
		switch(color)
		{
		case "��ɫ": cid = 0; break;
		case "��ɫ": cid = 1; break;
		case "��ɫ": cid = 2; break;
		case "��ɫ": cid = 3; break;
		case "��ɫ": cid = 4; break;
		case "��ɫ": cid = 5; break;
		case "��ɫ": cid = 6; break;
		default: {System.out.println("color: " + color + " don't exist!"); cid = -1;}
		}		
	}
	public String report()
	{
		return "hulu" + (nid+1);
	}
	
	int getNid()
	{
		return nid;
	}
	void setNid(int id)
	{
		this.nid = id;
	}
	int getCid()
	{
		return cid;
	}
	void setCid(int id)
	{
		this.cid = id;
	}
	String getName()
	{
		return name;
	}
	void setName(String name)
	{
		this.name = name;
	}
	String getColor()
	{
		return color;
	}
	void setColor(String color)
	{
		this.color = color;
	}
}
