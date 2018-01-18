import java.awt.Image;
import java.net.URL;
import java.util.Random;
import java.util.jar.Attributes.Name;

import javax.activation.FileDataSource;
import javax.swing.ImageIcon;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.xml.transform.Templates;



public class HuLuWa extends Thing2D implements Runnable{

	 private Field field;
	 private String[] name= {"大娃","二娃","三娃","四娃","五娃","六娃","七娃"};
	 private int rank;
	 public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

	private Position position;
	 private boolean alive=true;
	 //private int sleepTime=5000;

	    public HuLuWa(int x, int y, Field field, int rank) {
	        super(x, y);

	        this.field = field;
	        this.rank=rank;
	
	        String[] huluPng= {"hong.png","cheng.png","huang.png",
	        		"lv.png","qing.png","lan.png","zi.png"};
	      
	        URL loc = this.getClass().getClassLoader().getResource(huluPng[rank-1]);
	        ImageIcon iia = new ImageIcon(loc);
	        Image image = iia.getImage();
	        this.setImage(image);
	    }

	   
	    public boolean isAlive() {
			return alive;
		}


		public void setAlive(boolean alive) {
			this.alive = alive;
		}


		public void move(int x, int y)  {
			if(field.isFromFile()==false) {//常规操作
				int prePositionX=(this.x()-30)/20;
				int prePositionY=(this.y()-30)/20;
		    	 int nx = this.x() + x;
		         int ny = this.y() + y;
		         int positionX = (nx-30)/20;
		         int positionY = (ny-30)/20;
		         if(field.getPositions()[positionX][positionY].getHolder()!=null) {
		         	String toObj=field.getPositions()[positionX][positionY].getHolder().getClass().getSimpleName();
		         	if(toObj.equals("Grandpa")||toObj.equals("HuLuWa"))//friend
		         		return;
		         	else
		         		//enemy , attack
		         	{
		         		if(toObj.equals("Shejing")) {
		         			try {
								Random random=new Random();
								int a=random.nextInt(4);
								if(a==0) {//葫芦娃取胜.概率四分之一
								field.getShejing().setAlive(false);
								field.setCreature(this, positionX, positionY);
								 synchronized(this) {
									 field.getBuffer().append("battle: hulu"+rank+" win shejing\r\n");
									 if(!field.getGround().getTextArea().getText().contains("蛇精"))
									 field.getGround().getTextArea().append("蛇精阵亡\n");
									 field.getDeadSquare().put(nx, ny);
									 field.getPositions()[prePositionX][prePositionY].setHolder(null);
									 field.getPositions()[prePositionX][prePositionY].setOwned(false);
									 }
								}
								
								else {//葫芦娃阵亡
								alive=false;
								synchronized(this) {
									 field.getBuffer().append("battle: hulu"+rank+" lose shejing\r\n");
									 if(!field.getGround().getTextArea().getText().contains(name[rank-1]))
									 field.getGround().getTextArea().append(name[rank-1]+"阵亡\n");
									 field.getDeadSquare().put(this.x(), this.y());
									 field.getPositions()[prePositionX][prePositionY].setHolder(null);
									 field.getPositions()[prePositionX][prePositionY].setOwned(false);
									 }
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								return;
							}
		         		}
		         		else if(toObj.equals("Xiezijing")) {
		         			try {
								Random random=new Random();
								int a=random.nextInt(2);
								if(a==0) {//葫芦娃阵亡
									alive=false;
									synchronized(this) {
										 field.getBuffer().append("battle: hulu"+rank+" lose xiezijing\r\n");
										 if(!field.getGround().getTextArea().getText().contains(name[rank-1]))
										 field.getGround().getTextArea().append(name[rank-1]+"阵亡\n");
										 field.getDeadSquare().put(this.x(), this.y());
								    	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
								    	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
								    	 }
								}
								else {
									field.getXiezijing().setAlive(false);
									field.setCreature(this, positionX, positionY);
									 synchronized(this) {
										 field.getBuffer().append("battle: hulu"+rank+" win xiezijing\r\n");
										 if(!field.getGround().getTextArea().getText().contains("蝎子精"))
										 field.getGround().getTextArea().append("蝎子精阵亡\n");
										 field.getDeadSquare().put(nx, ny);
								    	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
								    	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
								    	 }
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								return;
							}
		         			
		         		}
		         		else if(toObj.equals("Xiaolouluo")) {
		         			try {
								Random random=new Random();
								int a=random.nextInt(4);
								int louluoRank=(field.getPositions()[positionX][positionY].getHolder()).getRank();
								if(a==0) {//葫芦娃阵亡概率为四分之一
									alive=false;
									synchronized(this) {
										 field.getBuffer().append("battle: hulu"+rank+" lose louluo"+louluoRank+"\r\n");
										 if(!field.getGround().getTextArea().getText().contains(name[rank-1]))
										 field.getGround().getTextArea().append(name[rank-1]+"阵亡\n");
										 field.getDeadSquare().put(this.x(), this.y());
								    	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
								    	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
								    	 }
									
									}
								else {
								
								
								
								field.getXiaolouluos()[louluoRank-1].setAlive(false);
								
								field.setCreature(this, positionX, positionY);
								
								 synchronized(this) {
									 field.getBuffer().append("battle: hulu"+rank+" win louluo"+louluoRank+"\r\n");
									 if(!field.getGround().getTextArea().getText().contains("小喽啰"+louluoRank+"号"))
									 field.getGround().getTextArea().append("小喽啰"+louluoRank+"号"+ "阵亡\n");
									 field.getDeadSquare().put(nx, ny);
									 field.getPositions()[prePositionX][prePositionY].setHolder(null);
									 field.getPositions()[prePositionX][prePositionY].setOwned(false);
									 }	         		
								 }
							} catch (Exception e) {
								// TODO Auto-generated catch block
								return;
							}
		         		}
		         		//Thread.sleep(1000);
		         		
		         	}
		         }
		         else //准备跳的下一格为空格
		         {
		        	 field.setCreature(this, positionX, positionY);
		        	 synchronized(this) {
		            	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
		            	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
		            	 }
		         }
		         if(alive==true) {
			         this.setX(nx);
			         this.setY(ny);
		         }
				}
			else//fupan
			{
				int prePositionX=(this.x()-30)/20;
				int prePositionY=(this.y()-30)/20;
		    	 int nx = this.x() + x;
		         int ny = this.y() + y;
		         int positionX = (nx-30)/20;
		         int positionY = (ny-30)/20;
		         if(field.getPositions()[positionX][positionY].getHolder()!=null) {
		         	String toObj=field.getPositions()[positionX][positionY].getHolder().getClass().getSimpleName();
		         	if(toObj.equals("Grandpa")||toObj.equals("HuLuWa"))//friend
		         		return;
		         	else
		         		//enemy , attack
		         	{
		         	  if(toObj.equals("Shejing")) {
		         		for(String s:field.getBattleStrs()) {
		         			String[] arr=s.split(" ");
		         			if(arr[1].contains("hulu"+rank)&&arr[3].contains("shejing")) {//交火
		         				if(arr[2].contains("win")) {//葫芦娃击败蛇精
		         					field.getShejing().setAlive(false);
				         			field.setCreature(this, positionX, positionY);
				         			 synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" win shejing\r\n");
				         				 field.getGround().getTextArea().append("蛇精阵亡\n");
				         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
				         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
				         	        	 }
		         				}
		         				else {//蛇精击败葫芦娃
		         					alive=false;
				         			synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" lose shejing\r\n");
				         				 field.getGround().getTextArea().append(name[rank-1]+"阵亡\n");
				         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
				         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
				         	        	 }
		         				}
		         				break;
		         			}
		         		}
		         	  }
		         	
		         
		         	else if(toObj.equals("Xiezijing")) {
		        		for(String s:field.getBattleStrs()) {
		         			String[] arr=s.split(" ");
		         			if(arr[1].contains("hulu"+rank)&&arr[3].contains("xiezijing")) {//交火
		         				if(arr[2].contains("win")) {//葫芦娃击败
		         					field.getXiezijing().setAlive(false);
				         			field.setCreature(this, positionX, positionY);
				         			 synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" win shejing\r\n");
				         				 field.getGround().getTextArea().append("蝎子精阵亡\n");
				         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
				         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
				         	        	 }
		         				}
		         				else {//
		         					alive=false;
				         			synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" lose shejing\r\n");
				         				 field.getGround().getTextArea().append(name[rank-1]+"阵亡\n");
				         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
				         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
				         	        	 }
		         				}
		         				break;
		         			}
		        		}
		         	}
		         		else if(toObj.equals("Xiaolouluo")) {
		            		for(String s:field.getBattleStrs()) {
			         			String[] arr=s.split(" ");
			         			if(arr[1].contains("hulu"+rank)&&arr[3].contains("louluo")) {//交火
			         				int ranktmp=arr[3].charAt(6)-'0';
			         				
			         				if(arr[2].contains("win")) {//葫芦娃击败
			         					field.getXiaolouluos()[ranktmp-1].setAlive(false);
					         			field.setCreature(this, positionX, positionY);
					         			 synchronized(this) {
					         				// field.getBuffer().append("battle: hulu"+rank+" win shejing\r\n");
					         				 field.getGround().getTextArea().append("小喽啰"+ranktmp+"号阵亡\n");
					         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
					         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
					         	        	 }
			         				}
			         				else {//
			         					alive=false;
					         			synchronized(this) {
					         				// field.getBuffer().append("battle: hulu"+rank+" lose shejing\r\n");
					         				 field.getGround().getTextArea().append(name[rank-1]+"阵亡\n");
					         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
					         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
					         	        	 }
			         				}
			         				break;
			         			}
			         		}
		         		}
		         	
		         		
		         	}
		         }
		         else //准备跳的下一格为空格
		         {
		        	 field.setCreature(this, positionX, positionY);
		        	 synchronized(this) {
		            	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
		            	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
		            	 }
		         }
		         if(alive==true) {
			         this.setX(nx);
			         this.setY(ny);
		         }
			}
	    }

		private void selectMove() {
			if(y()>90&&y()<270&&x()>90&&x()<270) {
				Random rand = new Random();
		    	int tmp=rand.nextInt(4);
		    	if(tmp==0)//往上移动一格 
		    		{
		    		if(this.y()>=50) {
		    		this.move(0, -20);
		    	

		    		}	
		    	}
		    	else if(tmp==1) {//往下移动一格
		    		if(this.y()<=310) {
		        		this.move(0, 20);
		        		}
		    	}
		    	else if(tmp==2) {//往右移动一格
		    		if(this.x()<=310) {
		    		 this.move(20, 0);
		    		}
		    	}
		    	else if(tmp==3) {//往左移动一格
		    		if(this.x()>=50) {
		    		 this.move(-20, 0);
		    		}
		    	 }
		    	return;
			}
	    	if(x()>=270) {
		    	Random rand = new Random();
		    	int tmp=rand.nextInt(6);
		    	if(tmp==0)//往上移动一格 
		    		{
		    		if(this.y()>=50) {
		    		this.move(0, -20);
		    		}
		    	}
		    	else if(tmp==1) {//往下移动一格
		    		if(this.y()<=310) {
		        		this.move(0, 20);
		        		}
		    	}
		    	else if(tmp==2) {//往右移动一格
		    		if(this.x()<=310) {
		    		 this.move(20, 0);
		    		}
		    	}
		    	else if(tmp==3||tmp==4||tmp==5) {//往左移动一格
		    		if(this.x()>=50) {
		    		 this.move(-20, 0);
		    		}
		    	 }
		    	return;
	    	}
	    	if(x()<=90){
	    		Random rand = new Random();
		    	int tmp=rand.nextInt(6);
		    	if(tmp==0)//往上移动一格 
		    		{
		    		if(this.y()>=50) {
		    		this.move(0, -20);
		    		}
		    	}
		    	else if(tmp==1) {//往下移动一格
		    		if(this.y()<=310) {
		        		this.move(0, 20);
		        		field.getBuffer().append("hulu"+rank+" 0 1\r\n");
		        		}	
		    	}
		    	else if(tmp==2||tmp==4||tmp==5) {//往右移动一格
		    		if(this.x()<=310) {
		    		 this.move(20, 0);
		    		}
		    	}
		    	else if(tmp==3) {//往左移动一格
		    		if(this.x()>=50) {
		    		 this.move(-20, 0);
		    		}
		    	}
		    	return;
	    	}
	    	if(y()>=270) {
	    		Random rand = new Random();
		    	int tmp=rand.nextInt(6);
		    	if(tmp==0||tmp==4||tmp==5)//往上移动一格 
		    		{
		    		if(this.y()>=50) {
		    		this.move(0, -20);
		    		}
		    	}
		    	else if(tmp==1) {//往下移动一格
		    		if(this.y()<=310) {
		        		this.move(0, 20);
		        		}
		    	}
		    	else if(tmp==2) {//往右移动一格
		    		if(this.x()<=310) {
		    		 this.move(20, 0);
		    		}
		    	}
		    	else if(tmp==3) {//往左移动一格
		    		if(this.x()>=50) {
		    		 this.move(-20, 0);
		    		}
		    	}
		    	return;
	    	}
	    	if(y()<=90) {
	    		Random rand = new Random();
		    	int tmp=rand.nextInt(6);
		    	if(tmp==0)//往上移动一格 
		    		{
		    		if(this.y()>=50) {
		    		this.move(0, -20);
		    		}
		    	}
		    	else if(tmp==1||tmp==4||tmp==5) {//往下移动一格
		    		if(this.y()<=310) {
		        		this.move(0, 20);
		        		}
		    	}
		    	else if(tmp==2) {//往右移动一格
		    		if(this.x()<=310) {
		    		 this.move(20, 0);
		    		}
		    	}
		    	else if(tmp==3) {//往左移动一格
		    		if(this.x()>=50) {
		    		 this.move(-20, 0);
		    		}
		    	}
		    	return;
	    	}
	    	
	    	Random rand = new Random();
	    	int tmp=rand.nextInt(4);
	    	if(tmp==0)//往上移动一格 
	    		{
	    		if(this.y()>=50) {
	    		this.move(0, -20);
	    		

	    		}
	    	}
	    	else if(tmp==1) {//往下移动一格
	    		if(this.y()<=310) {
	        		this.move(0, 20);
	        		}
	    	}
	    	else if(tmp==2) {//往右移动一格
	    		if(this.x()<=310) {
	    		 this.move(20, 0);
	    		}
	    	}
	    	else if(tmp==3) {//往左移动一格
	    		if(this.x()>=50) {
	    		 this.move(-20, 0);
	    		}
	    	 }
	    
	    	
	    }
	    public void run() {
	        while (!Thread.interrupted()) {
	        	if(alive==false&&field.isFromFile()==false) {
	        		break;
	        	}
	          



	            	selectMove();

	         
	            try {

	            	 Thread.sleep(sleepTime);
	             
	                this.field.repaint();

	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	      
	    }

		public void setPosition(Position position) {
			// TODO Auto-generated method stub
			this.position = position;
	        position.setHolder(this);
		}

		public Position getPosition() {
			// TODO Auto-generated method stub
			return position;
		}
	    
	
}
