package Homework3;

public class Scorpion implements Creature{
	int ID;
	String Name;
	
	Scorpion(){
		this.ID = 2;
		this.Name = "Ð«×Ó¾«   ";
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
