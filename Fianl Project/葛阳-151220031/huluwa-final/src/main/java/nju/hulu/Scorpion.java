package nju.hulu;

import java.net.URL;

import javax.swing.ImageIcon;


public class Scorpion extends BasicHuman implements Creature{
		
		Scorpion(Position pos,Field field)
		{
		
			this.sleeptime=600;
			this.type=Type.SCORPION;
		    this.group=Group.SS;
			this.field=field;
			this.setPosition(pos);
			loc1 = this.getClass().getClassLoader().getResource("miaomiao.png");
		    ImageIcon iia = new ImageIcon(loc1);
		    this.image = iia.getImage();
		    loc2 = this.getClass().getClassLoader().getResource("miaomiaoDead.png");
		    ImageIcon iib = new ImageIcon(loc2);
		    this.dead = iib.getImage();
		    currentloc=loc1;
		}
		
		

	}



