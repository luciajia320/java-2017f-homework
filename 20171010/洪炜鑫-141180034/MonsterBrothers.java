/**************************************************
	> File Name:  MonsterBrothers.java
	> Author:     Leuckart
	> Time:       2018-01-06 08:20
**************************************************/

import java.util.ArrayList;

public class MonsterBrothers
{
	private ArrayList<Monster> Brothers = new ArrayList<Monster>();

	public MonsterBrothers(int num)
	{
		for(int i=0;i<num;i++)
		{
			Brothers.add(new Monster());
		}
	}

	public Monster[] getBrothers()
	{
		return (Monster[])Brothers.toArray(new Monster[Brothers.size()]);
	}
}
