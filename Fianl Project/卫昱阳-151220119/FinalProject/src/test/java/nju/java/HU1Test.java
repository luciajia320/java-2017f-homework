package nju.java;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yuyang Wei on 2018/1/5.
 */
public class HU1Test {

    @Test
    public void TestMove()throws Exception{
        Field field=new Field();
        int x=50;
        int y=50;
        Grandfather grandfather=new Grandfather(0,0,field);
        grandfather.move(x,y);
        assertEquals(grandfather.x(),50);
        assertEquals(grandfather.y(),50);
    }

}