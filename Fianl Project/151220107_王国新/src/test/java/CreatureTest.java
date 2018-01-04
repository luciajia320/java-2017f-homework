import static org.junit.Assert.*;
import org.junit.Test;

public class CreatureTest {
	@Test
	public void testState() {
		Monster monster = new Monster(new Field(),0,0);
		monster.initState();
		assertEquals(monster.getState(),Creature.aliveState);
		
		monster.setState(Creature.attackState);
		assertEquals(monster.getState(),Creature.attackState);
		assertEquals(monster.getEnergyBlast().getEBvisible(),true);
		
		monster.setState(Creature.deadState);
		assertEquals(monster.isDead(),true);
	}
	
	@Test
	public void testConflict() {
		Monster monster = new Monster(new Field(),100,100);
		assertEquals(monster.conflict(101, 101, monster),true);
	}
	
	@Test
	public void testBeingAttacked() {
		Monster monster = new Monster(new Field(),100,100);
		monster.initState();
		//monster初始血量为200
		monster.beingAttacked(100);
		assertEquals(monster.isDead(),false);
		monster.beingAttacked(100);
		assertEquals(monster.isDead(),true);
	}
	
	@Test
	public void testOutOfBound() {
		Monster monster = new Monster(new Field(),100,100);
		monster.initState();
		monster.getEnergyBlast().setX(200);
		assertEquals(monster.outOfBound(),true);
	}
}
