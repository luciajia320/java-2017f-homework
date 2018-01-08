package Creature;

import UI.Field;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class FieldTest {
    @Test
    public void testinitWorld(){
        Field f=new Field();
        assertEquals(f.getCol()*5+60,f.getBoardWidth());
    }
}
