package huluwa;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestBattle {
    @Test
    public void testBattleFormation() {
        Battle b = new Battle();
        assertTrue(b.getCreatures().get(b.getMap()[0][2]).getName().equals(new Grandpa().getName()));

        // what a concealed error!!!
        // my comment: scorpion king:
        //     map[0][8] = 8;
        // thanks to JUnit Test.
//        assertTrue(b.getCreatures().get(b.getMap()[0][8]).getName().equals(new ScorpionKing().getName()));

        assertTrue(b.getCreatures().get(b.getMap()[0][8]).getName().equals(new SnakeQueen().getName()));
    }

}
