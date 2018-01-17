import Creature.*;
import Formation.*;
import Ground.BattleGround;
import Ground.Position;
import Exception.TooCrowdedException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BattleGroundTest {
    private static BattleGround battleGround = new BattleGround(15,10);

    @Before
    public void setUP(){ battleGround.initial(); }

    //Test function layout(Creature, Position)
    @Test(expected = Exception.class)
    public void c_layoutExceptionTest1() throws TooCrowdedException {
        Monster m1 = new Monster();
        battleGround.layout(m1, new Position(22,5));
        fail("放置Creature越界未抛出异常！");
    }

    @Test(expected = Exception.class)
    public void c_layoutExceptionTest2() throws TooCrowdedException {
        Monster m1 = new Monster();
        battleGround.layout(m1, new Position(2,5));
        fail("放置Creature冲突未抛出异常！");
    }

    @Test(expected = Exception.class)
    public void c_layoutExceptionTest3() throws TooCrowdedException {
        Creature m1 = battleGround.getCreatures().get(0);
        battleGround.layout(m1, new Position(2,5));
        fail("重复放置相同Creature未抛出异常！");
    }

    //Test function layout(Formation, Position)
    @Test(expected = Exception.class)
    public void f_layoutExceptionTest1() throws TooCrowdedException {
        Monster[] m1 = new Monster[9];
        for(int i = 0; i < m1.length; i++){
            m1[i] = new Monster();
        }
        SnakeFormation sf = new SnakeFormation(m1);
        battleGround.layout(sf, new Position(22,5));
        fail("放置Formation越界未抛出异常！");
    }

    @Test(expected = Exception.class)
    public void f_layoutExceptionTest2() throws TooCrowdedException {
        Formation f = battleGround.getFormations().get(0);
        battleGround.layout(f, new Position(2,5));
        fail("重复放置Formation未抛出异常！");
    }

    @Test(expected = Exception.class)
    public void f_layoutExceptionTest3() throws TooCrowdedException {
        Monster[] m1 = new Monster[9];
        for(int i = 0; i < m1.length; i++){
            m1[i] = new Monster();
        }
        SnakeFormation sf = new SnakeFormation(m1);
        battleGround.layout(sf, new Position(4,2));
        fail("放置Formation冲突未抛出异常！");
    }

    @Test
    //Test function conflicts(Position position)
    public void creature_conflictTest(){
        Monster m = new Monster();
        m.setPosition(new Position(2,5));
        assertTrue(battleGround.conflicts(m.getPosition()));
    }

    @Test
    //Test function conflicts(Formation formation)
    public void formation_conflictTest(){
        Monster[] m1 = new Monster[7];
        for(int i = 0; i < m1.length; i++){
            m1[i] = new Monster();
        }
        SnakeFormation sf = new SnakeFormation(m1);
        sf.setPosition(new Position(2,5));
        assertTrue(battleGround.conflicts(sf));
    }

    @Test
    //Test function alreadyLaid(Creature creature)
    public void c_alreadyLaidTest(){
        Creature c = battleGround.getCreatures().get(0);
        assertTrue(battleGround.alreadyLaid(c));
    }

    @Test
    //Test function alreadyLaid(Formation formation)
    public void f_alreadyLaidTest(){
        Formation f = battleGround.getFormations().get(0);
        assertTrue(battleGround.alreadyLaid(f));
    }


}
