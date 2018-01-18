package com.gxs;

import junit.framework.*;
import java.util.ArrayList;

public class TestCalaBro extends TestCase
{
	ArrayList<CalaBro>calaBro=CalaBro.getAcess();
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		System.out.println("setup");
	}
	
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
		System.out.println("teardown");
	}
	
	public void testImg()
	{
		for( CalaBro c : calaBro)
			assertNotNull(c.getImage());
	}
}