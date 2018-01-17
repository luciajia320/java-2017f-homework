
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import java.io.*;


public class Field extends JPanel {

	private final int ROW = 30;						//行数
		
	private final int COL = 30;						//列数
	
    private Player[] human = new Player[8];		    //爷爷和葫芦娃阵营

    private Player[] demon = new Player[8];		    //妖精阵营
    
    private int [][] fieldstate = new int[ROW][COL];					 

    private final int OFFSET = 30;					//偏移量

    private final int SPACE = 20;					

    private ArrayList tiles = new ArrayList();

    private int w = 0;

    private int h = 0;

    private boolean completed = false;
    
    private MyContainer cont = new MyContainer();
    
    private boolean isReadingFile = false;
    
    private int TOTAL_STEP = 0;
    
    private boolean clearFile = true;
    
    private int curStep = 0;

/*
    private String level =

            "..........\n" +

                    "..........\n" +

                    "..........\n" +

                    "..........\n" +

                    "..........\n" +

                    "..........\n" +

                    "..........\n" +

                    "..........\n";
*/
    private String level = "";
    
    
    public void setFormation(Player[] player,Formation.FORMATION F,int startR,int startC)
    {
    	int length = player.length;
    	Formation f = new Formation();
    	f.setFormation(F, player.length);
    	int arrayX[] = new int[20]; int arrayY[] = new int[20];
    	arrayX = f.getArrayPosX();
    	arrayY = f.getArrayPosY();
    	  	
    	for(int i = 0;i < length;++i)
    	{
    		if(!isOutOfBorder(startR + arrayX[i],startC + arrayY[i]))
    		{
    			player[i].setVRow(startR + arrayX[i]);
    			player[i].setVCol(startC + arrayY[i]);
    			player[i].setX(OFFSET + player[i].getVCol() * SPACE);
    			player[i].setY(OFFSET + player[i].getVRow() * SPACE);
    			fieldstate[startR + arrayX[i]][startC + arrayY[i]] = player[i].getId();
    		}
    		else
    		{
    			System.out.println("setFormation Error!");   			
    		}
    	}
    }
    
    
    public synchronized void writeToFile() throws IOException
    {
    	File f = new File("test.txt");
    	FileOutputStream fop = new FileOutputStream(f,true);
    	OutputStreamWriter writer = new OutputStreamWriter(fop,"UTF-8");
    	
    	if(clearFile)
    	{
    		FileWriter filewriter = new FileWriter(f);
    		filewriter.write("");
    		filewriter.flush();
    		filewriter.close();
    		clearFile = false;
    	}
    	
    	//writer.append("第" + (TOTAL_STEP++) + "步:\r\n");
    	
    	for(int i = 0;i < human.length;++i)
    	{
    		int alive = 0;
    		if(human[i].getAlive())
    			alive = 1;
    		writer.append("" + human[i].getId() + "," + human[i].getVRow() + "," + human[i].getVCol() + "," + alive + ",\r\n");
    	}
    	for(int i = 0;i < demon.length;++i)
    	{
    		int alive = 0;
    		if(demon[i].getAlive())
    			alive = 1;
    		writer.append("" + demon[i].getId() + "," + demon[i].getVRow() + "," + demon[i].getVCol() + "," + alive + ",\r\n");
    	}
    	
    	writer.close();
    	fop.close();
    }
       
    public void setPlayerDead(int id)
    {
    	if(id > 0)
    	{
    		human[id-1].setAlive(false);
    	}
    	else if(id < 0)
    	{
    		demon[(-id)-1].setAlive(false);
    	}
    	else
    	{
    		System.out.println("can't find this player !(id == 0)");
    	}
    }
    
    public void setPlayerFighting(int id,boolean flag)
    {
    	if(id > 0)
    		human[id-1].setFighting(flag);
    	else if(id < 0)
    		demon[(-id)-1].setFighting(flag);
    	else
    	{
    		System.out.println("can't find this player! (id == 0)");
    	}
    }
    
    public boolean playerIsFighting(int id)
    {
    	if(id > 0)
    	{
    		return human[id-1].getFighting();
    	}
    	else if(id < 0)
    	{
    		return demon[(-id)-1].getFighting();
    	}
    	else
    	{
    		System.out.println("can't find this player !(id == 0)");
    		return false;
    	}
    }
    
    public void setFieldState(int i,int j,int value)
    {
    	fieldstate[i][j] = value;
    }
    
