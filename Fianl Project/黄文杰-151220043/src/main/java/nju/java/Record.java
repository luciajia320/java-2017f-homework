package nju.java;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Record {
	private File file;
	private StringBuilder sb;
	private BufferedWriter  bwriter;
	private BufferedReader 	breader;
	boolean opened;
	public Record(){
		 this.sb=new StringBuilder();
		 this.file=null;
		 this.breader=null;
		 this.bwriter=null;
		 this.opened=false;
     }    
	public boolean isOpened() {
		return opened;
	}
	public void open(String filename){
		this.file  =  new  File(filename); 
		this.opened=true;
        if  (!file.exists())  {    
       	 try {  
                file.createNewFile(); // 文件的创建，注意与文件夹创建的区别  
            } catch (IOException e) {  
                e.printStackTrace();  
            }    
            System.out.println("创建文件成功");  
        }    
        try{
			this.breader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			this.bwriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
		}catch(IOException e){
				e.printStackTrace();
		}
	}
	public void close(){
		try{
			if(this.breader!=null)
			this.breader.close();
			if(this.bwriter!=null)
			this.bwriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void record(String str){
		this.sb.append(str);
	}
	public boolean writeRecord(){
		
		try{
			
			FileWriter fw=new FileWriter(file);
			fw.write("");
			fw.close();
			bwriter.write(sb.toString());
			//bwriter.append(str);
			//this.bwriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return true;
	}
	public String readRecord(){
		String str="";
		try{
			//this.breader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			 str=breader.readLine();
			// System.out.println(str);
			 //this.breader.close();
		}catch(IOException e){
				e.printStackTrace();
		}
		return str;
	}
}

