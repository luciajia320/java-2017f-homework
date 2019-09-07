package nju.java;

import java.util.Random;

public class Reshow implements Runnable {
    private Field field;
    Reshow(Field field){
        this.field=field;
    }
    public void run() {
        while(true) {
            Random rand = new Random();
            try {
                if (field.getReshowed()){
                    Thread.sleep(rand.nextInt(200)+200);
                    this.field.repaint();
                }
                else{
                    Thread.sleep(2000);
                }

            } catch (Exception e) {

            }
        }
    }
}
