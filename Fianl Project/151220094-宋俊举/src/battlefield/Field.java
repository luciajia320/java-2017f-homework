package battlefield;

import mainroles.Creature;
import mainroles.Huluwa;
import mainroles.Slime;
import mainroles.Xiezijing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;

public class Field extends JPanel {
    private Position positions[][];
    public final int N = 20;
    public final int M = 20;
    FileInputStream reader;
    FileOutputStream writer;
    public Field()
    {
        URL loc = this.getClass().getClassLoader().getResource("gs.png");
        ImageIcon iia = new ImageIcon(loc);
        Position.gs = iia.getImage();

        loc = this.getClass().getClassLoader().getResource("huluwa.png");
        iia = new ImageIcon(loc);
        Huluwa.ig = iia.getImage();
        Huluwa.ig = Huluwa.ig.getScaledInstance(1000/N,1000/M,Image.SCALE_DEFAULT);

        loc = this.getClass().getClassLoader().getResource("huluwaDeath.png");
        iia = new ImageIcon(loc);
        Huluwa.deathIg = iia.getImage();
        Huluwa.deathIg = Huluwa.deathIg.getScaledInstance(1000/N,1000/M,Image.SCALE_DEFAULT);


        loc = this.getClass().getClassLoader().getResource("xiezijing.png");
        iia = new ImageIcon(loc);
        Xiezijing.ig = iia.getImage();
        Xiezijing.ig = Xiezijing.ig.getScaledInstance(1000/N,1000/M,Image.SCALE_DEFAULT);

        loc = this.getClass().getClassLoader().getResource("xiezijingDeath.png");
        iia = new ImageIcon(loc);
        Xiezijing.deathIg = iia.getImage();
        Xiezijing.deathIg = Xiezijing.deathIg.getScaledInstance(1000/N,1000/M,Image.SCALE_DEFAULT);

        loc = this.getClass().getClassLoader().getResource("slime.png");
        iia = new ImageIcon(loc);
        Slime.ig = iia.getImage();
        Slime.ig = Slime.ig.getScaledInstance(1000/N,1000/M,Image.SCALE_DEFAULT);

        loc = this.getClass().getClassLoader().getResource("slimeDeath.png");
        iia = new ImageIcon(loc);
        Slime.deathIg = iia.getImage();
        Slime.deathIg = Slime.deathIg.getScaledInstance(1000/N,1000/M,Image.SCALE_DEFAULT);

        positions = new Position[N][];
        for(int i = 0;i<N;i++)
        {
            positions[i] = new Position[M];
            for(int j = 0;j<M;j++)
            {
                positions[i][j] = new Position();
                positions[i][j].setX(i);
                positions[i][j].setY(j);
            }
        }
        try {
            writer = new FileOutputStream("record.txt");
        }catch(IOException e)
        {
            System.out.println("Can't Open writefile record.txt");
            System.exit(1);
        }
        addKeyListener(new TAdapter());
        setFocusable(true);
    }


    public boolean setPosition(int x,int y,Creature c)
    {
        if(x<0||y<0||x>=N||y>=M)
        {
            return false;
        }
        if(positions[x][y].setHolder(c))
        {
            return true;
        }
        return false;
    }
    public Position getPosition(int x,int y)
    {
        return positions[x][y];
    }
    public Creature getCreature(int x,int y)
    {
        if(x>=N||y>=M||x<0||y<0)
            return null;
        return positions[x][y].getHolder();
    }
    public static boolean PaintOver = false;
    public void readWorld(Graphics g)
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        int xoffset = this.getWidth()/N;
        int yoffset = this.getHeight()/M;
        g.drawImage(Position.gs,0,0,this);
        for(int j = 0;j < M;j++)
        {
            for(int i = 0;i<N;i++)
            {
                try{
                    int ch = reader.read();
                    if(ch == -1) {
                        PaintOver = true;
                        return;
                    }
                    while(ch == '\r'||ch == '\n')
                    {
                        ch = reader.read();
                    }
                    ch = ch -'0';
                    if(ch == 1)
                    {
                        g.drawImage(Huluwa.ig, i * xoffset, j * yoffset, this);
                    }
                    else if(ch == 2)
                    {
                        g.drawImage(Xiezijing.ig,i*xoffset,j*yoffset,this);
                    }
                    else if(ch == 3)
                    {
                        g.drawImage(Slime.ig,i*xoffset,j*yoffset,this);
                    }
                    else if(ch==6)
                    {
                        g.drawImage(Huluwa.deathIg, i * xoffset, j * yoffset, this);
                    }
                    else if(ch == 7)
                    {
                        g.drawImage(Xiezijing.deathIg,i*xoffset,j*yoffset,this);
                    }
                    else if(ch == 8)
                    {
                        g.drawImage(Slime.deathIg,i*xoffset,j*yoffset,this);
                    }
                }catch(IOException e)
                {
                    System.out.println("Can't read file");
                    System.exit(1);
                }
            }
        }
    }

    public void buildWorld(Graphics g)
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        int xoffset = this.getWidth()/N;
        int yoffset = this.getHeight()/M;
        g.drawImage(Position.gs,0,0,this);
        Creature tempc;
        for(int j = 0;j < M;j++) {
            for (int i = 0; i < N; i++) {
                if((tempc = positions[i][j].getHolder())!=null)
                 {
                     if(tempc.getFileId()<5)
                        g.drawImage(tempc.getImage(), i * xoffset, j * yoffset, this);
                     else
                         g.drawImage(tempc.getDeathImage(),i * xoffset, j * yoffset, this);
                    try {
                        writer.write(Integer.toString(tempc.getFileId()).getBytes());
                    }catch(IOException e)
                    {
                        System.out.println("Can't Write File record.txt");
                        System.exit(1);
                    }
                }
                else
                {
                    try {
                        writer.write(Integer.toString(0).getBytes());
                    }catch(IOException e)
                    {
                        System.out.println("Can't Write File record.txt");
                        System.exit(1);
                    }
                }
            }
            try {
                String temps = "\r\n";
                writer.write(temps.getBytes());
            }catch(IOException e)
            {
                System.out.println("Can't Write File record.txt");
                System.exit(1);
            }
        }
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(gameMode)
            buildWorld(g);
        else
            readWorld(g);
    }

    public static boolean gameStart = false;
    public static boolean gameMode = true;//自动战斗,false 为读取文件战斗
    class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_SPACE)
            {
                gameStart = true;
                JOptionPane.showMessageDialog(null,"开始游戏");
            }
            else if(key == KeyEvent.VK_T)
            {
                gameStart = false;
            }
            else if(key == KeyEvent.VK_L)
            {

                JFileChooser jfc = new JFileChooser();
                jfc.showOpenDialog(null);
                File f = jfc.getSelectedFile();
                try{
                    reader = new FileInputStream(f);
                } catch(IOException e2)
                {
                    System.out.println("Can't open record " + f.getName());
                    System.exit(1);
                }
                JOptionPane.showMessageDialog(null,"文件加载可能会有点慢，请耐心等待文件加载");
                if(gameStart==false)
                    gameMode = false;//读取文件
                gameStart = true;
            }
        }
    }
}
