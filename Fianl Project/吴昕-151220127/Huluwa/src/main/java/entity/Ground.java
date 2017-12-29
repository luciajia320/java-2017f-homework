package entity;

import MyException.OutOfBounce;
import common.Constant;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 场地 */

public class Ground extends JPanel implements Constant, Runnable {
    public Ground() {
        for (int i = 0; i < GROUNDHEIGHT; i++)
            for (int j = 0; j < GROUNDWIDTH; j++)
                content[i][j] = -1;

        creatures = new ArrayList<>();
        brothers = new ArrayList<>();
        lackeys = new ArrayList<>();

        grandpa = new Grandpa(0, GROUNDHEIGHT / 2);
        creatures.add(grandpa);

        for (int i = 0; i < 7; i++)
            brothers.add(new Huluwa());
        new GooseSwing(new Location(0, 0), brothers);
        creatures.addAll(brothers);

        snake = new Snake(GROUNDWIDTH - 1, 0);
        scorpion = new Scorpion(GROUNDWIDTH - 1, GROUNDHEIGHT - 1);
        creatures.add(snake);
        creatures.add(scorpion);

        for (int i = 0; i < 7; i++)
            lackeys.add(new Monster());
        new XFormation(new Location(GROUNDWIDTH - XWIDTH, 0), lackeys);
        creatures.addAll(lackeys);

        for (Creature cre : brothers)
            cre.setEnemy(lackeys);
        for (Creature cre : lackeys)
            cre.setEnemy(brothers);

        Creature.ground = this;

        state = STATE.READY;
    }

    // 启动所有线程
    public void moveAll() {
        state = STATE.GOING;
        for (Creature cre : creatures) {
            if (cre.runnable == false) {
                cre.runnable = true;
                new Thread(cre).start();
            }
        }
    }

    // 终止所有线程, 这里通过抛出一个interrupt
    public void stopAll() {
        for (Creature cre : creatures) {
            cre.runnable = false;
            cre.interrupt();
        }
    }

    // 游戏重启
    public void restart() {
        state = STATE.READY;
        for (int i = 0; i < GROUNDHEIGHT; i++)
            for (int j = 0; j < GROUNDWIDTH; j++)
                content[i][j] = -1;
        for (Creature cre : creatures) {
            cre.live = true;
            try {
                cre.setImg();
            } catch (OutOfBounce e) {
                System.out.println(e.getMessage());
            }
        }
        new GooseSwing(new Location(0, 0), brothers);
        new XFormation(new Location(GROUNDWIDTH - XWIDTH, 0), lackeys);

        mf.thread = new Thread(this);
        mf.thread.start();
    }

    @Override
    public void run() {
        while (mf.thread.isInterrupted() == false) {
            if (state == STATE.OVER) {
                Creature cre = null;
                for (Creature c : creatures)
                    if (c.live == true) {
                        cre = c; break;
                    }
                gameOver(cre);
                mf.thread.interrupt();
            }
        }
    }

    // 游戏结束
    public void gameOver(Creature cre) {
        stopAll();
        String message;
        try {
            mf.fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        if (cre instanceof Huluwa) {
            scorpion.live = snake.live = false;
            scorpion.setImg();
            snake.setImg();
            repaint();
            message = "恭喜! 你赢了!";
        } else {
            grandpa.live = false;
            grandpa.setImg();
            repaint();
            message = "啊哦! 你输了!";
        }

        if (mf.fw != null) try {
            mf.fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        options(message);
    }

    public void options(String message) {

        Object[] options = {"开始新游戏", "读取存档", "退出"};

        // 开启新游戏面板提示
        int response = JOptionPane.showOptionDialog(null, message, "是否开始新游戏", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (response == 0) {
            getMf().dispose();
            getMf().newSave();
            restart();
            getMf().setVisible(true);
        }
        else if (response == 1) {
//            stopAll();
            JFileChooser jfc = new JFileChooser(SAVEPATH);
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = jfc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                replay(file);
            }
            else options("是否开启新游戏? ");
//            System.out.println(file.getName());
        }
        else  System.exit(0);
    }

    // 存档
    public void save() {
        StringBuilder sb = new StringBuilder();
        for (Creature cre : creatures)
            sb.append(cre.id + " " + cre.getX() + " " + cre.getY() + " " + cre.live + "\n");
        if (this.sb.equals(sb) == false) {
            try {
                mf.fw.write(sb.toString());
                mf.fw.write("\n");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    // 读档再现
    public synchronized void replay(File file) {
        state = STATE.SAVE;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            replayOnce();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // 读一次
    public synchronized void replayOnce() {
        String str;
        String[] sp;
        Creature cre;
        try {
            while ((str = br.readLine()) != null && str.equals("\n") == false) {
                sp = str.split(" ");
//                for (String s : sp) System.out.print(s + " "); System.out.println();
                if (sp[0].equals("") == true) break;
                cre = creatures.get(Integer.valueOf(sp[0]));
                cre.setPoint(Integer.valueOf(sp[1]), Integer.valueOf(sp[2]));
                cre.live = Boolean.valueOf(sp[3]);
//                System.out.println(cre.id + " " + cre.getX() + " " + cre.getY());
                try {
                    cre.setImg();
                } catch (OutOfBounce e) {
                    System.out.println(e.getMessage());
                }
                repaint();
            }

            if (str == null) {
                state = STATE.OVER;
                try {
                    if (fr != null) fr.close();
                    if (br != null) br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                options("存档读取完毕");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public synchronized void replayAll() {

    }

    public List<Creature> getCreatures() { return creatures; }
    public ArrayList<Creature> getBrothers() { return brothers; }
    public ArrayList<Creature> getLackeys() { return lackeys; }

    public void setMainFrame(MainFrame mf) { this.mf = mf; }
    public MainFrame getMf() { return mf; }

    public static STATE getState() { return state; }
    public void setState(STATE state) { this.state = state; }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(IMGPATH + "background.jpg");
//        g.drawImage(icon.getImage(), 0, 0, BGWIDTH, BGHEIGHT, icon.getImageObserver());
        g.drawImage(icon.getImage(), 0, 0, mf.getWidth(), mf.getHeight(), icon.getImageObserver());
        for (Creature cre : creatures)
            g.drawImage(cre.img, cre.getX() * IMGSIZE, cre.getY() * IMGSIZE, this);
    }

    // 表示当前位置矩阵放置的生物
    // -1表示没有生物, 若有生物, 存储生物id
    public static int content[][] = new int[GROUNDHEIGHT][GROUNDWIDTH];
    public static StringBuilder sb = new StringBuilder();
    private FileReader fr;
    public BufferedReader br;

    Grandpa grandpa;
    Scorpion scorpion;
    Snake snake;

    private static STATE state;
    private ArrayList<Creature> brothers;
    private ArrayList<Creature> lackeys;
    private List<Creature> creatures;
    private MainFrame mf;
}