    public int getFieldState(int i,int j)
    {
    	return fieldstate[i][j];
    }
    
    public int getROW()
    {
    	return ROW;
    }
	public int getCOL()
	{
		return COL;
	}
	
	public int getSPACE()
	{
		return SPACE;
	}
    
	public boolean isOutOfBorder(int vRow,int vCol)
	{
		if(vRow >= 0 && vRow < ROW && vCol >= 0 && vCol < COL)
			return false;
		else
			return true;
	}

	public boolean gameFinished()
	{
		boolean humanAllDead = true;
		boolean demonAllDead = true;
		for(int i = 0;i < human.length;++i)
		{
			if(human[i].isAlive())
			{
				humanAllDead = false;
				break;
			}
		}
		for(int i = 0;i < demon.length;++i)
		{
			if(demon[i].isAlive())
			{
				demonAllDead = false;
				break;
			}
		}
		if(humanAllDead || demonAllDead)
		{
			return true;
		}
		else
			return false;
	}
	
	public int findNearestEnemyId(Player player)
	{
		if(player.isAlive())
		{
			if(gameFinished())
				return 0;	
			else
			{
				if(player.isHuman())
				{
					int index = 0;int min = 65536;
					for(int i = 0;i < demon.length;++i)
					{
						if(demon[i].isAlive())
						{
							int dX = player.getVRow() - demon[i].getVRow();
							int dY = player.getVCol() - demon[i].getVCol();
							int distance = dX * dX + dY * dY;
							if(distance < min)
							{
								min = distance;
								index = -(i+1);
							}
						}
					}
					return index;
				}
				else
				{
					int index = 0;int min = 65536;
					for(int i = 0;i < human.length;++i)
					{
						if(human[i].isAlive())
						{
							int dX = player.getVRow() - human[i].getVRow();
							int dY = player.getVCol() - demon[i].getVCol();
							int distance = dX * dX + dY * dY;
							if(distance < min)
							{
								min = distance;
								index = i+1;
							}
						}
					}
					return index;
				}				
			}
		}
		else
		{
			return 0;
		}
	}
	
	public int getPlayerVRow(int id)
	{
		if(id > 0)
		{
			return human[id-1].getVRow();
		}
		else if(id < 0)
		{
			return demon[(-id)-1].getVRow();
		}
		else
		{
			return -1;
		}
	}
	
	public int getPlayerVCol(int id)
	{
		if(id > 0)
		{
			return human[id-1].getVCol();
		}
		else if(id < 0)
		{
			return demon[(-id)-1].getVCol();
		}
		else
		{
			return -1;
		}
	}
	
	
    public Field() {

    	initStringLevel();

        addKeyListener(new TAdapter());

        setFocusable(true);

        for(int i = 0;i < human.length;++i)
        {
        	human[i] = new Player(0 + OFFSET,0 + OFFSET,0,0,true,this);
        }
        for(int i = 0;i < demon.length;++i)
        {
        	demon[i] = new Player(0 + OFFSET,0 + OFFSET,0,0,false,this);
        }
        
        initWorld();

    }

    public void initStringLevel()
    {
    	String tmp = "";
    	for(int i = 0;i < COL;++i)
    		tmp += ".";
        tmp += "\n";
    	for(int i = 0;i < ROW;++i)
    		level += tmp;
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

                if (this.w < x) {

                    this.w = x;

                }

                x = OFFSET;

            } else if (item == '.') {

                a = new Tile(x, y);

                tiles.add(a);

                x += SPACE;

            } 

            else if (item == ' ') {

                x += SPACE;

            }

