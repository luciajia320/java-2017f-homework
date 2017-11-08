package Homework3;

public class Snake implements Creature{
	int ID;
	String Name;
	
	Snake(){
		this.ID = 4;
		this.Name = "Éß¾«       ";
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
