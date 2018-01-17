import org.junit.Test;

/** 
* QuickSort Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 1, 2018</pre> 
* @version 1.0 
*/ 
public class QuickSortTest {

    @Test
    public void testSort() throws Exception {
        class item implements Comparable
        {
            private int value;
            public item(int i)
            {
                value = i;
            }
            int getValue()
            {
                return this.value;
            }
            public boolean biggerthan(Comparable anathor)
            {
                if(anathor instanceof item)
                {
                    return ((item) anathor).getValue() < this.getValue();
                }
                else
                {
                    System.out.print("Comparing Creature item to other objects.error.\n");
                    return false;
                }
            }
        }
        QuickSort qs  = new QuickSort();
        item[] items = new item[6];
        items[0] = new item(1);
        items[1] = new item(5);
        items[2] = new item(4);
        items[3] = new item(3);
        items[4] = new item(6);
        items[5] = new item(2);
        qs.Sort(items);
        for(int i = 1 ;i<items.length;i++)
        {
            assert items[i].biggerthan(items[i-1]);
        }
    }
}
