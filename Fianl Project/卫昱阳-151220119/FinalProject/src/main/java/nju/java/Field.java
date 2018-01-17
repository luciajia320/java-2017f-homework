package nju.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Yuyang Wei on 2017/12/21.
 */
public class Field extends JPanel{
    //static savePic savepic=new savePic();
    static int Pic_count=0;
    private final int OFFSET=30;
    private final int SPACE=50;
    private static char record[][]=new char[13][13];
    private static char load[][]=new char[13][13];
    private ArrayList Grass=new ArrayList();
    private ArrayList Cloud=new ArrayList();
    private ArrayList TombStone=new ArrayList();

    boolean loadMode=false;
    PlayBack playback;
    private Grandfather grandfather;
    private Snake snake;
    private HU1 hu1;
    private HU2 hu2;
    private HU3 hu3;
    private HU4 hu4;
    private HU5 hu5;
    private HU6 hu6;
    private HU7 hu7;
    private Xiezi xiezi;
    private Toad toad58;
    private Toad toad78;
    private Toad toad47;
    private Toad toad87;
    private Toad toad56;
    private Toad toad76;
    private Toad toad65;

    //TODO
    private int w=0;
    private int h=0;

    private boolean completed=false;

    private char level[][]=new char[13][13];
                    //row==13;col==13

    public Field() throws FileNotFoundException {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public ArrayList Cloud(){return Cloud;}

    public ArrayList TombStone(){return TombStone;}

    public ArrayList Grass(){return Grass;}

    public Grandfather Grandfather(){return grandfather;}

    public Snake Snake(){return snake;}

    public HU1 HU1(){return hu1;}

    public HU2 HU2(){return hu2;}

    public HU3 HU3(){return hu3;}

    public HU4 HU4(){return hu4;}

    public HU5 HU5(){return hu5;}

    public HU6 HU6(){return hu6;}

    public HU7 HU7(){return hu7;}

    public Xiezi Xiezi(){return xiezi;}

    public Toad Toad58(){return toad58;}

    public Toad Toad78(){return toad78;}

    public Toad Toad47(){return toad47;}

    public Toad Toad87(){return toad87;}

    public Toad Toad56(){return toad56;}

    public Toad Toad76(){return toad76;}

    public Toad Toad65(){return toad65;}

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    /*
      Huluwa for 1 2 3...
      Grandfather for G
      Xiezi for X
      Snake for S
      Toad for T
      tombstone for D
    */
    public final void initWorld(){
        Pic_count=0;
        for(int i=0;i<13;i++){
            for(int j=0;j<13;j++){
                this.level[i][j]='.';
            }
        }
        this.level[6][0]='G';
        this.level[6][11]='S';
        this.level[3][2]='1';
        this.level[4][2]='2';
        this.level[5][2]='3';
        this.level[6][2]='4';
        this.level[7][2]='5';
        this.level[8][2]='6';
        this.level[9][2]='7';
        this.level[6][9]='X';
        this.level[5][8]='T';
        this.level[7][8]='T';
        this.level[4][7]='T';
        this.level[8][7]='T';
        this.level[5][6]='T';
        this.level[7][6]='T';
        this.level[6][5]='T';
        //TODO
        record=level;//init record
        try{
            File file=new java.io.File("HuLuWaRecord.txt");
            PrintStream outfile = new PrintStream(new FileOutputStream(file));
            outfile.print(record[0]);
            outfile=new PrintStream(new FileOutputStream(file,true));
            for(int i=1;i<12;i++){
                outfile.print(record[i]);
            }
            outfile.println(record[12]);
            outfile.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }//record

        int x = OFFSET;
        int y = OFFSET;

        Grass init_Grass;
        Cloud init_Cloud;

        for(int i=0;i<13;i++){
            for(int j=0;j<13;j++){
                if(i==0&&j!=12)
                {
                    init_Cloud=new Cloud(x,y);
                    Cloud.add(init_Cloud);
                    x+=SPACE;
                }
                if(j==12){
                    y+=SPACE;
                    if(this.w<x)
                    {
                        this.w=x;
                    }
                    x=OFFSET;
                }
                else if(i!=0&&level[i][j]=='.')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='T'&&i==5&&j==8)
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    toad58=new Toad(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='T'&&i==7&&j==8)
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    toad78=new Toad(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='T'&&i==4&&j==7)
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    toad47=new Toad(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='T'&&i==8&&j==7)
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    toad87=new Toad(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='T'&&i==5&&j==6)
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    toad56=new Toad(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='T'&&i==7&&j==6)
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    toad76=new Toad(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='T'&&i==6&&j==5)
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    toad65=new Toad(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='G')
                {
                    grandfather=new Grandfather(x,y,this);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='S')
                {
                    snake=new Snake(x,y,this);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='1')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    hu1=new HU1(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='2')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    hu2=new HU2(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='3')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    hu3=new HU3(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='4')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    hu4=new HU4(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='5')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    hu5=new HU5(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='6')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    hu6=new HU6(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='7')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    hu7=new HU7(x,y,this,record);
                    x+=SPACE;
                }
                else if(i!=0&&level[i][j]=='X')
                {
                    init_Grass=new Grass(x,y);
                    Grass.add(init_Grass);
                    xiezi=new Xiezi(x,y,this,record);
                    x+=SPACE;
                }

                //TODO
                h=y;
            }
        }
        grandfather = new Grandfather(grandfather.x(), grandfather.y(), this);
        snake = new Snake(snake.x(), snake.y(), this);
        hu1 = new HU1(hu1.x(), hu1.y(), this, record);
        hu2 = new HU2(hu2.x(), hu2.y(), this, record);
        hu3 = new HU3(hu3.x(), hu3.y(), this, record);
        hu4 = new HU4(hu4.x(), hu4.y(), this, record);
        hu5 = new HU5(hu5.x(), hu5.y(), this, record);
        hu6 = new HU6(hu6.x(), hu6.y(), this, record);
        hu7 = new HU7(hu7.x(), hu7.y(), this, record);
        xiezi = new Xiezi(xiezi.x(), xiezi.y(), this, record);
        toad58 = new Toad(toad58.x(), toad58.y(), this, record);
        toad78 = new Toad(toad78.x(), toad78.y(), this, record);
        toad47 = new Toad(toad47.x(), toad47.y(), this, record);
        toad87 = new Toad(toad87.x(), toad87.y(), this, record);
        toad56 = new Toad(toad56.x(), toad56.y(), this, record);
        toad76 = new Toad(toad76.x(), toad76.y(), this, record);
        toad65 = new Toad(toad65.x(), toad65.y(), this, record);

