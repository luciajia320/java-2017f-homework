package nju.hulu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
}
