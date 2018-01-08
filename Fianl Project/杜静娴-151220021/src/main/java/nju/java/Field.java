package nju.java;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Field extends JPanel {
    private static final int xoffset = 60;
    private static final int yoffset = 80;

    private static ArrayList<String> war_note = new ArrayList<String>();

    public Mutex mutex;
    private Background background;

    private ArrayList<Huluwa> hulubang = new ArrayList<Huluwa>();
    private ArrayList<Louluo> xiaolouluo = new ArrayList<Louluo>();
    private Yeye yeye = new Yeye(120,320, this);
    private Shejing shejing = new Shejing(1270,320, this);
    private Xiezi xiezi;

    private ArrayList<Player> good_staff = new ArrayList<Player>();
    private ArrayList<Player> bad_staff = new ArrayList<Player>();

    private PositionGenerator hpos = new PositionGenerator(Format.heyileft,180,100,xoffset,yoffset);
    private PositionGenerator xpos = new PositionGenerator(Format.changshe,1440-250, 90,xoffset,yoffset+10);
    private static final int w = 1440;
    private static final int h = 810;
    private boolean completed = false;
    private boolean inBattle = false;

    {
        Position pos = xpos.nextPos();
        xiezi = new Xiezi(pos.getX(),pos.getY(),this);
        good_staff.add(yeye);
        bad_staff.add(shejing);
        bad_staff.add(xiezi);

        war_note.add(new String("Waiting..."));
    }

    public ArrayList<Player> getGoodStaff() {
        return good_staff;
    }

    public ArrayList<Player> getBadStaff() {
        return bad_staff;
    }

    public Field(Mutex mutex) throws Exception {
        this.mutex = mutex;
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
        setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
    }

    public static int getBoardWidth() {
        return w;
    }

    public static int getBoardHeight() {
        return h;
    }

    public static boolean outOfHoriRange(Position pos){
        return !(pos.getX() >= xoffset*2 && pos.getX() <= w-xoffset*2);
    }

    public static boolean outOfLeftRange(Position pos) {
        return !(pos.getX() >= xoffset*2);
    }

    public static boolean outOfRightRange(Position pos) {
        return !(pos.getX() <= w-xoffset*2);
    }

    public final void initWorld() {

        for(int i = 0; i < 7; i ++){
            Huluwa wa = null;
            try {
                Position pos = hpos.nextPos();
                wa = new Huluwa(pos.getX(), pos.getY(),this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            hulubang.add(wa);
        }

        for(int i = 1; i < 7; i ++) {
            Louluo shu = null;
            try {
                Position pos = xpos.nextPos();
                shu = new Louluo(pos.getX(), pos.getY(),this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            xiaolouluo.add(shu);
        }
        good_staff.addAll(hulubang);
        bad_staff.addAll(xiaolouluo);
        background = new Background();


    }

    public void buildWorld(Graphics g) {

        ArrayList world = new ArrayList();
        world.add(background);

        world.addAll(good_staff);
        world.addAll(bad_staff);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
                if(item instanceof Huluwa || item instanceof Yeye)
                    g.setColor(new Color(0,255,0));
                else
                    g.setColor(new Color(255,0,0));
                double rate = ((double)((Player) item).getLife())/((double)((Player) item).getFullLife());
                double length = rate*40.0;
                g.drawRoundRect(item.x()+10, item.y()-3,40,5,1,1);
                g.fillRoundRect(item.x()+10, item.y()-3,(int)length,5,1,1);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

        }
        if (completed) {
            war_note.add(new String("Completed.\n"));
            gameOver();
        }
        g.setColor(new Color(100,100,100));
        int size = war_note.size(), offset = 0;
        if((size-1)*16+20 >= 300)
            offset = (size-1)*16+20 - 300;
        for(int i = 0; i < size; i ++) {
            if(20 + 16 * i-offset >= 20)
                g.drawString(war_note.get(i), 25, 20 + 16 * i-offset);
        }

        this.setVisible(true);
    }

    public void reInitWorld(){
//        shejing.recover();
        Huluwa.reInit();
        Yeye.reInit();
        Shejing.reInit();
        Xiezi.reInit();
        Louluo.reInit();


        war_note.clear();
        war_note = new ArrayList<String>();
        war_note.add("Waiting...");

        completed = false;

        hulubang = new ArrayList<Huluwa>();
        xiaolouluo = new ArrayList<Louluo>();
        try {
            yeye = new Yeye(120,320, this);
            shejing = new Shejing(1270,320, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        good_staff.clear();
        bad_staff.clear();

        hpos = new PositionGenerator(Format.heyileft,180,100,xoffset,yoffset);
        xpos = new PositionGenerator(Format.changshe,1440-250, 90,xoffset,yoffset+10);

        completed = false;


        Position pos = null;
        try {
            pos = xpos.nextPos();
            xiezi = new Xiezi(pos.getX(),pos.getY(),this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        good_staff.add(yeye);
        bad_staff.add(shejing);
        bad_staff.add(xiezi);

        for(int i = 0; i < 7; i ++){
            Huluwa wa = null;
            try {
                pos = hpos.nextPos();
                wa = new Huluwa(pos.getX(), pos.getY(),this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            hulubang.add(wa);
        }

        for(int i = 1; i < 7; i ++) {
            Louluo shu = null;
            try {
                pos = xpos.nextPos();
                shu = new Louluo(pos.getX(), pos.getY(),this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            xiaolouluo.add(shu);
        }
        good_staff.addAll(hulubang);
        bad_staff.addAll(xiaolouluo);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    public ArrayList<String> getNote() {
        return war_note;
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE && !completed && !inBattle) {
                inBattle = true;
                ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
                for(Player v: good_staff) {
                    Thread t = new Thread(v);
                    exec.execute(t);
                }
                for(Player v: bad_staff) {
                    Thread t = new Thread(v);
                    exec.execute(t);
                }
                exec.shutdown();
                System.out.println(Thread.currentThread());

            } else if (key == KeyEvent.VK_ESCAPE) {
                battleStop();
            } else if (key == KeyEvent.VK_R) {
                inBattle = false;
                battleStop();
                reInitWorld();
                WarMonitor.restart();
                war_note.clear();
//            } else if (key == KeyEvent.VK_ENTER) {
//                Date now = new Date();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String finishTime = dateFormat.format( now );
//                saveAsFile("log"+finishTime+".log");
            } else if (key == KeyEvent.VK_L) {
                if(Logger.lastLog()!=null) {
                    battleStop();
                    loadBattle();
                }
            }
            repaint();
        }
    }

    private void battleStop() {
        synchronized (mutex){
            completed = true;
        }
        for(Player v : good_staff)
            v.stop();
        for(Player v : bad_staff)
            v.stop();
    }

    public void loadBattle() {
        war_note.clear();
        int t = -1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(good_staff);
        list.addAll(bad_staff);
        for(Player v : list) {
            v.setX(0);
            v.setY(0);
            v.recover();
        }
        new Thread(new StoryTeller(this, list)).start();
    }

    public void saveAsFile(String fn) {
        Logger logger = new Logger(this);
        logger.newLog(fn);
    }

    public void gameOver() {
        synchronized (mutex) {
            if (!completed) {
                completed = true;
            }
            for (Player v : good_staff)
                v.stop();
            for (Player v : bad_staff)
                v.stop();
            takeNote("Battle finished.");
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String finishTime = dateFormat.format(now);
            saveAsFile("log" + finishTime + ".log");
        }
    }

    public void takeNote(String s){war_note.add(s);};

    private class DaemonThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
}

class PositionGenerator{
    private int w = Field.getBoardWidth();
    private int h = Field.getBoardHeight();

    private int xoffset;
    private int yoffset;
    private Format format;
    private int x;
    private int y;
    private int level = 0;
    private int width = 0;

    private int index = -1;

    String formStr;

    PositionGenerator(Format fm, int x, int y, int xoffset, int yoffset){
        this(x,y,xoffset,yoffset);
        format = fm;
        formStr = format.toString();
    }

    PositionGenerator(int x, int y, int xoffset, int yoffset) {
        format = Format.randFormat();
        this.x = x;
        this.y = y;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
        formStr = format.toString();
    }

    public Position nextPos() throws NoMorePositionException {
        while(++index < formStr.length()) {
            if(formStr.charAt(index)=='\n'){
                if(width==0)
                    width=index;
                level++;
            }
            else if(formStr.charAt(index)=='@'){
                return new Position(x+(index-level*(width+1))*xoffset,y+level*yoffset);
            }
        }
        throw new NoMorePositionException();
    }

}

class Position {
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x;
    private int y;

    @Override
    public String toString() {
        return x+" "+y;
    }
}

class NoMorePositionException extends Exception { }