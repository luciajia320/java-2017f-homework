package nju.hulu;

import java.util.ArrayList;

public abstract class positionSetter {
	int ch,cw,space,xoffset,yoffset;
	int line;
	int row;
	String type;
	private int trans(int x,int y)//二维下标转换为一维
	  {
		 
	    		return (x+xoffset)*ch+y+yoffset;
	   }
	 
	public void setPosition(Queue queue,ArrayList<Position>  positions,int w,int h,int space)
	{
		this.ch=h;
		this.cw=w;
		this.space=space;
		
		ArrayList<Creature> creatures=queue.getCreatures();	
		xoffset=creatures.get(0).getPosition().getX();
		yoffset=creatures.get(0).getPosition().getY();
		int count=0;
		
		for (int i=0;i<line&&count<creatures.size();i++)
			for (int j=0;j<row&&count<creatures.size();j++)
			{			
				int c=j*line+i;
				if (type.charAt(c)=='s')
				{
					creatures.get(count).setPosition(positions.get(trans(i,j)));
					count++;
					
				}
			}
	}
}
