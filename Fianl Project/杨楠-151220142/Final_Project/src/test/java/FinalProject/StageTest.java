package FinalProject;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class StageTest {
	@BeforeClass
	public static void beforeClass() {
		Level l = new Level();
	}
	@Before
	public void before() {
		Stage.ground_above[0][5].settype(1);
		for(int i=0;i<Huluwa.getkinds();i++)
			Stage.ground_above[2][2+i].settype(8);
	}
	@Ignore
	public void testNot_complete() {
		int z = 0;
		if(new Stage(20,11).not_complete())
			z = 1;
		assertEquals(0,z);
	}

}
