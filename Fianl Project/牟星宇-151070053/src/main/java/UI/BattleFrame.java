package UI;

import ENUM.STATE;
import Ground.BattleGround;
import Hulu.Anno.AuthorAnno;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@AuthorAnno(name = "牟星宇", studentNum = 151070053, department = "信管")
public class BattleFrame extends JFrame {

    public static final int WIDTH = 1000; // 面板宽
    public static final int HEIGHT = 720; // 面板高
    public static final int ROWS = 10;//行数
    public static final int COLS = 15;//列数

    private static BattleGround battleGround = new BattleGround(COLS,ROWS);

    private Thread thread;

    public Thread getThread() { return thread;}

    public void setThread(Thread t){
        this.thread = t;
    }

    public BattleFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("HuluWorld");

        initial();

        setFocusable(true);
        setVisible(true);

    }


    public void initial(){

        //菜单栏
        this.setJMenuBar(new BattleMenu(this));

        battleGround.setBattleFrame(this);

        add(battleGround);


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {
                    start();
                } else if (key == KeyEvent.VK_R) {
                    restart();
                } else if(key == KeyEvent.VK_P){
                    pause();
                } else if(key == KeyEvent.VK_ESCAPE){
                    exit();
                }else if(key == KeyEvent.VK_L){
                    readSave();
                }else if(key == KeyEvent.VK_S){
                    save();
                }
            }
        });
    }

    public void start(){
        if(battleGround.getState() == STATE.READY){
            thread = new Thread(battleGround);
            thread.start();
            battleGround.start();
        }else if(battleGround.getState() == STATE.REPLAY){
            thread = new Thread(battleGround);
            thread.start();
            battleGround.getRecordPlayer().replay();
        } else{
            battleGround.pauseAll();
            Object[] options = { "确定", "取消" };
            int r = JOptionPane.showOptionDialog(null, "战斗未准备好", "警告",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
            battleGround.pauseAll();
            return;
        }
    }

    public void restart(){

        int r = JOptionPane.showConfirmDialog(this, "确定重新开始？", "提示", JOptionPane.YES_NO_OPTION);
        if(r == 0) {
            battleGround.stop();
            battleGround.restart();
        }
    }

    public void exit(){
        battleGround.pauseAll();
        Object[] options = {"确定","取消"};
        int r = JOptionPane.showConfirmDialog(this, "是否退出", "提示", JOptionPane.YES_NO_OPTION);
        if(r == 0) {
            dispose();
            battleGround.exit();
        }else if(r == 1){
            battleGround.pauseAll();
        }
    }

    public void save(){
        if(battleNotStop()) return;
        if(battleGround.getState() != STATE.OVER){
            Object[] options = { "确定", "取消" };
            int r = JOptionPane.showOptionDialog(null, "请先开始游戏再保存！", "警告",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
            return;
        }
        JFileChooser fd = new JFileChooser();
        fd.showSaveDialog(null);
        if(fd.getSelectedFile() != null) {
            battleGround.save(fd.getSelectedFile());
        }
    }

    public void readSave(){
        if(battleNotStop()) return;

        JFileChooser fd = new JFileChooser();
        fd.showOpenDialog(null);
        if(fd.getSelectedFile() != null) {
            battleGround.readSave(fd.getSelectedFile());
        }
    }

    public void pause(){battleGround.pauseAll();};

    public void gameInstruct(){

        pause();
        String gameInfo = "空格键 控制游戏开始\n“P”键 控制游戏暂停/继续\n“R”键 开始新游戏\n“S键” 保存当前记录\n“L键”读取存档\n“ESC键” 退出";
        JOptionPane.showMessageDialog(null, gameInfo);
        pause();

    }

    public void authorInstruct(){
        pause();
        String authorInfo = "姓名：牟星宇\n学号：151070053\n院系：信息管理学院";
        JOptionPane.showMessageDialog(null, authorInfo);
        pause();
    }

    public boolean battleNotStop(){
        if(battleGround.getState() != STATE.OVER && battleGround.getState() == STATE.RUNNING){
            battleGround.pauseAll();
            Object[] options = { "确定", "取消" };
            int r = JOptionPane.showOptionDialog(null, "当前战斗未结束", "警告",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
            battleGround.pauseAll();
            return true;
        }
        return false;
    }


    public void mainOptions(String message){
        Object[] options = {"保存本次战斗","开始新游戏","载入记录","退出"};
        int r = JOptionPane.showOptionDialog(null, message, "本次战斗结束",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);
        if(r == 0){
            save();
        }else if(r == 1){
            restart();
        }else if(r == 2){
            readSave();
        }else if(r == 3){
            exit();
        }
    }
}
