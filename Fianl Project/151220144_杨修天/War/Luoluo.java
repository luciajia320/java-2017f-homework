package War;

import javax.swing.ImageIcon;



public class Luoluo implements Creature,Runnable{
	private int live;
	private WarGround s;
	private int num;
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
	Luoluo(int num,WarGround s)
	{	this.num=num;
		this.s=s;
		live=1;
		image=new ImageIcon(this.getClass().getResource("à¶.png"));  
	}
	
	public void getsign()
	{
		System.out.print('¹Ö');
	}
	
	
	public void tellname()
	{
		System.out.print("Ð¡Ñý¹Ö");
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
			s.luoluomove(x,y,xx,yy,this.num);
			x=xx;
			y=yy;
		}
		else
		{
			Creature l=s.getholder().getliver(xx,yy);
	 		   if(l instanceof Huluwa)
	 		   {int index=(int)(Math.random()*10);  
	 		   	if(index<9)
	 		   	{  this.live=0;
	 			  image=new ImageIcon(this.getClass().getResource("dead.png"));
	 			 s.luoluodie(x,y,this.num);}
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
		s.luoluodie(x,y,this.num);
	}
	public void run()
	{	
		 while(this.live==1&&(s.getholder().getzheng()>0))
		 { 	  try { 
			   
			 if(s.getround()==1)
			 {
		 	   if(x>50&&!(s.getholder().haspeople(x-1,y)))
		 	   {
		 		  x-=1;
			   s.luoluomove(x+1,y,x,y,this.num);
			   //System.out.println("à¶†ª");
		 	   }
		 	   else if(x>50)
		 	   {   Creature l=s.getholder().getliver(x-1, y);
		 		   if(l instanceof Huluwa)
		 		   {int index=(int)(Math.random()*10);  
		 		   	if(index<9)
		 		   	{  this.live=0;
		 			  image=new ImageIcon(this.getClass().getResource("dead.png"));
		 			 s.luoluodie(x,y,this.num);}
		 		   	else
		 			   ((Huluwa) l).die();
		 		   }
		 		   else if(l instanceof Grandpa)
		 		   {
		 			  ((Grandpa) l).die();
		 		   }
		 	   }
		 	  else if(x==50)
		 		  s.setround();
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
		 while(s.getholder().getzheng()==0)
		 {	try { moveto(700,this.num*50);
			Thread.sleep(4);  }
		   catch (Exception e) {  
		         // TODO Auto-generated catch block  
		         e.printStackTrace();  
		     }  
		}
		}

	
}
