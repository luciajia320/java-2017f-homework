import java.net.URL;
import javax.swing.ImageIcon;

public class Scorpion extends Creature {
	
	public Scorpion(Field field) {
		super(0, 0);
		this.blood = 100;
		this.field = field;
		URL loc1, loc2, loc3,loc4;
		loc1 = this.getClass().getClassLoader().getResource("scorpion.png");
		loc2 = this.getClass().getClassLoader().getResource("scorpionAttack.png");
		loc3 = this.getClass().getClassLoader().getResource("scorpionHurt.png");
		loc4 = this.getClass().getClassLoader().getResource("scorpionDead.png");
		figureImage = new ImageIcon(loc1).getImage();
		attackImage = new ImageIcon(loc2).getImage();
		hurtImage = new ImageIcon(loc3).getImage();
		deadImage = new ImageIcon(loc4).getImage();
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
				Attacks(targets, 0);
				this.beginAttack = false;
			}
			hitEnemy(targets, 0);
			
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
