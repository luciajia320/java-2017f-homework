package nju.hulu;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

public class TestWithinRow extends TestCase {
	Field field;
	@Before
	 public void setUp() {
		field=new Field();
    }
	
	@Test 
	public void testwithinRow()
	{
		
		
		for (int i=1;i<=10;i++)
		{	
		assert(field.grandpa.withinRow(i*7-1,1)==false);
		}
		for (int i=2;i<=10;i++)
		{	
		assert(field.grandpa.withinRow((i-1)*7,-1)==false);
		}
	
		
	}
	/*
	@Test
	@Ignore
	public void testStartFight() throws InterruptedException
	{
		//field.restartLevel();
		for (int i=0;i<100;i++)
		{
			field.restartLevel();
			System.out.println(i);
			if(!field.isStarted())
			{
				field.startFight();
			}
			while(!field.isCompleted())
			{
				Thread.sleep(200);
			}
				
		}
	}*/
	
}
