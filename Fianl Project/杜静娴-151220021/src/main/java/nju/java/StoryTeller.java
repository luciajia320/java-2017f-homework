package nju.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StoryTeller implements Runnable {
    ArrayList <Player> list;
    Field field;
    public StoryTeller(Field field, ArrayList<Player> list) {
        this.list = list;
        this.field = field;
    }
    public void run() {
        int t = -1;
        while(!Thread.interrupted()) {
            System.out.println(new Date().toString());
            if (!Logger.load(field, list, ++t))
                return;
            field.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
