package main.java.nju.game;

import main.java.nju.creatures.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Battle extends JFrame{
    public static final int MAX_X = 1620;   //最大宽度
    public static final int MAX_Y = 950;    //最大高度
    public static final int TIME_CLOCK = 100;   //线程休眠时间（毫秒）
    public static final int MOVE_PATH = 100;    //Creature每一步移动距离
    public static final int REPLAY_CLOCK = 1;
    public enum Status{
        Welcome, Fighting, RePlaying, End
    }

    public ArrayList<Creature> Justice = null;
    public ArrayList<Creature> Evil = null;
    public ArrayList<Creature> Dead = null;

    public Battlefield field = null;

    public static Status aGame = Status.Welcome;

    private Grandpa aGrandpa = null;
    private CalabashDoll[] Calabashdolls = null;
    private Scorpion aScorpion = null;
    private Soilder[] Soilders = null;

    private ArrayList<Thread> allThreads = null;
    private Timer theTimer ;
    private ActionListener timerTask ;

    public void set_Field(Battlefield field){
        this.field = field;
    }
    public Battlefield get_Field(){
        return this.field;
    }
    public Status get_Status(){
        return this.aGame;
    }

    private void StartGame(){
        //对爷爷初始化
        aGrandpa = new Grandpa(0,400, Creature.C_Type.grandpa,this);
        aGrandpa.set_Image(field.get_Grandpa_Image());

        //对葫芦娃初始化
        Calabashdolls = new CalabashDoll[7];
        for(int i = 0;i < 7;i++) {
            Calabashdolls[i] = new CalabashDoll(100, 100*(i+1), Creature.C_Type.calabashdoll, i+1, this);
            Calabashdolls[i].set_Image(field.get_CalabashDoll_Image(i));
        }

        //对蝎子精初始化
        aScorpion = new Scorpion(1500,400, Creature.C_Type.scorpion,this);
        aScorpion.set_Image(field.get_Scorpion_Image());

        //对小兵初始化
        Soilders = new Soilder[7];
        for(int i = 0;i < 7;i++) {
            Soilders[i] = new Soilder(1400, 100*(i+1), Creature.C_Type.soilder, this);
            Soilders[i].set_Image(field.get_Soilder_Image());
        }

        //加入队列
        Justice = new ArrayList<Creature>();
        for(CalabashDoll i : Calabashdolls){
            Justice.add(i);
        }
        Justice.add(aGrandpa);
        Evil = new ArrayList<Creature>();
        Evil.add(aScorpion);
        for(Soilder i : Soilders){
            Evil.add(i);
        }

    }

    public synchronized void Battle_Status(){
        if(aGame == Status.Fighting){
            if(Justice.isEmpty() || Evil.isEmpty()){
                aGame = Status.End;
                for(Thread i : allThreads)
                    i.suspend();
            }
        }
    }

    public void initTimer(int time){
        timerTask = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Battle_Status();
                field.repaint();
            }
        };
        theTimer = new Timer(time,timerTask);
        theTimer.start();
    }

    //对所有线程初始化
    public void initThread() {

        StartGame();

        allThreads = new ArrayList<Thread>();
        if(!Justice.isEmpty()){
            for (Creature i : Justice) {
                allThreads.add(new Thread(i));
                i.set_Thread(allThreads.get(allThreads.size() - 1));
            }
        }

        if(!Evil.isEmpty()){
            for (Creature i : Evil) {
                allThreads.add(new Thread(i));
                i.set_Thread(allThreads.get(allThreads.size() - 1));
            }
        }
        for (Thread i : allThreads)
            i.start();
    }

}
