//package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

//import finalproject.Field;
public class TestProject {
	@Test
	public void testformation() {
		assertEquals(true,new Field().changshe(0, 0, true));
		assertEquals(true,new Field().YanXing(10, 15, false));
	}
}
