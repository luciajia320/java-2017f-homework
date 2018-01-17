package main.java.nju.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 40;

    private ArrayList tiles = new ArrayList();
    private String fileName = null;

    private int w = 0;
    private int h = 0;

    private int sideLength;
    private Position[][] board;
    private Creature[] huluwas, yaoguais;

    private String level;

    public Field(int n) {
        sideLength = n;
        board = new Position[n][n];
        for(int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                board[i][j] = new Position(i, j);
                if (i == 0 || j == 0 || i == sideLength - 1 || j == sideLength - 1)
                    board[i][j].setBorder();
            }
        }

        setLevel();
        addKeyListener(new TAdapter());
        setFocusable(true);
        try{
            initWorld();
        }catch (OutOfBoundException e){ e.printStackTrace();}
    }

    public Field(int n, Creature[] creatures) { sideLength = n; huluwas = creatures;}

    public void setLevel(){
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        for(int i = 0; i < sideLength; ++i){
            for(int j = 0; j < sideLength; ++j){
                sb.append('.');
            }
            sb.append('\n');
        }
        level = sb.toString();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public boolean isCrossBorder(int posX, int posY){
        if(posX < 0 || posX >= sideLength || posY < 0 || posY >= sideLength)
            return true;
        else return false;
    }

    public Position[][] getBoard(){ return board; }

    public Position getPositionAt(int x, int y){ return board[x][y];}

    public Creature[] getHuluwas() { return huluwas; }

    public Creature[] getYaoguais() { return yaoguais;}

    public void clearBoard(){
        for(int i = 0; i < sideLength; ++i)
            for(int j = 0; j < sideLength; ++j)
                board[i][j].clear();

    }
    public final void initWorld() throws OutOfBoundException {

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
            }
            h = y;
        }

        Formation f1 = new SnakeFormation(new Position(1,1));
        Formation f2 = new WingFormation(new Position(6,1));
        if(f1.isCrossBound(sideLength) || f2.isCrossBound(sideLength) || f1.conflict(f2) || f2.conflict(f1))
            throw new OutOfBoundException("Create formation illegally");


        huluwas = new Creature[8];
        yaoguais = new Creature[8];
        for (int i = 0; i < 7; ++i)
            huluwas[i] = new Huluwa(this, board[f1.getPositions()[i].getX()][f1.getPositions()[i].getY()],COLOR.values()[i], SENIORITY.values()[i]);
        huluwas[7] = new Laoyeye(this, board[f1.getPositions()[7].getX()][f1.getPositions()[7].getY()]);

        yaoguais[0] = new Xiezijing(this, board[f2.getPositions()[0].getX()][f2.getPositions()[0].getY()]);
        yaoguais[1] = new Shejing(this, board[f2.getPositions()[1].getX()][f2.getPositions()[1].getY()]);
        for (int i = 2; i < 8; ++i)
            yaoguais[i] = new Xiaoyaoguai(this, board[f2.getPositions()[i].getX()][f2.getPositions()[i].getY()]);
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);
        /*避免死亡实体掩盖活体标志*/
        for(int i = 0; i < huluwas.length; ++i) {
            if(huluwas[i].isAlive() == false)
            world.add(huluwas[i]);
        }
        for(int i = 0; i < yaoguais.length; ++i) {
            if(yaoguais[i].isAlive() == false)
            world.add(yaoguais[i]);
        }
        for(int i = 0; i < huluwas.length; ++i) {
            if(huluwas[i].isAlive() == true)
                world.add(huluwas[i]);
        }
        for(int i = 0; i < yaoguais.length; ++i) {
            if(yaoguais[i].isAlive() == true)
                world.add(yaoguais[i]);
        }
        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if(item instanceof Creature){
                g.drawImage(item.getImage(), item.imageX()+4, item.imageY()+4, this);
            }else {
                g.drawImage(item.getImage(), item.imageX(), item.imageY(), this);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
        //readRecord(1);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_SPACE){
                for(int idx = 0; idx < huluwas.length; ++idx)
                    new Thread(huluwas[idx]).start();
                for(int idx = 0; idx < yaoguais.length; ++idx)
                    new Thread(yaoguais[idx]).start();
            }
            else if(key == KeyEvent.VK_T){
                for(int i = 0; i < huluwas.length; ++i)
                    System.out.println("huluwa" + (i+1) + ": （" +huluwas[i].getPosition().getX() + "," + huluwas[i].getPosition().getY() + ")");
                for(int i = 0; i < yaoguais.length; ++i)
                    System.out.println("yaoguai" + (i+1) +": (" + yaoguais[i].getPosition().getX() + ","+yaoguais[i].getPosition().getY()+")");
            }
            else if(key == KeyEvent.VK_Y){
                for(int i = 0; i < huluwas.length; ++i)
                    if(huluwas[i].isAlive()) System.out.println("huluwa"+(i+1)+": alive");
                for(int i = 0; i < yaoguais.length; ++i)
                    if(yaoguais[i].isAlive()) System.out.println("yaoguai" + (i+1) +": alive");
            }
            else if(key == KeyEvent.VK_R){
                restartLevel();
            }
            else if(key == KeyEvent.VK_L){
                String recorName = openDialog();
                if(recorName != null)
                    readRecord("src/records/" + recorName);
            }
            else if(key == KeyEvent.VK_W){
                Creature.creatureWait();
            }
            else if(key == KeyEvent.VK_Q){
                synchronized (this) {
                    Creature.creatureWakeup();
                    notifyAll();
                }
            }
            else if(key == KeyEvent.VK_DOWN){
                try {
                    traceForward();
                }
                catch (FileNotExistException notExist){ notExist.printStackTrace();}
            }
            else if(key == KeyEvent.VK_UP){
                try {
                    traceBack();
                }
                catch (FileNotExistException notExist){ notExist.printStackTrace();}
            }
            repaint();
        }
    }

    public void restartLevel() {
        tiles.clear();
        clearBoard();
        Creature.resetArgument();
        try{
            initWorld();
        }catch (OutOfBoundException e){ e.printStackTrace();}

    }

    public void record(String dictoryPath){
        File dir = new File(dictoryPath);
        if(dir.exists() && Creature.getStepCount() == 0){
            if(dir.isDirectory()){
                File[] files = dir.listFiles();
                for(int i = 0; i < files.length; ++i)
                    files[i].delete();
            }
            dir.delete();
        }
        dir.mkdir();
        String filePath = dictoryPath + "/record" + Creature.getStepCount() + ".txt";
        File file = new File(filePath);
        if(!file.exists()){
            try{file.createNewFile();}
            catch (Exception e) { e.printStackTrace();}
        }

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < this.getHuluwas().length; ++i) {
            Creature creature = this.getHuluwas()[i];
            String str = creature.getPosition().getX() + " " + creature.getPosition().getY() + " " +
                    creature.isAlive() + " " + creature.getSpeed() + " " + creature.getStrength() + " " + creature.getLegion();
            list.add(str);
        }
        for(int i = 0; i < this.getYaoguais().length; ++i) {
            Creature creature = this.getYaoguais()[i];
            String str = creature.getPosition().getX() + " " + creature.getPosition().getY() + " " +
                    creature.isAlive() + " " + creature.getSpeed() + " " + creature.getStrength() + " " + creature.getLegion();
            list.add(str);
        }
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            for(String s: list)
                ps.println(s);
        }
        catch (Exception e) { e.printStackTrace();}
    }

    public void readRecord(String recordName){
        File file = new File(recordName);
        ArrayList<String> stringList = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null){
                stringList.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int len = this.getHuluwas().length;
        for(int i = 0; i < len; ++i){
            String[] array = stringList.get(i).split("\\s+");
            int posX = Integer.parseInt(array[0]), posY = Integer.parseInt(array[1]);
            boolean aliveStatus = Boolean.parseBoolean(array[2]);
            int speed = Integer.parseInt(array[3]), strength = Integer.parseInt(array[4]), legion = Integer.parseInt(array[5]);
            if(i < 7)
                huluwas[i] = new Huluwa(this, board[posX][posY],COLOR.values()[i], SENIORITY.values()[i]);
            else
                huluwas[i] = new Laoyeye(this, board[posX][posY]);
            if(aliveStatus == false){ huluwas[i].dead(); huluwas[i].setImage("resources/dead1.png");}
        }
        for(int i = 0; i < this.getYaoguais().length; ++i){
            String[] array = stringList.get(i+len).split("\\s+");
            int posX = Integer.parseInt(array[0]), posY = Integer.parseInt(array[1]);
            boolean aliveStatus = Boolean.parseBoolean(array[2]);
            int speed = Integer.parseInt(array[3]), strength = Integer.parseInt(array[4]), legion = Integer.parseInt(array[5]);
            if(i == 0) yaoguais[i] = new Xiezijing(this, board[posX][posY]);
            else if(i == 1) yaoguais[i] = new Shejing(this, board[posX][posY]);
            else yaoguais[i] = new Xiaoyaoguai(this, board[posX][posY]);
            if(i >= 4) yaoguais[i].setImage("resources/yaojing.png");
            if(aliveStatus == false) { yaoguais[i].dead(); yaoguais[i].setImage("resources/dead2.png");}
        }
    }

    public void traceForward() throws FileNotExistException{
        if(fileName == null) throw new FileNotExistException("file does not exist\n");

        String str = fileName.replaceAll("[^0-9]", "");
        int idx = Integer.parseInt(str) + 1;
        str = "record" + idx + ".txt";
        String filePath = "src/records/" + str;
        File file = new File(filePath);
        if(!file.exists()) throw new FileNotExistException("The current file is at the end");
        else{
            fileName = str;
            readRecord(filePath);
        }
    }

    public void traceBack() throws FileNotExistException {
        if(fileName == null) throw new FileNotExistException("File does not exist\n");

        String str = fileName.replaceAll("[^0-9]", "");
        int idx = Integer.parseInt(str) - 1;
        str = "record" + idx + ".txt";
        String filePath = "src/records/" + str;
        File file = new File(filePath);
        if(!file.exists()) throw new FileNotExistException("The current file is at the begin");
        else {
            fileName = str;
            readRecord(filePath);
        }
    }

    public String openDialog(){
        String recordName = null;
        JFileChooser jfc=new JFileChooser(new File("src/records"));
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        int result = jfc.showOpenDialog(new JFrame());
        if(result == JFileChooser.CANCEL_OPTION); //关闭
        else{
            File file = jfc.getSelectedFile();
            this.fileName = recordName = file.getName();
        }
        return recordName;
    }
}