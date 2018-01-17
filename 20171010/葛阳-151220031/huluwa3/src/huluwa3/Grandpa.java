package huluwa3;

public class Grandpa extends basicHuman implements Creature{
	
	public void printCreature()
	{
		System.out.print("👴");
	}

	@Override
	public void report() {
		System.out.print("Grandpa");
		
	}
	

}
