
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.*;
import javax.swing.*;

import static java.lang.Thread.sleep;
import static java.util.concurrent.Executors.*;

public class Field extends JPanel {

    private final int OFFSET = 5;
    private final int SPACE = 60;
    private ImageIcon background;
    private Player dawa, erwa, sanwa, siwa, wuwa, liuwa, qiwa, scorpion, snake, grandfather;
    private CopyOnWriteArrayList<Player> soldiers = new CopyOnWriteArrayList<>();
    private ExecutorService exec;
    private record Record;
    private rePlay replay;
    private int w = 1300;
    private int h = 700;
    private boolean completed = false, start = false;

    public CopyOnWriteArrayList<Player> world = new CopyOnWriteArrayList<>();

    private String level =
                    ".~............(.....\n" +
                    ".!..............(...\n" +
                    ".@............(.....\n" +
                    ".#............&.(...\n" +
                    ".$............(.....\n" +
                    ".%..............(...\n" +
                    ".^............(.....\n" +
                    ")...............(...\n" +
                    "..................*.\n";

    Field() {
        URL url = getClass().getResource("battle.jpg");
        background = new ImageIcon(url);
        Image img = background.getImage();
        img = img.getScaledInstance(w, h, Image.SCALE_DEFAULT);
        background.setImage(img);
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, getBoardWidth(), getBoardHeight());
        add(label);

        setFocusable(true);

        initWorld();
        addKeyListener(new TAdapter());
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public void setCompleted() {
        completed = true;
    }
    public boolean getCompleted() { return completed; }

    public synchronized CopyOnWriteArrayList<Player> getWorld() {
        return world;
    }

    public final void initWorld() {
        exec = newCachedThreadPool();

        int x = OFFSET;
        int y = OFFSET;
        Record = new record(this);
        replay = new rePlay(this);

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }
                x = OFFSET;
            }

            else if (item == '.') {
                x += SPACE;
            }

            else if (item == '~') {
                dawa = new Dawa(x, y, this);
                x += SPACE;
            }
            else if (item == '!') {
                erwa = new Erwa(x, y, this);
                x += SPACE;
            }
            else if (item == '@') {
                sanwa = new Sanwa(x, y, this);
                x += SPACE;
            }
            else if (item == '#') {
                siwa = new Siwa(x, y, this);
                x += SPACE;
            }
            else if (item == '$') {
                wuwa = new Wuwa(x, y, this);
                x += SPACE;
            }
            else if (item == '%') {
                liuwa = new Liuwa(x, y, this);
                x += SPACE;
            }
            else if (item == '^') {
                qiwa = new Qiwa(x, y, this);
                x += SPACE;
            }
            else if (item == '&') {
                scorpion = new Scorpion(x, y, this);
                x += SPACE;
            }
            else if (item == '*') {
                snake = new Snake(x, y, this);
                x += SPACE;
            }
            else if (item == '(') {
                Player soldier = new Soldier(x, y, this);
                soldiers.add(soldier);
                x += SPACE;
            }
            else if (item == ')') {
                grandfather = new grandFather(x, y, this);
                x += SPACE;
            }

            else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }
    }

    public void buildWorld(Graphics g) {

        if (completed) {
            String out = "战斗结束！";
            g.setFont(new Font("隶书", Font.BOLD,100));
            g.setColor(Color.orange);
            double rate=0.90;
            int x = (int)(getBoardWidth()/2 - rate * g.getFontMetrics().stringWidth(out)/2);
            int y = getBoardHeight() / 2 + g.getFontMetrics().getHeight()/3;
            g.drawString(out, x, y);
            return;
        }

        else {
            world.add(dawa);
            world.add(erwa);
            world.add(sanwa);
            world.add(siwa);
            world.add(wuwa);
            world.add(liuwa);
            world.add(qiwa);
            world.add(scorpion);
            world.add(snake);
            world.add(grandfather);
            world.addAll(soldiers);

            for (Player item : getWorld()) {

                if (item != null && item.isAlive()) {
                    g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
                }

            }
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

                start = true;

                exec.execute(dawa);
                exec.execute(erwa);
                exec.execute(sanwa);
                exec.execute(siwa);
                exec.execute(wuwa);
                exec.execute(liuwa);
                exec.execute(qiwa);
                exec.execute(scorpion);
                exec.execute(snake);
                exec.execute(grandfather);

                for(Player soldier : soldiers)
                    exec.execute(soldier);
                exec.execute(Record);

            } else if (key == KeyEvent.VK_R) {

                start = false;
                restartLevel();

            } else if (key == KeyEvent.VK_E) {

                exec.shutdown();
                world.clear();
                completed = true;
                start = false;

            } else if ((key == KeyEvent.VK_L && !start) || (key == KeyEvent.VK_L && completed)) {
                exec.execute(replay);
            }

            repaint();
        }
    }



    public void restartLevel() {

        exec.shutdownNow();
        soldiers.clear();
        world.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}