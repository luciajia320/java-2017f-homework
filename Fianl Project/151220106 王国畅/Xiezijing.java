import java.net.URL;

import javax.swing.ImageIcon;

public class Xiezijing extends Louluo{
	public Xiezijing(FenceSitter audience, Field field) {
		super(audience, field);
		// TODO Auto-generated constructor stub
        URL loc = this.getClass().getClassLoader().getResource("Xiezijing.png");
        ImageIcon iia = new ImageIcon(loc);
		this.image = iia.getImage();
		this.luck-=2;
	}
	
	@Override
	public int getNum(){
		return 9;
	}

}
