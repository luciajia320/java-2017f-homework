package nju.wz.creature;


import nju.wz.position.Field;
import nju.wz.position.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Review extends Player {

    public static boolean isReview = false;
    public static int[][] map = Field.map;

    public Review(Field field) {
        super(field);
    }

    @Override
    public void setPosition(Position position) {
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void report() {
    }

    @Override
    public void run() {
        try {
            //                BufferedReader reader = new BufferedReader(
            //                        new InputStreamReader(new FileInputStream(field.reviewFile)));
            isReview = true;
            BufferedReader br = new BufferedReader(new FileReader("review.txt"));
            //int[][] map = field.getMap();

            int number = 1;
            String line;
            String[] s;
            while(true) {
                line = br.readLine();
                if(line == null) {
                    break;
                }
                s = line.split(" ");
                if(number % 9 != 0) {
                    for(int i = 0; i < s.length; i++) {
                        map[number - 1][i] = Integer.valueOf(s[i]);
                        //System.out.print(Integer.valueOf(s[i]) + " ");
                    }
                    //System.out.println();
                }
                else {
                    field.reviewMap(map);
                    field.repaint();
                    Thread.sleep(new Random().nextInt(500) + 300);
                    number = 0;
                }
                number++;
            }
        }
        catch(Exception e) {
            //e.printStackTrace();
        }

    }
}
