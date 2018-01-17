import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import java.util.Random;

import javax.swing.ImageIcon;



public class Player extends Thing2D implements Runnable {

    private Field field;
    
    private int vRow;							
    
    private int vCol;
    
    private boolean isHuman;
    
    private boolean isAlive;
    
    private boolean isFighting;
    
    private int id;													

    public Player(int x, int y,int vRow,int vCol,boolean isHuman, Field field) {

        super(x, y);
        
        this.vRow = vRow;
        
        this.vCol = vCol;

        this.field = field;
        
        this.isHuman = isHuman;
        
        isAlive = true;
        
        if(this.isHuman)
        {
			URL loc = this.getClass().getClassLoader().getResource("human.png");
        	//String loc = "src/main/resources/human.png";
        
        	ImageIcon iia = new ImageIcon(loc);

        	Image image = iia.getImage();

        	this.setImage(image);
        }	
        else
        {
			URL loc = this.getClass().getClassLoader().getResource("demon.png");
        //	String loc = "src/main/resources/demon.png";
        	
        	ImageIcon iia = new ImageIcon(loc);

        	Image image = iia.getImage();

        	this.setImage(image);
        	
        }
    }

    public synchronized boolean move(int x, int y) throws IOException {

    	if(isAlive)
    	{
    		if(this.vCol + x >= 0 && this.vCol + x < field.getCOL() && this.vRow + y >= 0 && this.vRow + y < field.getROW())
    		{
    			if(field.getFieldState(this.vRow + y, this.vCol + x) == 0)
    			{
    				field.setFieldState(this.vRow + y, this.vCol + x, this.getId());    				
    				this.vCol += x;
    				this.vRow += y;
    				int nx = this.x() + x * field.getSPACE();
    				int ny = this.y() + y * field.getSPACE();
    				this.setX(nx);
    				this.setY(ny);
    				field.setFieldState(this.vRow - y, this.vCol - x, 0);
    				
    				field.writeToFile();
    				return true;
    			}
    		}
    	}
    	return false;
    }
      
    public synchronized void fight() throws IOException
    {
    	if(isAlive && !isFighting)
    	{	
    		int[] x = {-1,0,1,-1,1,-1,0,1};
    		int[] y = {-1,-1,-1,0,0,1,1,1};
    		for(int i = 0;i < x.length;++i)
    		{
    			if(field.isOutOfBorder(x[i] + this.vRow,y[i] + this.vCol) == false)
    			{
    					int enemyId = field.getFieldState(x[i] + this.vRow, y[i] + this.vCol);
    				
    					if(enemyId > 0 && field.playerIsFighting(enemyId) == false)
    					{
    						if(isHuman)
    						{
    							
    						}
    						else
    						{
    							isFighting = true;	field.setPlayerFighting(enemyId, true);
    							
    							Random rand = new Random();
    							int number = rand.nextInt(100);
    							if(number % 2 == 0)
    							{
    								isAlive = false;
    								field.setFieldState(this.vRow, this.vCol, 0);    								
    								field.repaint();
    								//System.out.println("id: " + id + " fight with " + enemyId + " Winner is " + enemyId );
    								field.writeToFile();
    							}
    							else
    							{
    								field.setPlayerDead(enemyId);
    								field.setFieldState(this.vRow + x[i], this.vCol + y[i],0);
    								field.repaint();
    								//System.out.println("id: " + id + " fight with " + enemyId + " Winner is " + id );   
    								field.writeToFile();
    							}
    							
    							isFighting = false; field.setPlayerFighting(enemyId, false);
    							break;
    						}
    					}
    					else if(enemyId < 0)
    					{
    						if(isHuman)
    						{
    							isFighting = true;	field.setPlayerFighting(enemyId, true);
    							
    							Random rand = new Random();
    							int number = rand.nextInt(100);
    							if(number % 2 == 0)
    							{
    								isAlive = false;
    								field.setFieldState(this.vRow, this.vCol, 0);
    								field.repaint();
    								//System.out.println("id: " + id + " fight with " + enemyId + " Winner is " + enemyId);  
    								field.writeToFile();
    							}
    							else
    							{
    								field.setPlayerDead(enemyId);
    								field.setFieldState(this.vRow + x[i], this.vCol + y[i],0);
    								field.repaint();
    								//System.out.println("id: " + id + " fight with " + enemyId + " Winner is " + id );
    								field.writeToFile();
    							}
    							
    							isFighting = false; field.setPlayerFighting(enemyId, false);
    							break;
    							
    						}
    						else
    						{
    							
    						}
    					}
    					else
    					{
    						
    					}
    			}
    		}
    	
    	}
    }
    

    
    public int getId()
    {
    	return id;
    }
    public void setId(int id)
    {
    	this.id = id;
    }
    
