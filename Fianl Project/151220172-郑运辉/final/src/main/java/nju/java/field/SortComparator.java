package nju.java.field;

import nju.java.creature.Thing2D;

import java.util.Comparator;

public class SortComparator implements Comparator {
    public int compare(Object o1,Object o2){
        Thing2D t1=(Thing2D)o1;
        Thing2D t2=(Thing2D)o2;
        if(t1.y()>t2.y())
            return 1;
        else if(t1.y()<t2.y())
            return -1;
        else{
            if(t1.x()>t2.x())
                return 1;
            else
                return -1;
        }
    }
}