package nju.hulu;

import java.net.URL;

import javax.swing.ImageIcon;

public class Snake extends BasicHuman implements Creature{
		
		Snake(Position pos,Field field)
		{
			sleeptime=500;
			this.type=Type.SNAKE;
		    this.group=Group.SS;
			this.field=field;
			this.setPosition(pos);
			loc1 = this.getClass().getClassLoader().getResource("snake.png");
		    ImageIcon iia = new ImageIcon(loc1);
		    this.image = iia.getImage();
		    loc2 = this.getClass().getClassLoader().getResource("snakeDead.png");
		    ImageIcon iib = new ImageIcon(loc2);
		    this.dead = iib.getImage();
		    currentloc=loc1;
		    
		}
		
		

	}




