package nju.java;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class GroundTest {
    Player player;
    @Before
    public void f(){
        player=new Player(10,10,new Field());
    }

    @Test
    public void distance(){
        Player p=new Player(20,20,new Field());
        assertEquals(200,player.distance(p));
    }
}
