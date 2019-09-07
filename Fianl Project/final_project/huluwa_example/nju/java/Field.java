package nju.java;

import niuxuCharacter.*;
import niuxuFrame.*;
import niuxuStrategy.*;
import niuxuTool.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import niuxuCharacter.Player;
import niuxuCharacter.Thing2D;
import niuxuFrame.Team;
import niuxuTool.Fate;
import niuxuTool.newWindow;

public class Field extends JPanel {

    private final int OFFSET = 40;
    private final int SPACE = 30;

    //private ArrayList seats = new ArrayList();
    //private Player player;
   // private ArrayList players =new ArrayList();

    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    Team goodTeam;
    Team badTeam;
    private Fate victory;

    
    //��ͼ�㼯,ע�⣺��ά���������JPanel�Ƿ��ŵ�
    Point[][] points = new Point[16][8];
    //����ָʾ�ŶӲ߿��Ķ�ά����
    //int[][] huluStrategy = new int[16][8];
    //boolean[][] badStrategy = new boolean[16][8];

    public Field() {
    	//iΪ������y��jΪ������x
    	for (int i=0; i<16; i++) {
    		for (int j=0; j<8; j++) {
    			points[i][j]=new Point(i,j);
    		}
    	}
    	
    	//��«�޲���һ�ֳ���
    	/*for (int i=0; i<16; i++) {
    		for (int j=0; j<8; j++) {
    			if(i == 2) {
    				if(j==0)
    					huluStrategy[i][j]=8;
    				else
    					huluStrategy[i][j]=j;
    			}
    			else
    				huluStrategy[i][j]=0;
    		}
    	}*/
    	victory = new Fate(new Point(15,7));
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }
    
    //��дpaintComponent������JPanel�ӱ���
    @Override
    protected void paintComponent(Graphics g){
    	super.paintComponent(g);
        URL loc = this.getClass().getClassLoader().getResource("resources/unlimited.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        g.drawImage(image, 0, 0,this.getWidth(), this.getHeight(), this);
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
        //Seat a;
        //Player b;
        //����team������strategy�õ�players
        /*for(int i=0; i<8; i++) {
        	if(huluStrategy[2][i] == 8) {
        		a = new Seat(points[2][i]);
        		b = new ruler(points[2][i], this);
        		seats.add(a);
        		players.add(b);
        	}
        	else {
        		a = new Seat(points[2][i]);
        		b = new servant(points[2][i], i, this);
        		seats.add(a);
        		players.add(b);
        	}
        }*/
    	goodTeam = new Team(true);
    	goodTeam.setPlayers(this);
    	badTeam = new Team(false);
    	badTeam.setPlayers(this);
        w = 17;
        h = 9;
    }

    
    public void buildWorld(Graphics g) {
    	//��ָ��������ɫ
        //g.setColor(new Color(0, 0, 0));
        //ָ����0��0����ʼ��Ⱥ͸߶�Ϊthis.getWidth(), this.getHeight()������
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.add(victory);
        world.addAll(goodTeam.getPlayers());


        //world.add(player);
        world.addAll(badTeam.getPlayers());


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x()*80, item.y()*80, this);
            } else {
                g.drawImage(item.getImage(), item.x()*80, item.y()*80, this);
            }
            //����������ɫ��ָ��λ��д��Completed
            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }


    //��ͼ�ı�׼����paint(Graphics g)
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //���������
        Color c = g.getColor(); //��¼��ǰ������ɫ�������ֳ�
        setBorder(BorderFactory.createLineBorder(Color.black)); //��������߿�
        //g.fillRect(0, 0, this.getWidth(), this.getHeight()); ��һ��ʵ�����ľ���
        //g.drawrect��һ�����ľ���
        g.setColor(Color.white);
        for(int i=0; i<720; i+=80) { 
            g.drawLine(0, i, 1280, i);      
        }
        for(int i=0; i<1360; i+=80) { 
            g.drawLine(i, 0, i, 640);      
        }
        buildWorld(g);
    }

    //��������
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
            	story.writeToFile();
                //player.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {
            	story.readFile();
                //player.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {
            	newWindow.printResult();
            } else if (key == KeyEvent.VK_S) {

                ExecutorService exec = Executors.newCachedThreadPool();
                for(int t=0; t<goodTeam.getPlayers().size(); t++) {
                	exec.execute((Player)goodTeam.getPlayers().get(t));
                }
                for(int t=0; t<badTeam.getPlayers().size(); t++) {
                	exec.execute((Player)badTeam.getPlayers().get(t));
                }
            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }
    
    //�ṩ�㼯��player����
    public Point[][] getPoints() {
    	return points;
    }


    public void restartLevel() {

        goodTeam.getPlayers().clear();
        badTeam.getPlayers().clear();
    	for (int i=0; i<16; i++) {
    		for (int j=0; j<8; j++) {
    			points[i][j]=new Point(i,j);
    		}
    	}
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}