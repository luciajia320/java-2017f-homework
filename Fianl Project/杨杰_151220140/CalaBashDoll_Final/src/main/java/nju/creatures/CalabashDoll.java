package main.java.nju.creatures;

import java.util.Random;

import main.java.nju.game.*;

import java.util.ArrayList;

public class CalabashDoll extends Creature {
    private int number;
    public CalabashDoll(int x,int y,C_Type type,int number,Battle battle){
        super(x,y,type,battle);
        this.number = number;
    }

    public int get_Number(){
        return this.number;
    }

    public void Walk(){
        if(this.get_LiveStatus() == true){
            Random random = new Random();
            int s = random.nextInt(2)%(2) + 1;
            if(s == 1){
                if(this.get_Pos_X() < 1500){
                    this.set_Pos_X(get_Pos_X()+100);
                }
                 else{
                   if(this.get_Pos_Y() < 400){
                      this.set_Pos_Y(get_Pos_Y()+100);
                   }
                  else{
                      this.set_Pos_Y(get_Pos_Y()-100);
                  }
              }
            }
        }
    }

    public boolean Attack(ArrayList<Creature> Evil){
        for(Creature e : Evil) {
            if (e.get_LiveStatus() == true) {
                //if (e.get_Type() == C_Type.soilder) {
                    if ((e.get_Pos_Y() == this.get_Pos_Y()) && (Math.abs(e.get_Pos_X() - this.get_Pos_Y()) <= 100)) {
                        Random random = new Random();
                        int s = random.nextInt(2) % (2) + 1;
                        if (s == 1) {
                            e.kill();
                        } else
                            this.kill();
                    }

                //}
                if (e.get_Type() == C_Type.scorpion) {
                    if ((e.get_Pos_X() == this.get_Pos_Y()) && (Math.abs(e.get_Pos_Y() - this.get_Pos_Y()) <= 100)) {
                        e.kill();
                        return true;
                    }

                }
            }

        }
        return false;
    }

    public void run() {

        while (!Thread.interrupted()) {
            if (Battle.aGame == Battle.Status.Fighting) {
                try {
                    if (!this.get_LiveStatus()|| Battle.aGame != Battle.Status.Fighting) {

                        Thread.sleep(Battle.TIME_CLOCK);
                        continue;
                    }

                    Walk();



                    Thread.sleep(Battle.TIME_CLOCK);

                } catch (Exception e) {

                }
            } else if (Battle.aGame == Battle.Status.RePlaying) {

                try {
                    Thread.sleep(0,Battle.REPLAY_CLOCK);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }

}
