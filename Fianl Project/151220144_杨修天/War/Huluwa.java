package War;
import javax.swing.*;



import java.awt.*;
import java.io.IOException;
import java.net.URL;

class Huluwa extends JFrame implements Creature,Compare,Runnable
{	private Identity id;
	private WarGround s;
	private ImageIcon image; 
	private int x;
	private int y;
	private int live;
	public void setposition(int a,int b)
	{
		x=a;
		y=b;	
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	public ImageIcon getimage()
	{
		return image;
		
	}
Huluwa(Identity id,WarGround s)
{		live=1;
		this.id=id;
		this.s=s;
		image=new ImageIcon(this.getClass().getResource(this.id.senior+".png"));  
}

public void getsign()
{
	System.out.print(id);
}
public Identity getid()
{
	return id;
	
}


public void tellcolor()
{
	System.out.print(id+" ");
}
public void tellname()
{
	System.out.print(id.context+" ");
}

@Override
public boolean kill_or_die(Compare other)//所有实现了compare接口的类均可作为方法的参数传递进来
{
	 if (other instanceof  Huluwa)//instanceof判断是否是葫芦娃类，返回值为true or false
            return this.id.senior>((Huluwa) other).id.senior;
        else
            return false;
}

public void die()
{
	this.live=0;
	image=new ImageIcon(this.getClass().getResource("dead.png"));
	s.huluwadie(x,y,this.id.senior-1);
}
public void moveto(int a,int b)
{	
	int xx=x;
	int yy=y;
	if(xx<a)
		xx++;
	else if(xx>a)
		xx--;
	if(yy<b)
		yy++;
	else if(yy>b)
		yy--;
	if(!(s.getholder().haspeople(xx,yy)))
	{	
		s.huluwamove(x,y,xx,yy,this.id.senior-1);
		x=xx;
		y=yy;
	}
	else
	{
		Creature l=s.getholder().getliver(xx,yy);
 		   if(l instanceof Luoluo)
 		   {int index=(int)(Math.random()*10);  
 		   	if(index<2)
 		   	{  this.die();}
 		   	else
 			   ((Luoluo) l).die();
 		   }
 		   else if(l instanceof Snake)
 		  {int index=(int)(Math.random()*10);  
		   	if(index<6)
		   	{  this.die();}
		   	else
			   ((Snake) l).die();
		   }
 		  else if(l instanceof Xiezijing)
 		  {int index=(int)(Math.random()*10);  
		   	if(index<4)
		   	{  this.die();}
		   	else
			   ((Xiezijing) l).die();
		   }
	}
}
public void run()
{	
	 while(this.live==1&&(s.getholder().getfan()>0))
	 { 	  try { 
		   
		   
	 	  if(s.getround()==1)
	 	   {
	 	   if(x<749&&!(s.getholder().haspeople(x+1,y)))
	 	   {
	 		  x+=1;
		   s.huluwamove(x-1,y,x,y,this.id.senior-1);
		   
	 	   }
	 	   else if(x<749)
	 	   {
	 		   
	 	   }
	 	   else if(x==749)
	 	   	{
	 		   s.setround();
	 	   	}
	 	   }
	 	   else
	 	   {
	 		   moveto(375,225);
	 			  
	 		   
	 	   }
	 	   Thread.sleep(3);  }
		   catch (InterruptedException e) {  
		         // TODO Auto-generated catch block  
		         e.printStackTrace();  
		     }   
	 }
	 while(s.getholder().getfan()==0)
	
	 {	try { moveto(50,id.senior*50);
		Thread.sleep(5);  }
	   catch (Exception e) {  
	         // TODO Auto-generated catch block  
	         e.printStackTrace();  
	     }  
	}
	}

}

enum Identity
{
	红("老大",1),
	橙("老二",2),
	黄("老三",3),
	绿("老四",4),		
	青("老五",5),
	蓝("老六",6),
	紫("老七",7);
  public String context;
  public int senior;
  private Identity(String context,int x)
  {
    	 this.context = context;
    	 this.senior=x;
   }
  
}