/**************************************************
	> File Name:  Fangyuan.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:40
**************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Fangyuan extends Policy
{
	public static final int MonsterNumber=7;
	static
	{
		location=new HashMap<String,ArrayList<Position>>();

		location.put("Grandfather",new ArrayList<Position>());
		location.get("Grandfather").add(new Position(5,0));

		location.put("Huluwa",new ArrayList<Position>());
		location.get("Huluwa").add(new Position(5,2));
		location.get("Huluwa").add(new Position(5,3));
		location.get("Huluwa").add(new Position(5,4));
		location.get("Huluwa").add(new Position(5,5));
		location.get("Huluwa").add(new Position(5,6));
		location.get("Huluwa").add(new Position(5,7));
		location.get("Huluwa").add(new Position(5,8));

		location.put("Scorpion",new ArrayList<Position>());
		location.get("Scorpion").add(new Position(5,11));

		location.put("Monster",new ArrayList<Position>());
		location.get("Monster").add(new Position(3,13));
		location.get("Monster").add(new Position(4,12));
		location.get("Monster").add(new Position(4,14));
		location.get("Monster").add(new Position(5,15));
		location.get("Monster").add(new Position(6,12));
		location.get("Monster").add(new Position(6,14));
		location.get("Monster").add(new Position(7,13));

		location.put("Snake",new ArrayList<Position>());
		location.get("Snake").add(new Position(5,17));
	}
}
