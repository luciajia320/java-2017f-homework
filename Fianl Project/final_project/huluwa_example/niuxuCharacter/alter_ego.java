package niuxuCharacter;

import nju.java.Field;
import nju.java.Point;
import nju.java.story;

public class alter_ego extends Player {

	public alter_ego() {
		// TODO Auto-generated constructor stub
	}

	public alter_ego(Point p, Field field) {
		super(p, field);
		name = "alter_ego";
		// TODO Auto-generated constructor stub
		story.writeStory("alter_ego at "+"("+this.x()+","+this.y()+")\r\n");
		setImage("resources/alter_ego.png");
	}

	@Override
	public String getSide() {
		// TODO Auto-generated method stub
		return "bad";
	}

}