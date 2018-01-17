
/**
 * Created by qin on 18.1.3.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.text.Format;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Field extends JPanel {

    private final int OFFSET = 20;
    private final int SPACE = 20;
    private Thread[] threads=new Thread[16];
    private int countActionThread;
    private ArrayList tiles = new ArrayList();
    private Player[] players=new Player[16];
    private int countplayer;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    /*private String level =
            "................\n" +
                    "................\n" +
                    "................\n" +
                    "................\n" +
                    ".......@........\n" +
                    "................\n" +
                    "................\n" +
                    "................\n";*/

    private Position[][] positions=new Position[8][16];
    private Creature[][] creatures=new Creature[8][16];

    public void freshBattle(){
        boolean noneenemy=true;
        for(int i=0;i<16;i++)
            for(int j=i+1;j<16;j++){
                if(players[i].playerPartisan() ==players[j].playerPartisan())
                    continue;//同伙忽略
                else if(!players[i].Alive())
                    continue;
                else if(!players[j].Alive())
                    continue;
                else {
                    noneenemy=false;
                    if(players[i].distanceSqure(players[j])<1600) {
                        if (players[i].battle(players[j]))
                        {threads[j].interrupt();players[j].Dead(); players[j].setImage(null);}//j lose
                        else
                        {threads[i].interrupt();players[i].Dead();players[i].setImage(null);}//i lose
                        }
                    else continue;
                    }
                }
        if(noneenemy){
            for(int i=0;i<16;i++)
                if(players[i].Alive())
                    threads[i].interrupt();
        }
    }

    private void getRandomField(){
        Random r1=ThreadLocalRandom.current();
        Random r2=ThreadLocalRandom.current();
        Formation justice;
        Formation evil;
        switch (r1.nextInt(8)){
            case 0:justice=new FormationArrow(false);break;
            case 1:justice=new FormationBucket(false);break;
            case 2:justice=new FormationGoose(false);break;
            case 3:justice=new FormationMoon(false);break;
            case 4:justice=new FormationScale(false);break;
            case 5:justice=new FormationSnake(false);break;
            case 6:justice=new FormationWing(false);break;
            case 7:justice=new FormationYoke(false);break;
            default:justice=new FormationSnake(false);break;
        }
        switch (r2.nextInt(8)){
            case 0:evil=new FormationArrow(true);break;
            case 1:evil=new FormationBucket(true);break;
            case 2:evil=new FormationGoose(true);break;
            case 3:evil=new FormationMoon(true);break;
            case 4:evil=new FormationScale(true);break;
            case 5:evil=new FormationSnake(true);break;
            case 6:evil=new FormationWing(true);break;
            case 7:evil=new FormationYoke(true);break;
            default:evil=new FormationSnake(true);break;
        }

        for(int i=0;i<8;i++){
            int j=0;
            for(;j<8;j++) {
                this.creatures[i][j] = justice.getCreature(i,j);
                this.positions[i][j] = new Position(i,j);
                this.creatures[i][j].setPosition(this.positions[i][j]);
            }
            for(;j<16;j++) {
                this.creatures[i][j] = evil.getCreature(i,j-8);
                this.positions[i][j] = new Position(i,j);
                this.creatures[i][j].setPosition(this.positions[i][j]);
            }
        }
    }

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        getRandomField();
        countplayer=0;
        countActionThread = 0;

        int x = OFFSET;
        int y = OFFSET;

        Tile a;
/**/
        for (int i=0;i<8;i++){
            for(int j=0;j<16;j++){
                a=new Tile(x,y);
                tiles.add(a);

                if(creatures[i][j].getClass()==GrassGround.class)
                    ;
                else if(creatures[i][j].getClass()==GrandFa.class)
                    players[countplayer++] = new Player(x, y, this,creatures[i][j]);
                else if(creatures[i][j].getClass()==HuLuWa.class)
                    players[countplayer++] = new Player(x, y, this,creatures[i][j]);
                else if(creatures[i][j].getClass()==MonsterScorpion.class)
                    players[countplayer++] = new Player(x, y, this,creatures[i][j]);
                else if(creatures[i][j].getClass()==MonsterServent.class)
                    players[countplayer++] = new Player(x, y, this,creatures[i][j]);
                else if(creatures[i][j].getClass()==MonsterSnake.class)
                    players[countplayer++] = new Player(x, y, this,creatures[i][j]);
                x +=SPACE;
            }
            if(this.w<x)
            w=x;
            x=OFFSET;
            y+=SPACE;
            h=y;
        }

/*
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
            } else if (item == '@') {
                a = new Tile(x, y);
                tiles.add(a);
                players[countplayer++] = new Player(x, y, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }
//////////////////////////////////////////////////////////////////////
        players[countplayer++] = new Player(0+ OFFSET,0+OFFSET, this);
*/
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);

    for(int i=0;i<countplayer;i++)
        world.add(players[i]);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    public Player findClosest(Player a){
        int tmax=0;
        int pointer=0;
        for(int i=0;i<16;i++){
            if(a.playerPartisan() ==players[i].playerPartisan())
                continue;//同伙忽略
            else if(!players[i].Alive())
                continue;
            else {
                int t=a.distanceSqure(players[i]);
                if(t>tmax){
                    tmax=t;pointer=i;
                }
            }
        }

            return players[pointer];
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


            /*if (key == KeyEvent.VK_LEFT) {


                players[0].move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {


                players[0].move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


                players[0].move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


                players[0].move(0, SPACE);

            } else*/
            if(key==KeyEvent.VK_SPACE) {
                for (int i=0;i<countplayer;i++) {
                    threads[countActionThread]=new Thread(players[i]);
                    threads[countActionThread].start();
                    countActionThread++;
                }
            }
            else if(key==KeyEvent.VK_L){
                ;///////to be added
            }
            else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }


    public void restartLevel() {

        tiles.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}
