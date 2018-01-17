package War;

import javax.swing.ImageIcon;

public class Grandpa implements Creature,Runnable{
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
	Grandpa(WarGround s)
	{	live=1;
		this.s=s;
		image=new ImageIcon(this.getClass().getResource("ү.png"));  
	}
	
	
	public void getsign()
	{
		System.out.print('ү');
	}
	
	public void tellname()
	{
		System.out.print("��үү");
	}
	public void die()
	{
		this.live=0;
		image=new ImageIcon(this.getClass().getResource("dead.png"));
		s.yeyedie(x,y);
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
			s.yeyemove(x,y,xx,yy);
			x=xx;
			y=yy;
		}
		else
		{
			Creature l=s.getholder().getliver(xx,yy);
	 		   if(l instanceof Luoluo)
	 			  this.die();
	 		   else if(l instanceof Snake)
	 			  this.die();
	 		   else if(l instanceof Xiezijing)
	 			  this.die();   
		}
	}
	public void run()
	   {	
		 while(this.live==1&&(s.getholder().getfan()>0))
		 { 	  try { 
			   
			   
		 	   if(s.getround()==1)
		 	   {
		 	   if(x<700&&!(s.getholder().haspeople(x+1,y)))
		 	   {
		 		  x+=1;
			   s.yeyemove(x-1,y,x,y);
			   //System.out.println("үү");
		 	   }
		 	  else if(x<700)
		 	   {
		 		   
		 	   }
		 	  
		 	   }
		 	   else
		 	   {
		 		   moveto(375,225);
		 	   }
		 	   Thread.sleep(4);  }
			   catch (InterruptedException e) {  
			         // TODO Auto-generated catch block  
			         e.printStackTrace();  
			     }   
		 }
		 while(s.getholder().getfan()==0)
		 {	try { moveto(0,200);
			Thread.sleep(3);  }
		   catch (Exception e) {  
		         // TODO Auto-generated catch block  
		         e.printStackTrace();  
		     }  
		}
		}
	
}
