import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReplayDialog extends JDialog implements ActionListener{
    JPanel panel;
    JLabel label;
    JButton button1;
    JButton button2;
    JComboBox<String> box;  
    String filename; 
    private boolean replayBegin = false;
      
    public ReplayDialog(Frame f, String s, boolean b)   
    {  
        super(f, s, b);  
        setLayout(new FlowLayout(FlowLayout.CENTER,500,20));  
        panel = new JPanel();
        label = new JLabel("选择回放文件");  
        box = new JComboBox<String>();  
        button1 = new JButton("Yes");
        button2 = new JButton("Cancle");  
        button1.addActionListener(this);   
        button2.addActionListener(this);    
        panel.add(button1);  
        panel.add(button2); 
        box.addItem("null");

        add(label);  
        add(box); 
        add(panel); 
        
        setSize(400, 300);  
        setVisible(false);
        setLocationRelativeTo(null);
    }  
  
    public String getFileName() {
    	return filename;
    }
    
    public void AddRecord() {
    	if(box.getSelectedItem() == "null"&&box.getItemCount()==1)
    		box.remove(0);
    	int newRecord = box.getItemCount()+ 1;
    	box.addItem("record "+ newRecord);
    }
    
    public void updateRecord() throws IOException {
    	box.removeAllItems();
    	int size = fileSize();
    	for(int i = 0;i<size;i++) {
    		box.addItem("record "+ i);
    	}
    }
    
    public int fileSize() throws IOException {
		File path = new File("");
		String dirPath = path.getCanonicalPath() + "\\src\\main\\resources\\replay";
		File replayDir = new File(dirPath);
		String[] list = replayDir.list();
		return list.length;
    }
    
    public void actionPerformed(ActionEvent e) {  
        if(e.getSource() == button1)  
        {  
            filename = (String)box.getSelectedItem();
            this.replayBegin = true;
            System.out.println(filename);
            setVisible(false);  
        }  
        else  
        {  
            setVisible(false);  
        }  
    } 
    
    public void setReplayBegin(boolean v) {
    	this.replayBegin = v;
    }
    
    public boolean isReplayBegin() {
    	return this.replayBegin;
    }
}
