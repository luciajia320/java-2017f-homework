package niuxuCharacter;

import nju.java.Field;
import nju.java.Point;
import nju.java.story;

public class avenger extends Player implements Boss {

	public avenger() {
		// TODO Auto-generated constructor stub
	}

	public avenger(Point p, Field field) {
		super(p, field);
		// TODO Auto-generated constructor stub
		name = "avenger";
		story.writeStory("avenger at "+"("+this.x()+","+this.y()+")\r\n");
		setImage("resources/avenger.png");
	}

	@Override
	public void sayBoss() {
		// TODO Auto-generated method stub
		System.out.println("I'm boss");
	}

	@Override
	public String getSide() {
		// TODO Auto-generated method stub
		return "bad";
	}

}