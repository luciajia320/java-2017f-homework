package GroundDollMaven.testGame;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public void test(){
    	boolean succ=App.saveRecordInFile("yes!", "./file/log.txt");
    	assertEquals(succ,true);
    }
    public static void main(String args[]){ 
        junit.textui.TestRunner.run(AppTest.class);    }
}
