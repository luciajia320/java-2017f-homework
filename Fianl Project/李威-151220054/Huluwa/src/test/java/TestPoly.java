import junit.framework.TestCase;

public class TestPoly extends TestCase {
    public void test(){
        Thing2D creature=new HuLuWa(0,0,null,3);
        assertEquals(3,creature.getRank());//多态，没毛病

    }

}
