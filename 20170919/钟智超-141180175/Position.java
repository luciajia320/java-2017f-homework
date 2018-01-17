class Position
{
	int countingNumber;
	HuluBoy holder;

	Position(int rank_var)
	{
		countingNumber = rank_var;
		if(rank_var == 0)
			holder = null;
		else if(rank_var > 0 & rank_var < 8)
			holder = new HuluBoy(rank_var);
		else
			System.out.println("Error position!");
	}

	void numberOff()
	{
		System.out.println("Position " + countingNumber + "\t" + holder.name + "\t" + holder.color);
	}
}