package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

import Battle.Battle;
import Position.Position;
import creature.Creature;
import creature.Name;

public class Field extends JPanel {

    //输出的文件地址
    private File file=new File("D:\\battle.txt");

    //常量设定，space表示几个像素为一个坐标单位，由此可将战场分为row行，col列
    private final int SPACE = 5;
    private final int row=60;
    private final int col=120;

    //战斗负责敌我双方的阵营以及战斗判定等
    private Battle battle;

    //w,h为战场的像素级高度与宽度
    private int w = 0;
    private int h = 0;

    //表示战斗是否打完
    private int completed = 0;

    //表示战场上战士们是否就绪准备战斗
    private boolean is_formated=false;

    //战场坐标表示
    Position[][] scenery;

    public File getFile() {
        return file;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getCompleted(){ return completed;}
    public Position[][] getScenery() {
        return scenery;
    }

    public Field() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        //初始化战场
        scenery=new Position[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                scenery[i][j]=new Position(j*SPACE,i*SPACE);


        int x = 0;
        int y = 0;


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Position item = scenery[i][j];
                if (item.getSomeone()==null) {
                    x += SPACE;
                }
            }
            if(w<x){
                w=x;
            }
            x=0;
            y += SPACE;
        }

        //为宽和高分别加上每个图片的大致高度和宽度
        h = y+70;
        w+=60;

        //初始化战斗
        battle=new Battle(this);
    }

    public Battle getBattle() {
        return battle;
    }

    public void buildWorld(Graphics g) {

        //用以显示战场
        ArrayList <Creature>creatures=null;

        //判断是否为战斗回放，若为回放与真正的战斗所取的战士链表不同
        if(battle.replay)
            creatures=battle.replayList;
        else
            creatures=battle.returnAll();


        for (int i = 0; i < creatures.size(); i++) {

            UI.Thing2D item = (UI.Thing2D) creatures.get(i);

            g.drawImage(item.getImage(), item.getPosition().getX(), item.getPosition().getY(), this);

            if (completed!=0) {

                g.setColor(new Color(255, 255, 255));
                if(completed==2)
                    g.drawString("Huluwa Winner,Chicken Dinner!", 25, 20);
                else
                    g.drawString("Enemy Winner,Chicken Grandpa!", 25, 20);
            }

        }
    }


    //回放函数
    void Playback(File f){

        final File x=f;
        //为了能够实时重绘故使用多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList <Creature> creaturesR=new ArrayList<>();
                BufferedReader br=null;
                try{
                    String temp=null;
                    br=new BufferedReader(new FileReader(x));
                    temp=br.readLine();
                    int i=0;
                    int j=0;

                    //以下为判断逻辑
                    while((temp=br.readLine())!=null)
                    {
                        //当为非空行
                        if(!temp.equals("")) {
                            for (int x = 0; x < temp.length(); x++, j++)
                                if (temp.charAt(x) != '*') {
                                    char c = temp.charAt(x);
                                    char cid = temp.charAt(++x);
                                    creaturesR.add(getCreature(c, i, j, ((int) cid) - 48));
                                }
                            j=0;
                            i++;
                        }

                        //当为空行，表示一帧图像播放完成，播放战斗图像链表，并重置图像接受链表
                        else
                        {
                            i=0;
                            j=0;
                            battle=new Battle(creaturesR);
                            repaint();
                            try{
                                Thread.sleep(10);
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            creaturesR=new ArrayList<>();
                        }
                    }
                }catch (IOException e)
                {
                    e.printStackTrace();
                }

                //完全播放完毕后重置所有设置
                battle.replay=false;
                initWorld();
                is_formated=false;
                if (completed!=0) {
                    completed = 0;
                }
            }
        }).start();

    }

    //此函数用于给回放函数创建临时战士对象，播放完后会自动被垃圾回收机制回收
    private Creature getCreature(char id,int x,int y,int direction){
        switch (id)
        {
            case '一':
            {
                return new Creature(x,y,this, Name.一,4,direction);
            }
            case '二':
            {
                return new Creature(x,y,this, Name.二,4,direction);
            }
            case '三':
            {
                return new Creature(x,y,this, Name.三,4,direction);
            }
            case '四':
            {
                return new Creature(x,y,this, Name.四,4,direction);
            }
            case '五':
            {
                return new Creature(x,y,this, Name.五,4,direction);
            }
            case '六':
            {
                return new Creature(x,y,this, Name.六,4,direction);
            }
            case '七':
            {
                return new Creature(x,y,this, Name.七,4,direction);
            }
            case '爷':
            {
                return new Creature(x,y,this, Name.爷,2,direction);
            }
            case '蛇':
            {
                return new Creature(x,y,this, Name.蛇,3,direction);
            }
            case '蝎':
            {
                return new Creature(x,y,this, Name.蝎,3,direction);
            }
            case '蜈':
            {
                return new Creature(x,y,this, Name.蜈,5,direction);
            }
            default:
                return null;
        }

    }


    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                //若战士已排好队就绪，则将每个战士作为线程开启，否则先排成阵列
                if(is_formated){
                    for(Creature p:battle.returnAll())
                        new Thread(p).start();
                }
                else
                {
                    battle.allset();
                    is_formated=true;
                }

                //开始输出到文件
                FileWriter fw=null;
                BufferedWriter bw=null;
                try{
                    if(!file.exists())
                    {
                        file.createNewFile();
                    }
                    fw=new FileWriter(file.getAbsoluteFile());
                    bw=new BufferedWriter(fw);
                    fw.write("");
                    bw.write("This is a battle!\r\n");
                    bw.close();
                    fw.close();
                }
                catch(IOException a){
                    a.printStackTrace();
                }

            } else if (key == KeyEvent.VK_ENTER) {
                restartLevel();
            }
            else if(key==KeyEvent.VK_L){
                JFileChooser jfc=new JFileChooser();
                if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    File f=jfc.getSelectedFile();
                    Playback(f);
                }
                else
                    System.out.println("No file is selected!");
            }
            repaint();
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    public void restartLevel() {

        if(battle.returnAll()!=null)
            for(Creature c:battle.returnAll())
                c.setIs_interupt(true);

        initWorld();
        is_formated=false;
        if (completed!=0) {
            completed = 0;
        }
    }
}