package test.java;

import main.java.nju.java.Creature;
import main.java.nju.java.Field;
import main.java.nju.java.FileNotExistException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FieldTest {
    @Test(expected = Exception.class)
    public void testReadRecord(){
        Creature[] creatures = new Creature[3];
        new Field(10, creatures).readRecord(null);
    }

    @Test(expected = FileNotExistException.class)
    public void testTraceBack() throws FileNotExistException{
        Creature[] creatures = new Creature[3];
        new Field(10,creatures).traceBack();
    }

    @Test(expected = FileNotExistException.class)
    public void testTraceForward() throws FileNotExistException{
        Creature[] creatures = new Creature[3];
        new Field(10,creatures).traceForward();
    }
}
