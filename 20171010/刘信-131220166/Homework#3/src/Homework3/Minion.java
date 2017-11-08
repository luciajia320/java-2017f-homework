package Homework3;

public class Minion implements Creature{
	int ID;
	String Name;
	
	Minion(){
		this.ID = 3;
		this.Name = "小喽啰";
	}

	@Override
	public int returnID(){
		return this.ID;
	}

	@Override
	public String returnName(){
		return this.Name;
	}
}
