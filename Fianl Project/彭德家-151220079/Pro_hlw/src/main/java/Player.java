//package finalproject;

import java.awt.Image;
//import java
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
  private Field field;
  private int speed = 7;
  public Player(int x, int y, Field field,int num) {
      super(x, y,num);
      URL loc;
      this.field = field;
      
      if (this.type()<0)
      	loc= this.getClass().getClassLoader().getResource("player1.png");
      else
      	loc= this.getClass().getClassLoader().getResource("player.png");
      ImageIcon icon = new ImageIcon(loc);
      Image image = icon.getImage();
      this.setImage(image);
  }

  public void move(int x, int y) {
      int nx = this.x() + x;
      int ny = this.y() + y;
      this.setX(nx);
      this.setY(ny);
  }
  public synchronized boolean canmove(int x,int y) {
	  int nx = this.x() + x;
	  int ny = this.y() + y;
	  int distance = 10;
	  for(int i = 0;i < this.field._player().size();i++) {
		  Player item = (Player)this.field._player().get(i);
		  if(this.type() != item.type() && this.type() * item.type() > 0) {
			  //if(nx == item.x() && ny == item.y()) {
			  if(Math.abs(item.x() - nx) < distance && Math.abs(item.y() - ny) < distance) {
				  return false;
			  }
		  }
	  }
	  return true;
  }
  public synchronized int judgex()
  {
  	for (int i = 0; i < this.field._player().size(); i++) {
			Player item=(Player)this.field._player().get(i);
			if (this.type()==item.type())
				continue;
			else
			{
				if (this.type()*item.type()<0)
				{
					if (this.x()<item.x())
						return speed+Math.abs(this.type());
					else if (this.x()>item.x())
						return -speed-Math.abs(this.type());
					else
						return 0;
				}
			}
		}
  	return -1;
  }
  
  public synchronized int judgey()
  {
  	for (int i = 0; i < this.field._player().size(); i++) {
			Player item=(Player)this.field._player().get(i);
			if (this.type()==item.type())
				continue;
			else
			{
				if (this.type()*item.type()<0)
				{
					if (this.y()<item.y())
						return speed+Math.abs(this.type());
					else if (this.y()>item.y())
						return -speed-Math.abs(this.type());
					else
						return 0;
				}
			}
		}
  	return -1;
  }
  
  public synchronized int fight()
  {
  	/*for (int i = 0; i < this.field._player().size(); i++) {
  		System.out.println(((Player)this.field._player().get(i)).type());
  	}*/
	  int fightdistance = 30;
  	for (int i = 0; i < this.field._player().size(); i++) {
			Player item=(Player)this.field._player().get(i);
			if (this.type()==item.type()||((this.type()>0)&&(item.type()>0))||((this.type()<0)&&(item.type()<0)))
			{
				continue;
			}
			else
			{
				if ((Math.abs(this.y()-item.y())<=fightdistance)&&(Math.abs(this.x()-item.x())<=fightdistance))
				{
					
					if (Math.abs(this.type())<=Math.abs(item.type()))
					{
						int k=-1;
						for (int j = 0; j < this.field._player().size(); j++) {
							if (this.type()==((Player)this.field._player().get(j)).type())
							{
								k=j;
								break;
							}
						}
						if (k!=-1)
						{
							this.field.getdeadplayer().add(k);
							this.field._player().remove(k);
						}
						return 0;
					}
				}
			}
		}
  	return -1;
  }
  
  public void run() {
      while (!Thread.interrupted()) {
//      	synchronized (Player.class) {
      	//System.out.println(this.field._player().size());
    	  try {
			this.field.saveToFile();
    	  } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
    	  }
	            Random rand = new Random();
	            if (this.fight()==0)
	            {
	            	
		            this.field.repaint();
		            Thread.currentThread().interrupt();
	            }
	            else
	            {
	            	int _x=this.judgex(),_y=this.judgey();
		            if (_x==-1&&_y==-1)
		            {       	
			             this.field.repaint();
			             Thread.currentThread().interrupt();
		            }
		            else
		            {
		            	if(this.canmove(_x, _y))
		            		this.move(_x, _y);
			            try {
			
			                Thread.sleep(rand.nextInt(1000) + 1000);
			                this.field.repaint();
			
			            } catch (Exception e) {
			            	e.printStackTrace();
			            }
		            }
		            
	            }
      }
  }
}


