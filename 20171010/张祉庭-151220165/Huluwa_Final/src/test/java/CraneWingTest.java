

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* CraneWing Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 31, 2017</pre> 
* @version 1.0 
*/ 
public class CraneWingTest { 

    map bm;
    CraneWing fm;
@Before
public void before() throws Exception {
    bm=new map();
    fm=new CraneWing(bm);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: set_map() 
* 
*/ 
@Test
public void testSet_map() throws Exception { 
//TODO: Test goes here...
    assertEquals(false,bm.positions[3][4].isHolderNull());
    assertEquals(true,fm.positions[4][1].isHolderNull());
    assertEquals(COLOR.CHI,bm.positions[3][3].ReturnHolder().getColor());
    assertEquals(COLOR.BLACK,fm.positions[9][7].ReturnHolder().getColor());
} 


} 
