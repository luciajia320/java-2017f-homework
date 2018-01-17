package tangwy.nju.edu.cn.huluwasvsdemons;

import tangwy.nju.edu.cn.huluwasvsdemons.things.creature.Creature;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GUIWindow extends JFrame implements ActionListener,KeyListener{
    public static final int
            offset=40,
            rowNum=20,
            columnNum=32;
    private static GUIWindow instance=new GUIWindow();
    public static ExecutorService exec = Executors.newCachedThreadPool();
    private static Battlefield battlefield=new Battlefield();
    private static int tick=0;
    private boolean
            running=true,
            completed=false,
            replayMode =false;
    private FunctionButton
            restartButton=new FunctionButton("New Battle",0,0,this,true),
            pauseButton=new FunctionButton("PAUSE",0,200,this,true),
            continueButton=new FunctionButton("CONTINUE",0,400,this,false),
            loadButton=new FunctionButton("LOAD",0,600,this,true);

    private SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Timer timer=new Timer(20,this);
    private Display display=new Display();
    private static int battleNum=0;
    public static int getBattleNum(){ return battleNum;}
    private static ArrayList<Replay> replay;

    public static ArrayList<Replay> getReplay() {
        return replay;
    }

    public static Battlefield getBattlefield() {
        return battlefield;
    }

    public static int getTick() {
        return tick;
    }

    public static GUIWindow getInstance(){
        return instance;
    }

    public synchronized void waitForRunning(int tick) throws InterruptedException{
        if(tick>=getTick()) wait();
    }

    public void keyReleased(KeyEvent event){
        int key=event.getKeyCode();
        if(key==KeyEvent.VK_N)restart();
        else if(key==KeyEvent.VK_SPACE)pause();
        else if(key==KeyEvent.VK_L)load();
    }
    //仅完成接口，无实际作用
    public void keyTyped(KeyEvent event){}

    public void keyPressed(KeyEvent event){}

    public void actionPerformed(ActionEvent event){
        Object source=event.getSource();
        if(source==restartButton)restart();
        else if(source==pauseButton)pause();
        else if(source==continueButton)pause();
        else if(source==loadButton)load();
        else if(source==timer){
            if(running){
                if(!replayMode){
                    try{
                        replay=new ArrayList<Replay>();
                        for(Creature creature:battlefield.getTeam1()){
                            if(!creature.isDied())
                                replay.add(new Replay(creature.getX(),creature.getY(),creature.getImageName()));
                        }
                        for(Creature creature:battlefield.getTeam2()){
                            if(!creature.isDied())
                                replay.add(new Replay(creature.getX(),creature.getY(),creature.getImageName()));
                        }
                        objectOutputStream.writeObject(replay);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    if(battlefield.getTeam1().isEmpty()||battlefield.getTeam2().isEmpty()){
                        completed=true;
                        running=false;
                        try{
                            objectOutputStream.close();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    updateButtonsStates();
                    display.repaint();
                }
                else{
                    try{
                       replay=(ArrayList<Replay>) objectInputStream.readObject();
                    }
                    catch (Exception e){
                        completed=true;
                        running=false;
                    }
                    updateButtonsStates();
                    display.repaint();
                }

                timeEvent();
            }
        }
        setFocusable(true);
    }

    //构造函数私有化的目的在于保证该类最多只有一个实例
    private  GUIWindow(){
        setLayout(null);
        setSize(240+1280,40+800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setTitle("葫芦娃大战妖精");
        add(restartButton);
        add(pauseButton);
        add(continueButton);
        add(loadButton);
        add(display);
        addKeyListener(this);
        try{
            fileOutputStream=new FileOutputStream(df.format(new Date())+".replay");
            objectOutputStream=new ObjectOutputStream(fileOutputStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        timer.start();
    }

    private synchronized void timeEvent(){
        tick++;
        notifyAll();
    }

    private synchronized void restart(){
        System.out.println("restart");
        battleNum++;
        tick++;
        notifyAll();
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }
        catch (Exception e){}
        try{
            fileOutputStream=new FileOutputStream(df.format(new Date())+".replay");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        completed=false;
        running=true;
        replayMode =false;
        battlefield=new Battlefield();
        updateButtonsStates();
        tick=0;
    }

    private synchronized void pause(){
        System.out.println("pause or continue");
        running=!running;
        updateButtonsStates();
    }


    private synchronized void load(){
        JFileChooser jFileChooser=new JFileChooser(".");
        jFileChooser.setFileSelectionMode(jFileChooser.FILES_ONLY );
        jFileChooser.showOpenDialog(new JLabel());
        File file=jFileChooser.getSelectedFile();
        try{
            FileInputStream fileInputStream=new FileInputStream(file);
            objectInputStream=new ObjectInputStream(fileInputStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        tick=0;
        replayMode=true;
        completed=false;
        running=true;
        updateButtonsStates();
    }

    private void updateButtonsStates(){
        pauseButton.setEnabled(running&&!completed);
        continueButton.setEnabled(!running&&!completed);
    }

}

class Replay implements Serializable{
    int x,y;
    String imageName;

    public Replay(int x, int y, String imageName) {
        this.x = x;
        this.y = y;
        this.imageName = imageName;
    }
}
