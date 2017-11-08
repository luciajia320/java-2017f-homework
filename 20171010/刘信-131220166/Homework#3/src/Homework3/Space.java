package Homework3;

public class Space {
	int x;
	int y;
	boolean used;
	Creature usage;
	Space(int i, int j){
		this.x = i;
		this.y = j;
		this.used = false;
		this.usage = null;
	}
}
