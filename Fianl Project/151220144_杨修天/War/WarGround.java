package War;

import javax.swing.JFrame;
import java.awt.Color;  
import java.awt.Font;  
import java.awt.Image;  
import java.awt.Point;  
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;  
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;

import javax.sound.sampled.LineUnavailableException;  
import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
import javax.swing.JLabel;
import javax.swing.text.BadLocationException;

import java.util.concurrent.*; 

import org.omg.CORBA.PUBLIC_MEMBER;




public class WarGround extends JFrame implements KeyListener{
	private int round;
	private Holder holder;
    private JLabel back; 
    private JLabel [] bro;
    private Huluwa brothers[];
    private ImageIcon[]images;
    private Grandpa yeye;
    private JLabel grand;
    private ImageIcon grandpa;
    private ImageIcon she;
    private Snake beauty;
    private JLabel snake;
    private Xiezijing guai;
    private JLabel xiezi;
    private ImageIcon xie;
    private Luoluo []xiaode;
    private JLabel []louluo;
    private ImageIcon luo;
    public WarGround(){  
    	round=1;
    	holder=new Holder();
    	this.setResizable(false);//不能够修改大小  
        this.getContentPane().setLayout(null);  
        this.setTitle("葫芦娃战争");  
        this.setBounds(300,100,800,500);  
        back=new JLabel();  
        ImageIcon icon=new ImageIcon(this.getClass().getResource("back.gif"));  
        back.setIcon(icon);  
        back.setBounds(0,-20,800,500);  
        this.getContentPane().add(back);
        addKeyListener(this);
        brothers=new Huluwa[7];
        xiaode=new Luoluo[7];
		 for (int i = 0; i < brothers.length; i++)
		 {
	            brothers[i] = new Huluwa(Identity.values()[i],this);
	            xiaode[i]=new Luoluo(i,this);
	     }
		 bro=new JLabel[7];
		 images=new ImageIcon[7];
		 louluo=new JLabel[7];
		 luo=new ImageIcon();
		 for(int i=0;i<7;i++){  
	            bro[i]=new JLabel();  
	            images[i]=brothers[i].getimage();   			
	            bro[i].setSize( images[i].getIconWidth(), images[i].getIconHeight());  
	       bro[i].setIcon(images[i]);
	       this.back.add(bro[i]);
	       louluo[i]=new JLabel(); 
	       luo=xiaode[i].getimage();
	       louluo[i].setSize( luo.getIconWidth(), luo.getIconHeight());  
	       louluo[i].setIcon(luo);
	       this.back.add(louluo[i]);
		 }    
		 	for(int i=0,j=1,k=1;i<7;i++,k++) 
		 	{
		 		bro[i].setLocation(j*100,i*50+100); 
		 		brothers[i].setposition(j*100,i*50+100);
		 		holder.setliver(j*100, i*50+100, brothers[i]);
		 		
		 		louluo[i].setLocation(j*500+k*30,i*50+100); 
		 		xiaode[i].setposition(j*500+k*30,i*50+100);
		 		holder.setliver(j*500+k*30,i*50+100,xiaode[i]);
		 	}
		 	
		 	yeye=new Grandpa(this);
		 	grand=new JLabel();
			grandpa=new ImageIcon();
			grandpa=yeye.getimage();
			grand.setSize( grandpa.getIconWidth(), grandpa.getIconHeight());
			grand.setIcon(grandpa);
		    this.back.add(grand);
		    grand.setLocation(50,200); 
		    yeye.setposition(50,200);
		    holder.setliver(50,200,yeye);
		    
		    beauty=new Snake(this);
		 	snake=new JLabel();
			she=new ImageIcon();
			she=beauty.getimage();
			snake.setSize( she.getIconWidth(), she.getIconHeight()); 
			snake.setIcon(she);
		    this.back.add(snake);
		    snake.setLocation(700,250); 
		    beauty.setposition(700,250);
		    holder.setliver(700,250,beauty);
		   
		    guai=new Xiezijing(this);
		 	xiezi=new JLabel();
			xie=new ImageIcon();
			xie=guai.getimage();
			xiezi.setSize( xie.getIconWidth(), xie.getIconHeight()); 
			xiezi.setIcon(xie);
		    this.back.add(xiezi);
		    xiezi.setLocation(500,200);
		    guai.setposition(500,200);
		    holder.setliver(500,200,guai);
		    
		    this.setVisible(true); 
		    
		    
		    
    }  
    public void yeyemove(int xx,int yy,int x,int y)
    {
    	holder.moveliver(xx,yy);
    	holder.setliver(x,y,yeye);
    	grand.setLocation(x,y); 
    }
    public void huluwamove(int xx,int yy,int x,int y,int z)
    {	holder.moveliver(xx,yy);
    	holder.setliver(x, y,brothers[z]);
    	bro[z].setLocation(x,y); 
    }
    public void shejingmove(int xx,int yy,int x,int y)
    {	holder.moveliver(xx,yy);
		holder.setliver(x,y,beauty);
    	snake.setLocation(x,y); 
    }
    public void luoluomove(int xx,int yy,int x,int y,int z)
    {	holder.moveliver(xx,yy);
    	holder.setliver(x,y,xiaode[z]);
    	louluo[z].setLocation(x,y); 
    }    
    public void xiezijingmove(int xx,int yy,int x,int y)
    {	holder.moveliver(xx,yy);
		holder.setliver(x,y,guai);
    	xiezi.setLocation(x,y); 
    }
    public void luoluodie(int x,int y,int z)
    {	holder.fandie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=xiaode[z].getimage();
    	louluo[z].setSize(temp.getIconWidth(),temp.getIconHeight()); 
		louluo[z].setIcon(temp);
    	louluo[z].setLocation(x,y); 
    }   
    public void xiezijingdie(int x,int y)
    {	holder.fandie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=guai.getimage();
    	xiezi.setSize(temp.getIconWidth(),temp.getIconHeight()); 
		xiezi.setIcon(temp);
    	xiezi.setLocation(x,y); 
    }  
    public void shejingdie(int x,int y)
    {	holder.fandie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=beauty.getimage();
    	snake.setSize(temp.getIconWidth(),temp.getIconHeight()); 
    	snake.setIcon(temp);
    	snake.setLocation(x,y); 
    }  
    public void huluwadie(int x,int y,int z)
    {	holder.zhengdie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=brothers[z].getimage();
    	bro[z].setSize(temp.getIconWidth(),temp.getIconHeight()); 
		bro[z].setIcon(temp);
    	bro[z].setLocation(x,y); 
    }  
    public void yeyedie(int x,int y)
    {	holder.zhengdie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=yeye.getimage();
    	grand.setSize(temp.getIconWidth(),temp.getIconHeight()); 
		grand.setIcon(temp);
    	grand.setLocation(x,y); 
    }  
    public Holder getholder()
    {
    	return holder;
    }
    public int getround()
    {
    	return round;
    }
    public void setround()
    {
    	round++;
    }
    public void keyPressed(KeyEvent e)
	{	if(e.getKeyChar()==e.VK_SPACE)
		{	
		ExecutorService exec = Executors.newFixedThreadPool(100);
	    exec.execute(yeye);
	    exec.execute(beauty);
	    for(int i=0;i<7;i++)
	    	{exec.execute(brothers[i]);
	         exec.execute(xiaode[i]);
	    	}
	    exec.execute(guai);
		}
		
	}
public void keyTyped(KeyEvent e)
	{	
	}
public void keyReleased(KeyEvent e)
	{	
		
	}
    
}