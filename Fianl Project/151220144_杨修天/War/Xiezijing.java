package War;

import javax.swing.ImageIcon;

public class Xiezijing implements Creature,Runnable{
	private int live;
	private ImageIcon image; 
	private int x;
	private int y;
	private WarGround s;
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
	Xiezijing(WarGround s)
	{	this.s=s;
		live=1;
		image=new ImageIcon(this.getClass().getResource("蝎.png"));  
	}
	
	public void getsign()
	{
		System.out.print('蝎');
	}
	
	public void tellname()
	{
		System.out.print("二当家");
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
			s.xiezijingmove(x,y,xx,yy);
			x=xx;
			y=yy;
		}
		else
		{
			Creature l=s.getholder().getliver(xx,yy);
	 		   if(l instanceof Huluwa)
	 		   {int index=(int)(Math.random()*10);  
	 		   	if(index<7)
	 		   	{  this.live=0;
	 			  image=new ImageIcon(this.getClass().getResource("dead.png"));
	 			 s.xiezijingdie(x,y);}
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
		s.xiezijingdie(x,y);
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
			   s.xiezijingmove(x+1,y,x,y);
			   //system.out.println("蝎子精");
		 	   }
		 	  else if(x>0)
		 	   {   Creature l=s.getholder().getliver(x-1, y);
		 		   if(l instanceof Huluwa)
		 		   {int index=(int)(Math.random()*10);  
		 		   	if(index<7)
		 		   	{  this.live=0;
		 			  image=new ImageIcon(this.getClass().getResource("dead.png"));
		 			 s.xiezijingdie(x,y);}
		 		   	else
		 			   ((Huluwa) l).die();
		 		   }
		 		   else if(l instanceof Grandpa)
		 		   {
		 			  ((Grandpa) l).die();
		 		   }
		 	   }
		 	 else if(x==0)
		 	   	{
		 		   s.setround();
		 	   	}  
			 }
			 else
			 {
				 moveto(375,225);
			 }
		 	   Thread.sleep(2);  }
			   catch (Exception e) {  
			         // TODO Auto-generated catch block  
			         e.printStackTrace();  
			     }   
		 }
		 while(s.getholder().getzheng()==0)
		{	try { moveto(740,200);
			Thread.sleep(5);  }
		   catch (Exception e) {  
		         // TODO Auto-generated catch block  
		         e.printStackTrace();  
		     }  
		}
		}
}