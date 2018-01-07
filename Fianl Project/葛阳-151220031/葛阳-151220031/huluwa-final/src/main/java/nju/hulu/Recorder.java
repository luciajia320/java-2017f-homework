package nju.hulu;

import java.awt.Image;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Recorder implements Serializable{
	public ArrayList<RecordList> table=new ArrayList<RecordList>();
	public int next=0;
	public int current=-1;
	public void addList()
	{
		current++;
		table.add(new RecordList());
	
	}
	public void addNode(int x,int y,URL loc)
	{
		table.get(current).add(x,y,loc);
	}
	
	
	public  boolean withinRange()
	{
		return next<table.size()-1;
	}
	
	public  RecordList getNext()
	{			
		if(withinRange())
			return table.get(next++);
		else
			return table.get(table.size()-1);
				
	}
	public boolean finished()
	{
		if(withinRange())
			return false;
		else
			return true;
	}
	
	

}
