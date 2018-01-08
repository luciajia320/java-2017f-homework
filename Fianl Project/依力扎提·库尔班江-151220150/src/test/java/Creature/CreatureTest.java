package Creature;

import UI.Field;
import creature.Creature;
import creature.Name;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreatureTest {
    @Test
    public void testSpeak(){
        Creature c=new Creature(1,1,new Field(), Name.一,1,1);
        assertEquals(true,c.speak().equals("一"));
    }
}
