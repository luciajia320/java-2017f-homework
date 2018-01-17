package HuLu.Field;

import HuLu.Creature.BadMan;
import HuLu.Creature.Creature;
import HuLu.Creature.GoodMan;
import HuLu.Creature.HuLuWa;
import HuLu.Replay.Record;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class God implements Runnable {
    private Field field;
    private ArrayList<GoodMan> goodMEN;
    private ArrayList<BadMan>badMEN;
    private int goodAlive;
    private int badAlive;
    private Record recorder;

    public God(Field field, Record recorder){
        this.field = field;
        goodMEN = field.getGoodMEN();
        badMEN = field.getBadMEN();
        this.recorder = recorder;
    }

    public int getAliveNum(){
        int num = 0;
        for(Creature c : goodMEN ){
            if(c instanceof HuLuWa && c.isAlive() == false)
                num++;
        }
        if(num == 7)
            return 1;

        num = 0;
        for(Creature c : badMEN){
            if(c.isAlive() == false)
                num++;
        }
        if(num == 8)
            return 2;

        return 0;
    }
    public synchronized void run(){
        field.repaint();
        recorder.makeRecord(field.getGoodMEN(), field.getBadMEN());
        if(getAliveNum() == 1 || getAliveNum() == 2) {
            field.setGameState(GameState.END);
            field.endGame();
            return;
        }

        for (Creature c : goodMEN) {
            //已经死了？
            if (!c.isAlive()) continue;
            //还活着
            //看四周有没有敌人？
            int k = c.meetEnemy();
            //没有敌人
            if (k == -1) {
                //下一步去哪？
                int rand = new Random().nextInt(10);
                //随便走吧
                if (rand >= 8) {
                    int nextstep = new Random().nextInt(4);
                    c.getNext(nextstep);
                }
                //还是去找敌人
                else {
                    c.findnearest();
                }
            } else {
                //打一架
                c.attackAt(k);
            }
        }

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