    public void setVRow(int r)
    {
    	vRow = r;
    }
    
    public void setVCol(int c)
    {
    	vCol = c;
    }

    public int getVRow()
    {
    	return vRow;
    }
    
    public int getVCol()
    {
    	return vCol;
    }
    
    public void setFighting(boolean fighting)
    {
    	isFighting = fighting;
    }
    
    public boolean getFighting()
    {
    	return isFighting;
    }
    
    public void setHuman(boolean flag)
    {
    	isHuman = flag;
    }
    public void setAlive(boolean flag)
    {
    	isAlive = flag;
    }
    public boolean getAlive()
    {
    	return isAlive;
    }
    
    public boolean isHuman()
    {
    	return isHuman;
    }
    
    public boolean isAlive()
    {
    	return isAlive;
    }

    public void run() {

        while (!Thread.interrupted()) {
        			
        	if(isAlive) {
        		
        			if(field.gameFinished())
        				return;//Thread.currentThread().interrupt();
        			
        			try {
						fight();
						} catch (IOException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		
        			int enemyId = field.findNearestEnemyId(this);
        		
        			if(enemyId != 0)
        			{
        				int enemyPosR = field.getPlayerVRow(enemyId);
        				int enemyPosC = field.getPlayerVCol(enemyId);
        			
        				if(this.vCol > enemyPosC)
        				{
        					try {
        						if(move(-1,0))
        						{
								
        						}
        						else
        						{
        							if(this.vRow > enemyPosR)
        							{
        								move(0,-1);
        							}
        							else if(this.vRow < enemyPosR)
        							{
        								move(0,1);
        							}
        							else
        							{
									
        							}
        						}
        					} catch (IOException e) {
							// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
        				}
        				else if(this.vCol < enemyPosC)
        				{
        					try {
        						if(move(1,0))
        						{
								
        						}
        						else
        						{
        							if(this.vRow > enemyPosR)
        							{
        								try {
        									move(0,-1);
        								} catch (IOException e) {
										// TODO Auto-generated catch block
        									e.printStackTrace();
        								}
        							}
        							else if(this.vRow < enemyPosR)
        							{
        								try {
        									move(0,1);
        								} catch (IOException e) {
										// TODO Auto-generated catch block
        									e.printStackTrace();
        								}
        							}
        							else
        							{
									
        							}
        						}
        					} catch (IOException e) {
							// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
        				}
        				else
        				{
        					if(this.vRow > enemyPosR)
        					{
        						try {
        							move(0,-1);
        						} catch (IOException e) {
								// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
        					}
        					else if(this.vRow < enemyPosR)
        					{
        						try {
        							move(0,1);
        						} catch (IOException e) {
								// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
        					}
        					else
        					{
    						
        					}
        				
        				}
        			}
            
        				try {
            	
        					Random rand = new Random();
            	
        					
        					if(this.id > 0)
        					{
        						Thread.sleep(500 + 1000 / this.id);
        					}
        					else
        					{
        						Thread.sleep(500 + 1000 / (-this.id));
        					}

        					//System.out.println("...");
            	
        					this.field.repaint();

        						} catch (Exception e) {


        					}
            
        	}
        	else
        	{
        		return;
        	}
        }
    }
}