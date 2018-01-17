package com.gxs;
import java.util.ArrayList;



public class Shape 
{
	
	
	public static final ShapeItem Line=new ShapeItem(new ArrayList<Position>() {{
		add(new Position(0,0));add(new Position(1,0));add(new Position(2,0));
		add(new Position(3,0));add(new Position(4,0));add(new Position(5,0));
		add(new Position(6,0));
	}});
	
	public static final ShapeItem Dot=new ShapeItem(new ArrayList<Position>() {{
		add(new Position(0,0));
	}});
	
	public static final ShapeItem Wing=new ShapeItem(new ArrayList<Position>() {{
		add(new Position(0,0));add(new Position(1,-1));add(new Position(1,1));
		add(new Position(2,-2));add(new Position(2,2));add(new Position(3,-3));
		add(new Position(3,3));add(new Position(4,-4));add(new Position(4,4));
	}});
	
	public static final ShapeItem SmallWing=new ShapeItem(new ArrayList<Position>() {{
		add(new Position(0,0));add(new Position(1,-1));add(new Position(1,1));
		add(new Position(2,-2));add(new Position(2,2));add(new Position(3,-3));
		add(new Position(3,3));
	}});
	
	public static final ShapeItem Moon=new ShapeItem(new ArrayList<Position>() {{
		add(new Position(0,0));add(new Position(0,-1));
		add(new Position(0,1));add(new Position(1,0));
		add(new Position(1,-1));add(new Position(1,1));
		add(new Position(2,0));add(new Position(2,-1));add(new Position(2,1));
		add(new Position(3,-2));add(new Position(3,2));add(new Position(4,-2));
		add(new Position(4,2));add(new Position(4,-3));add(new Position(4,3));
		add(new Position(5,-3));add(new Position(5,3));add(new Position(5,-4));
		add(new Position(5,4));add(new Position(6,-4));add(new Position(6,4));
	}});
	
	private Shape() {}
	private static Shape ShapeService=new Shape();
	public static Shape getShapeService()
	{
		return ShapeService;
	}
	public ArrayList<Position> makeShape(Position key, Dir dir,ShapeItem pattern)
	{
		ArrayList<Position>r=new ArrayList<Position>();
		int x=key.getX(),y=key.getY();
		for(int i=0;i<pattern.size();i++)
		{
			switch(dir)
			{
			case UP:	r.add(new Position(pattern.get(i).getX()+x,pattern.get(i).getY()+y));	break;
			case DOWN:	r.add(new Position(-pattern.get(i).getX()+x,-pattern.get(i).getY()+y));	break;
			case LEFT:	r.add(new Position(-pattern.get(i).getY()+x,pattern.get(i).getX()+y));  break;
			case RIGHT:	r.add(new Position(pattern.get(i).getY()+x,-pattern.get(i).getX()+y));	break;
			}
		}
		return r;
	}
}


	