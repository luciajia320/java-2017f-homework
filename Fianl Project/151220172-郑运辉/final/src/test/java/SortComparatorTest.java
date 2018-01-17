import nju.java.common.cell;
import nju.java.creature.COLOR;
import nju.java.creature.Huluwa;
import nju.java.field.Field;
import nju.java.field.SortComparator;
import org.junit.Test;



import java.util.ArrayList;


import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SortComparatorTest {
    @Test
    public void testCompara(){
        Field field=new Field();
        Huluwa test=new Huluwa(new cell(0,1), COLOR.red,field,null);
        Huluwa test2=new Huluwa(new cell(1,0),COLOR.orange,field,null);
        Huluwa test3=new Huluwa(new cell(3,3),COLOR.yellow,field,null);
        Huluwa test4=new Huluwa(new cell(2,4),COLOR.green,field,null);
        Huluwa test5=new Huluwa(new cell(4,2),COLOR.blue,field,null);
        Huluwa test6=new Huluwa(new cell(6,7),COLOR.indogo,field,null);
        Huluwa test7=new Huluwa(new cell(5,5),COLOR.purple,field,null);
        ArrayList<Huluwa> HuluwaList=new ArrayList<Huluwa>();
        HuluwaList.add(test);
        HuluwaList.add(test2);
        HuluwaList.add(test3);
        HuluwaList.add(test4);
        HuluwaList.add(test5);
        HuluwaList.add(test6);
        HuluwaList.add(test7);

        Collections.sort(HuluwaList,new SortComparator());
        for (int i=0;i<6;i++){
            assert(HuluwaList.get(i).position.getY()<=HuluwaList.get(i+1).position.getY());
            if(HuluwaList.get(i).position.getY()==HuluwaList.get(i+1).position.getY())
                assert(HuluwaList.get(i).position.getX()<=HuluwaList.get(i+1).position.getX());
        }
    }

}
