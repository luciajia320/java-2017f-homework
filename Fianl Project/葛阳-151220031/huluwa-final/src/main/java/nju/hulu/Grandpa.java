package nju.hulu;

import java.net.URL;

import javax.swing.ImageIcon;

public class Grandpa extends BasicHuman implements Creature{
		
		Grandpa(Position pos,Field field)
		{
		
			sleeptime=900;
			this.type=Type.GRANDPA;
		    this.group=Group.HULU;
			this.field=field;
			this.setPosition(pos);
			loc1 = this.getClass().getClassLoader().getResource("grandpa.png");
		    ImageIcon iia = new ImageIcon(loc1);
		    this.image = iia.getImage();
		    loc2 = this.getClass().getClassLoader().getResource("grandpaDead.png");
		    ImageIcon iib = new ImageIcon(loc2);
		   
		    this.dead = iib.getImage();
		    currentloc=loc1;
		}
		
		

	}



