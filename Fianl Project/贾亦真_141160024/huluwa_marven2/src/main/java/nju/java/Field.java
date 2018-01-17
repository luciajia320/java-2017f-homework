package nju.java;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import nju.java.common.Constant;

public class Field extends JPanel {

    private final int OFFSET = 30;
    public static final int SPACE = 40;
    private Image background;

    public JFrame getParentFrame(){
        JFrame parentFrame = null;
        parentFrame = (JFrame)this.getParent().getParent().getParent().getParent();
        return parentFrame;
    }

    private ArrayList<Human> humans;
    private ArrayList<Monster> monsters;
    private ArrayList<Creature> creature_list;

    private Constant.STATE state;
    private long start_time;
    String SavePath;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
            "..1..........L......\n" +
                    "...................t" +
                    "...2..........L....\n" +
                    "...................t" +
                    "..3............L...\n" +
                    "...................t" +
                    "...4............S..\n" +
                    "...................t" +
                    "..5............X...\n" +
                    "...................t" +
                    "...6..........L....\n" +
                    "...................t" +
                    "..7..........L.....\n" +
                    "...................t" +
                    "...Y........L......\n" +
                    "...................t";

    public Field() {
        this.setSize(new Dimension(Constant.DEFAULT_LAYOUT_WIDTH, Constant.DEFAULT_LAYOUT_HEIGHT));
        initWorld();
        addKeyListener(new TAdapter(this));
        setFocusable(true);
    }


    public void StartPlay(){
        state = Constant.STATE.PLAYING;
        start_time = System.currentTimeMillis();
        SavePath = new Long(start_time).toString() + ".txt";

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for ( Creature c:this.creature_list){
            cachedThreadPool.execute( new ChaosLoader(c) );
        }
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public void save(){
        try {
            FileWriter fw = new FileWriter(this.SavePath, true);
            Long c_time = new Long(System.currentTimeMillis() - this.start_time);
            fw.write( c_time.toString() + "\n");
            for ( Creature c: this.getHumans()){
                String info = c.x() + " " + c.y() + " " + c.isAlive() + "\n";
                fw.write(info);
            }
            for ( Creature c: this.getMonsters()){
                String info = c.x() + " " + c.y() + " " + c.isAlive() + "\n";
                fw.write(info);
            }
            fw.close();
        }catch ( IOException e ){
            System.out.println(e.toString());
        }
    }

    public BufferedReader LoadSave(File file) throws Exception{
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        return br;
    }

    public void ShowRecord( ArrayList<Record> records) throws Exception {
        if ( records.size() != this.creature_list.size()) {
            throw new Exception( "Records and creature_list has Unequal Length!" );
        }
        for (int i = 0; i < records.size(); i++) {
            Creature c = this.creature_list.get(i);
            Record rec = records.get(i);
            c.setX(rec.x);
            c.setY(rec.y);
            if (!rec.isAlive) c.die();
        }
        this.repaint();
    }

    public final void initWorld() {

        humans = new ArrayList<Human>();
        monsters = new ArrayList<Monster>();
        creature_list = new ArrayList<Creature>();
        state = Constant.STATE.START;

        int x = OFFSET;
        int y = OFFSET;

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }
                x = OFFSET;
            } else if (item == 't') {
                y += SPACE / 2;
                if (this.w < x) {
                    this.w = x;
                }
                x = OFFSET;
            } else if (item == '.') {
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            } else if (item >= '1' & item <= '7') {
                humans.add(new Huluwa(x, y, this, Huluwa.RANK.values()[item - '1']));
                x += SPACE;
            } else if (item == 'S') {
                monsters.add(new Snake(x, y, this));
                x += SPACE;
            } else if (item == 'X') {
                monsters.add(new Scorpion(x, y, this));
                x += SPACE;
            } else if (item == 'L') {
                monsters.add(new LouLuo(x, y, this));
                x += SPACE;
            } else if (item == 'Y') {
                humans.add(new Grandpa(x, y, this));
                x += SPACE;
            }

            h = y;

        }

        URL bckg_loc = this.getClass().getClassLoader().getResource("background.jpg");
        ImageIcon iia = new ImageIcon(bckg_loc);
        background = iia.getImage().getScaledInstance(w + OFFSET, h + OFFSET, Image.SCALE_SMOOTH);

        //Complete creature_list
        creature_list.addAll(this.getHumans());
        creature_list.addAll(this.getMonsters());

    }

    public void buildWorld(Graphics g) {
        g.clearRect(10, Constant.DEFAULT_LAYOUT_HEIGHT - 50, 100, 30);

        g.drawImage(background, 0, 0, this);
        int h_count = 0;
        int m_count = 0;
        // show creatures
        for (Creature c: creature_list){
            g.drawImage(c.getImage(), c.x() + 2, c.y() + 2, this);
            if (c instanceof Human & c.isAlive)
                h_count++;
            else if (c instanceof Monster & c.isAlive){
                m_count++;
            }
        }

        g.setColor(java.awt.Color.RED);
        g.setFont( new Font("黑体",Font.BOLD, 18));
        g.drawString("状态 " + this.getState().toString() + "",
                10, Constant.DEFAULT_LAYOUT_HEIGHT - 50);

        g.drawString("比分  " + h_count + ":" + m_count,
                10, Constant.DEFAULT_LAYOUT_HEIGHT - 20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
        if (this.getState() == Constant.STATE.PLAYING) save();
    }


    public void restartLevel() {
        repaint();
        initWorld();
        this.state = Constant.STATE.START;
    }

    public ArrayList<Human> getHumans(){
        return humans;
    }

    public ArrayList<Monster> getMonsters(){
        return monsters;
    }

    public Constant.STATE getState(){
        return state;
    }

    public void setState( Constant.STATE state1){
        this.state = state1;
    }

}