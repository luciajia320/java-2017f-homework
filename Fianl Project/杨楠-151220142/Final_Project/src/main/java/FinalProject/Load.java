package FinalProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;

public class Load implements Runnable {
	private Thread t;
	private String path;
	private int count;
	public Load(String s) {
		path = s;
	}
	public void run() {
	      try {
	    	  RandomAccessFile rf = null;
	          try {
	              rf = new RandomAccessFile(path, "r");
	              long fileLength = rf.length();
	              long start = rf.getFilePointer();
	              long readIndex = start + fileLength -1;
	              rf.seek(readIndex);
	              count = rf.read();
	              rf.close();
	          }catch (IOException e) {
	              e.printStackTrace();
	          } 
	          try
	          {
	             FileInputStream fileIn = new FileInputStream(path);
	             InputStreamReader reader = new InputStreamReader(fileIn, "UTF-8");
	             for(int k=0;k<count;k++) {
	             for(int i=0;i<Stage.xsize();i++)
	            	 for(int j=0;j<Stage.ysize();j++) {
	            		 Stage.ground_below[i][j].settype((int)fileIn.read());
	            		 Stage.ground_mid[i][j].settype((int)fileIn.read());
	            		 Stage.ground_above[i][j].settype((int)fileIn.read());
	            		 Stage.ground_above[i][j].setdirection((int)fileIn.read());
	            		 Stage.ground_extra[i][j].settype((int)fileIn.read());
	            	 }
	              Ground.flush();
		          Thread.sleep(500);
	             }
	             reader.close();
	             fileIn.close();
	          }catch(IOException i)
	          {
	             i.printStackTrace();
	             return;
	          }
	      }catch (InterruptedException e) {
	         System.out.println("Load  interrupted.");
	      }
	}
	   public void start () {
	      if (t == null) {
	    	  Level.load();
	         t = new Thread (this);
	         t.start ();
	      }
	   }
}
