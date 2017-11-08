package Homework3;

public class Grandpa implements Creature{
	int ID;
	String Name;
	Grandpa(){
		this.ID = 1;
		this.Name = "¿œ“Ø“Ø   ";
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
