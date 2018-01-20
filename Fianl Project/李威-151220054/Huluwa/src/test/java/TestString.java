import junit.framework.TestCase;

public class TestString extends TestCase{
    public void test() {
        String s="123 456 789";
        String[] arr=s.split(" ");
        assertEquals("456",arr[1]);
    }
}
