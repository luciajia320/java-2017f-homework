package nju.hulu;

import java.awt.Image;
import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;

public class RecordNode implements Serializable{
	int x;int y;
	URL loc;	
	transient Image image;
	RecordNode()
	{
		
	}
	RecordNode(int a,int b,URL url)
	{
		
		x=a;
		y=b;
		loc=url;
		ImageIcon iia = new ImageIcon(loc);
		image = iia.getImage();
		
		
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Image getImage()
	{
		ImageIcon iia = new ImageIcon(loc);
		image = iia.getImage();
		return image;
	}
	
}
