package nju.java;

import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.Assert;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FieldTest {

    @Test
    public void loadSave() throws Exception {

        Field field = new Field();
        File file = new File("Best.txt");
        field.LoadSave(file);

        try {
            File unexist_file = new File("unexisted.txt");
            field.LoadSave(unexist_file);
            Assert.fail("Expected FileNotFoundException Didn't get");
        }
        catch (FileNotFoundException ex) {

        }

    }

    @Test
    public void showRecord() throws Exception {
        Field field = new Field();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.add(field);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(field.getBoardWidth() + 30,
                field.getBoardHeight() + 2 * 30);
        frame.setLocationRelativeTo(null);

        ArrayList<Record> records = new ArrayList<>();
        int x = 100;
        int y = 150;
        for ( int i = 0; i < 16; i++){
            if ( i % 5 == 0) x = 100;
            x += 100;
            y = i / 5 * 100 + 150;
            records.add( new Record(x, y,i%2==0) );
        }
        field.ShowRecord(records);

        try{
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }


    @Test
    public void replay() throws Exception{


    }

}