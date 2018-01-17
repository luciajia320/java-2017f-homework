package nju.hulu;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Position<T extends Creature> {

	private int x,y;
//	private Image backImage;//背景图片
	Creature holder;
	boolean exist;
	
	Position (int x1,int y1)
	{
		exist=false;
		x=x1;
		y=y1;
	//	 URL loc = this.getClass().getClassLoader().getResource("tile.png");
	 //    ImageIcon iia = new ImageIcon(loc);
	  //   Image image = iia.getImage();
	//     this.backImage=image;
		
	}
/*	public Image getBackImage() {
		return this.backImage;
	}
	
	public Image getHolderImage() 
	{
		if (exist)return holder.getImage();
		else return this.backImage;
	}*/
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
/*	public void setPosition(int x1,int y1)
	{
		this.x=x1;
		this.y=y1;
		
	}*/
	Creature getHolder()
	{
		if (exist)
			return holder;
		else
		{
			System.out.println("no Holder ");
			return null;
		}
	}
	
	public void setHolder(T holder1)
	{
		holder=holder1;
		this.exist=true;
	}

	/*public boolean equal(Position<T> pos)
	{
		if (this.x==pos.x&&this.y==pos.y)
			return true;
		else
			return false;
	}*/

	public void setNull()
	{
		this.exist=false;
	}
	
}
