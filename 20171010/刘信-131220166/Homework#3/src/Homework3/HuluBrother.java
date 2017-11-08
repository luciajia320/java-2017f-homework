package Homework3;

public class HuluBrother implements Creature{
	int ID;
	String Name;
	
	HuluBrother(int order){
		this.ID = 4 + order;
		switch(order){
			case 1:
				this.Name = "老大       ";
				break;
			case 2:
				this.Name = "老二       ";
				break;
			case 3:
				this.Name = "老三       ";
				break;
			case 4:
				this.Name = "老四       ";
				break;
			case 5:
				this.Name = "老五       ";
				break;
			case 6:
				this.Name = "老六       ";
				break;
			case 7:
				this.Name = "老七       ";
				break;
			default:
				break;
		}
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