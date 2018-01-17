package HuLu.Replay;

import HuLu.Creature.Creature;
import HuLu.Creature.Thing2D;
import HuLu.Field.Field;
import HuLu.Field.GameState;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Replay implements Runnable{
    private ArrayList<Item>items = new ArrayList<Item>();

    private Field field;
    private String filename;

    public Replay(Field field, String filename){
        this.field = field;
        this.filename = filename;
        readFile();
    }

    void readFile(){
        try {
            FileInputStream inputStream = null;
            Scanner sc = null;
            String str;
            inputStream = new FileInputStream(filename);
            sc = new Scanner(inputStream, "UTF16");
            int i = 0;
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                String[] state = str.split(" ");
                if(state[0] == null || state[1] == null || state[2] ==null) break;
                items.add(new Item(i, new Integer(state[0]), new Integer(state[1]), state[2]));
                i = (i+1)% 16;
            }
            sc.close();

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
        }
    }


    public ArrayList<Item> getItems(){
        return items;
    }

    public void run(){
        while (!Thread.currentThread().isInterrupted() && field.getGameState() == GameState.REPLAY)
        {
            field.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
