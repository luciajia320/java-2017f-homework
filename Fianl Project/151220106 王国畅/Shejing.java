import java.net.URL;

import javax.swing.ImageIcon;

public class Shejing extends Louluo{
	public Shejing(FenceSitter audience, Field field) {
		super(audience, field);
		// TODO Auto-generated constructor stub
        URL loc = this.getClass().getClassLoader().getResource("Shejing.png");
        ImageIcon iia = new ImageIcon(loc);
		this.image = iia.getImage();
		this.luck+=2;
	}
	
	@Override
	public int getNum(){
		return 10;
	}
}
