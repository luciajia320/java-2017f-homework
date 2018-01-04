import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class decorateImg extends Thing2D{
	private Image winImage;
	private Image loseImage;
	private Image bkgImage;
	int x;
	int y;
	public decorateImg() {
		super(0,0);
        URL loc1,loc2,loc3;
        loc1 = this.getClass().getClassLoader().getResource("win.png");
        loc2 = this.getClass().getClassLoader().getResource("lose.png"); 
        loc3 = this.getClass().getClassLoader().getResource("bkg2.jpg");
        winImage = new ImageIcon(loc1).getImage();
        loseImage = new ImageIcon(loc2).getImage();
        bkgImage= new ImageIcon(loc3).getImage();
	}
	
	
	public Image getWinImage() {
		this.setX(50);
		this.setY(50);
		return winImage;
	}
	
	public Image getLoseImage() {
		this.setX(450);
		this.setY(300);
		return loseImage;
	}
	
	public Image getBkgImage() {
		this.setX(50);
		this.setY(50);
		return bkgImage;
	} 
}
