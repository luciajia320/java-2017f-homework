
class Huluwa {
	
//	private Name name;
//	private Color color;
	
	private String name;
	private String color;
	private int nid;
	private int cid;
	private int posX;
	
	Huluwa(String name,String color)
	{
		this.name = name;
		this.color = color;
		switch(name)
		{
		case "老大": nid = 0; break;
		case "老二": nid = 1; break;
		case "老三": nid = 2; break;
		case "老四": nid = 3; break;
		case "老五": nid = 4; break;
		case "老六": nid = 5; break;
		case "老七": nid = 6; break;
		default: {System.out.println("name: " + name + " don't exist!"); nid = -1;} 
		}
		switch(color)
		{
		case "红色": cid = 0; break;
		case "橙色": cid = 1; break;
		case "黄色": cid = 2; break;
		case "绿色": cid = 3; break;
		case "青色": cid = 4; break;
		case "蓝色": cid = 5; break;
		case "紫色": cid = 6; break;
		default: {System.out.println("color: " + color + " don't exist!"); cid = -1;}
		}		
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
	
	void setPos(int x)
	{
		posX = x;
	}

}

