package nju.java;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;


public class Field extends JPanel {

    public final int OFFSET = 30;
    public final int SPACE = 100;

    enum GlobalState {BeforeStart, GoodManWin, BadManWin, RUNNING, PLAYING};
    private GlobalState globalState = GlobalState.BeforeStart;

    private ArrayList tiles = new ArrayList();
    private List<Creature> creatureList = new ArrayList<Creature>();
    private FileOutputStream out;
    private RecordPlayer recordPlayer = null;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n";

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

    public List<Creature> getCreatureList() {
        return creatureList;
    }

    public final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Tile a;


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == '@') {
                //player = new Player(x, y, this, "player.png");
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }

        //initPlayer();

    }

    private void initPlayer() {

        try {
            LittleUtils.out = new FileOutputStream("record.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PlayersFactory huluwaFactory = new HuluwaFactory(QueueType.长蛇);
        Creature[] huluwas = huluwaFactory.create(this, new Thing2D(OFFSET,OFFSET), SPACE);

        PlayersFactory demonFactory = new DemonFactory(QueueType.方门);
        Creature[] demons = demonFactory.create(this, new Thing2D(OFFSET + 5 * SPACE, OFFSET), SPACE);

        creatureList.addAll(Arrays.asList(demons));
        creatureList.addAll(Arrays.asList(huluwas));
        creatureList.add(new Shejing(OFFSET + 5 * SPACE, OFFSET + 7 * SPACE, this));
        creatureList.add(new Grandpa(OFFSET, OFFSET + 7 * SPACE, this));

        LittleUtils.record("CREATE_START\n");
        for(Creature creature : creatureList){
            LittleUtils.record(creature.toString() + "\t" + creature.x() + "\t" + creature.y() + "\n");
        }
        LittleUtils.record("CREATE_OVER\n\n");
        LittleUtils.record("PLAY_START\n");

    }

    public void buildWorld(Graphics g){

        switch (globalState){
            case BeforeStart:
                showMenu(g);
                break;
            case RUNNING:
                buildRunningWorld(g);
                break;
            case PLAYING:
                buildRunningWorld(g);
                //recordPlayer.next(this);
                break;
            case BadManWin:
            case GoodManWin:
                buildStopWorld(g, globalState);
                break;
        }

    }

    private void showGrassBackground(Graphics g){
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        for (Object obj : tiles) {
            Thing2D thing2D = (Thing2D) obj;
            try {
                g.drawImage(thing2D.getImage(), thing2D.x(), thing2D.y(), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showMenu(Graphics g){
        showGrassBackground(g);
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font(null, 10, 50));
        g.drawString("葫芦娃大战妖精", OFFSET + 5 * SPACE, OFFSET + 5 * SPACE);
    }

    private void buildStopWorld(Graphics g, GlobalState globalState){

        showGrassBackground(g);

        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font(null, 10, 50));
        String str = null;
        if(globalState == GlobalState.GoodManWin){
            str = "GoodMan Win!";
        }
        else{
            str = "BadMan Win!";
        }
        g.drawString(str + "\t" + "Play Over!", OFFSET + SPACE, OFFSET + 2 * SPACE);
        return;
    }

    private void buildRunningWorld(Graphics g){

        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        try {

            for (Object obj : tiles) {
                Thing2D thing2D = (Thing2D) obj;
                g.drawImage(thing2D.getImage(), thing2D.x(), thing2D.y(), this);
            }

            for (Creature creature : creatureList) {
                if (creature.isDead()) {
                    Thing2D item = (Thing2D) creature;
                    g.drawImage(item.getImage(), item.x(), item.y(), this);
                }
            }

            for (Creature creature : creatureList) {
                if (!creature.isDead()) {
                    Thing2D item = (Thing2D) creature;
                    g.drawImage(item.getImage(), item.x(), item.y(), this);
                    g.setColor(new Color(255,0,0));
                    g.fillArc(item.x(), item.y(),10,10, 0, 360);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                initPlayer();
                globalState = GlobalState.RUNNING;

                for(int i = 0; i < creatureList.size(); i++){
                    new Thread(creatureList.get(i)).start();
                }

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            } else if(key == KeyEvent.VK_L){

                FileDialog fileDialog = new FileDialog(new Frame(), "读取", FileDialog.LOAD);
                fileDialog.setVisible(true);
                String file = fileDialog.getDirectory() + fileDialog.getFile();
                recordPlayer = new RecordPlayer(file, creatureList, Field.this);
                globalState = GlobalState.PLAYING;

            }

            repaint();
        }
    }

    public Tile getTile(int x, int y) {
        for(Object obj : tiles){
            Tile tile = (Tile)obj;
            if(tile.x() == x && tile.y() == y) {
                return tile;
            }
        }
        return null;
    }

    public Creature.Direction findNearEnemgy(Creature me){
        int min = Integer.MAX_VALUE;
        Creature.Direction d = null;

        for(Creature creature : creatureList){
            if(!creature.isDead() && ((creature instanceof GoodMan && me instanceof BadMan)
                            || (creature instanceof BadMan && me instanceof GoodMan))){
                int w = creature.x() - me.x();
                int h = creature.y() - me.y();
                int distance = (int)Math.sqrt(w * w + h * h);
                if(min > distance){
                    min = distance;
                    if(Math.abs(w) >= Math.abs(h)){
                        if(w >= 0) d = Creature.Direction.右;
                        else d = Creature.Direction.左;
                    }
                    else{
                        if(h >= 0) d = Creature.Direction.上;
                        else d = Creature.Direction.下;
                    }
                }
            }
        }
        if(d == null) {
            if(me instanceof BadMan)
                globalState = GlobalState.BadManWin;
            else{
                globalState = GlobalState.GoodManWin;
            }
            LittleUtils.record("PLAY_OVER\n");
            try {
                LittleUtils.out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return d;
    }

    public void restartLevel() {

        tiles.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}