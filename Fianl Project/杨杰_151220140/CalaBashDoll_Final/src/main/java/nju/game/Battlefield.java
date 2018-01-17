package main.java.nju.game;

import main.java.nju.creatures.Creature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Iterator;

public class Battlefield extends JPanel{
    private ImageIcon Background_Image = null;
    private ImageIcon[] CalabashDoll_Image = null;
    private ImageIcon Grandpa_Image = null;
    private ImageIcon Soilder_Image = null;
    private ImageIcon Scorpion_Image = null;
    private ImageIcon Dead_Image = null;
    private ImageIcon RIP_Image = null;

    private Battle battle = null;

    public Battlefield(Battle battle){
        this.battle = battle;
        setFocusable(true);
        addKeyListener(new TAdapter());

        Background_Image = new ImageIcon("images/background.jpg");

        Grandpa_Image = new ImageIcon("images/grandpa.png");
        Soilder_Image = new ImageIcon("images/soilder.png");
        Scorpion_Image = new ImageIcon("images/scorpion.png");
        Dead_Image = new ImageIcon("images/dead.png");
        RIP_Image = new ImageIcon("images/rip.png");
        CalabashDoll_Image = new ImageIcon[7];
        CalabashDoll_Image[0] = new ImageIcon("images/1.png");
        CalabashDoll_Image[1] = new ImageIcon("images/2.png");
        CalabashDoll_Image[2] = new ImageIcon("images/3.png");
        CalabashDoll_Image[3] = new ImageIcon("images/4.png");
        CalabashDoll_Image[4] = new ImageIcon("images/5.png");
        CalabashDoll_Image[5] = new ImageIcon("images/6.png");
        CalabashDoll_Image[6] = new ImageIcon("images/7.png");

    }

    public ImageIcon get_Background_Image(){
        return Background_Image;
    }

    public ImageIcon get_CalabashDoll_Image(int number){
        return CalabashDoll_Image[number];
    }

    public ImageIcon get_Grandpa_Image(){
        return Grandpa_Image;
    }

    public ImageIcon get_Soilder_Image(){
        return Soilder_Image;
    }

    public ImageIcon get_Scorpion_Image(){
        return Scorpion_Image;
    }

    class TAdapter extends KeyAdapter{

        public synchronized void keyPressed(KeyEvent event){
            int key = event.getKeyCode();
            if(key == KeyEvent.VK_SPACE && Battle.aGame == Battle.Status.Welcome){
                Battle.aGame = Battle.Status.Fighting;
                battle.initThread();
                battle.initTimer(Battle.TIME_CLOCK);
            }
        }
    }

    private synchronized void paintImage(Graphics g){
        for(int i = 0;i < 1600; i += 100){
            for(int j = 0; j < 900; j += 100){
                g.drawImage(Background_Image.getImage(),i,j,100,100,this);
            }
        }
        if( battle.aGame == Battle.Status.Welcome ) {
            for (int i = 0; i < 7; i++) {
                g.drawImage(CalabashDoll_Image[i].getImage(), 100, 100 * (i + 1), 100, 100, this);
            }
            g.drawImage(Grandpa_Image.getImage(), 0, 400, 100, 100, this);
            for (int i = 100; i < 800; i += 100) {
                g.drawImage(Soilder_Image.getImage(), 1400, i, 100, 100, this);
            }
            g.drawImage(Scorpion_Image.getImage(), 1500, 400, 100, 100, this);

        }

        if( battle.aGame == Battle.Status.Fighting ) {

            if (!battle.Justice.isEmpty()) {
                for (Creature i : battle.Justice) {
                    if (i.get_LiveStatus() == true) {
                        g.drawImage(i.get_Image().getImage(), i.get_Pos_X(), i.get_Pos_Y(), 100, 100, this);
                        boolean res = i.Attack(battle.Evil);
                        if(res == true){
                            battle.aGame = Battle.Status.End;
                            //g.drawString("游戏结束", 40, 120);
                        }
                    }

                    if (i.get_LiveStatus() == false) {
                        g.drawImage(RIP_Image.getImage(), i.get_Pos_X(), i.get_Pos_Y(), 100, 100, this);
                    }
                }
            }
            if (!battle.Evil.isEmpty()) {
                for (Creature i : battle.Evil) {
                    if (i.get_LiveStatus() == true) {
                        g.drawImage(i.get_Image().getImage(), i.get_Pos_X(), i.get_Pos_Y(), 100, 100, this);
                        boolean res = i.Attack(battle.Justice);
                        if(res == true) {
                            battle.aGame = Battle.Status.End;
                        }
                    }
                    else{
                        g.drawImage(Dead_Image.getImage(), i.get_Pos_X(), i.get_Pos_Y(), 100, 100, this);
                    }
                }

            /*
            if(!battle.Dead.isEmpty()){
                for(Creature i : battle.Dead){
                    g.drawImage(Dead_Image.getImage(),i.get_Pos_X(),i.get_Pos_Y(),100,100,this);
                }
            }
            */
                int num = 0;
                for (Creature i : battle.Justice) {

                    if(i.get_LiveStatus() == false){
                        num++;
                    }
                    if(i.get_Type() == Creature.C_Type.grandpa) {
                        if(i.get_LiveStatus() == false) {
                            battle.aGame = Battle.Status.End;
                            g.drawString("游戏结束，蝎子精获胜", 0, 0);
                        }
                    }
                }
                if(num == 7){
                    battle.aGame = Battle.Status.End;
                    g.drawString("游戏结束，蝎子精获胜", 0, 0);
                }

                int num2 = 0;
                for (Creature i : battle.Evil) {
                    if(i.get_LiveStatus() == false){
                        num2++;
                    }
                    if(i.get_Type() == Creature.C_Type.scorpion) {
                        if(i.get_LiveStatus() == false) {
                            battle.aGame = Battle.Status.End;
                            g.drawString("游戏结束，葫芦娃获胜", 0, 0);
                        }
                    }
                }
                if(num == 7){
                    battle.aGame = Battle.Status.End;
                    g.drawString("游戏结束，葫芦娃获胜", 0, 0);
                }


                if (battle.Justice.isEmpty() || battle.Evil.isEmpty()) {
                    battle.aGame = Battle.Status.End;
                }
            }
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        paintImage(g);
    }
}
