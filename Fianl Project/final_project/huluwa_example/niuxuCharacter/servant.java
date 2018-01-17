package niuxuCharacter;

import nju.java.*;

public class servant extends Player {
	
	private int number;
	private String color;
	
	servant() {
		super();
	}
	
	public servant(Point p, int number, Field field) {
		super(p, field);
		this.number=number;
		switch(number) {
		case 1: {
			name="saber";
			color="RED";
			setImage("resources/saber.png");
			break;
		}
		case 2: {
			name="archer";
			color="ORANGE";
			setImage("resources/archer.png");
			break;
		}
		case 3: {
			name="lancer";
			color="YELLOW";
			setImage("resources/lancer.png");
			break;
		}
		case 4: {
			name="rider";
			color="GREEN";
			setImage("resources/rider.png");
			break;
		}
		case 5: {
			name="caster";
			color="CYAN";
			setImage("resources/caster.png");
			break;
		}
		case 6: {
			name="assasin";
			color="BLUE";
			setImage("resources/assassin.png");
			break;
		}
		case 7: {
			name="berserker";
			color="PURPLE";
			setImage("resources/berserker.png");
			break;
		}
		}
		story.writeStory(name+" at "+"("+this.x()+","+this.y()+")\r\n");
	}
	public int getNumber() {
		return number;
	}
	public String getPaihang() {
		return name;
	}
	public String getColor() {
		return color;
	}
	

	@Override
	public String getSide() {
		// TODO Auto-generated method stub
		return "good";
	}

}