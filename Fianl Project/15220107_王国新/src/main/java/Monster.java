import java.net.URL;
import javax.swing.ImageIcon;

public class Monster extends Creature{
	
	public Monster(Field field,int x,int y) {
		super(x, y);
		this.field = field;
		this.blood = 200;
		URL loc1,loc2,loc3;
		loc1 = this.getClass().getClassLoader().getResource("monster.png");
		loc2 = this.getClass().getClassLoader().getResource("monsterAttack.png");
		loc3 = this.getClass().getClassLoader().getResource("monsterHurt.png");
		figureImage = new ImageIcon(loc1).getImage();
		attackImage = new ImageIcon(loc2).getImage();
		hurtImage = new ImageIcon(loc3).getImage();
		deadImage = figureImage;
		this.setImage(figureImage);
	}
	
	public boolean outOfBound() {
		if (eb.x() < 300) 
			return true;
		return false;
	}
	
	public void run() {
		while (!Thread.interrupted()) {
			if(isAllEnemiesDead() ||this.isDead()) {
				this.field.repaint();
				break;
			}
			
			if(beginAttack) {
				Attacks(targets, 0);
				this.beginAttack = false;
			}
			hitEnemy(targets,0);
			
			if(this.state==hurtState) {
				setState(attackState);
			}
			
			eb.move();
			
			
			try {
				Thread.sleep(100);
				this.field.repaint();
			} catch (Exception e) {
			}
		}
	}


}
