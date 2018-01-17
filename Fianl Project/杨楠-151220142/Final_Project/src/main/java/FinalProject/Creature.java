package FinalProject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Creature extends Type implements Runnable {
	private Thread thread;
	private Step step;
	private int index;
	private int hp;
	private int damage;
	private static int amount;
	public static int All = Level.getamount();
	private int direction;
	private int debug;
	public Creature() {
		row = -1;
		col = -1;
		index = -1;
		hp = -1;
		direction = 2;
		hp = 1;
		damage = 0;
	}
	public Creature(int i,Step s) {
		row = -1;
		col = -1;
		step = s;
		index = i;
		direction = 2;
		hp = 1;
		damage = 0;
		setall(Level.getamount());
		resetamount();
	}
	@Override
	public void settype(int n) {
		t = n;
	}
	public void setpower(int n,int d){  
		hp = n;
		damage = d;
	}
	public int getminushp() {  
		return -damage;
	}
	public int getdirection() {
		return direction;
	}
	public void setdirection(int d) {
		direction = d;
	}
	@Override
	public void setposition(int x,int y) {
		if(x<0||y<0||x>=Stage.xsize()||y>=Stage.ysize()) {
			System.out.println("wrong");
			return ;
		}
		row = x;
		col = y;
		Stage.ground_above[x][y] = this;
	}
	@Override
	public void leaveposition() {
		Creature c = new Creature();
		Stage.ground_above[row][col] = c;
		row = -1;
		col = -1;
	}
	public void dead() {
		if(gettype()!=8) {
			settype(0);
			Stage.ground_mid[row][col].settype(1);
			Stage.ground_above[row][col] = this;
		}
		else {
			settype(0);
			Stage.ground_mid[row][col].settype(1);
			Stage.ground_above[row][col] = this;
			Stage.show();
			Stage.gameover();
		}
	}
	public boolean alive() {
		if(hp<=0) {
			dead();
			return false;
		}
		return true;
	}
	public void uphp(int x) {
		hp += x;
	}
	public void onestep() {
		if(gettype()==0) {
			return ;
		}
		else {
			int	x = row;
			int y = col;
			int dx,dy,c;
			Random r;
			dx = 0;
			dy = 0;
			if(getroop()>0){
				if(Stage.ground_above[x][y+1].getroop()<0) {
					dy = 1;
				}else if(Stage.ground_above[x][y-1].getroop()<0) {
					dy = -1;
				}else if(Stage.ground_above[x+1][y].getroop()<0) {
					dx = 1;
				}else if(Stage.ground_above[x-1][y].getroop()<0) {
					dx = -1;
				}
				else {
				dx = 0;
				dy = 0;
				r = new Random();
				c = Math.abs(r.nextInt() % 4);
				direction = c;
				if(c==0)
					dx = 1;
				else if(c==1) 
					dx = -1;
				else if(c==2)
					dy = 1;
				else 
					dy = -1;
				if(dx*dy!=0)
					System.out.println("err");}
				}
			else {
				dx = -1;
				dy = 0;
				direction = 1;
				if(x==1) {
					dx = 0;
					if(y<(Stage.ysize()/2)) {
						dy = 1;
						direction = 2;
					}else {
						dy = -1;
						direction = 3;
					}
				}
			}
			debug = 0;
			int jd = unable_move(x,y,x+dx,y+dy);
    	while(jd==0) {
    		r = new Random();
    		c = Math.abs(r.nextInt() % 4);
    		direction = c;
    		dx = 0;
    		dy = 0;
        	if(c==0)
        		dx = 1;
        	else if(c==1) 
        		dx = -1;
        	else if(c==2)
        		dy = 1;
        	else 
        		dy = -1;
        	jd = unable_move(x,y,x+dx,y+dy);
    	}
    	if(jd==-1) {
    		dx = 0;
    		dy = 0;
    	}
    	int res = Stage.ground_above[x][y].getroop()+Stage.ground_above[x+dx][y+dy].getroop();
		if(res==0 && Stage.ground_above[x][y].getroop()!=0) {
			Stage.ground_above[x][y].uphp(Stage.ground_above[x+dx][y+dy].getminushp());
			Stage.ground_above[x+dx][y+dy].uphp(Stage.ground_above[x][y].getminushp());
			if(Stage.ground_above[x][y].alive()&&Stage.ground_above[x+dx][y+dy].alive()) {
				Stage.ground_extra[x][y].settype(1);
				Stage.ground_extra[x+dx][y+dy].settype(1);
			}
		}
		else{
			int it = Stage.ground_mid[x+dx][y+dy].effectness();
			if(it!=0 && Stage.ground_above[x][y].getroop()>0) {
				if(it==1) {
					uphp(1);
					Stage.ground_extra[x+dx][y+dy].settype(2);
				}
				else if(it==-1) {
					;//
				}
				Stage.ground_mid[x+dx][y+dy] = new Middle();
			}
			leaveposition();
			setposition(x+dx,y+dy);	
		}
		}
	}
	public int unable_move(int x0,int y0,int x,int y) {
		debug++;
		if(debug >10)
			return -1;
		if(x>=Stage.xsize()||y>=Stage.ysize()||x<0||y<0) {
			return 0;
		}
		if(Stage.ground_mid[x][y].unable_cross()) {
			return 0;
		}
		int res = Stage.ground_above[x][y].getroop()+Stage.ground_above[x0][y0].getroop();
		if(res>1 || res <-1) {
			return 0;
		}
		return 1;
		
	}
	public static int getamount() {
		return amount;
	}
	public static void resetamount() {
		amount = All;
	}
	public static void setall(int i) {
		All = i;
	}
	public void run() {
		try {
            while (!Thread.interrupted()) {
            	step.waitForshowed(index);
            	
            	onestep();
            	
            	step.showing(index);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending task"+gettype());
    }
	public void start() {
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
}

