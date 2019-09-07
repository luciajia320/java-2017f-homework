/**************************************************
	> File Name:  Chonge.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:40
**************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Chonge extends Policy
{
	public static final int MonsterNumber=5;
	static
	{
		location.get("Grandfather").add(new Position(5,0));

		location.get("Huluwa").add(new Position(5,2));
		location.get("Huluwa").add(new Position(5,3));
		location.get("Huluwa").add(new Position(5,4));
		location.get("Huluwa").add(new Position(5,5));
		location.get("Huluwa").add(new Position(5,6));
		location.get("Huluwa").add(new Position(5,7));
		location.get("Huluwa").add(new Position(5,8));

		location.get("Snake").add(new Position(5,18));

		location.get("Scorpion").add(new Position(5,11));

		location.get("Monster").add(new Position(6,12));
		location.get("Monster").add(new Position(5,13));
		location.get("Monster").add(new Position(6,14));
		location.get("Monster").add(new Position(5,15));
		location.get("Monster").add(new Position(6,16));
	}
}