            h = y;

        }
        
        for(int i = 0;i < ROW;++i)
        {
        	for(int j = 0;j < COL;++j)
        	{
        		fieldstate[i][j] = 0;
        	}
        }
        
        for(int i = 0;i < human.length;++i)
        {
        	human[i].setId(i+1);
        	human[i].setFighting(false);
        	human[i].setAlive(true);
        }
        for(int i = 0;i < demon.length;++i)
        {
        	demon[i].setId(-(i+1));
        	demon[i].setFighting(false);
        	demon[i].setAlive(true);
        }
              
        Random rand = new Random();
        int r = rand.nextInt(3);
        
        if(r % 3 == 0)
        {       
        	setFormation(human,Formation.FORMATION.CHANGSHE,0,0);
        	setFormation(demon,Formation.FORMATION.CHANGSHE,0,COL/2);
        }
       
        else if(r % 3 == 1)
        {
        	setFormation(human,Formation.FORMATION.HEYI,8,8);
        	setFormation(demon,Formation.FORMATION.CHANGSHE,0,COL-1);
        }
        else
        {
       
        setFormation(human,Formation.FORMATION.YANXING,0,COL/2);
        setFormation(demon,Formation.FORMATION.CHANGSHE,0,COL-1);
        
        }
    }

    

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));

        g.fillRect(0, 0, this.getWidth(), this.getHeight());



        ArrayList world = new ArrayList();

        world.addAll(tiles);

        //********************************************************************
        for(int i = 0;i < human.length;++i)
        {
        	if(human[i].isAlive())
        		world.add(human[i]);
        }
        for(int i = 0;i < demon.length;++i)
        {
        	if(demon[i].isAlive())
        		world.add(demon[i]);
        }

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



    class TAdapter extends KeyAdapter {



        @Override

        public void keyPressed(KeyEvent e) {



            if (completed) {

                return;

            }





            int key = e.getKeyCode();




            if (key == KeyEvent.VK_SPACE) {
            	
            	if(isReadingFile)
            	{
            		
            	}
            	else
            	{
            	
            			for(int i = 0;i < human.length;++i)
            			{          				
            				new Thread(human[i]).start();
            			}
            			for(int i = 0;i < demon.length;++i)
            			{
            				new Thread(demon[i]).start();
            			}          	           		      
            			
            			//System.out.println("VK_SPACE");
            	}
            		
            } else if (key == KeyEvent.VK_R) {
            	          	
            	
            	clearFile = true;
            	
            	isReadingFile = false;
            	
            	TOTAL_STEP = 0;
            	
                restartLevel();
                
                
            }
            else if(key == KeyEvent.VK_L)
            {
   
            	try {
						replay();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

            }
            else if(key == KeyEvent.VK_LEFT)
            {
            	if(isReadingFile)
            	{
            		if(curStep > 0)
            		{
            			--curStep;
            		}
            		else
            		{
            			curStep = cont.getTotalStep()-1;
            		}
            		//System.out.println("LEFT FUCKING " + curStep);
            		setPlayerByFile();
            		repaint();
            	}
            }
            else if(key == KeyEvent.VK_RIGHT)
            {
            	if(isReadingFile)
            	{
            		if(curStep == cont.getTotalStep()-1)
            		{
            			curStep = 0;
            		}
            		else
            		{
            			++curStep;
            		}          		
            		//System.out.println("RIGHT FUCKING " + curStep);
            		setPlayerByFile();
            		repaint();
            	}
            }
            repaint();

        }

    }


    public void replay() throws FileNotFoundException
    {
    	
    	FileDialog dlg;
    	String filename = null;


    	dlg = new FileDialog(new Frame(),"select records",FileDialog.LOAD);
    	dlg.setVisible(true);
    	filename = dlg.getFile();

    	if(filename != null)
    	{
    		cont.readfile(filename);
    		isReadingFile = true;
    		curStep = 0;
    	}
    	else
    	{
    		isReadingFile = false;
    	}
    	
    	if(isReadingFile)
    	{
    		setPlayerByFile();
    		repaint();
    	}
    	

    }
    
    public void setPlayerByFile()
    {
    	int playerNum = cont.getPlayerNum();
		for(int j = 0;j < playerNum;++j)
		{
			int id = cont.getVIdValue(curStep * playerNum + j);
			int row = cont.getVRValue(curStep * playerNum + j);
			int col = cont.getVCValue(curStep * playerNum + j);
			int alive = cont.getVAValue(curStep * playerNum + j);
			//System.out.println(id + " " + row + " " + col + " " + alive + "\n");
			
			if(id > 0)
			{
				human[id-1].setVRow(row);
				human[id-1].setVCol(col);
				human[id-1].setX(OFFSET + col * SPACE);
				human[id-1].setY(OFFSET + row * SPACE);
				if(alive == 1)
					human[id-1].setAlive(true);
				else
					human[id-1].setAlive(false);
			}
			else
			{
				demon[(-id)-1].setVRow(row);
				demon[(-id)-1].setVCol(col);
				demon[(-id)-1].setX(OFFSET + col * SPACE);
				demon[(-id)-1].setY(OFFSET + row * SPACE);
				if(alive == 1)
					demon[(-id)-1].setAlive(true);
				else
					demon[(-id)-1].setAlive(false);
			}
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