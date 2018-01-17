//package finalproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 20;

    @SuppressWarnings("rawtypes")
	private ArrayList tiles = new ArrayList();
    @SuppressWarnings("rawtypes")
	private ArrayList player = new ArrayList();
    private ArrayList deadplayer = new ArrayList();
    //private Player[] player;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    int file=0;
    static private int T = 0;
    private String level =
            "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n" +
                    "....................\n";

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
    
    @SuppressWarnings("rawtypes")
	public ArrayList _player() {
        return this.player;
    }
    @SuppressWarnings("rawtypes")
    public ArrayList getdeadplayer() {
    	return this.deadplayer;
    }
    @SuppressWarnings("unchecked")
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
            } /*else if (item == '@') {
                player = new Player(x, y, this);
                x += SPACE;
            }*/ else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }
        this.changshe(0, 0, true);
        this.YanXing(10, 10, false);
    }
    public boolean changshe(int pointx,int pointy,boolean kind) {
    	int num = 8;
    	if(kind) {
    		for(int i = 0;i < num;i++) {
    			player.add(new Player(pointx + OFFSET + SPACE,pointy + OFFSET + (i)*SPACE,this,- (i + 1)));
    		}
    	}
    	else {
    		for(int i = 0;i < num;i++) {
    			player.add(new Player(pointx + OFFSET + SPACE,pointy + OFFSET + (i)*SPACE,this, (i + 1)));
    		}
    	}
    	return true;
    }
    public boolean YanXing(int pointx,int pointy,boolean kind) {
    	int num = 8;
    	if(kind) {
    		for(int i = 0;i < num;i++) {
    			player.add(new Player(0 + OFFSET + (pointx + i)*SPACE, 0 + OFFSET +(pointy+i) * SPACE,this,-(i + 1)));
    		}
    	}
    	else {
    		for(int i = 0;i < num;i++) {
    			player.add(new Player(0 + OFFSET + (pointx + i)*SPACE, 0 + OFFSET +(pointy +i)* SPACE,this,(i + 1)));
    		}
    	}
    	return true;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);
        world.addAll(player);



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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }
    public synchronized void saveToFile() throws IOException{
    	{
        	String F="review"+(T++)+".txt";
        	File f = new File(F);	
        	FileWriter fileWriter =new FileWriter(F);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();

        	FileOutputStream fop = new FileOutputStream(f,true);
        	OutputStreamWriter writer = new OutputStreamWriter(fop,"UTF-8");
        	writer.append("" + player.size());
        	writer.append("\r\n");
        	for (int i = 0; i < player.size(); i++) {
        		Player item=(Player)(player.get(i));
        		writer.append(""+item.type());
        		writer.append("\r\n");
        		writer.append(""+item.x());
        		writer.append("\r\n");
        		writer.append(""+item.y());
        		writer.append("\r\n");	
    		}
        	writer.close();
        	fop.close();
        }
    }
    public void readfile() {
    	FileDialog dlg ;
        String fileName = null;
        Scanner geter;
        String File = "";
        while(fileName == null){
            dlg = new FileDialog(new JFrame(), "选择文件", FileDialog.LOAD);
            dlg.setVisible(true);
            fileName = dlg.getFile();
        }
        System.out.println(fileName);
        for (int i = 0; i < fileName.length(); i++) {
			if ((fileName.charAt(i)>='0')&&(fileName.charAt(i)<='9'))
				File=File+fileName.charAt(i);
		}
       // System.out.println(fileName);
        file=Integer.parseInt(File);
        //System.out.println(file);
        System.out.println(File);
        player.clear();
        deadplayer.clear();
        try {
			geter=new Scanner(new FileReader(fileName));
			String val = geter.nextLine();
			int lineofplayer = Integer.parseInt(((String)(val)));
			int T2 = 0,T3 = 0,T4 = 0;
			System.out.println(val);
			System.out.println(lineofplayer);
	        for (int i = 0; i < lineofplayer; i++) {
				String t2=geter.nextLine();
				String t3=geter.nextLine();
				String t4 = geter.nextLine();
				System.out.println(t2);
				T2=Integer.parseInt(t2);
				
				T3=Integer.parseInt(t3);
				T4=Integer.parseInt(t4);
	           Player item=new Player(T3,T4, this,T2);
	           player.add(item);
	     }
	      System.out.println(player.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void getNext(int t)
    {
    	if (t<=1||t>= T)
    	{
    		System.out.println(t+" "+T);
    		return ;
    	}
    	player.clear();
        deadplayer.clear();
        Scanner geter;
        try {
        	geter=new Scanner(new FileReader("review"+t+".txt"));
			int lineofplayer = Integer.parseInt(geter.nextLine());
			int T2 = 0,T3 = 0,T4 = 0;
	        for (int i = 0; i < lineofplayer; i++) {
				String t2=geter.nextLine();
				String t3=geter.nextLine();
				String t4 = geter.nextLine();
				T2=Integer.parseInt(t2);
				T3=Integer.parseInt(t3);
				T4=Integer.parseInt(t4);
	           Player item=new Player(T3,T4, this,T2);
	           player.add(item);
	     }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }


    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {
            	player.clear();
            	deadplayer.clear();
            	file--;
            	getNext(file);
            	/*for (int i = 0; i < player.size(); i++) {
            		((Player) player.get(i)).move(-SPACE, 0);
        		}*/
             

            } else if (key == KeyEvent.VK_RIGHT) {
            	player.clear();
            	deadplayer.clear();
            	file++;
            	getNext(file);
                
            }
            else if (key == KeyEvent.VK_L) {
            	readfile();
            }else if (key == KeyEvent.VK_UP) {
            	
            	for (int i = 0; i < player.size(); i++) {
            		((Player)player.get(i)).move(0, -SPACE);
        		}
                

            } else if (key == KeyEvent.VK_DOWN) {

            	for (int i = 0; i < player.size(); i++) {
            		((Player)player.get(i)).move(0, SPACE);
        		}
                

            } else if (key == KeyEvent.VK_SPACE) {
            	for (int i = 0; i < player.size(); i++) {
            		new Thread(((Player)player.get(i))).start();
        		}
                

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }
            
            repaint();
        }
    }


    public void restartLevel() {

        tiles.clear();
        player.clear();
        deadplayer.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}


