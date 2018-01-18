package War;

import javax.swing.ImageIcon;

public class Snake implements Creature,Runnable{
	private WarGround s;
	private int live;
	private ImageIcon image;  
	private int x;
	private int y;
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
	Snake(WarGround s)
	{	live=1;
		this.s=s;
		image=new ImageIcon(this.getClass().getResource("…ﬂ.png")); 
	}
	
	
	
	
	public void getsign()
	{
		System.out.print("…ﬂ");
	}
	
	
	public void tellname()
	{
		System.out.print("¿œ¥Û");
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
			s.shejingmove(x,y,xx,yy);
			x=xx;
			y=yy;
		}
		else
		{
			Creature l=s.getholder().getliver(xx,yy);
	 		   if(l instanceof Huluwa)
	 		   {int index=(int)(Math.random()*10);  
	 		   	if(index<3)
	 		   	{  this.live=0;
	 			  image=new ImageIcon(this.getClass().getResource("dead.png"));
	 			 s.shejingdie(x,y);}
	 		   	else
	 			   ((Huluwa) l).die();
	 		   }
	 		   else if(l instanceof Grandpa)
	 		   {
	 			  ((Grandpa) l).die();
	 		   }
			
		}
	}
	public void die()
	{
		this.live=0;
		image=new ImageIcon(this.getClass().getResource("dead.png"));
		s.shejingdie(x,y);
	}
	public void run()
	   {	
		 while(this.live==1&&(s.getholder().getzheng()>0))
		 { 	  try { 
			   
			 if(s.getround()==1)
			 {
		 	   if(x>0&&!(s.getholder().haspeople(x-1,y)))
		 	   {
		 		  x-=1;
			   s.shejingmove(x+1,y,x,y);
			   
		 	   }
		 	  else if(x>0)
		 	   {   Creature l=s.getholder().getliver(x-1, y);
		 		   if(l instanceof Huluwa)
		 		   {int index=(int)(Math.random()*10);  
		 		   	if(index<3)
		 		   	{  this.live=0;
		 			  image=new ImageIcon(this.getClass().getResource("dead.png"));
		 			 s.shejingdie(x,y);}
		 		   	else
		 			   ((Huluwa) l).die();
		 		   }
		 		   else if(l instanceof Grandpa)
		 		   {
		 			  ((Grandpa) l).die();
		 		   }
		 	   }
		 	  else if(x==0)
		 		  s.setround();
			 }
			 else
			 {
				 moveto(375,225);
			 }
		 	   Thread.sleep(3);  }
			   catch (Exception e) {  
			         // TODO Auto-generated catch block  
			         e.printStackTrace();  
			     }   
		 }
		 while(s.getholder().getzheng()==0)
		 {	try { moveto(740,150);
			Thread.sleep(5);  }
		   catch (Exception e) {  
		         // TODO Auto-generated catch block  
		         e.printStackTrace();  
		     }  
		}
		}
}