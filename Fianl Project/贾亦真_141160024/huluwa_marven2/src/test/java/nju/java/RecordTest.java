package nju.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecordTest {
    @Test
    public void Test1(){
        Record r = new Record(1,2,true);
        System.out.println(r.x);
        System.out.println(r.y);
        System.out.println(r.isAlive);
    }

}