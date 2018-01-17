package com.gxs;
import java.util.ArrayList;


public class ShapeItem
{
	private ArrayList<Position>pattern;
	
	public ShapeItem(ArrayList<Position>pattern)
	{
		this.pattern=pattern;
	}
	public Position get(int index)
	{
		return pattern.get(index);
	}
	public int size()
	{
		return pattern.size();
	}
}