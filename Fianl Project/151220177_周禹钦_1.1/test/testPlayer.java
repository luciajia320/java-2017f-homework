package test;

/**
 * Created by qin on 18.1.5.
 */
import junit.framework.TestCase;
import main.creature.GrandFa;
import main.play.Player;
public class testPlayer extends TestCase{
    Player testplayer;
    @Override
    protected void setUp()throws Exception{
        super.setUp();
        testplayer=new Player(0,0,null,new GrandFa());
        System.out.println("setUp.");
    }


    @Override
    protected void tearDown()throws Exception{
        super.tearDown();
        System.out.println("testReportAndReplay tearDown.");
    }

    public void testMove(){

        System.out.print("test move()");
        testplayer.move(20,15);
        assertEquals(20,testplayer.x());
        assertEquals(15,testplayer.y());
        System.out.println("  pass.");

        System.out.print("test move() reject unusual move");
        testplayer.move(50,60);
        assertNotSame(50,testplayer.x());
        assertNotSame(60,testplayer.y());
        System.out.println("  pass.");

        System.out.print("test Dead()");
        testplayer.Dead();
        assertEquals(false,testplayer.Alive());
        System.out.println("  pass.");


        System.out.print("test move() when dead");
        testplayer.move(50,40);
        assertNotSame(50,testplayer.x());
        assertNotSame(40,testplayer.y());
        System.out.println("  pass.");

    }

}