        //TODO
    }//initWorld

    public void buildWorld(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        ArrayList world=new ArrayList();
        world.addAll(Grass);
        world.addAll(Cloud);
        world.addAll(TombStone);
        world.add(grandfather);
        world.add(snake);
        world.add(hu1);
        world.add(hu2);
        world.add(hu3);
        world.add(hu4);
        world.add(hu5);
        world.add(hu6);
        world.add(hu7);
        world.add(xiezi);
        world.add(toad47);
        world.add(toad56);
        world.add(toad58);
        world.add(toad65);
        world.add(toad76);
        world.add(toad78);
        world.add(toad87);

        //TODO
        for(int i=0;i<world.size();i++)
        {
            Thing2D item=(Thing2D)world.get(i);
            if(item instanceof Grass){
                g.drawImage(item.getImage(),item.x(),item.y(),this);
            }
            else if(item instanceof Cloud) {
                g.drawImage(item.getImage(),item.x(),item.y(),this);
            }
            else if(item instanceof TombStone){
                g.drawImage(item.getImage(),item.x(),item.y(),this);
            }
            else if(item instanceof Grandfather){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof Snake){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof HU1){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof HU2){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof HU3){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof HU4){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof HU5){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof HU6){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof HU7){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof Xiezi){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
            else if(item instanceof Toad){
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            //TODO

            if(completed)
            {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(!loadMode) {
            buildWorld(g);
        }
        else
        {
            playback.play(g);
        }
    }

    class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            if(completed){
                return;
            }

            int key=e.getKeyCode();

            if(key==KeyEvent.VK_SPACE) {
                new Thread(hu1).start();
                new Thread(hu2).start();
                new Thread(hu3).start();
                new Thread(hu4).start();
                new Thread(hu5).start();
                new Thread(hu6).start();
                new Thread(hu7).start();
                new Thread(xiezi).start();
                new Thread(toad47).start();
                new Thread(toad56).start();
                new Thread(toad58).start();
                new Thread(toad65).start();
                new Thread(toad76).start();
                new Thread(toad78).start();
                new Thread(toad87).start();

                //Thread.yield();
                //TODO
            }
            else if(key==KeyEvent.VK_R){
                restartLevel();
            }//restart
            else if(key==KeyEvent.VK_L){
                System.out.println("Start review");
                loadMode=true;

                playback=new PlayBack(Field.this);
                playback.init_back_count();
                new Thread(playback).start();

                //return;
                //savepic.save();
            }//load to review
            repaint();
        }

    }//TAdapter

    public void restartLevel() {
        //Cloud.clear();
        Grass.clear();
        initWorld();



        if (completed) {
            completed = false;
        }
    }
}
