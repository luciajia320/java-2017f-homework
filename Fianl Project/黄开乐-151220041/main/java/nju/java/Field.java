package nju.java;

import java.io.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class Field extends JPanel {

    private final int OFFSET = 10;
    private final int SPACE = 60;

    private ArrayList tiles = new ArrayList();
    public ArrayList Creature = new ArrayList();
    //private Player player;
    private Calabash_brother red = new Calabash_brother(SPACE+ OFFSET,0+OFFSET, Character.Red_cala, this);
    private Calabash_brother orange = new Calabash_brother(SPACE+ OFFSET,SPACE+OFFSET, Character.Orange_cala, this);
    private Calabash_brother yellow = new Calabash_brother(SPACE+ OFFSET,SPACE*2+OFFSET, Character.Yellow_cala, this);
    private Calabash_brother green = new Calabash_brother(SPACE+ OFFSET,SPACE*3+OFFSET, Character.Green_cala, this);
    private Calabash_brother cyan = new Calabash_brother(SPACE+ OFFSET,SPACE*4+OFFSET, Character.Cyan_cala, this);
    private Calabash_brother blue = new Calabash_brother(SPACE+ OFFSET,SPACE*5+OFFSET, Character.Blue_cala, this);
    private Calabash_brother purple = new Calabash_brother(SPACE+ OFFSET,SPACE*6+OFFSET, Character.Purple_cala, this);
    private Grandpa grandpa =  new Grandpa(9*SPACE+ OFFSET,SPACE*4+OFFSET, this);
    private Monster snake_women =  new Monster(6*SPACE+ OFFSET,SPACE*4+OFFSET, Character.snake_women, this);
    private Monster scorpion_man =  new Monster(6*SPACE+ OFFSET,SPACE*3+OFFSET, Character.Scorpion_man, this);
    private Monster toad1 =  new Monster(9*SPACE+ OFFSET,0+OFFSET, Character.Toad1, this);
    private Monster toad2 =  new Monster(8*SPACE+ OFFSET,SPACE+OFFSET, Character.Toad2, this);
    private Monster toad3 =  new Monster(7*SPACE+ OFFSET,SPACE*2+OFFSET, Character.Toad3, this);
    private Monster toad4 =  new Monster(7*SPACE+ OFFSET,SPACE*5+OFFSET, Character.Toad4, this);
    private Monster toad5 =  new Monster(8*SPACE+ OFFSET,SPACE*6+OFFSET, Character.Toad5, this);
    private Monster toad6 =  new Monster(9*SPACE+ OFFSET,SPACE*7+OFFSET, Character.Toad6, this);
    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    private boolean p = false;
    private Thread red_cala;
    private Thread orange_cala;
    private Thread yellow_cala;
    private Thread green_cala;
    private Thread cyan_cala;
    private Thread blue_cala;
    private Thread purple_cala;
    private Thread sman_cala;
    private Thread swoman_cala;
    private Thread t1_cala;
    private Thread t2_cala;
    private Thread t3_cala;
    private Thread t4_cala;
    private Thread t5_cala;
    private Thread t6_cala;
    private Thread gp_cala;
    

    private String level =
            		"..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n";

    public Field() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt", false)));               
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }     
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }
    
    public boolean get_completed() {
    	return completed;
    }

    public final void initWorld() {

    	
        int x = OFFSET;
        int y = OFFSET;
        Tile a;

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
                red = new Calabash_brother(x, y, Character.Red_cala, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            } 

            h = y;
        }
        
        //player = new Player(0+ OFFSET,0+OFFSET, this);
        red = new Calabash_brother(2*SPACE+ OFFSET,0+OFFSET, Character.Red_cala, this);
        orange = new Calabash_brother(SPACE+ OFFSET,SPACE+OFFSET, Character.Orange_cala, this);
        yellow = new Calabash_brother(SPACE+ OFFSET,SPACE*2+OFFSET, Character.Yellow_cala, this);
        green = new Calabash_brother(SPACE+ OFFSET,SPACE*3+OFFSET, Character.Green_cala, this);
        cyan = new Calabash_brother(SPACE+ OFFSET,SPACE*4+OFFSET, Character.Cyan_cala, this);
        blue = new Calabash_brother(SPACE+ OFFSET,SPACE*5+OFFSET, Character.Blue_cala, this);
        purple = new Calabash_brother(SPACE+ OFFSET,SPACE*6+OFFSET, Character.Purple_cala, this);
        grandpa =  new Grandpa(9*SPACE+ OFFSET,SPACE*4+OFFSET, this);
        snake_women =  new Monster(6*SPACE+ OFFSET,SPACE*4+OFFSET, Character.snake_women, this);
        scorpion_man =  new Monster(6*SPACE+ OFFSET,SPACE*3+OFFSET, Character.Scorpion_man, this);
        toad1 =  new Monster(9*SPACE+ OFFSET,0+OFFSET, Character.Toad1, this);
        toad2 =  new Monster(8*SPACE+ OFFSET,SPACE+OFFSET, Character.Toad2, this);
        toad3 =  new Monster(7*SPACE+ OFFSET,SPACE*2+OFFSET, Character.Toad3, this);
        toad4 =  new Monster(7*SPACE+ OFFSET,SPACE*5+OFFSET, Character.Toad4, this);
        toad5 =  new Monster(8*SPACE+ OFFSET,SPACE*6+OFFSET, Character.Toad5, this);
        toad6 =  new Monster(9*SPACE+ OFFSET,SPACE*7+OFFSET, Character.Toad6, this);
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);


        world.add(red);
        world.add(orange);
        world.add(yellow);
        world.add(green);
        world.add(cyan);
        world.add(blue);
        world.add(purple);
        world.add(grandpa);
        world.add(snake_women);
        world.add(scorpion_man);
        world.add(toad1);
        world.add(toad2);
        world.add(toad3);
        world.add(toad4);
        world.add(toad5);
        world.add(toad6);

        Creature.clear();
        Creature.add(red);
        Creature.add(orange);
        Creature.add(yellow);
        Creature.add(green);
        Creature.add(cyan);
        Creature.add(blue);
        Creature.add(purple);
        Creature.add(grandpa);
        Creature.add(snake_women);
        Creature.add(scorpion_man);
        Creature.add(toad1);
        Creature.add(toad2);
        Creature.add(toad3);
        Creature.add(toad4);
        Creature.add(toad5);
        Creature.add(toad6);

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
    
    public void stop_game() {
    	completed = true;
    	p = false;
    	String str=new String(101 + " ");
		BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt", true)));               
            out.write(str);
            out.newLine();    
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
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

            if (p) {
                return;
            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_S) {

                red_cala = new Thread(red);
                orange_cala = new Thread(orange);
                yellow_cala = new Thread(yellow);
                green_cala = new Thread(green);
                cyan_cala = new Thread(cyan);
                blue_cala = new Thread(blue);
                purple_cala = new Thread(purple);
                gp_cala = new Thread(grandpa);
                swoman_cala = new Thread(snake_women);
                sman_cala = new Thread(scorpion_man);
                t1_cala = new Thread(toad1);
                t2_cala = new Thread(toad2);
                t3_cala = new Thread(toad3);
                t4_cala = new Thread(toad4);
                t5_cala = new Thread(toad5);
                t6_cala = new Thread(toad6);
                red_cala.start();
                orange_cala.start();
                yellow_cala.start();
                green_cala.start();
                cyan_cala.start();
                blue_cala.start();
                purple_cala.start();
                gp_cala.start();
                swoman_cala.start();
                sman_cala.start();
                t1_cala.start();
                t2_cala.start();
                t3_cala.start();
                t4_cala.start();
                t5_cala.start();
                t6_cala.start();
                p = true;

            } else if (key == KeyEvent.VK_R) {
            	
                restartLevel();
            } 
            else if (key == KeyEvent.VK_L) {
            	p = true;
            	JFileChooser jFileC = new JFileChooser(System.getProperty("user.dir"));
                int Value = jFileC.showOpenDialog(null);
                if (Value == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jFileC.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    if (!selectedFile.getName().equals("inputdata.txt"))
                        return;
                    new ReplayTask(selectedFile).start();
                }
            }
            else if(key == KeyEvent.VK_K) {
            	String str=new String();
                try{
                    BufferedReader in=new
                    BufferedReader(new FileReader("data.txt"));
                    BufferedWriter out=new
                    BufferedWriter(new FileWriter("inputdata.txt"));
                    while((str=in.readLine())!=null){
                        System.out.println(str);
                        out.write(str);  //将读取到的 1 行数据写入输出流
                        out.newLine();  //写入换行符
                    }
                    out.flush();
                    in.close();
                    out.close();
                }
                catch(IOException el){
                    System.out.println("出现错误"+el);
                }
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
    
    class ReplayTask extends Thread{
    	 private  BufferedReader bufferedReader;
         ReplayTask(File file) {
             try {
                 bufferedReader = new BufferedReader(new FileReader(file));
             } 
             catch (Exception e) {
                 System.out.println("Initialize bufferedReader: " + e);
             }
         }
		@Override
        public void run() {
            try {
                while (true) {
                	String str = bufferedReader.readLine();
            		if(str==null) break;
            		String s[]= str.split(" ") ;
            		int character = Integer.parseInt(s[0]) ;
            		
            		if(character != 100 && character != 101) {
            			int x2 = Integer.parseInt(s[3]);
                		int y2 = Integer.parseInt(s[4]);
            			if(character == 1) {
            				red.setX(x2);
            				red.setY(y2);
            			}
            			else if(character == 2) {
            				orange.setX(x2);
            				orange.setY(y2);
            			}
            			else if(character == 3) {
            				yellow.setX(x2);
            				yellow.setY(y2);
            			}
            			else if(character == 4) {
            				green.setX(x2);
            				green.setY(y2);
            			}
            			else if(character == 5) {
            				cyan.setX(x2);
            				cyan.setY(y2);
            			}
            			else if(character == 6) {
            				blue.setX(x2);
            				blue.setY(y2);
            			}
            			else if(character == 7) {
            				purple.setX(x2);
            				purple.setY(y2);
            			}
            			try {

                            Thread.sleep( 1000);
                            //this.field.repaint();

                        } catch (Exception d) {

                        }
            		}
            		else if(character == 100) {
            			int st = Integer.parseInt(s[1]);
            			switch(st) {
            			case 1: red.turn_dead();break;
            			case 2: orange.turn_dead();break;
            			case 3: yellow.turn_dead();break;
            			case 4: green.turn_dead();break;
            			case 5: cyan.turn_dead();break;
            			case 6: blue.turn_dead();break;
            			case 7: purple.turn_dead();break;
            			case 8: scorpion_man.turn_dead();break;
            			case 9: snake_women.turn_dead();break;
            			case 10: toad1.turn_dead();break;
            			case 11: toad2.turn_dead();break;
            			case 12: toad3.turn_dead();break;
            			case 13: toad4.turn_dead();break;
            			case 14: toad5.turn_dead();break;
            			case 15: toad6.turn_dead();break;
            			}
            		}
            		else if(character == 101)
            			break;
                    repaint();
                    //sleep(500);
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
            finally {
            	stop_game();
            }
        }
    }

        
}