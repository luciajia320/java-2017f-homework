/**************************************************
	> File Name:  God.java
	> Author:     Leuckart
	> Time:       2017-10-06 20:33
**************************************************/

public class God
{
	public static void main(String[] args)
	{
		Field field = new Field();

		field.initField();
		field.enQueueCreature(new Grandfather()).enQueueCreature(HuluBrothers.getInstance().shuffle().getBrothers()).enQueueCreature(new Snake()).enQueueCreature(new Scorpion());
		field.enQueueCreature(new MonsterBrothers(Heyi.MonsterNumber).getBrothers());
		field.setPolicy("Heyi");
		//field.display();
		//field.display();
		//field.display();
		//field.display();

/*
		field.initField();
		field.enQueueCreature(new Grandfather()).enQueueCreature(HuluBrothers.getInstance().shuffle().getBrothers()).enQueueCreature(new Snake()).enQueueCreature(new Scorpion());
		field.enQueueCreature(new MonsterBrothers(Yanxing.MonsterNumber).getBrothers());
		field.setPolicy("Yanxing");
		field.display();

		field.initField();
		field.enQueueCreature(new Grandfather()).enQueueCreature(HuluBrothers.getInstance().shuffle().getBrothers()).enQueueCreature(new Snake()).enQueueCreature(new Scorpion());
		field.enQueueCreature(new MonsterBrothers(Chonge.MonsterNumber).getBrothers());
		field.setPolicy("Chonge");
		field.display();

		field.initField();
		field.enQueueCreature(new Grandfather()).enQueueCreature(HuluBrothers.getInstance().shuffle().getBrothers()).enQueueCreature(new Snake()).enQueueCreature(new Scorpion());
		field.enQueueCreature(new MonsterBrothers(Yulin.MonsterNumber).getBrothers());
		field.setPolicy("Yulin");
		field.display();

		field.initField();
		field.enQueueCreature(new Grandfather()).enQueueCreature(HuluBrothers.getInstance().shuffle().getBrothers()).enQueueCreature(new Snake()).enQueueCreature(new Scorpion());
		field.enQueueCreature(new MonsterBrothers(Fangyuan.MonsterNumber).getBrothers());
		field.setPolicy("Fangyuan");
		field.display();

		field.initField();
		field.enQueueCreature(new Grandfather()).enQueueCreature(HuluBrothers.getInstance().shuffle().getBrothers()).enQueueCreature(new Snake()).enQueueCreature(new Scorpion());
		field.enQueueCreature(new MonsterBrothers(Yanyue.MonsterNumber).getBrothers());
		field.setPolicy("Yanyue");
		field.display();

		field.initField();
		field.enQueueCreature(new Grandfather()).enQueueCreature(HuluBrothers.getInstance().shuffle().getBrothers()).enQueueCreature(new Snake()).enQueueCreature(new Scorpion());
		field.enQueueCreature(new MonsterBrothers(Fengshi.MonsterNumber).getBrothers());
		field.setPolicy("Fengshi");
		field.display();
*/
		field.shutDown();
	}
}
