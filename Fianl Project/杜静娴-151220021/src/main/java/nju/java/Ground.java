package nju.java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Ground extends JFrame {

    private final int OFFSET = 30;

    private Mutex mutex = new Mutex();

    public Ground() {
        super("葫芦大战鼠兵蝎将");InitUI();
    }

    public void InitUI() {
        Field field = null;
        WarMonitor warmonitor = null;
        try {
            field = new Field(mutex);
            warmonitor = new WarMonitor(field,mutex);
            new Thread(warmonitor).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws InterruptedException {
        Ground ground = new Ground();
        ground.setVisible(true);
    }
}

class Mutex {           //互斥访问的对象
    private boolean waxOn = false;
    private int count = 16;

    public synchronized void waxed() { //打16层蜡才除一次
        count--;
        if(count == 0) {
            waxOn = true; // Ready to buff
            notifyAll();
        }
    }

    public synchronized void buffed() {
        count = 16;
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}