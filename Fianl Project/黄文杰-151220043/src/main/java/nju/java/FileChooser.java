package nju.java;



import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class FileChooser{  
    private JFrame frame;   
      
    public FileChooser(JFrame frame) {  
        this.frame = frame;  
    }
    public String show() {  
    	JFileChooser chooser = new JFileChooser(".");  
        chooser.showOpenDialog(frame);  
        String filePath = chooser.getSelectedFile().getAbsolutePath();  
        return filePath;
        //System.out.println(filePath);
    }  

}  
