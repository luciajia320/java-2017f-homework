/**************************************************
	> File Name:  Yanyue.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:40
**************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Yanyue extends Policy
{
	public static final int MonsterNumber=18;
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

		location.get("Snake").add(new Position(5,17));

		location.get("Scorpion").add(new Position(5,11));

		location.get("Monster").add(new Position(4,11));
		location.get("Monster").add(new Position(6,11));

		location.get("Monster").add(new Position(4,12));
		location.get("Monster").add(new Position(5,12));
		location.get("Monster").add(new Position(6,12));

		location.get("Monster").add(new Position(3,13));
		location.get("Monster").add(new Position(7,13));

		location.get("Monster").add(new Position(4,14));
		location.get("Monster").add(new Position(5,14));
		location.get("Monster").add(new Position(6,14));

		location.get("Monster").add(new Position(2,14));
		location.get("Monster").add(new Position(8,14));

		location.get("Monster").add(new Position(3,15));
		location.get("Monster").add(new Position(7,15));

		location.get("Monster").add(new Position(2,16));
		location.get("Monster").add(new Position(8,16));

		location.get("Monster").add(new Position(1,17));
		location.get("Monster").add(new Position(9,17));
	}
}
