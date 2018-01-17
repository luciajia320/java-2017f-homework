import com.sun.org.apache.regexp.internal.RE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 40;
    private boolean battleOver = true;
    private boolean replaying = false;

    private Replay replay;

    private ArrayList tiles = new ArrayList();
    private ArrayList<Creature> creatures = new ArrayList<Creature>();

    ExecutorService exec = Executors.newCachedThreadPool();

    private int w = 460;
    private int h = 380;
    private boolean completed = false;

    private static BackGround backGround = new BackGround();

    private String level =
                    "1       l \n" +
                    " 2       l\n" +
                    "  3     s \n" +
                    "   4     l\n" +
                    "  5     x \n" +
                    " 6       l\n" +
                    "7       l \n" +
                    "y        l\n";

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        //initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Tile a;

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item >= '1' && item <= '7') {
                switch (item){
                    case '1':
                        creatures.add(new HuluWa(HuluColor.红 , Name.大, x, y, this));
                        break;
                    case '2':
                        creatures.add(new HuluWa(HuluColor.橙 , Name.二, x, y, this));
                        break;
                    case '3':
                        creatures.add(new HuluWa(HuluColor.黄 , Name.三, x, y, this));
                        break;
                    case '4':
                        creatures.add(new HuluWa(HuluColor.绿 , Name.四, x, y, this));
                        break;
                    case '5':
                        creatures.add(new HuluWa(HuluColor.青 , Name.五, x, y, this));
                        break;
                    case '6':
                        creatures.add(new HuluWa(HuluColor.蓝 , Name.六, x, y, this));
                        break;
                    case '7':
                        creatures.add(new HuluWa(HuluColor.紫 , Name.七, x, y, this));
                        break;
                }
                x += SPACE;
            }
            else if(item >= 'a' && item  <= 'z'){
                switch (item) {
                    case 'y':
                        creatures.add(new Yeye(x, y, this));
                        break;
                    case 's':
                        creatures.add(new Shejing(x, y, this));
                        break;
                    case 'x':
                        creatures.add(new Xiezi(x, y, this));
                        break;
                    case 'l':
                        creatures.add(new Louluo(x, y, this));
                        break;
                }
                x += SPACE;
            }
            else if (item == ' ') {
                x += SPACE;
            }
        }

    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        //world.addAll(tiles);
        world.add(backGround);
        world.addAll(creatures);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Creature) {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
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

            if (completed) {
                return;
            }

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                //player.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {
                if(replaying) {
                    if (replay.next()) ;
                    else {
                        replay.close();
                        replaying = false;
                    }
                }

                //player.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


                //player.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


                //player.move(0, SPACE);

            } else if (key == KeyEvent.VK_SPACE) {
                //creatures.clear();
                if(battleOver) {
                    Creature.CreateRecorder();
                    //initWorld();
                    if (replaying) {
                        replaying = false;
                        replay.close();
                    }
                    restartLevel();

                    battleOver = false;
                    for (Creature i : creatures)
                        exec.execute(i);
                }
            } else if (key == KeyEvent.VK_R) {
                //restartLevel();
            } else if (key == KeyEvent.VK_L)  {

                if(battleOver && !replaying) {
                    replaying = true;
                    replay = new Replay(Field.this);
                }
            }

            repaint();
        }
    }

    public void restartLevel() {

        tiles.clear();
        creatures.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }

    public int getOFFSET(){
        return OFFSET;
    }

    public int getSPACE(){
        return SPACE;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void setBattleOver(){
        battleOver = true;
    }
}