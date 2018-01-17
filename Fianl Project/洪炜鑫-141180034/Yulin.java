/**************************************************
	> File Name:  Yulin.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:40
**************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Yulin extends Policy
{
	public static final int MonsterNumber=9;
	static
	{
		location.get("Grandfather").add(new Position(5,0));

		location.get("Huluwa").add(new Position(2,3));
		location.get("Huluwa").add(new Position(3,3));
		location.get("Huluwa").add(new Position(4,3));
		location.get("Huluwa").add(new Position(5,3));
		location.get("Huluwa").add(new Position(6,3));
		location.get("Huluwa").add(new Position(7,3));
		location.get("Huluwa").add(new Position(8,3));

		location.get("Snake").add(new Position(5,17));

		location.get("Scorpion").add(new Position(5,11));

		location.get("Monster").add(new Position(2,14));
		location.get("Monster").add(new Position(3,13));
		location.get("Monster").add(new Position(4,12));
		location.get("Monster").add(new Position(4,14));
		location.get("Monster").add(new Position(5,13));
		location.get("Monster").add(new Position(5,15));
		location.get("Monster").add(new Position(6,14));
		location.get("Monster").add(new Position(7,13));
		location.get("Monster").add(new Position(8,14));
	}
}
