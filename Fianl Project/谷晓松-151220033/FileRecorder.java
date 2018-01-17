package com.gxs;



import java.io.*;
import java.util.ArrayList;

public class FileRecorder
{
	private ObjectOutputStream os;
	private String filename;
	static
	{
		File dir=new File(Constant.SAVEPATH);
		if(!dir.exists())
			dir.mkdir();
	}
	FileRecorder(String filename)
	{
		this.filename=filename;
		try {
			os=new ObjectOutputStream(new FileOutputStream(filename));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	FileRecorder()
	{
		int i=0;
		filename=Constant.SAVEPATH+"r";
		while(new File(filename+i).exists())
			i++;
		filename+=i;
		try {
			os=new ObjectOutputStream(new FileOutputStream(filename));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public synchronized void recordObject(Serializable s)
	{
		try {
			os.writeObject(s);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void close()
	{
		try {
			os.writeObject(null);
			os.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	static ArrayList<File> getSave(int num)
	{
		ArrayList<File> af=new ArrayList<File>();
		String filename=Constant.SAVEPATH+"r";
		for(int i=0;i<num;i++)
		{
			File f=new File(filename+i);
			if(f.exists())
				af.add(f);
		}
		return af;
	}
}