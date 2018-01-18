package nju.java;

import java.util.Random;

public class Monster extends Player{
    private final int OFFSET = 10;
    private final int SPACE = 60;
	public Monster(int x, int y, Character c, Field field) {
		super(x, y, c, field);
		state = Statement.stop;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            if(field.get_completed() == true)
            	break;
            //this.move(rand.nextInt(10), rand.nextInt(10));
            if(state == Statement.Right)
            	if(x() < 9*SPACE + OFFSET) {
            			this.move(SPACE, 0);
            	}
            	else {
            		state = Statement.Left;
            		turn_left();
            	}
            if(state == Statement.Left)
            	if(x() > OFFSET) {
            		this.move(-SPACE, 0);
            	}
            	else {
            		state = Statement.Right;
            		turn_right();
            	}
            try {
                Thread.sleep(1000);
                this.field.repaint();
                if(state == Statement.Right)
            		check_fight(x()+SPACE, y());
            	if(state == Statement.Left)
                	check_fight(x()-SPACE, y());

            } catch (Exception e) {

            }
        }
	}
}
