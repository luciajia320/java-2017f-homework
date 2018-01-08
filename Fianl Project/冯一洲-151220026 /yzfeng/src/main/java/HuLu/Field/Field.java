package HuLu.Field;

import HuLu.Creature.*;
import HuLu.Formation.Holder;
import HuLu.Formation.Formation;
import HuLu.Replay.Item;
import HuLu.Replay.Record;
import HuLu.Replay.Replay;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Field extends JPanel{
    private ImageIcon background;
    private final int SPACE = 60;
    private final int N = 17;
    private final int M = 9;
    private static int count = 0;
    private static final int HULU_NUM =  7;
    private Record recorder;
    private Replay replayer;
    private ArrayList<Item>items;
    private int replay_count;

    private Holder[][]holders = new Holder[N][M];
    private ArrayList<GoodMan> goodMEN;
    private ArrayList<BadMan>badMEN;
    private GameState gameState = GameState.START;


    public ArrayList<GoodMan> getGoodMEN() {
        return goodMEN;
    }

    public ArrayList<BadMan> getBadMEN() {
        return badMEN;
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public GameState getGameState(){
        return gameState;
    }

    public Field() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        for(int i = 0; i < N;i++)
            for(int j = 0; j< M;j++){
                holders[i][j] = new Holder();
            }

        URL loc = this.getClass().getClassLoader().getResource("bg.png");
        background= new ImageIcon(loc);

        initWorld();
    }

    public int getBoardWidth() {
        return this.N;
    }
    public int getBoardHeight() {
        return this.M;
    }

    public synchronized Holder[][] getHolders(){
        return holders;
    }
    public void leaveHolder(int x, int y){
        holders[x][y].leaveHolder();
    }
    public void setHolder(int x, int y, Creature item){
        holders[x][y].setItem(item);
    }

    public void clearHolder(){
        for(int i =0; i<N; i++)
            for(int j = 0; j < M; j++){
            holders[i][j].leaveHolder();
            }
    }

    public final void initWorld() {
        count = 0;
        gameState =GameState.START;
        goodMEN = new ArrayList<GoodMan>();
        badMEN = new ArrayList<BadMan>();

        recorder = new Record();
        //recorder.makeRecord(goodMEN, badMEN);
        //使用CyclicBarrier来同步Creature线程和God线程
        CyclicBarrier cyclic = new CyclicBarrier(16, new God(this, recorder));

        //add HULU
        for(int i = 0; i < HULU_NUM; i++)
            goodMEN.add(new HuLuWa(count++, this, cyclic));
        //add Grandpa
        goodMEN.add(new GrandPa(count++, this, cyclic));

        //add Scorpion
        for(int i = 0; i<4; i++)
            badMEN.add(new Scorpion(count++, this, cyclic));

        //add Louluo
        for(int i = 0; i<3; i++)
            badMEN.add(new Louluo(count++, this, cyclic));

        //add Snake
            badMEN.add(new Snake(count++, this, cyclic));

        Formation.HeYiformation(this, goodMEN);
        Formation.Snakemation(this, badMEN);


    }

    public void buildWorld(Graphics g) {
        //绘制背景
        g.drawImage(background.getImage(), 0, 0,background.getIconWidth(),background.getIconHeight(), this);

        //绘制Creature
        for (int i = 0; i < goodMEN.size(); i++) {
            Creature item = goodMEN.get(i);
            g.drawImage(item.getImage(), item.x() * SPACE, item.y() * SPACE, this);
        }
       for(int i = 0; i < badMEN.size();i++){
            Creature item = badMEN.get(i);
            g.drawImage(item.getImage(), item.x() * SPACE, item.y() * SPACE, this);
        }
    }

    public void newReplay(){
        FileDialog dialog=new FileDialog(new Frame(),"选择回放文件",FileDialog.LOAD);
        dialog.setDirectory("./Record");
        dialog.setVisible(true);
        if(dialog.getFile() == null){
            return;
        }
        gameState = GameState.REPLAY;
        replayer = new Replay(this, dialog.getDirectory() + dialog.getFile());
        items = replayer.getItems();
        replay_count = 0;
        new Thread(replayer).start();
    }

    public void replay(Graphics g) {
        g.drawImage(background.getImage(), 0, 0,background.getIconWidth(),background.getIconHeight(), this);
        for(int i = 0; i< 16;i++){
        //绘制Creature
            if(i + replay_count >= items.size()-1)
            {
                gameState = GameState.REPLAYEND;
                break;
            }
            Item item = items.get(i + replay_count);
            g.drawImage(item.getImage(), item.x() * SPACE, item.y() * SPACE, this);
        }
        replay_count += 16;
    }

    @Override
    public void paint(Graphics g) {
        if(gameState == GameState.REPLAYEND)
            return;

        if(gameState == GameState.GAMING || gameState == GameState.START || gameState == GameState.END)
        {
            super.paint(g);
            buildWorld(g);
        }
        else if(gameState == GameState.REPLAY)
        {
            super.paint(g);
            replay(g);
        }

    }

    //Start the Game
    public void gameStart(){
        if(gameState != GameState.GAMING)
            gameState = GameState.GAMING;
        else return;

        ExecutorService exec = Executors.newCachedThreadPool();
        for(Creature c : goodMEN)
            exec.execute(c);
        for(Creature c: badMEN)
            exec.execute(c);
    }

    public void endGame(){
        count = 0;
        clearHolder();
        recorder.writeFile();
    }

    //监听键盘
    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                if(gameState == GameState.END || gameState == GameState.REPLAYEND){
                    initWorld();
                }
                gameStart();
            }
            else if (key == KeyEvent.VK_R) {
                if(gameState == GameState.END || gameState == GameState.REPLAYEND){
                    initWorld();
                }
            }
            else if (key == KeyEvent.VK_L && gameState != GameState.GAMING) {
                newReplay();
            }
            repaint();
        }
    }
}


