import java.util.Random;
import java.io.*;

public class Referee extends Speices implements Runnable {

    private int win;
    private int lose;
    public Referee(int x, int y, Fight fight, String pic) {
        super(x, y, fight, pic);
        win = -1;
        lose = -1;
    }

    public void run() {

        while (!Thread.interrupted()) {
            try {
                if (this.fight.enemyAllDead() == true)
                    this.win = 1;
                else if (this.fight.friendsAllDead() == true)
                    this.lose = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public int getWin() {
        return this.win;
    }
    public int getLose() {
        return this.lose;
    }
}