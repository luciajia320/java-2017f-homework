package nju.wz.position;

import nju.wz.creature.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Field extends JPanel {

    public static final int OFFSET = 50;
    public static final int SPACE = 50;

    private int threadLength = 16;
    public Thread[] threads = null;
    public String reviewFileName;
    public static final String finalName = "review.txt";

    public Review review;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    BufferedWriter writer;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    private boolean huluwaWin = false;

    public static final int FIELD_LENGTH = 8;
    public static final int FIELD_WIDTH = 8;
    public static int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
    };

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

    public void resetMap() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length - 1; j++) {
                map[i][j] = 0;
            }
        }
    }

    void showMap() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void writeMap() {
        lock.lock();
        try {
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    writer.write(map[i][j] + " ");
                }
                writer.write("\n");
            }
            writer.write("\n");
        }
        catch(IOException e) {
        }
        finally {
            lock.unlock();
        }
    }

    public void reviewMap(int[][] map1) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                map[i][j] = map1[i][j];
            }
        }
    }

    public void setMap(int n1, int n2, int choose) {
        map[n1][n2] = choose;
    }

    public final void initWorld() {

        threads = new Thread[threadLength];
        //resetMap();
        int x = OFFSET;
        int y = OFFSET;

        for(int n1 = 0; n1 < map.length; n1++) {
            for(int n2 = 0; n2 < map[n1].length; n2++) {
                int item = map[n1][n2];
                if(item == -1) {
                    y += SPACE;
                    if(this.w < x) {
                        this.w = x;
                    }
                    x = OFFSET;
                }
                else if(item == 0) {
                    Tile a = new Tile(x, y);
                    tiles.add(a);
                    x += SPACE;
                }
                else if(item == 1) {
                    Tile a = new Tile(x, y);
                    tiles.add(a);
                    x += SPACE;
                }
                h = y;
            }

        }
        players = GodisGod.getPlayers(this);
        review = GodisGod.getReview(this);
    }

    public void buildMap(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList<Player> players = new ArrayList<>();

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] != 0 && map[i][j] != -1) {
                    Player p = null;

                    if(map[i][j] == 1) {
                        p = new Huluwa(this, 0);
                    }
                    else if(map[i][j] == 2) {
                        p = new Grandfather(this, 0);
                    }
                    else if(map[i][j] == 7) {
                        p = new Shejing(this, 0);
                    }
                    else if(map[i][j] == 8) {
                        p = new Xiezijing(this, 0);
                    }
                    else if(map[i][j] == 9) {
                        p = new Xiaoloulou(this, 0);
                    }
                    if(p != null) {
                        p.setX(p.getX(j));
                        p.setY(p.getY(i));
                        players.add(p);
                    }
                }
            }
        }
        ArrayList world = new ArrayList();
        world.addAll(tiles);
        world.addAll(players);

        for(int i = 0; i < world.size(); i++) {
            Thing2D item = (Thing2D)world.get(i);
            if(item instanceof Player) {
                Player player = (Player)item;
                int y1 = ((Player)item).index1();
                int x1 = ((Player)item).index2();
                map[y1][x1] = getType(player);
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            }
            else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
        }
        g.setColor(new Color(192, 21, 13));
        g.drawString("回放", OFFSET, OFFSET - 30);
        resetMap();
    }

    public int getType(Player p) {
        int type = 0;
        if(p instanceof Huluwa) {
            type = 1;
        }
        else if(p instanceof Grandfather) {
            type = 2;
        }
        else if(p instanceof Shejing) {
            type = 7;
        }
        else if(p instanceof Xiezijing) {
            type = 8;
        }
        else if(p instanceof Xiaoloulou) {
            type = 9;
        }
        return type;
    }

    public void buildWorld(Graphics g) {
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);
        world.addAll(players);
        resetMap();
        for(int i = 0; i < world.size(); i++) {
            Thing2D item = (Thing2D)world.get(i);
            if(item instanceof Player) {
                Player player = (Player)item;
                if(player.isDie()) {
                    continue;
                }
                int y1 = ((Player)item).index1();
                int x1 = ((Player)item).index2();
                map[y1][x1] = getType(player);
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            }
            else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
        }
        if(isCompleted()) {
            try {
                if(writer != null) {
                    writer.close();
                }
            }
            catch(IOException e) {
            }finally{
                writer = null;
            }
            completed = true;
            g.setColor(new Color(192, 21, 13));
            if(huluwaWin) {
                g.drawString("战斗结束， 葫芦娃一方获胜", OFFSET, OFFSET - 30);
            }
            else {
                g.drawString("战斗结束， 蛇精一方获胜", OFFSET, OFFSET - 30);
            }
            for(int i = 0; i < players.size(); i++) {
                if(threads[i] != null && threads[i].isAlive()) {
                    threads[i].interrupt();
                }
            }
        }
        g.setColor(new Color(6, 6, 4));
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length - 1; j++) {
                if(i == 0) {
                    g.drawString(String.valueOf(j), OFFSET * j + 66, OFFSET - 5);
                }
            }
            g.drawString(String.valueOf(i), OFFSET - 10, OFFSET * i + 74);
        }
        g.setColor(new Color(6, 6, 4));
        g.drawString("SPACE - 开始/继续", OFFSET, h + 20);
        g.drawString("L - 文件", OFFSET * 4, h + 20);
        g.drawString("S - 暂停", OFFSET * 6, h + 20);
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_SPACE && Review.isReview == false) {
                if(completed == true) {
                    restartLevel();
                }
                for(int i = 0; i < players.size(); i++) {
                    int id = players.get(i).getID();
                    threads[id] = new Thread(players.get(i), players.get(i).getName());
                    threads[id].start();
                }
                if(reviewFileName == null || writer == null) {
                    reviewFileName = finalName;
                    try {
                        writer = new BufferedWriter(
                                new OutputStreamWriter(new FileOutputStream(reviewFileName)));
                        for(int i = 0; i < 10; i++) {
                            writeMap();
                        }
                    }
                    catch(FileNotFoundException e1) {
                        System.out.println("File Not Found Error!");
                    }
                }
            }
            else if(key == KeyEvent.VK_S) {
                for(int i = 0; i < players.size(); i++) {
                    if(threads[i] != null && threads[i].isAlive()) {
                        threads[i].interrupt();
                    }
                }
            }
            else if(key == KeyEvent.VK_L && Review.isReview == false) {
                restartLevel();
                for(int i = 0; i < players.size(); i++) {
                    if(threads[i] != null && threads[i].isAlive()) {
                        threads[i].interrupt();
                    }
                }
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "文本文件(*.txt;*.md)", "txt", "md");
                jfc.setFileFilter(filter);

                while(true) {
                    int returnVal = jfc.showOpenDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = jfc.getSelectedFile();
                        if(file.exists()) {
                            String name = file.getName();
                            if(finalName.equals(name)) {
                                System.out.println(finalName + " is selected.");
                                break;
                            }
                            else {
                                System.out.println("Please select file: " + finalName);
                            }
                        }
                    }
                    else {
                        System.out.println("Open file error, select again: ");
                    }
                }
                Thread t = new Thread(review);
                t.start();
            }
            repaint();
        }
    }

    boolean isCompleted() {
        int goodAlive = 0, badAlive = 0;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length - 1; j++) {
                int type = map[i][j];
                if(type == 1 || type == 2) {
                    goodAlive++;
                }
                else if(type == 7 || type == 8 || type == 9) {
                    badAlive++;
                }
            }
        }
        if(goodAlive == 0 || badAlive == 0) {
            huluwaWin = (badAlive == 0);
            return true;
        }
        return false;

    }

    private void moveInField(Player a, Player b) {
        int disY = a.index1() - b.index1();
        int disX = a.index2() - b.index2();
        if(disX == 0 && disY == 0) {
            System.out.println("Error exist.");
        }
        else if(disX - disY >= 0 && disX + disY >= 0) {
            a.move(-OFFSET, 0);
        }
        else if(disX - disY >= 0 && disX + disY < 0) {
            a.move(0, OFFSET);
        }
        else if(disX - disY < 0 && disX + disY >= 0) {
            a.move(0, -OFFSET);
        }
        else if(disX - disY < 0 && disX + disY < 0) {
            a.move(OFFSET, 0);
        }
    }

    public boolean isNot(Player a, Player b) {
        if(a.isDie() || b.isDie()) {
            return false;
        }
        if(a instanceof Monster && b instanceof GoodPerson
                || a instanceof GoodPerson && b instanceof Monster) {
            return true;
        }
        return false;
    }

    public void findEnemy(Player a) {
        lock.lock();
        try {
            int min = 1000;
            Player closedEnemy = null;
            for(int i = 0; i < players.size(); i++) {
                Player b = players.get(i);
                if(isNot(a, b) == false) {
                    continue;
                }
                int dis1 = a.index1() - b.index1();
                int dis2 = a.index2() - b.index2();
                int distance = dis1 * dis1 + dis2 * dis2;
                if(distance < min) {
                    min = distance;
                    closedEnemy = b;
                }
            }
            if(min == 1) {
                combat(a, closedEnemy);
                writeMap();
            }
            else if(1 < min && min < 1000) {
                moveInField(a, closedEnemy);
                writeMap();
            }
        }
        finally {
            lock.unlock();
        }
    }

    public void combat(Player a, Player b) {
        try {
            if(a.isDie() || b.isDie()) {
                return;
            }
            if(a.compareTo(b) > 0) {
                System.out.println(a.getName() + " 和 " + b.getName() + " 发生了战斗, " + b.getName() + " 死亡");
                b.setDie(true);
                map[b.index1()][b.index2()] = 0;
            }
            else {
                System.out.println(a.getName() + " 和 " + b.getName() + " 发生了战斗, " + a.getName() + " 死亡");
                a.setDie(true);
                map[a.index1()][a.index2()] = 0;
            }
        }
        finally {
        }

    }

    public boolean isExist(Player p, int n1, int n2) {
        if(map[n1][n2] == 0) {
            int type = getType(p);
            map[n1][n2] = type;
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(completed == false && Review.isReview == false) {
            buildWorld(g);
        }
        if(Review.isReview == true) {
            buildMap(g);
        }
    }

    public void restartLevel() {
        tiles.clear();
        players.clear();
        threads = null;
        huluwaWin = false;
        resetMap();
        initWorld();
        if(completed) {
            completed = false;
        }
    }
}