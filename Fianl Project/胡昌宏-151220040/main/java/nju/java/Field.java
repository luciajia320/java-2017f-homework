package nju.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.io.*;

import javax.swing.*;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Field extends JPanel {

    private final int OFFSET = 10;
    private final int SPACE = 60;

    private ArrayList tiles = new ArrayList();
   // private Player player;
    
    private Grandpa grandpa;
    private Snake snake;
    private Scorpion scorpion;
    private ArrayList huluwa = new ArrayList();
    private ArrayList goblin = new ArrayList();
   // private Creature Hu1,Hu2,Hu3,Hu4,Hu5,Hu6,Hu7,Goblin1,Goblin2,Goblin3,Goblin4,Goblin5,Goblin6,Goblin7;
    ArrayList position = new ArrayList();
    
    
    private int w = SPACE*15;
    private int h = SPACE*8 + SPACE/2;
    //private int w = 0;
    //private int h = 0;
    private boolean completed = false;
    
    int enemyCount = 0;
    int huluCount = 0;
    Thread ThreadHu1,ThreadHu2,ThreadHu3,ThreadHu4,ThreadHu5,ThreadHu6,ThreadHu7;
    Thread ThreadGrandpa,ThreadSnake,ThreadScorpion;
    Thread ThreadGoblin1,ThreadGoblin2,ThreadGoblin3,ThreadGoblin4,ThreadGoblin5,ThreadGoblin6,ThreadGoblin7;
    
    private String level =
            		".1........g....\n" +
                    "..2......g.....\n" +
                    "...3......g.c..\n" +
                    "p...4....g.....\n" +
                    "...5......g.s..\n" +
                    "..6......g.....\n" +
                    ".7........g....\n" +
                    "...............\n";

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
    
    public int getSpace() {
    	return SPACE;
    }
    public int getOffset() {
    	return OFFSET;
    }
    
    public final void initWorld() {

        //int x = OFFSET;
        //int y = OFFSET;

        Tile a;

        for(int x = 0; x < w; x += SPACE) {
            for(int y = SPACE/2; y < h; y += SPACE) {
                tiles.add(new Tile(x, y));
            }
         }
         //初始化Creature
         int x = 0, y = SPACE/2;
        int goblinRank = 11;
        for (int i = 0; i < level.length(); i++) {
        	 
            char item = level.charAt(i);
            
            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }
                x = OFFSET;
            }else if(item == '.') {
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            } else if (item == 'g') {
            	Goblin gb = new Goblin(x,y,goblinRank,this);
            	goblin.add(gb);
                x += SPACE;
                enemyCount++;
                goblinRank++;
            }else if (item == 'p') {
            	grandpa = new Grandpa(x,y,8,this);
                x += SPACE;
                huluCount++;
            }else if (item == 's') {
            	snake = new Snake(x,y,9,this);
                x += SPACE;
                enemyCount++;
            }else if (item == 'c') {
            	scorpion = new Scorpion(x,y,10,this);
                x += SPACE;
                enemyCount++;
            }else if (item >= '1' && item <='7') {
            	Huluwa h;
            	switch(item) {
            	case '1':h = new Huluwa(x,y,1,this);break;
            	case '2':h = new Huluwa(x,y,2,this);break;
            	case '3':h = new Huluwa(x,y,3,this);break;
            	case '4':h = new Huluwa(x,y,4,this);break;
            	case '5':h = new Huluwa(x,y,5,this);break;
            	case '6':h = new Huluwa(x,y,6,this);break;
            	case '7':h = new Huluwa(x,y,7,this);break;
            	default:h = new Huluwa(x,y,6,this);break;
            	}
            	huluwa.add(h);
            	x += SPACE;
            	huluCount++;
            }
            
            h = y;
        }

        //player = new Player(0+ OFFSET,0+OFFSET, this);

    }

    public void buildWorld(Graphics g) {
    	
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("background.png")).getImage(), 0, 0, this);
        ArrayList world = new ArrayList();
        
        //System.out.println(enemyCount);
        //System.out.println(huluCount);
        
        world.addAll(tiles);
        world.addAll(huluwa);
        world.addAll(goblin);
        world.add(snake);
        world.add(scorpion);
        world.add(grandpa);
        //world.add(player);
        position.clear();
        position.addAll(huluwa);
        position.add(grandpa);
        position.add(scorpion);
        position.add(snake);
        position.addAll(goblin);

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            
            g.drawImage(item.getImage(), item.x(), item.y(), this);

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


                //player.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


                //player.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


                //player.move(0, SPACE);

            } else if (key == KeyEvent.VK_SPACE) {

                //new Thread(player).start();
            	//for (int i = 0; i < huluwa.size(); i++) {
                //     Huluwa h = (Huluwa) huluwa.get(i);
                 //    new Thread(h).start();
            	//}
 
            	ThreadHu1 = new Thread((Huluwa) huluwa.get(0));
            	ThreadHu2 = new Thread((Huluwa) huluwa.get(1));
            	ThreadHu3 = new Thread((Huluwa) huluwa.get(2));
            	ThreadHu4 = new Thread((Huluwa) huluwa.get(3));
            	ThreadHu5 = new Thread((Huluwa) huluwa.get(4));
            	ThreadHu6 = new Thread((Huluwa) huluwa.get(5));
            	ThreadHu7 = new Thread((Huluwa) huluwa.get(6));
            	ThreadHu1.start();
            	ThreadHu2.start();
            	ThreadHu3.start();
            	ThreadHu4.start();
            	ThreadHu5.start();
            	ThreadHu6.start();
            	ThreadHu7.start();
            	
            	//for (int i = 0; i < goblin.size(); i++) {
                  //  Goblin b = (Goblin) goblin.get(i);
                  //  new Thread(b).start();
               // }
            	ThreadGoblin1 = new Thread((Goblin) goblin.get(0));
            	ThreadGoblin2 = new Thread((Goblin) goblin.get(1));
            	ThreadGoblin3 = new Thread((Goblin) goblin.get(2));
            	ThreadGoblin4 = new Thread((Goblin) goblin.get(3));
            	ThreadGoblin5 = new Thread((Goblin) goblin.get(4));
            	ThreadGoblin6 = new Thread((Goblin) goblin.get(5));
            	ThreadGoblin7 = new Thread((Goblin) goblin.get(6));
            	ThreadGoblin1.start();
            	ThreadGoblin2.start();
            	ThreadGoblin3.start();
            	ThreadGoblin4.start();
            	ThreadGoblin5.start();
            	ThreadGoblin6.start();
            	ThreadGoblin7.start();
            	
            	
            	ThreadGrandpa = new Thread(grandpa);
            	ThreadGrandpa.start();
            	ThreadSnake = new Thread(snake);
            	ThreadSnake.start();
            	ThreadScorpion = new Thread(scorpion);
            	ThreadScorpion.start();
            	
            }else if (key == KeyEvent.VK_L) {
                JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
                int returnValue = jFileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    if (!selectedFile.getName().equals("record.txt"))
                        return;
                    new ReplayTask(selectedFile).start();
                }
            }
 
            else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }
    
    public void StopGame() {
    	ThreadHu1.stop();
    	ThreadHu2.stop();
    	ThreadHu3.stop();
    	ThreadHu4.stop();
    	ThreadHu5.stop();
    	ThreadHu6.stop();
    	ThreadHu7.stop();
    	ThreadGoblin1.stop();
    	ThreadGoblin2.stop();
    	ThreadGoblin3.stop();
    	ThreadGoblin4.stop();
    	ThreadGoblin5.stop();
    	ThreadGoblin6.stop();
    	ThreadGoblin7.stop();
    	ThreadGrandpa.stop();
    	ThreadSnake.stop();
    	ThreadScorpion.stop();
    }
    
    
    public void restartLevel() {

        tiles.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
    
    
    class ReplayTask extends Thread{

        private  BufferedReader bufferedReader;
        ReplayTask(File file) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (Exception e) {
                System.out.println("Initialize bufferedReader: " + e);
            }
        }
        @Override
        public void run() {
            try {
                while (true) {
                	String str = bufferedReader.readLine();
            		if(str == null) 
            			break;
            		String s[]= str.split(" ") ;
            		int toX = Integer.parseInt(s[3]) ;
            		int toY = Integer.parseInt(s[4]) ;
            		if(s[0].equals("Hu1")) {
            			Huluwa h = (Huluwa) huluwa.get(0);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Hu2")) {
            			Huluwa h = (Huluwa) huluwa.get(1);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Hu3")) {
            			Huluwa h = (Huluwa) huluwa.get(2);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Hu4")) {
            			Huluwa h = (Huluwa) huluwa.get(3);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Hu5")) {
            			Huluwa h = (Huluwa) huluwa.get(4);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Hu6")) {
            			Huluwa h = (Huluwa) huluwa.get(5);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Hu7")) {
            			Huluwa h = (Huluwa) huluwa.get(6);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Grandpa")) {
            			grandpa.setX(toX);
            			grandpa.setY(toY);
            		}
            		else  if(s[0].equals("Snake")) {
            			snake.setX(toX);
            			snake.setY(toY);
            		}
            		else  if(s[0].equals("Scorpion")) {
            			scorpion.setX(toX);
            			scorpion.setY(toY);
            		}
            		else if(s[0].equals("Goblin1")) {
            			Goblin h = (Goblin) goblin.get(0);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Goblin2")) {
            			Goblin h = (Goblin) goblin.get(1);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Goblin3")) {
            			Goblin h = (Goblin) goblin.get(2);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Goblin4")) {
            			Goblin h = (Goblin) goblin.get(3);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Goblin5")) {
            			Goblin h = (Goblin) goblin.get(4);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Goblin6")) {
            			Goblin h = (Goblin) goblin.get(5);
            			h.setX(toX);
            			h.setY(toY);
            		}
            		else if(s[0].equals("Goblin7")) {
            			Goblin h = (Goblin) goblin.get(6);
            			h.setX(toX);
            			h.setY(toY);
            		}
                    repaint();
                    sleep(500);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    bufferedReader.close();
                } catch (Exception e1) {
                    System.out.println(e1);
                }
                restartLevel();
                Thread.currentThread().interrupt();
            }
        }
    }
}
    
    
    
    
    
    




