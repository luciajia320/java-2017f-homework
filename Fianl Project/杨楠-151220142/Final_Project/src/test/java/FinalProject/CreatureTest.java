package FinalProject;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CreatureTest {
	@BeforeClass
	public static void beforeClass() {
		Level l = new Level();
	}

	@Ignore
	public void testOnestep() {
		Creature c = new Creature();
		c.setposition(1, 6);
		c.onestep();
		if(Stage.ground_above[1][7].gettype()!=c.gettype())
			fail();
		assertEquals(c.getdirection(),2);
	}

	@Ignore
	public void testUnable_move() {
		if(new Creature().unable_move(0,0,0,1)!=0)
			fail();
	}

}
