package FinalProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class Stage extends Thread{
	public static Base[][] ground_below;
	public static Middle[][] ground_mid;
	public static Creature [][] ground_above;
	public static Extra [][] ground_extra;
	private static int xsize;
	private static int ysize;
	private static boolean pause; 
	private static boolean first;
	private static int   count;
	private Thread thread;
	private Step step;
	public Stage(int x,int y) {
		xsize = x;
		ysize = y;
		ground_below = new Base [xsize][ysize];
		ground_mid = new Middle [xsize][ysize];
		ground_above = new Creature [xsize][ysize];
		ground_extra = new Extra [xsize][ysize];
		clear();
		pause = false;
	}
	public static void setfirst() {
		count = 0;
		first = true;
	}
	public static void clear() {
		for(int i=0;i<xsize;i++)
			for(int j=0;j<ysize;j++) {
				Base t1 = new Base();
				Middle t2 = new Middle();
				Creature t3 = new Creature();
				Extra t4 = new Extra();
				ground_below[i][j] = t1;
				ground_mid[i][j] = t2;
				ground_above[i][j] = t3;
				ground_extra[i][j] = t4;
			}
	}
	public void setstep(Step s) {
		step = s;
	}
	public static int xsize() {
		return xsize;
	}
	public static int ysize() {
		return ysize;
	}
	public static void show() {
		/**for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) 
				ground[j][i].showname();
			System.out.println(" ");
			}
		System.out.println();*/
		try
	      {
			 File file = new File("data.hlw"); 
	         FileOutputStream fileOut;
	         if(first) {
	        	 fileOut= new FileOutputStream(file);
	        	 first = false;
	         }
	         else {
	        	 fileOut= new FileOutputStream(file,true);
	         }
	         for(int i=0;i<xsize;i++)
	        	 for(int j=0;j<ysize;j++) {
	        		 fileOut.write(ground_below[i][j].gettype());
	        		 fileOut.write(ground_mid[i][j].gettype());
	        		 fileOut.write(ground_above[i][j].gettype());
	        		 fileOut.write(ground_above[i][j].getdirection());
	        		 fileOut.write(ground_extra[i][j].gettype());
	        	 }
	         count++;
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		Ground.flush();
	}
	public boolean not_complete() {
		int z = 0;
		for(int i=0;i<xsize;i++)
			for(int j=0;j<ysize;j++) {
					int x = ground_above[i][j].getroop();
					if(x!=z && x!=0) {
						if(z==0)
							z = x;
						else {
							return true;
						}
					}
					
			}
		return false;
	}
	public static void gameover() {
		pause = true;
	}
	public void interupt() {
		thread.interrupt();
	}
	public void run() {
		try {
            while (!Thread.interrupted() && not_complete() &&!pause) {
            	show();
            	for(int i=0;i<xsize;i++)
        			for(int j=0;j<ysize;j++) {
        				ground_extra[i][j].settype(0);
        			}
            	Thread.sleep(500);
            	step.showed();
            	step.waitFormoved();
            }
        } catch (InterruptedException e) {
            System.out.println("mission completed!");
        }
		if(pause == false) {
        System.out.println("mission completed!");
        show();
        //thread = null;
        Level.levelpass();}
		else {
			try {
			 File file = new File("data.hlw"); 
	         FileOutputStream fileOut;
	         fileOut= new FileOutputStream(file,true);
	         fileOut.write(count);
	         fileOut.close();
			}catch(IOException i)
		      {
		          i.printStackTrace();
		      }
			System.out.println("game over!");
		}
    }
	public void start() {
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
}
