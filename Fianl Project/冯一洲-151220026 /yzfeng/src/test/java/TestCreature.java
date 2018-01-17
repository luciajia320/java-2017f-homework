import HuLu.Creature.BadMan;
import HuLu.Creature.GoodMan;
import HuLu.Creature.HuLuWa;
import HuLu.Creature.Snake;
import HuLu.Field.Field;
import HuLu.Formation.Holder;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCreature {
    Holder S[][] = new Holder[4][4];
    {
        for(int i =0; i< 4;i++)
            for(int j = 0; j<4;j++)
                S[i][j] = new Holder();
    }
    HuLuWa huluwa = new HuLuWa(0, null, null);
    Snake snake = new Snake(1, null, null);

    @Test
   public void empty1(){
        assertEquals(S[2][3].isEmpty(), true);
    }

    @Test
    public void empty2(){
        S[2][2].setItem(new GoodMan(0, null, null));
        assertEquals(S[2][2].isEmpty(), false);
    }

    @Ignore
    public void meetEnemy(){
        S[0][0].setItem(huluwa);
        S[0][1].setItem(snake);
        assertEquals(huluwa.meetEnemy(), true);
    }
}


