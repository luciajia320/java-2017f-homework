package Homework3;

public class Grandpa implements Creature{
	int ID;
	String Name;
	Grandpa(){
		this.ID = 1;
		this.Name = "老爷爷";
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
