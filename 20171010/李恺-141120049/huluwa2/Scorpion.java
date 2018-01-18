package huluwa2;

public class Scorpion extends Creature {
	Scorpion(){
		this.species=Species.SCORPION;
	}

	@Override
	public void report() {
		System.out.print("Ð«×Ó¾«");
	}
}
