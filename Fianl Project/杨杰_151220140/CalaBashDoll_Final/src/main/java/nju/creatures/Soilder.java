package main.java.nju.creatures;

import main.java.nju.game.Battle;

import java.util.Random;

import java.util.ArrayList;

public class Soilder extends Creature {
    public Soilder(int x, int y, C_Type type, Battle battle) {
        super(x, y, type, battle);
    }

    public void Walk() {
        if (this.get_LiveStatus() == true) {
            Random random = new Random();
            int st = random.nextInt(2) % (2) + 1;
            if (st == 1) {
                if (this.get_Pos_X() > 0) {
                    this.set_Pos_X(get_Pos_X() - 100);
                } else {
                    if (this.get_Pos_Y() <= 400) {
                        this.set_Pos_Y(get_Pos_Y() + 100);
                    } else {
                        this.set_Pos_Y(get_Pos_Y() - 100);
                    }
                }
            }
        }
    }

    public void run() {
        while (!Thread.interrupted()) {
            if (Battle.aGame == Battle.Status.Fighting) {
                try {
                    if (!this.get_LiveStatus() || Battle.aGame != Battle.Status.Fighting) {

                        Thread.sleep(Battle.TIME_CLOCK);
                        continue;
                    }

                    Walk();


                    Thread.sleep(Battle.TIME_CLOCK);

                } catch (Exception e) {

                }
            } else if (Battle.aGame == Battle.Status.RePlaying) {

                try {
                    Thread.sleep(0, Battle.REPLAY_CLOCK);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean attack(ArrayList<Creature> Evil) {
        for(Creature e : Evil) {
            if (e.get_Type() == C_Type.grandpa) {
                if ((e.get_Pos_X() == this.get_Pos_Y()) && (Math.abs(e.get_Pos_Y() - this.get_Pos_Y()) <= 100)) {
                    e.kill();
                    return true;
                }

            }
        }
        return false;
    }
}