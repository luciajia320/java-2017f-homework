package nju.hulu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JFileChooser;


public class FileHelper {
	public void saveRecords(Recorder recorder,String head) {
		
		
		File newfile = new File("records/");
		if (!newfile.exists())
			newfile.mkdir();
		UUID uuid = UUID.randomUUID();
		String fileName=head+uuid.toString()+".rec";
	    File file = new File("records/"+fileName); 
	    try{
	    		if (!file.exists())   
	    			file.createNewFile();
	   
	    		  
	    		FileOutputStream fos = new FileOutputStream(file);  
	    		ObjectOutputStream oos = new ObjectOutputStream(fos);  		   
			oos.writeObject(recorder);
			oos.flush();  
			oos.close();  
			fos.close(); 
		
		} 	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		
	}
	

	public Recorder readFile() throws Exception{
		File newfile = new File("records/");
		if (!newfile.exists())
			newfile.mkdir();
		
		File samplefile = new File("records/Sample.rec");
		if (!samplefile.exists())
		{
		//	System.out.println("here");
			copyFile("classes/Sample.rec","records/Sample.rec");
		}
		
		
		JFileChooser jfc=new JFileChooser();
		jfc.setCurrentDirectory(new File("records/"));
		
		
        if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            File file=jfc.getSelectedFile();
      
            if (!file.getName().endsWith(".rec"))
            {
            
            	System.out.println("wrong File");	
            	return null;
            }
           
            FileInputStream fis = new FileInputStream(file);  
            ObjectInputStream ois = new ObjectInputStream(fis); 
            
            Recorder recorder = (Recorder) ois.readObject();  
          //  System.out.println(recorder.current);
         
            ois.close();  
            fis.close();  
        //    System.out.println(file.getName());
            return recorder;
        }
        else
        {
            System.out.println("No file is selected!");
        }
		return null;
	}
	public void copyFile(String oldPath, String newPath) { 
		try { 
		int bytesum = 0; 
		int byteread = 0; 
		File oldfile = new File(oldPath); 
		System.out.println(oldPath);
		if (oldfile.exists()) { //文件存在时 
			
		InputStream inStream = new FileInputStream(oldPath); //读入原文件 
		FileOutputStream fs = new FileOutputStream(newPath); 
		byte[] buffer = new byte[1444]; 
		int length; 
		while ( (byteread = inStream.read(buffer)) != -1) { 
		bytesum += byteread; //字节数 文件大小 
		System.out.println(bytesum); 
		fs.write(buffer, 0, byteread); 
		} 
		inStream.close(); 
		} 
		} 
		catch (Exception e) { 
		System.out.println("复制单个文件操作出错"); 
		e.printStackTrace(); 

		} 

		} 

	
	
}
