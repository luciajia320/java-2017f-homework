package niuxuCharacter;

import nju.java.Field;
import nju.java.Point;
import nju.java.story;

public class ruler extends Player implements Boss {

	public ruler() {
		super();
	}
	public ruler(Point p, Field field) {
		super(p,field);
		name = "ruler";
		setImage("resources/ruler.png");
		story.writeStory("ruler at "+"("+this.x()+","+this.y()+")\r\n");
	}
	@Override
	public void sayBoss() {
		// TODO Auto-generated method stub
		System.out.println("I'm boss.");
	}

	@Override
	public String getSide() {
		// TODO Auto-generated method stub
		return "good";
	}

}
