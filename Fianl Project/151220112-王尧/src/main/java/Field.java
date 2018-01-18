import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import java.lang.StringBuilder;
import java.util.Arrays;

public class Field extends JPanel {

    private final int OFFSET = 240;
    private final int SPACE = 60;

    private ArrayList tiles = new ArrayList();
    //private Player player;

    private ArrayList justices = new ArrayList();
    private ArrayList evils = new ArrayList();

    private int w = 0;
    private int h = 0;
    boolean completed = false;

    private Image background;

    public ArrayList getJustices() {
        return justices;
    }

    public ArrayList getEvils() {
        return evils;
    }

    private int spaceX, spaceY;

    public int getSpaceX() {
        return spaceX;
    }

    public int getSpaceY() {
        return spaceY;
    }

    private Position[][] space;

    public void setSpace(int x, int y) {
        spaceX = x;
        spaceY = y + 1;
        space = new Position[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                space[i][j] = new Position(i, j);
    }

    /*
    private String level =
            "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n";
    */

    private String level;

    public void setLevel(int x, int y) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++)
                s.append('.');
            s.append('\n');
        }
        level = s.toString();

    }

    public void modifyLevel(int x, int y, char c) {
        char[] charArray = level.toCharArray();
        int index = spaceY * x + y;
        charArray[index] = c;
        level = new String(charArray);
        System.out.println(level);
    }

    public void setFormation() {
        Changshe changshe = new Changshe();
        changshe.form(7, new Position(1, 0), this);

        modifyLevel(4, 2, '8');

        Heyi heyi = new Heyi();
        heyi.form(4, new Position(3,9), this);
    }

    public Field(int x, int y) {

        addKeyListener(new TAdapter());
        setFocusable(true);

        loadImage();

        setSpace(x, y);

        setLevel(x, y);

        setFormation();

        initWorld();
    }

    public void loadImage() {
        URL loc = this.getClass().getClassLoader().getResource("back.jpg");
        ImageIcon iia = new ImageIcon(loc);
        background = iia.getImage();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = 0;
        int y = 0;

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = 0;
            }
            else if (item == '.') {
                x += SPACE;
            }
            else if (item >= '1' && item <= '7') {
                justices.add(new Huluwa(item- '0', this, new Position(x, y)));
                x += SPACE;
            }
            else if (item == '8') {
                justices.add(new Yeye(new Position(x,y), this));
                x += SPACE;
            }
            else if (item == '&') {
                evils.add(new Xiezijing(new Position(x,y), this));
                x += SPACE;
            }
            else if (item == '$') {
                evils.add(new Shejing(new Position(x,y), this));
                x += SPACE;
            }
            else if (item == '0') {
                evils.add(new Xiaolouluo(new Position(x,y), this));
                x += SPACE;
            }
            else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }
    }

    public void buildWorld(Graphics g) {

        //g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight()+240);
        g.drawImage(background,0,0,this);

        ArrayList world = new ArrayList();
        //world.addAll(tiles);


        world.addAll(justices);
        world.addAll(evils);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Creature) {
                if (((Creature) item).isAlive())
                    g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
                else
                    g.drawImage(item.getDeadImage(), item.x() + 2, item.y() + 2, this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }
        }
    }

    void updateState() {
        boolean flag = true;
        for(int i = 0; i < justices.size();i++) {
            Creature c = (Creature) justices.get(i);
            if (c.isAlive()) {
                flag = false;
                break;
            }
        }
        if(flag == false) {
            flag = true;
            for (int i = 0; i < evils.size(); i++) {

                Creature c = (Creature) evils.get(i);
                if (c.isAlive()) {
                    flag = false;
                    break;
                }
            }
        }
        completed = flag;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (completed) {
                return;
            }
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                start();
            }
            else if (key == KeyEvent.VK_R) {
                restartLevel();
            }
            else if (key == KeyEvent.VK_L) {
                //replay();
            }

            repaint();
        }
    }

    public void start() {
        if (!completed)
            restartLevel();

        for (int i = 0; i < justices.size(); i++) {
            new Thread((Creature)justices.get(i)).start();
        }
        for (int i = 0; i < justices.size(); i++) {
            new Thread((Creature)evils.get(i)).start();
        }
    }

    public void restartLevel() {

        tiles.clear();
        if (completed) {
            completed = false;
        }
        justices.clear();
        evils.clear();
        initWorld();
    }
}