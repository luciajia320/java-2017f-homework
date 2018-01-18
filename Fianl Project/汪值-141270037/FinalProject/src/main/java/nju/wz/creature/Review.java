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
            isReview = true;
            BufferedReader br = new BufferedReader(new FileReader("review.txt"));
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
                    }
                }
                else {
                    field.reviewMap(map);
                    field.repaint();
                    Thread.sleep(new Random().nextInt(200) + 100);
                    number = 0;
                }
                number++;
            }
            isReview = false;
        }
        catch(Exception e) {
        }
    }
}
