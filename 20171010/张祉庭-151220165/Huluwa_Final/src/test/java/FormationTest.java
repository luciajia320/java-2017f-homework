//package test.;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* Formation Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 31, 2017</pre> 
* @version 1.0 
*/ 
public class FormationTest {
    map bm;
    Formation fm;
@Before
public void before() throws Exception {
    bm=new map();
    fm=new Formation(bm);
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
    assertEquals(true,fm.positions[9][7].isHolderNull());
} 

/** 
* 
* Method: report_map() 
* 
*/ 
@Test
public void testReport_map() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: sort() 
* 
*/ 
@Test(timeout = 2000)
public void testSort() throws Exception { 
//TODO: Test goes here... 
} 


} 
