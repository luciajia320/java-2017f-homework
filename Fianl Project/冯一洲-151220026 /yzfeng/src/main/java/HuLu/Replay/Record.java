package HuLu.Replay;

import HuLu.Creature.BadMan;
import HuLu.Creature.Creature;
import HuLu.Creature.GoodMan;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Record {
    private static ArrayList<Item> items;

    public Record(){
        items = new ArrayList<Item>();
    }
    public void makeRecord(ArrayList<GoodMan> goodMEN, ArrayList<BadMan>  badMEN){
        for(int i = 0; i<goodMEN.size();i++) {
            Creature c = goodMEN.get(i);
            String isAlive;
            if(c.isAlive())
                isAlive = "true";
            else
                isAlive = "false";
            items.add(new Item(c.getId(), c.x(), c.y(), isAlive));
        }
        for(int i = 0; i<badMEN.size();i++) {
            Creature c;
            c = badMEN.get(i);
            String isAlive;
                if(c.isAlive())
                    isAlive = "true";
                else
                    isAlive = "false";
                items.add(new Item(c.getId(), c.x(), c.y(), isAlive));
        }

    }

    public static void writeFile(){
        try{
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar calendar = Calendar.getInstance();
            String filename = df.format(calendar.getTime());

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Record/" + filename, true),"UTF16"));
            for(Item c: items)
            {
                bw.write(c.x() + " " + c.y() +" "+ c.isAlive());
                bw.newLine();
            }
            bw.close();
      }catch(IOException e){
            e.printStackTrace();
        }
    }
}
