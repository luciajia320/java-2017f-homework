package nju.hulu;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Servant extends BasicHuman implements Creature{
	
	
	Servant(Position pos,Field field)
	{
		sleeptime=800;
		this.type=Type.SERVANT;
	    this.group=Group.SS;
		this.field=field;
		loc1 = this.getClass().getClassLoader().getResource("servant1.png");
	    ImageIcon iia = new ImageIcon(loc1);
	    this.image = iia.getImage();
	    loc2 = this.getClass().getClassLoader().getResource("servant1Dead.png");
	    ImageIcon iib = new ImageIcon(loc2);
	    this.dead = iib.getImage();
	    this.setPosition(pos);
	    currentloc=loc1;
	}
	  
}