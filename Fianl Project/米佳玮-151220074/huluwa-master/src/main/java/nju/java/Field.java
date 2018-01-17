package nju.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 60;

    private ArrayList tiles = new ArrayList();
    private ArrayList<Player> player1=new ArrayList<Player>();
    private ArrayList<Player> player2=new ArrayList<Player>();

    enum STATE { READY,PLAYING, OVER, RESHOW };

    private int w = 0;
    private int h = 0;
    private STATE completed = STATE.READY;
    //private boolean reshowed=false;

    private String level =
            "@........l\n" +
                    "g.......l.\n" +
                    "@......l..\n" +
                    "@......x..\n" +
                    "@......s..\n" +
                    "@.......l.\n" +
                    "@........l\n" +
                    "@.........\n";

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
        new Thread(new Reshow(this)).start();
        FileOperation.creatFile("report.txt");
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        Huluwa.num=0;
        Xiaolouluo.num=0;

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
                a = new Tile(x, y);
                tiles.add(a);
                player1.add(new Huluwa(x, y, this));
                x += SPACE;
            }
            else if (item == 'g') {
                a = new Tile(x, y);
                tiles.add(a);
                player1.add(new GrandPa(x, y, this));
                x += SPACE;
            }
            else if (item == 's') {
                a = new Tile(x, y);
                tiles.add(a);
                player2.add(new Shejing(x, y, this));
                x += SPACE;
            }
            else if (item == 'x') {
                a = new Tile(x, y);
                tiles.add(a);
                player2.add(new Xiezijing(x, y, this));
                x += SPACE;
            }
            else if (item == 'l') {
                a = new Tile(x, y);
                tiles.add(a);
                player2.add(new Xiaolouluo(x, y, this));
                x += SPACE;
            }
            else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }

        for(Player p:player1) {
            p.setEnemys(player2);
            p.setFriends(player1);
        }
        for(Player p:player2) {
            p.setEnemys(player1);
            p.setFriends(player2);
        }
        //player = new Player(0+ OFFSET,0+OFFSET, this);

    }

    public void buildWorld(Graphics g) {
        if(completed==STATE.RESHOW){
            reShow();
        }
        if (completed==STATE.OVER) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("Completed", 25, 20);
            return;
        }
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);


        world.addAll(player1);
        world.addAll(player2);

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (!(item instanceof Player)) {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

        }

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                Player p=(Player)item;
                if(!p.getIsLive())
                    g.drawImage(item.getImage(), item.x() , item.y() , this);
            }

        }

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                Player p=(Player)item;
                if(p.getIsLive())
                    g.drawImage(item.getImage(), item.x() , item.y() , this);
            } /*else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }*/



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


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

               // new Thread(player).start();
                if(completed==STATE.READY) {
                    completed = STATE.PLAYING;
                    moveAll();
                }
            } else if (key == KeyEvent.VK_R) {
                restartLevel();
                completed=STATE.READY;
            } else if(key== KeyEvent.VK_L){
                if (completed==STATE.OVER||completed==STATE.READY) {
                    System.out.println("reshow");
                    restartLevel();
                    completed=STATE.RESHOW;
                    return;
                    //reShow();
                }
            }

            repaint();
        }
    }

    public void moveAll() {
        for (Player p : player1) {
            new Thread(p).start();
        }
        for (Player p : player2) {
            new Thread(p).start();
        }
    }

    public void restartLevel() {
        player1.clear();
        player2.clear();
        tiles.clear();
        initWorld();
    }

    public void reShow(){
        String str=null;
        if ((str=FileOperation.input())!=null){
            String [] arr = str.split("\\s+");
            if(arr[0].equals("GrandPa")){
                if(arr[1].equals("lose")){
                    for(Player p: player1){
                        if(p instanceof GrandPa){
                            p.setNewImage();
                            p.setIsLive();
                            //p.myRepaint();
                            break;
                        }
                    }
                }
                else{
                    for(Player p: player1){
                        if(p instanceof GrandPa){
                            p.setPoint(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                            //p.myRepaint();
                            break;
                        }
                    }
                }
            }
            else if(arr[0].equals("Shejing")){
                if(arr[1].equals("lose")){
                    for(Player p: player2){
                        if(p instanceof Shejing){
                            p.setNewImage();
                            p.setIsLive();
                            //p.myRepaint();
                            break;
                        }
                    }
                }
                else{
                    for(Player p: player2){
                        if(p instanceof Shejing){
                            p.setPoint(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                            //p.myRepaint();
                            break;
                        }
                    }
                }
            }
            else if(arr[0].equals("Xiezijing")){
                if(arr[1].equals("lose")){
                    for(Player p: player2){
                        if(p instanceof Xiezijing){
                            p.setNewImage();
                            p.setIsLive();
                            //p.myRepaint();
                            break;
                        }
                    }
                }
                else{
                    for(Player p: player2){
                        if(p instanceof Xiezijing){
                            p.setPoint(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                            //p.myRepaint();
                            break;
                        }
                    }
                }
            }
            else if(arr[0].indexOf("Huluwa")!=-1){
                if(arr[1].equals("lose")){
                    for(Player p: player1){
                        if(p instanceof Huluwa){
                            Huluwa temp=(Huluwa)p;
                            if(temp.getNo()==Character.getNumericValue(arr[0].charAt(6))){
                                p.setNewImage();
                                p.setIsLive();
                                //p.myRepaint();
                                break;
                            }
                        }
                    }
                }
                else{
                    for(Player p: player1){
                        if(p instanceof Huluwa){
                            Huluwa temp=(Huluwa)p;
                            if(temp.getNo()==Character.getNumericValue(arr[0].charAt(6))){
                                p.setPoint(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                                //p.myRepaint();
                                break;
                            }
                        }
                    }
                }
            }
            else if(arr[0].indexOf("Xiaolouluo")!=-1){
                if(arr[1].equals("lose")){
                    for(Player p: player2){
                        if(p instanceof Xiaolouluo){
                            Xiaolouluo temp=(Xiaolouluo)p;
                            if(temp.getNo()==Character.getNumericValue(arr[0].charAt(10))){
                                p.setNewImage();
                                p.setIsLive();
                                //p.myRepaint();
                                break;
                            }
                        }
                    }
                }
                else{
                    for(Player p: player2){
                        if(p instanceof Xiaolouluo){
                            Xiaolouluo temp=(Xiaolouluo)p;
                            if(temp.getNo()==Character.getNumericValue(arr[0].charAt(10))){
                                p.setPoint(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                                //p.myRepaint();
                                break;
                            }
                        }
                    }
                }
            }
        }
        else{
            completed=STATE.OVER;
        }

    }

    public void setCompleted(){ completed=STATE.OVER;}
    public boolean getReshowed(){return completed==STATE.RESHOW;}
}