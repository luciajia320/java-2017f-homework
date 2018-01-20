package nju.java;

import java.util.ArrayList;

import static java.lang.Thread.yield;

public class WarMonitor implements Runnable {
    static Field field;

    private final int OFFSET = 60;

    private static boolean over = false;

    private Mutex mutex;

    static ArrayList<Player> good;
    static ArrayList<Player> bad;

    public WarMonitor(Field field, Mutex mutex) {
        this.field = field;
        this.mutex = mutex;
        good = field.getGoodStaff();
        bad = field.getBadStaff();
    }

    private synchronized static void battleOver(){
        over = true;
        field.gameOver();
    }

    public synchronized static void restart(){
        over = false;
        good = field.getGoodStaff();
        bad = field.getBadStaff();
    }

    public synchronized void run() {
        while (!Thread.interrupted()) {
            if(over)
                return;
            try {
                mutex.waitForWaxing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wax Off! ");
            ArrayList<Player> list = new ArrayList<Player>();
            list.addAll(good);
            list.addAll(bad);
            adjust(list);       //调整行进造成的位置交错
            int count = 0;
            for (Player p : good) {
                if (p.isAlive()) {
                    for (Player q : bad) {
                        if (q.isAlive()) {
                            count++;
                            break;
                        }
                    }
                }
                if(count > 0)
                    break;
            }
            if (count <= 0) {
                battleOver();
            }
            mutex.buffed();
            try {
                yield();
                Thread.sleep(10);
                field.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private synchronized void adjust(ArrayList<Player> list) {
        for(Player p:list){
            p.setMoved(false);
        }
        for(Player p:list) {
            if (!p.isStopped() && p.isAlive() && !p.isMoved()) {
                p.randMove();
                for (Player q : list) {
                    if (q != p) {
                        int xoff = Math.abs(p.getPos().getX() - q.getPos().getX());
                        int yoff = Math.abs(p.getPos().getY() - q.getPos().getY());
                        if (xoff <= OFFSET && yoff <= OFFSET) {
                            if (q.isAlive() && !q.isBusy() && ((p.isTeam() && !q.isTeam()) || (!p.isTeam() && q.isTeam()))) {
                                p.setMoved(true);
                                q.setMoved(true);
                                p.stopToFight(q);
                                q.stopToFight(p);
                                break;
                            } else {
                                p.randMove();
                            }
                        }
                    }
                }
            }
            if(!p.isMoved())
                p.setMoved(true);
        }
    }
}
