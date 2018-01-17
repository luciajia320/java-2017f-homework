package nju.hulu;

import java.awt.Image;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class RecordList implements Serializable{
	ArrayList<RecordNode> list=new ArrayList<RecordNode>();
	int next=0;
	
	public void add(int x,int y,URL loc)
	{
		list.add(new RecordNode(x,y,loc));
	
	}
	public boolean withinRange()
	{
		if(next< list.size())
			return true;
		else
		{
			next=0;
			return false;
		}
		
	}
	public  RecordNode getNext()
	{			
			
			return list.get(next++);
			
				
	}
	
	
}
