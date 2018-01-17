package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Calabash_brother extends Player{
    private final int OFFSET = 10;
    private final int SPACE = 60;
	public Calabash_brother(int x, int y, Character c, Field field) {
		super(x, y, c, field);
		state = Statement.stop;
		if(c == Character.Red_cala)
			state = Statement.Right;
	}

	@Override
	public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            if(field.get_completed() == true)
            	break;
            if(state != Statement.Dead && state != Statement.stop) {
            	int k = stritage();
            	if(k == 1) {
            		this.move(SPACE, 0);
            	}
            	else if(k == 2) {
            		this.move(-SPACE, 0);
            	}
            	else if(k == 3) {
            		this.move(0, SPACE);
            	}
            	else if(k == 4) {
            		this.move(0, -SPACE);
            	}
            }
            try {
                Thread.sleep(1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
	
	public int stritage() {
		if(Huluwa == Character.Red_cala) {
	    	Player item = (Player) field.Creature.get(8);
	    	for(int i = 8;i < field.Creature.size();i++) {
	    		 item = (Player) field.Creature.get(i);
	    		 if(item.state != Statement.Dead)
	    			 break;
	    	}
	    	if(item.state != Statement.Dead) {
	    		if(item.x() > this.x() && this.x() < 9*SPACE + OFFSET)
	    			return 1;
	    		else if(item.x() < this.x() && this.x() > OFFSET)
	    			return 2;
	    		else if(item.y() > this.y())
	    			return 3;
	    		else
	    			return 4;   		    
	    	}
	    	else
	    		field.stop_game();
		}
		else if(Huluwa == Character.Orange_cala) {
	    	Player item= (Player) field.Creature.get(10);
	    	for(int i = 10;i < field.Creature.size();i++) {
	    		 item = (Player) field.Creature.get(i);
	    		 if(item.state != Statement.Dead)
	    			 break;
	    	}
	    	if(item.state == Statement.Dead)
	    		item = (Player) field.Creature.get(8);
	    	if(item.state == Statement.Dead)
	    		item = (Player) field.Creature.get(9);
	    	if(item.state != Statement.Dead) {
	    		if(item.x() > this.x() && this.x() < 9*SPACE + OFFSET)
	    			return 1;
	    		else if(item.x() < this.x() && this.x() > OFFSET)
	    			return 2;
	    		else if(item.y() > this.y())
	    			return 3;
	    		else
	    			return 4;   		    
	    	}
	    	else
	    		field.stop_game();
		}
		else if(Huluwa == Character.Yellow_cala) {
	    	Player item = (Player) field.Creature.get(8);
	    	for(int i = 8;i < field.Creature.size();i++) {
	    		 item = (Player) field.Creature.get(i);
	    		 if(item.state != Statement.Dead)
	    			 break;
	    	}
	    	if(item.state != Statement.Dead) {
	    		if(item.x() > this.x() && this.x() < 9*SPACE + OFFSET)
	    			return 1;
	    		else if(item.x() < this.x() && this.x() > OFFSET)
	    			return 2;
	    		else if(item.y() > this.y())
	    			return 3;
	    		else
	    			return 4;   		    
	    	}
	    	else
	    		field.stop_game();
		}
		else if(Huluwa == Character.Green_cala) {
	    	Player item = (Player) field.Creature.get(8);
	    	for(int i = 8;i < field.Creature.size();i++) {
	    		 item = (Player) field.Creature.get(i);
	    		 if(item.state != Statement.Dead)
	    			 break;
	    	}
	    	if(item.state != Statement.Dead) {
	    		if(item.x() > this.x())
	    			return 1;
	    		else if(item.x() < this.x())
	    			return 2;
	    		else if(item.y() > this.y())
	    			return 3;
	    		else
	    			return 4;   		    
	    	}
	    	else
	    		field.stop_game();
		}
		else if(Huluwa == Character.Cyan_cala) {
	    	Player item = (Player) field.Creature.get(8);
	    	for(int i = 8;i < field.Creature.size();i++) {
	    		 item = (Player) field.Creature.get(i);
	    		 if(item.state != Statement.Dead)
	    			 break;
	    	}
	    	if(item.state != Statement.Dead) {
	    		if(item.x() > this.x() && this.x() < 9*SPACE + OFFSET)
	    			return 1;
	    		else if(item.x() < this.x() && this.x() > OFFSET)
	    			return 2;
	    		else if(item.y() > this.y())
	    			return 3;
	    		else
	    			return 4;   		    
	    	}
	    	else
	    		field.stop_game();
		}
		else if(Huluwa == Character.Blue_cala) {
	    	Player item= (Player) field.Creature.get(10);
	    	for(int i = 10;i < field.Creature.size();i++) {
	    		 item = (Player) field.Creature.get(i);
	    		 if(item.state != Statement.Dead)
	    			 break;
	    	}
	    	if(item.state == Statement.Dead)
	    		item = (Player) field.Creature.get(8);
	    	if(item.state == Statement.Dead)
	    		item = (Player) field.Creature.get(9);
	    	if(item.state != Statement.Dead) {
	    		if(item.x() > this.x() && this.x() < 9*SPACE + OFFSET)
	    			return 1;
	    		else if(item.x() < this.x() && this.x() > OFFSET)
	    			return 2;
	    		else if(item.y() > this.y())
	    			return 3;
	    		else
	    			return 4;   
	    		
	    	}
	    	else
	    		field.stop_game();	
		}
		else if(Huluwa == Character.Purple_cala) {
	    	Player item= (Player) field.Creature.get(10);
	    	for(int i = 10;i < field.Creature.size();i++) {
	    		 item = (Player) field.Creature.get(i);
	    		 if(item.state != Statement.Dead)
	    			 break;
	    	}
	    	if(item.state == Statement.Dead)
	    		item = (Player) field.Creature.get(8);
	    	if(item.state == Statement.Dead)
	    		item = (Player) field.Creature.get(9);
	    	if(item.state != Statement.Dead) {
	    		if(item.x() > this.x() && this.x() < 9*SPACE + OFFSET)
	    			return 1;
	    		else if(item.x() < this.x() && this.x() > OFFSET)
	    			return 2;
	    		else if(item.y() > this.y())
	    			return 3;
	    		else
	    			return 4;   
	    		
	    	}
	    	else
	    		field.stop_game();	
		}
		return 0;
	}
}
