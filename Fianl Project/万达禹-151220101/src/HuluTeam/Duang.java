

package HuluTeam;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Duang extends JPanel 
{
	
	private String path = "source/1053-";
	private int idx;
	Duang(int idx){
		this.idx=idx;
	}
	@Override
	public void paint(Graphics g)
    {
            String temp = "";
            
            ImageObserver imageObserver = new ImageObserver()
            {

                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y,
                        int width, int height)
                {
                    // TODO Auto-generated method stub
                    return false;
                }
            };
            
            if(idx <= 10) {
            	temp = path  + idx + ".png";
            
            	ImageIcon it = new ImageIcon(temp);
            
            	g.drawImage(it.getImage(), 0, 0, 200, 200,
            			imageObserver);
            }
            
    }

    

}
