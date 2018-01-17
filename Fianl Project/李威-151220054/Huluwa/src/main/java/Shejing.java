import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Shejing extends Thing2D implements Runnable {
    private Field field;
    private Position position;
    private boolean alive=true;
    private String[] name= {"大娃","二娃","三娃","四娃","五娃","六娃","七娃"};
 
    public Shejing(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("snake.png");
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

	public void move(int x, int y) {
		if(field.isFromFile()==false) {
			int prePositionX=(this.x()-30)/20;
			int prePositionY=(this.y()-30)/20;
	    	 int nx = this.x() + x;
	         int ny = this.y() + y;
	         int positionX = (nx-30)/20;
	         int positionY = (ny-30)/20;
	         if(field.getPositions()[positionX][positionY].getHolder()!=null) {
	         	String toObj=field.getPositions()[positionX][positionY].getHolder().getClass().getSimpleName();
	         	if(toObj.equals("Xiezijing")||toObj.equals("Xiaolouluo"))//friend
	         		return;
	         	else
	         		//enemy , attack
	         	{
	         		if(toObj.equals("HuLuWa")) {
	         			try {
							Random random=new Random();
							int a=random.nextInt(4);
							int huluRank=(field.getPositions()[positionX][positionY].getHolder()).getRank();
							if(a==0) {//葫芦娃取胜.概率三分之一
							alive=false;
							//field.setCreature(this, positionX, positionY);
							 synchronized(this) {
								 field.getBuffer().append("battle: shejing lose hulu"+huluRank+"\r\n");
								 if(!field.getGround().getTextArea().getText().contains("蛇精"))
							     field.getGround().getTextArea().append("蛇精阵亡\n");
								 field.getDeadSquare().put(this.x(), this.y());
								 field.getPositions()[prePositionX][prePositionY].setHolder(null);
								 field.getPositions()[prePositionX][prePositionY].setOwned(false);
								 }
							}
							else {//葫芦娃阵亡概率三分之二,蛇精取胜
								
								
								field.getHuLuWas()[huluRank-1].setAlive(false);
								
								field.setCreature(this, positionX, positionY);
								
								 synchronized(this) {
									 field.getBuffer().append("battle: shejing win hulu"+huluRank+"\r\n");
									 if(!field.getGround().getTextArea().getText().contains(name[huluRank-1]))
								     field.getGround().getTextArea().append(name[huluRank-1]+ "阵亡\n");
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
	         		else if(toObj.equals("Grandpa")) {
	        			try {
							Random random=new Random();
							int a=random.nextInt(5);
							if(a==0) {//爷爷取胜.概率五分之一
							alive=false;
							//field.setCreature(this, positionX, positionY);
							 synchronized(this) {
								 field.getBuffer().append("battle: shejing lose grandpa\r\n");
								 if(!field.getGround().getTextArea().getText().contains("蛇精"))
							     field.getGround().getTextArea().append("蛇精阵亡\n");
								 field.getDeadSquare().put(this.x(), this.y());
								 field.getPositions()[prePositionX][prePositionY].setHolder(null);
								 field.getPositions()[prePositionX][prePositionY].setOwned(false);
								 }
							}
							else {//爷爷阵亡概率五分之四,蛇精取胜
								
								
								field.getGrandpa().setAlive(false);
								
								field.setCreature(this, positionX, positionY);
								
								 synchronized(this) {
									 field.getBuffer().append("battle: shejing win grandpa\r\n");
									 if(!field.getGround().getTextArea().getText().contains("爷爷"))
									 field.getGround().getTextArea().append( "爷爷阵亡\n");
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
		else {//fupan
			int prePositionX=(this.x()-30)/20;
			int prePositionY=(this.y()-30)/20;
	    	 int nx = this.x() + x;
	         int ny = this.y() + y;
	         int positionX = (nx-30)/20;
	         int positionY = (ny-30)/20;
	         if(field.getPositions()[positionX][positionY].getHolder()!=null) {
	         	String toObj=field.getPositions()[positionX][positionY].getHolder().getClass().getSimpleName();
	         	if(toObj.equals("Xiezijing")||toObj.equals("Xiaolouluo"))//friend
	         		return;
	         	else
	         		//enemy , attack
	         	{
	         		if(toObj.equals("HuLuWa")) {
	         			for(String s:field.getBattleStrs()) {
		         			String[] arr=s.split(" ");
		         			if(arr[1].contains("shejing")&&arr[3].contains("hulu")) {//交火
		         				int ranktmp=arr[3].charAt(4)-'0';
		         				
		         				if(arr[2].contains("win")) {//蛇精击败葫芦娃
		         					field.getHuLuWas()[ranktmp-1].setAlive(false);
				         			field.setCreature(this, positionX, positionY);
				         			 synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" win shejing\r\n");
				         				 field.getGround().getTextArea().append(name[ranktmp-1]+"阵亡\n");
				         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
				         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
				         	        	 }
		         				}
		         				else {//
		         					alive=false;
				         			synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" lose shejing\r\n");
				         				 field.getGround().getTextArea().append("蛇精阵亡\n");
				         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
				         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
				         	        	 }
		         				}
		         				break;
		         			}
		         		}
	         		}
	         		else if(toObj.equals("Grandpa")) {
	        			for(String s:field.getBattleStrs()) {
		         			String[] arr=s.split(" ");
		         			if(arr[1].contains("shejing")&&arr[3].contains("grandpa")) {//交火
		         				
		         				
		         				if(arr[2].contains("win")) {//蛇精击败爷爷
		         					field.getGrandpa().setAlive(false);
				         			field.setCreature(this, positionX, positionY);
				         			 synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" win shejing\r\n");
				         				 field.getGround().getTextArea().append("爷爷阵亡\n");
				         	        	 field.getPositions()[prePositionX][prePositionY].setHolder(null);
				         	        	 field.getPositions()[prePositionX][prePositionY].setOwned(false);
				         	        	 }
		         				}
		         				else {//
		         					alive=false;
				         			synchronized(this) {
				         				// field.getBuffer().append("battle: hulu"+rank+" lose shejing\r\n");
				         				 field.getGround().getTextArea().append("蛇精阵亡\n");
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
    	if(x()<=90) {
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

	            //this.move(rand.nextInt(10), rand.nextInt(10));
	            try {

	            	 Thread.sleep(sleepTime);
	               // Thread.sleep(rand.nextInt(1000) + 1000);
	                 this.field.repaint();

	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	       
	    }

	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		 this.position = position;
	        position.setHolder(this);
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
}