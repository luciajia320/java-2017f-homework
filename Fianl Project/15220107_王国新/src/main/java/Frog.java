import java.net.URL;
import javax.swing.ImageIcon;

public class Frog extends Creature {
	public int ordinal;

	public Frog(int ordinal,Field field) {
		super(0, 0);
		this.blood = 100;
		this.field = field;
		this.ordinal = ordinal;
		URL loc1,loc2,loc3;
		loc1 = this.getClass().getClassLoader().getResource("frog.png");
		loc2 = this.getClass().getClassLoader().getResource("frogHurt.png");
		loc3 = this.getClass().getClassLoader().getResource("frogDead.png");
		figureImage = new ImageIcon(loc1).getImage();
		hurtImage = new ImageIcon(loc2).getImage();
		deadImage = new ImageIcon(loc3).getImage();
		attackImage = figureImage;
		this.setImage(figureImage);
	}

	public boolean outOfBound() {
		if (eb.x() < 300) 
			return true;
		return false;
	}

	public void run() {
		while (!Thread.interrupted()) {
			if (isAllEnemiesDead() || this.isDead()) {
				this.field.repaint();
				break;
			}

			if (beginAttack) {
				Attacks(targets, this.ordinal);
				this.beginAttack = false;
			}
			hitEnemy(targets, this.ordinal);
			
			if(this.state==hurtState)
				setState(attackState);
			eb.move();

			try {
				Thread.sleep(100);
				this.field.repaint();
			} catch (Exception e) {
			}
		}
	}
}
