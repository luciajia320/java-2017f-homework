import nju.java.common.cell;
import nju.java.creature.COLOR;
import nju.java.creature.Huluwa;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SerializableTest implements Serializable{
    File file;
    @Before
    public void TestWrite(){
        ArrayList arrayList=new ArrayList();
        Huluwa h1=new Huluwa(new cell(1,1), COLOR.blue,null,null);
        Huluwa h2=new Huluwa(new cell(1,1),COLOR.red,null,null);
        arrayList.add(h1);
        arrayList.add(h2);
        try{
            fileChoose();
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(arrayList);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void TestRead(){
        try {
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            ArrayList arrayList=new ArrayList();
            arrayList.addAll((ArrayList)ois.readObject());
            Huluwa s=(Huluwa)arrayList.get(0);
            assert(s.getColor()==COLOR.blue);
            s=(Huluwa)arrayList.get(1);
            assert(s.getColor()==COLOR.red);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Ignore
    private void fileChoose() {
        File directory = new File("testDocument");
        if(!directory.exists()) {
            directory.mkdir();
        }
        Date cur = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd号 HH时mm分ss秒");
        file = new File("testDocument" + File.separator + simpleDateFormat.format(cur) + "-test.txt");

    }
}

class Student implements Serializable{
    public Student(int age){
        this.age=age;
    }
    public int age;
}
