package nju.java;

import java.io.FileOutputStream;
import java.io.IOException;

public class LittleUtils {
    public static int distance(Creature c1, Creature c2){
        int w = c1.x() - c2.x();
        int h = c1.y() - c2.y();
        return (int)Math.sqrt(w * w + h * h);
    }

    public static boolean distanceTooClose(Creature c1, Creature c2){
        return distance(c1, c2) < 50;
    }

    public static FileOutputStream out;

    public static void record(String str){
        try {
            out.write(str.getBytes(), 0, str.length());
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
