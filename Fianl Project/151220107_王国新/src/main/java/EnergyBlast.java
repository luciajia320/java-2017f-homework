import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class EnergyBlast extends Thing2D {
	private int damage;
	private Image blastImage;
	private int speedX;
	private int speedY;

	private Boolean Visible;

	public EnergyBlast(int x, int y, Creature c) {
		super(x, y);
		this.Visible = false;
		Random random = new Random();
		
		URL loc = null;
		if (c instanceof Huluwa) {
			int index = ((Huluwa) c).getSeniority().ordinal() + 1;
			loc = this.getClass().getClassLoader().getResource(index + "Blast.png");
			this.damage = random.nextInt(11) + 25;
			this.speedX = 15;
		} else if (c instanceof Frog) {
			loc = this.getClass().getClassLoader().getResource("frogBlast.png");
			this.damage = random.nextInt(11) + 15;
			this.speedX = -15;
		} else if (c instanceof Scorpion) {
			loc = this.getClass().getClassLoader().getResource("scorpionBlast.png");
			this.damage = random.nextInt(11) + 20;
			this.speedX = -15;
		} else if (c instanceof Monster) {
			loc = this.getClass().getClassLoader().getResource("snakeBlast.png");
			this.damage = random.nextInt(11) + 30;
			this.speedX = -15;
		}
		blastImage = new ImageIcon(loc).getImage();

		this.setImage(blastImage);
	}

	public void move() {
		int nx = this.x() + speedX;
		int ny = this.y() + speedY;
		if (nx > 1500 || nx < 0)
			this.Visible = false;
		else {
			if (this.Visible) {
				this.setX(nx);
				this.setY(ny);
			}
		}
	}

	public boolean getEBvisible() {
		return Visible;
	}

	public void setEBvisible(Boolean v) {
		Visible = v;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedY(int y) {
		this.speedY = y;
	}
}
