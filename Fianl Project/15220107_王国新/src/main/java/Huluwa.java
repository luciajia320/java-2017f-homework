import java.net.URL;
import javax.swing.ImageIcon;

public class Huluwa extends Creature {
	private SENIORITY seniority;
	
	public Huluwa(SENIORITY seniority, Field field) {
		super(0, 0);
		this.blood = 100;
		this.field = field;
		this.seniority = seniority;
				
		switch (seniority) {
		case 老大:
			loadAllImage(1);
			break;
		case 老二:
			loadAllImage(2);
			break;
		case 老三:
			loadAllImage(3);
			break;
		case 老四:
			loadAllImage(4);
			break;
		case 老五:
			loadAllImage(5);
			break;
		case 老六:
			loadAllImage(6);
			break;
		case 老七:
			loadAllImage(7);
			break;
		default:
			break;
		}
	}

	
	public boolean outOfBound() {
		if (eb.x() > 1150) 
			return true;
		return false;
	}
	
	public void loadAllImage(int index) {
		URL loc1, loc2, loc3, loc4;
		loc1 = this.getClass().getClassLoader().getResource(index + "stand.png");
		loc2 = this.getClass().getClassLoader().getResource(index + "attack.png");
		loc3 = this.getClass().getClassLoader().getResource(index + "hurt.png");
		loc4 = this.getClass().getClassLoader().getResource(index + "dead.png");
		this.figureImage = new ImageIcon(loc1).getImage();
		this.attackImage = new ImageIcon(loc2).getImage();
		this.hurtImage = new ImageIcon(loc3).getImage();
		this.deadImage = new ImageIcon(loc4).getImage();
		this.setImage(this.figureImage);
	}
	
	public SENIORITY getSeniority() {
		return seniority;
	}
		
	public void run() {
		while (!Thread.interrupted()) {
			if(this.isDead())
				return;
			if(isAllEnemiesDead()) {
				if(targets.size()==1)
					break;
				//endAttack();
				this.field.repaint();
			}
			//-----------攻击敌人--------------
			if(beginAttack) {
				if(targets.get(0) instanceof Monster)
					Attacks(targets.get(0));
				else
					Attacks(targets, this.getSeniority().ordinal());
				this.beginAttack = false;
			}
			
			//--------判断是否击中或被击中并更新状态-----------
			if(targets.get(0) instanceof Monster)
				hitEnemy(targets.get(0));
			else
				hitEnemy(targets,this.getSeniority().ordinal());
			if(this.state==hurtState)
				setState(attackState);
			//----------攻击波的移动------------
			eb.move();
					
			try {
				Thread.sleep(100);
				this.field.repaint();

			} catch (Exception e) {
			}
		}
	}

}

enum SENIORITY {
	老大, 老二, 老三, 老四, 老五, 老六, 老七
}