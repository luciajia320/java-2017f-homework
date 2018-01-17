import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;



public final class Ground extends JFrame {

    private final int OFFSET = 30;
    public JTextArea getTextArea() {
		return textArea;
	}


	private JTextArea textArea = new JTextArea( 14, 10);
    


    //Mouseclicked mouseclicked=new Mouseclicked();

    
    
    public Ground() {
        InitUI();
    }

   
    public void InitUI() {
    	setLayout(new BorderLayout());
    	
        Field field = new Field(this);
        add(field);
      
       // addMouseListener(mouseclicked);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET+10,
                field.getBoardHeight() +  11*OFFSET);
        textArea.setEditable(false);
        getContentPane().add("South", textArea);
        
        
        //add(t2);
        setLocationRelativeTo(null);
       
        setTitle("Ground");
    }


  
	
}
