package FinalProject;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Field extends JPanel {

    private final int OFFSET = 0;
    private final int SPACE = 40;

    private ArrayList tiles = new ArrayList();
    private ArrayList items = new ArrayList();
    private ArrayList creatures = new ArrayList();
    private ArrayList effects = new ArrayList();
    //private Player player;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

   /** private String level =
            "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n";
*/
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

        int x = OFFSET;
        int y = OFFSET;
        
        Tile a;
        Item b;
        Player c;
        Effect d;
        tiles.clear();
        items.clear();
        creatures.clear();
        effects.clear();

        for(int i=0;i<Stage.xsize();i++) {
        	for(int j=0;j<Stage.ysize();j++) {
        		a = new Tile(x,y,Stage.ground_below[i][j].gettype());
    			tiles.add(a);
    			if(Stage.ground_mid[i][j].gettype() >0) {
    				b = new Item(x,y,Stage.ground_mid[i][j].gettype());
    				items.add(b);
    			}
    			if(Stage.ground_above[i][j].gettype() >0) {
    				c = new Player(x,y,this,Stage.ground_above[i][j].gettype(),Stage.ground_above[i][j].getdirection());
        			creatures.add(c);
    			}
    			if(Stage.ground_extra[i][j].gettype() >0) {
    				d = new Effect(x,y,Stage.ground_extra[i][j].gettype());
    				effects.add(d);
    			}
    			y += SPACE;
        	}
        	x+= SPACE;
        	y  = OFFSET;
        }
        w = Stage.xsize()*SPACE;
        h = Stage.ysize()*SPACE;
        
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world_below = new ArrayList();
        ArrayList world_mid = new ArrayList();
        ArrayList world_above = new ArrayList();
        ArrayList world_extra = new ArrayList();
        world_below.addAll(tiles);
        world_mid.addAll(items);
        world_above.addAll(creatures);
        world_extra.addAll(effects);
        
       
        for (int i = 0; i < world_below.size(); i++) {

            Thing2D item = (Thing2D) world_below.get(i);
            g.drawImage(item.getImage(), item.x(), item.y(),this);
           

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
        for (int i = 0; i < world_mid.size(); i++) {

            Thing2D item = (Thing2D) world_mid.get(i);
            g.drawImage(item.getImage(), item.x(), item.y(),42,42,this);
           

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
        for (int i = 0; i < world_above.size(); i++) {

            Thing2D item = (Thing2D) world_above.get(i);
            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x(), item.y(),40 ,40,this);
            } 
            

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
        for (int i = 0; i < world_extra.size(); i++) {

            Thing2D item = (Thing2D) world_extra.get(i);
            g.drawImage(item.getImage(), item.x(), item.y(),30,30,this);

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


           if (key == KeyEvent.VK_L) {
            	System.out.println("Loading¡£¡£¡£");
            	JFileChooser jFileChooser = new JFileChooser();
            	FileNameExtensionFilter filter  = new FileNameExtensionFilter
                        ("Allowd File", "hlw");
                jFileChooser.setFileFilter(filter);
                int i = jFileChooser.showOpenDialog(null);
                if(i== jFileChooser.APPROVE_OPTION){ 
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    String name = jFileChooser.getSelectedFile().getName();
                    Load load = new Load(path);
                    load.start();
                }
               

            } else if(key == KeyEvent.VK_SPACE) {
            	Level.start();
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
}

