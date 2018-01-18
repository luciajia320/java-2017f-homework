
enum  CREATURE_TYPE{GOURD,HUMAN,SNAKE,MONSTER,SCORPION}

public class Being {
	public boolean is_creature;
	Being(){is_creature=false;}
	public boolean get_creature_type(){
		return is_creature;
	}
}

