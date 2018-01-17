/**package FinalProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;

public class Savefile implements java.io.Serializable{
	private Base[][] ground1;
	private Middle[][] ground2;
	private Creature [][] ground3;
	private Extra [][] ground4;
	public Savefile() {
		int xsize = Stage.xsize();
		int ysize = Stage.ysize();
		ground1 = new Base [xsize][ysize];
		ground2 = new Middle [xsize][ysize];
		ground3 = new Creature [xsize][ysize];
		ground4 = new Extra [xsize][ysize];
		for(int i=0;i<xsize;i++)
			for(int j=0;j<ysize;j++) {
				Base t1 = new Base();
				Middle t2 = new Middle();
				Creature t3 = new Creature();
				Extra t4 = new Extra();
				ground1[i][j] = t1;
				ground2[i][j] = t2;
				ground3[i][j] = t3;
				ground4[i][j] = t4;
			}
	}
	public void getinfor() {
		for(int i=0;i<Stage.xsize();i++)
			for(int j=0;j<Stage.ysize();j++) {
				ground1[i][j].settype(Stage.ground_below[i][j].gettype());
				ground2[i][j].settype(Stage.ground_mid[i][j].gettype());
				ground3[i][j].settype(Stage.ground_above[i][j].gettype());
				ground4[i][j].settype(Stage.ground_extra[i][j].gettype());
			}
	}
	public static void save() {
		int count = 0;
		Base[][] ground11;
		Middle[][] ground22;
		Creature [][] ground33;
		Extra [][] ground44;
		int xsize = Stage.xsize();
		int ysize = Stage.ysize();
		ground11 = new Base [xsize][ysize];
		ground22 = new Middle [xsize][ysize];
		ground33 = new Creature [xsize][ysize];
		ground44 = new Extra [xsize][ysize];
		for(int i=0;i<xsize;i++)
			for(int j=0;j<ysize;j++) {
				Base t1 = new Base();
				Middle t2 = new Middle();
				Creature t3 = new Creature();
				Extra t4 = new Extra();
				ground11[i][j] = t1;
				ground22[i][j] = t2;
				ground33[i][j] = t3;
				ground44[i][j] = t4;
			}
		RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile("data.hlw", "r");
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
           FileInputStream fileIn = new FileInputStream("data.hlw");
           //InputStreamReader reader = new InputStreamReader(fileIn, "UTF-8");
           for(int k=0;k<count;k++) {
           for(int i=0;i<Stage.xsize();i++)
          	 for(int j=0;j<Stage.ysize();j++) {
          		 ground11[i][j].settype((int)fileIn.read());
          		 ground22[i][j].settype((int)fileIn.read());
          		 ground33[i][j].settype((int)fileIn.read());
          		 ground33[i][j].setdirection((int)fileIn.read());
          		 ground44[i][j].settype((int)fileIn.read());
          	 }           
           }
           //reader.close();
           fileIn.close();
        }catch(IOException i)
        {
           i.printStackTrace();
           return;
        }
        try{JFileChooser chooser = new JFileChooser();
        int value = chooser.showSaveDialog(null);
        //System.out.println(value);
        if (value == JFileChooser.APPROVE_OPTION) {
            File newFile = chooser.getSelectedFile();
            if (newFile.exists() == false) {
            	File tempFile = new File(chooser.getSelectedFile().getAbsolutePath()+".hlw");
                newFile.createNewFile();
                newFile.renameTo(tempFile);
            }
            FileOutputStream fileOut;
            fileOut= new FileOutputStream(newFile);
            for(int i=0;i<xsize;i++)
	        	 for(int j=0;j<ysize;j++) {
	        		 fileOut.write(ground11[i][j].gettype());
	        		 fileOut.write(ground22[i][j].gettype());
	        		 fileOut.write(ground33[i][j].gettype());
	        		 fileOut.write(ground33[i][j].getdirection());
	        		 fileOut.write(ground44[i][j].gettype());
	        	 }
            fileOut.write(count);
            fileOut.close();
        }
        }catch (IOException e1) {
            e1.printStackTrace();
        }
	}
}*/
