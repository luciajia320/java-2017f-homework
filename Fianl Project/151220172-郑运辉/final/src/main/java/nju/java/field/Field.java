package nju.java.field;

import nju.java.common.GLOBAL;
import nju.java.common.IOFile;
import nju.java.common.cell;
import nju.java.creature.*;
import nju.java.field.BattleAttr;
import nju.java.field.MODE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Field extends JPanel implements Serializable{
    public int getBoardHeight() {
        return BoardHeight;
    }
    public int getBoardWidth() {
        return BoardWidth;
    }
    public int getXcount() { return Xcount; }
    public int getYcount(){return Ycount;}
    public synchronized void setFieldGround(cell first, cell second, BattleAttr battleAttr){
        fieldGround[first.cellX][first.cellY]=BattleAttr.SPACE;
        fieldGround[second.cellX][second.cellY]=battleAttr;
    }
    public BattleAttr getFieldGround(cell c){
        return fieldGround[c.cellX][c.cellY];
    }
    public BattleAttr getFieldGround(int x,int y){
        return fieldGround[x][y];
    }

    // 计时器以及事件监听器
    private Timer timer ;
    private ActionListener timerTask ;
    private void LoadOrSave(){
        if(mode== MODE.RECORD){
            try {
                IOFile.writeFile(world);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(mode==MODE.REPLAY){
            String str = null;
            if (IOFile.getReadFile() == null) {
                return;
            }
            str = IOFile.getNextString();
            if (str != null) {
                playback(str);
            } else {
                mode = mode.OVER;
            }
            repaint();
        }
    }

    private void playback(String str){
        String name = null;
        int id=0;
        int x = -1, y = -1;
        boolean die = false;

        String[] readFileLine = str.split(" ");
        if(readFileLine.length != 5)
            return;
        name = readFileLine[0];
        id = Integer.parseInt(readFileLine[1]);
        x = Integer.parseInt(readFileLine[2]);
        y = Integer.parseInt(readFileLine[3]);
        die = (readFileLine[4].equals("true")) ? true:false;

        if (name.equals("GOOD") ) {
            HuluwaList.get(id).setX(x);
            HuluwaList.get(id).setY(y);
            if(die){
                HuluwaList.get(id).Exit();
            }
        }
        else{
            AnimalList.get(id).setX(x);
            AnimalList.get(id).setY(y);
            if(die){
                AnimalList.get(id).Exit();
            }
        }
        repaint();
    }

    public void initTimer(int time){
        timerTask = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LoadOrSave();
            }
        };
        timer = new Timer(time,timerTask);
        timer.start();
    }

    public Field(){
        URL path = this.getClass().getClassLoader().getResource("Background.jpg");
        background = new ImageIcon(path);
        BoardWidth = background.getIconWidth();
        BoardHeight = background.getIconHeight();
        mode=MODE.BEGIN;
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    private void LoadThing(){
        HuluwaList.clear();
        AnimalList.clear();


        for(int i=0;i<7;i++){
            HuluwaList.add(new Huluwa(new cell(0,i* GLOBAL.TIMES), COLOR.values()[i],this,AnimalList));
        }

        for(int i=0;i<2;i++){
            for(int j=0;j<Ycount/GLOBAL.TIMES;j++){
                Animal animal;
                if(i==0 && j==3)
                    animal=new Animal(new cell(Xcount-(i+1)*GLOBAL.TIMES,j*GLOBAL.TIMES), BAD.xiezi,this,this.HuluwaList);
                else if(i==0 && j==4)
                    animal=new Animal(new cell(Xcount-(i+1)*GLOBAL.TIMES,j*GLOBAL.TIMES),BAD.she,this,this.HuluwaList);
                else
                    animal=new Animal(new cell(Xcount-(i+1)*GLOBAL.TIMES,j*GLOBAL.TIMES),BAD.wugong,this,this.HuluwaList);
                animal.setID(i*(Ycount/GLOBAL.TIMES)+j);
                AnimalList.add(animal);
            }

        }

        for(int i=0;i<Ycount;i++)
            fieldGround[0][i]=BattleAttr.GOOD;
        fieldGround[10*GLOBAL.TIMES][3*GLOBAL.TIMES]=BattleAttr.BAD;
        repaint();
    }

    private ArrayList world = new ArrayList();

    private final void initWorld() {
        inifieldGround();
        LoadThing();
    }

    public void buildWorld(Graphics g) {
        setBackground(g);
        world.clear();
        world.addAll(HuluwaList);
        world.addAll(AnimalList);

        Collections.sort(world, new SortComparator());

        for (int i = 0; i < world.size(); i++) {
            Thing2D item = (Thing2D) world.get(i);
            if(item.isExit())
                g.drawImage(item.getDieImage(), item.x(), item.y(), this);
            else
                g.drawImage(item.getImage(), item.x(), item.y(), this);
        }
    }

    @Override
    public synchronized void paint(Graphics g) {
        super.paint(g);
        if(mode==MODE.RECORD) {
            check();

        }
        buildWorld(g);
        checkEnding(g);
    }

    //完毕
    private boolean GoodEnd;
    //背景大小
    private int BoardWidth ;
    private int BoardHeight ;
    //x、y方向格子数
    private int Xcount=11*GLOBAL.TIMES;
    private int Ycount=7*GLOBAL.TIMES;
    //背景图
    private ImageIcon background;
    //存储
    private ArrayList<Huluwa> HuluwaList=new ArrayList<Huluwa>();
    private ArrayList<Animal> AnimalList=new ArrayList<Animal>();
    //标志区域
    private BattleAttr[][] fieldGround;

    //设置背景图片
    private void setBackground(Graphics g) {
        g.drawImage(background.getImage(),0,0,background.getImageObserver());
    }

    //初始化场地
    private void inifieldGround(){
        fieldGround=new BattleAttr[Xcount][Ycount];
        for(int i=0;i<Xcount;i++)
            for(int j=0;j<Ycount;j++)
                fieldGround[i][j]=BattleAttr.SPACE;
    }

    //检测战斗
    private void check(){
        int i,j;
        for(i=0;i<HuluwaList.size();i++) {
            for (j = 0; j < AnimalList.size(); j++) {
                //System.out.println(i+" "+!HuluwaList.get(i).isExit()+" "+j+" "+!AnimalList.get(j).isExit());
                //System.out.println(!HuluwaList.get(i).isExit() && !AnimalList.get(j).isExit());
                if(!HuluwaList.get(i).isExit() && !AnimalList.get(j).isExit()){
                    if (HuluwaList.get(i).getC().isNeig(AnimalList.get(j).getC())){
                        if (GLOBAL.checkWin(HuluwaList.get(i).getPow(), AnimalList.get(j).getPow())) {
                            AnimalList.get(j).Exit();
                            fieldGround[AnimalList.get(j).getC().cellX][AnimalList.get(j).getC().cellY] = BattleAttr.SPACE;
                        } else {
                            HuluwaList.get(i).Exit();
                            fieldGround[HuluwaList.get(i).getC().cellX][HuluwaList.get(i).getC().cellY] = BattleAttr.SPACE;
                        }
                        break;
                    }
                }
            }
        }
    }

    private void checkEnding(Graphics g){
        int i;
        for(i=0;i<HuluwaList.size();i++){
            if(!HuluwaList.get(i).isExit()){
                break;
            }
        }
        if(i == HuluwaList.size() -1){
            mode=MODE.OVER;
            GoodEnd=false;
            return;
        }

        int j;
        for(j=0;j<AnimalList.size();j++) {
            if(!AnimalList.get(j).isExit()){
                break;
            }
        }

        if(j == AnimalList.size()-1) {
            mode=MODE.OVER;
            GoodEnd=true;
            return;
        }
    }
    //结局
    private void Ending(Graphics g){
        try{
            Thread.sleep(100);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if(GoodEnd){
            URL path = this.getClass().getClassLoader().getResource("GoodEnd.jpg");
            ImageIcon pic = new ImageIcon(path);
            g.drawImage(pic.getImage(),0,0,BoardWidth,BoardHeight,pic.getImageObserver());
            for(int i=0;i<HuluwaList.size();i++){
                HuluwaList.get(i).Exit();
            }
        }
        else{
            URL path = this.getClass().getClassLoader().getResource("BadEnd.jpg");
            ImageIcon pic = new ImageIcon(path);
            g.drawImage(pic.getImage(),0,0,BoardWidth,BoardHeight,pic.getImageObserver());
            for(int j=0;j<AnimalList.size();j++){
                AnimalList.get(j).Exit();
            }
        }
        mode=MODE.BEGIN;
    }

    //模式
    private MODE mode;

    //写文件
    private File file;
    //弹出选择文件框
    private void fileChoose(String s){
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "test");
        file=jfc.getSelectedFile();
    }

    class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key==KeyEvent.VK_E){
                System.exit(0);
            }

            if(mode != MODE.RECORD){
                if (key == KeyEvent.VK_R) {
                    mode=MODE.BEGIN;
                    for (Huluwa h:HuluwaList){
                        h.Exit();
                    }
                    for (Animal a:AnimalList){
                        a.Exit();
                    }
                    LoadThing();
                }
            }

            if(mode==MODE.OVER || mode==MODE.BEGIN) {
                if (key == KeyEvent.VK_SPACE) {
                    mode=MODE.RECORD;;
                    //反转数组，防止第一个太快
                    //Collections.reverse(HuluwaList);
                    //Collections.reverse(AnimalList);

                    ExecutorService exec = Executors.newCachedThreadPool();
                    for (Huluwa h:HuluwaList){
                        exec.execute(h);
                    }
                    for (Animal a:AnimalList){
                        exec.execute(a);
                    }
                    exec.shutdown();

                    initTimer(100);
                }

                if(key ==KeyEvent.VK_L){
                    mode = MODE.REPLAY;
                    fileChoose("请选择回放文件");
                    IOFile.setReadFile(file);
                    LoadThing();
                    initTimer(10);
                }
            }
            else
                ;

        }
    }
}

