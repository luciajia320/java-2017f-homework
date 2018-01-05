import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.FILE;

public class Field extends JPanel {
    final int N = 15;
    static final int HLW_SUM = 7;
    public ArrayList<ArrayList<Position>> positions;
    public ArrayList<ArrayList<Creature>> creatures;
    int flag=0;
    int flag2=0;
    int sign1 = 0;

    FileOutputStream out;
    JLabel jl=new JLabel();
    public ArrayList<Position> getPositions(int line) {
        ArrayList<Position> tmpp = new ArrayList<Position>();
        for (int i = (N - HLW_SUM) / 2; i < (N + HLW_SUM) / 2; ++i)
            tmpp.add(positions.get(i).get(line));
        return tmpp;
    }

    public ArrayList<Creature> getCreatures(int line) {
        return creatures.get(line);
    }

    public ArrayList<ArrayList<Position>> getPositions() {
        return positions;
    }

    public ArrayList<ArrayList<Creature>> getCreatures() {
        return creatures;
    }

    public Field() {

        this.positions = new ArrayList<ArrayList<Position>>();
        this.creatures = new ArrayList<ArrayList<Creature>>();
        for (int i = 0; i < N; ++i) {
            this.positions.add(new ArrayList<Position>());
            this.creatures.add(new ArrayList<Creature>());
        }
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) {
                this.creatures.get(i).add(new Blank(i,j));
                this.positions.get(i).add(new Position(i, j));/*[i][j]*/
                this.creatures.get(i).get(j).setPosition(this.positions.get(i).get(j));
            }
    }

    public void putIn(Queue queue) {
        ArrayList<Position> tmp1 = queue.getPositions();
        for (int i = 0; i < tmp1.size(); ++i) {
            int x = tmp1.get(i).getX();
            int y = tmp1.get(i).getY();
            this.positions.get(x).get(y).setHolder(tmp1.get(i).getHolder());
            this.creatures.get(x).set(y, tmp1.get(i).getHolder());
            this.creatures.get(x).get(y).setPosition(tmp1.get(i));
        }
    }

    public void rollCall() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j)
                this.positions.get(i).get(j).getHolder().report();
            System.out.println();
        }
        System.out.flush();
    }

    private final int OFFSETX = 125;
    private final int OFFSETY = 0;

    private final int SPACE = 50;
    private int w = 0;
    private int h = 0;
    public boolean completed = false;

    public void initfield() {
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

    public ArrayList<Tile> tiles = new ArrayList<Tile>();

    public final void initWorld() {

        int x = OFFSETX;
        int y = OFFSETY;

        Tile a = new Tile(0, 0);
        tiles.add(a);
        Creature c;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                Position tmpp = positions.get(i).get(j);
                if (tmpp.getHolder() instanceof HuLuWa) {
                    if (((HuLuWa) tmpp.getHolder()).getname() == "赤") {
                        c = new HuLuWa("赤",x, y, this, 1);
                        this.creatures.get(i).set(j,c);
                        this.positions.get(i).get(j).setHolder(c);
                        c.setPosition(this.positions.get(i).get(j));
                    }
                    if (((HuLuWa) tmpp.getHolder()).getname() == "橙") {
                        c = new HuLuWa("橙",x, y, this, 2);
                        this.creatures.get(i).set(j,c);
                        this.positions.get(i).get(j).setHolder(c);
                        c.setPosition(this.positions.get(i).get(j));
                    }
                    if (((HuLuWa) tmpp.getHolder()).getname() == "黄") {
                        c = new HuLuWa("黄",x, y, this, 3);
                        this.creatures.get(i).set(j,c);
                        this.positions.get(i).get(j).setHolder(c);
                        c.setPosition(this.positions.get(i).get(j));
                    }
                    if (((HuLuWa) tmpp.getHolder()).getname() == "绿") {
                        c = new HuLuWa("绿",x, y, this, 4);
                        this.creatures.get(i).set(j,c);
                        this.positions.get(i).get(j).setHolder(c);
                        c.setPosition(this.positions.get(i).get(j));
                    }
                    if (((HuLuWa) tmpp.getHolder()).getname() == "青") {
                        c = new HuLuWa("青",x, y, this, 5);
                        this.creatures.get(i).set(j,c);
                        this.positions.get(i).get(j).setHolder(c);
                        c.setPosition(this.positions.get(i).get(j));
                    }
                    if (((HuLuWa) tmpp.getHolder()).getname() == "蓝") {
                        c = new HuLuWa("蓝",x, y, this, 6);
                        this.creatures.get(i).set(j,c);
                        this.positions.get(i).get(j).setHolder(c);
                        c.setPosition(this.positions.get(i).get(j));
                    }
                    if (((HuLuWa) tmpp.getHolder()).getname() == "紫") {
                        c = new HuLuWa("紫",x, y, this, 7);
                        this.creatures.get(i).set(j,c);
                        this.positions.get(i).get(j).setHolder(c);
                        c.setPosition(this.positions.get(i).get(j));
                    }
                    x += SPACE;
                } else if (tmpp.getHolder() instanceof Blank) {
                    c = new Blank("空",x, y,this);
                    this.creatures.get(i).set(j,c);
                    this.positions.get(i).get(j).setHolder(c);
                    c.setPosition(this.positions.get(i).get(j));
                    x += SPACE;
                } else if (tmpp.getHolder() instanceof Grandfather) {
                    c = new Grandfather("🎅",x, y, this, 8);
                    this.creatures.get(i).set(j,c);
                    this.positions.get(i).get(j).setHolder(c);
                    c.setPosition(this.positions.get(i).get(j));
                    x += SPACE;
                } else if (tmpp.getHolder() instanceof Monster) {
                    if (((Monster) tmpp.getHolder()).getname() == "🐛")
                        c = new Monster("🐛",x, y, this, 9);
                    else if (((Monster) tmpp.getHolder()).getname() == "🐍")
                        c = new Monster("🐍",x, y, this, 10);
                    else
                        c = new Monster("💀",x, y, this, 11);
                    this.creatures.get(i).set(j,c);
                    this.positions.get(i).get(j).setHolder(c);
                    c.setPosition(this.positions.get(i).get(j));
                    x += SPACE;
                }
            }
            y += SPACE;
            if (this.w < x) {
                this.w = x;
            }
            x = OFFSETX;
            h = y;
        }
        w-=OFFSETX;
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(125, 0, this.getWidth(), this.getHeight());

        Creature item =tiles.get(0);
        g.drawImage(item.getImage(), item.x(), item.y(), this);
        if (completed) {
            g.setColor(new Color(0, 0, 0));
            g.drawString("Completed", 25, 20);
        }
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j) {
                if (!(creatures.get(i).get(j).getPosition().getHolder() instanceof Blank)){
                    item=creatures.get(i).get(j);
                    g.drawImage(item.getImage(), item.x(), item.y(), this);
                    if (completed) {
                        g.setColor(new Color(0, 0, 0));
                        g.drawString("Completed", 25, 20);
                    }
                }

            }
        }
        g.setColor(new Color(25, 150, 150));
        g.setFont(new Font("宋体",Font.BOLD,20));
        g.drawString("SPACE开始 很抱歉(┬＿┬)由于移动后原位置一直消除不了L键复盘未成功战场记录在out.txt", 0, 650);
        if(sign1==1){
            g.setColor(new Color(255, 0, 0));
            //Font font = new Font("Arial",Font.BOLD,12);
            g.setFont(new Font("宋体",Font.BOLD,40));
            g.drawString("爷爷阵亡 葫芦娃lose(┬＿┬)", 200, 400);
        }
        else if (sign1==2){
            g.setColor(new Color(255, 0, 0));
            g.setFont(new Font("宋体",Font.BOLD,40));
            g.drawString("妖精阵亡 葫芦娃win♪(＾∀＾●)ﾉ", 200, 400);
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

            if (key == KeyEvent.VK_SPACE) {
                // new Thread(player).start();
               try{
                  //  out=new FileOutputStream("C:\\Users\\Thinkad\\IdeaProjects\\HuLuWa_Final\\src\\out.txt");
                   out=new FileOutputStream("out.txt");
                }
                catch (FileNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override

                    public void run() {
                        for (int i = 0; i < N && flag == 0; ++i)
                            for (int j = 0; j < N && flag == 0; ++j) {
                                if (creatures.get(i).get(j) instanceof Monster && creatures.get(i).get(j).exist) {
                                    flag = 1;
                                }
                            }
                        //flag==0 妖精全死了
                        for (int i = 0; i < N && flag2 == 0; ++i)
                            for (int j = 0; j < N && flag2 == 0; ++j) {
                                if ((creatures.get(i).get(j) instanceof HuLuWa ||creatures.get(i).get(j) instanceof Grandfather) && creatures.get(i).get(j).exist) {
                                    flag2 = 1;
                                }
                            }
                        //flag2==0 葫芦娃 爷爷全死了
                       // System.out.println(flag+" "+flag2);
                        if (flag == 0 || flag2 == 0) {
                            record();

                            for (int i = 0; i < N; ++i)
                                for (int j = 0; j < N; ++j) {
                                    if (creatures.get(i).get(j) instanceof Grandfather && !creatures.get(i).get(j).exist) {
                                        sign1 = 1; //爷爷死了
                                        break;
                                    }
                                }
                            if(sign1==0)//爷爷没死 妖怪死光了
                                sign1=2;
                            for (int i = 0; i < N; ++i)
                                for (int j = 0; j < N; ++j) {
                                    creatures.get(i).get(j).setexist(false);
                                }
                            this.cancel();

                            //sign1 0未开始 1葫芦娃输了 2葫芦娃赢了

                           //     System.out.println("葫芦娃赢了");
                            try {
                                out.close();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    else
                        record();

                    }
                },0,2000);
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < N; ++j) {
                        if( !(creatures.get(i).get(j) instanceof Blank) )
                            new Thread(creatures.get(i).get(j)).start();
                    }

                }
                System.out.println(sign1);
                repaint();

            }
            else if(key==KeyEvent.VK_L){
                String strin="\0";

                File file=new File("out.txt");
                try {
                    FileInputStream in = new FileInputStream(file);
                    int size=in.available();
                    byte[] buff=new byte[size];
                    in.read(buff);
                    in.close();
                    strin=new String(buff,"UTF-8");
                    System.out.println(strin);
                }
                catch (FileNotFoundException e1)
                {
                    e1.printStackTrace();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }

              //  initfield();
                System.out.println("我很抱歉，由于存在bug(移动时生物体可以移入新位置 但原位置新移入Blank生物体始终不成功)复盘难以实现");
                System.out.println("战场移动记录在out.txt");
            }
        }
    }
    public void record(){
        flag=0;
        flag2=0;
        byte[] buff=new byte[]{};
        String aa="\0";
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                if( !(creatures.get(i).get(j) instanceof Blank) ){
                    if(creatures.get(i).get(j).exist)
                        aa+=creatures.get(i).get(j).getname()+"活"+i+"\t"+j+"\t";
                    else
                        aa+=creatures.get(i).get(j).getname()+"死"+i+"\t"+j+"\t";
                }
                else
                    aa+="         \t";
            }
            aa+="\n";
        //    System.out.println();
        }
        aa+="-----------\n";
   //     System.out.println(aa);
        buff=aa.getBytes();

        try{
            out.write(buff,0,buff.length);
        }
        catch (FileNotFoundException e1)
        {
             e1.printStackTrace();
         }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }
}
