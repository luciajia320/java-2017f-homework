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
    int battlenum=0;
    int battle_i=0;
    String strin="\0";
    int index=0;

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
                    c = new Grandfather("爷",x, y, this, 8);
                    this.creatures.get(i).set(j,c);
                    this.positions.get(i).get(j).setHolder(c);
                    c.setPosition(this.positions.get(i).get(j));
                    x += SPACE;
                } else if (tmpp.getHolder() instanceof Monster) {
                    if (((Monster) tmpp.getHolder()).getname() == "蝎")
                        c = new Monster("蝎",x, y, this, 9);
                    else if (((Monster) tmpp.getHolder()).getname() == "蛇")
                        c = new Monster("蛇",x, y, this, 10);
                    else
                        c = new Monster("喽",x, y, this, 11);
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
        g.drawString("SPACE开始 L键复盘 满意的战场记录在与src文件夹同一目录下的best.txt ", 0, 630);

        g.drawString("若存在小bug请见谅叉掉再次来过♪(＾∀＾●)ﾉ\"", 0, 680);

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
                repaint();

            }
            else if(key==KeyEvent.VK_L){
                for (int i = 0; i < N; ++i)
                    for (int j = 0; j < N; ++j) {
                        creatures.get(i).get(j).setexist(false);
                    }
             /*   try {
                    out.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/
                JFileChooser fd = new JFileChooser();
                fd.showOpenDialog(null);
               // File file=new File("out.txt");
                File file=fd.getSelectedFile();
                if(!file.exists())
                    System.exit(0);
                try {
                    strin="\0";
                    FileInputStream in = new FileInputStream(file);

                    int size=in.available();
                    byte[] buff=new byte[size];
                    in.read(buff);
                    in.close();
                    strin=new String(buff,"UTF-8");
                  //  System.out.println(strin);
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
     //           System.out.println("我很抱歉，由于存在bug(移动时生物体可以移入新位置 但原位置新移入Blank生物体始终不成功)复盘难以实现");
     //           System.out.println("战场移动记录在out.txt");

                int len=strin.length();
                battlenum=0;
                for (int i=0;i<len;++i){
                    if(strin.charAt(i)=='\n')
                        battlenum++;
                }
                //System.out.println(battlenum);
                index=0;

                    //读入棋盘了
                battle_i=0;
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        index = recover(strin, index);
                        battle_i += 1;
                        repaint();
                        if(battle_i==battlenum)
                            this.cancel();
                    }
                },0,1500);
            }
        }
    }
   public int recover(String strin,int index){
       ArrayList<ArrayList<Creature>>creatures_new = new ArrayList<ArrayList<Creature>>();
       ArrayList<ArrayList<Position>>positions_new = new ArrayList<ArrayList<Position>>();
       for (int m = 0; m < N; ++m) {
           positions_new.add(new ArrayList<Position>());
           creatures_new.add(new ArrayList<Creature>());
       }
       for (int m = 0; m < N; ++m)
           for (int n = 0; n < N; ++n) {
               creatures_new.get(m).add(new Blank(m,n));
               positions_new.get(m).add(new Position(m, n));/*[i][j]*/
               creatures_new.get(m).get(n).setPosition(positions_new.get(m).get(n));
           }
       for (int m=index; strin.charAt(m)!='\n';++m,index=m){
           int sx=0,sy=0;
           String tx="";
           String ty="";
           int k=m;
           m += 2;
           tx+= strin.charAt(m);
           if (strin.charAt(m + 1) != ' ') {
               tx+= strin.charAt(m + 1);
               m += 3;
           } else {
               m += 2;
           }
           ty+= strin.charAt(m);
           if (strin.charAt(m + 1) != ' '&&strin.charAt(m+1)<'9'&&strin.charAt(m+1)>='0') {
               ty+= strin.charAt(m + 1);

               m += 1;
           }
           sx=char2int(tx);
           sy=char2int(ty);
           Creature c=new Blank("空",0,0,this);
           if(strin.charAt(k)=='赤')
                c = new HuLuWa("赤",sy*50+125,sx*50, this, 1);
           if(strin.charAt(k)=='橙')
               c = new HuLuWa("橙",sy*50+125,sx*50, this, 2);
           if(strin.charAt(k)=='黄')
               c = new HuLuWa("黄",sy*50+125,sx*50, this, 3);
           if(strin.charAt(k)=='绿')
               c = new HuLuWa("绿",sy*50+125,sx*50, this, 4);
           if(strin.charAt(k)=='青')
               c = new HuLuWa("青",sy*50+125,sx*50, this, 5);
           if(strin.charAt(k)=='蓝')
               c = new HuLuWa("蓝",sy*50+125,sx*50, this, 6);
           if(strin.charAt(k)=='紫')
               c = new HuLuWa("紫",sy*50+125,sx*50, this, 7);
           if(strin.charAt(k)=='爷')
               c = new Grandfather("爷",sy*50+125,sx*50, this, 8);
           if(strin.charAt(k)=='蝎')
               c = new Monster("蝎",sy*50+125,sx*50, this, 9);
           if(strin.charAt(k)=='蛇')
               c = new Monster("蛇",sy*50+125,sx*50, this, 10);
           if(strin.charAt(k)=='喽')
               c = new Monster("喽",sy*50+125,sx*50, this, 11);
           creatures_new.get(sx).set(sy,c);
           positions_new.get(sx).get(sy).setHolder(c);
           c.setPosition(positions_new.get(sx).get(sy));

       }
       index+=1;
       creatures=creatures_new;
       positions=positions_new;

       return index;
   }
    public int char2int(String c) {
        int n=0;
        for (int i=0;i<c.length();++i)
            n=10*n+(c.charAt(i)-'0');
        return n;
    }

    public void record(){
        flag=0;
        flag2=0;
        byte[] buff=new byte[]{};
        String aa="";
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                if( !(creatures.get(i).get(j).getPosition().getHolder() instanceof Blank) ){
                    if(creatures.get(i).get(j).exist)
                        aa+=creatures.get(i).get(j).getname()+"活"+i+" "+j;
                    else
                        aa+=creatures.get(i).get(j).getname()+"死"+i+" "+j;
                }
            }
        //    aa+="\n";
        //    System.out.println();
        }
        aa+="\n";
     //   System.out.println(aa);
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
