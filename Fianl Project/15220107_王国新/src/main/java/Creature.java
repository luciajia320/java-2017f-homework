import java.awt.Image;
import java.util.ArrayList;

public abstract class Creature extends Thing2D implements Runnable{
	protected Field field;
	protected final static int aliveState = 0;
	protected final static int attackState = 1;
	protected final static int hurtState = 2;
	protected final static int deadState = 3;
	protected final static int HuluwaBlood = 100;
	protected final static int ScorpionBlood = 100;
	protected final static int FrogBlood = 100;
	protected final static int MonsterBlood = 200;
	
	
	//各种状态的图片
	protected Image figureImage;
	protected Image attackImage;
	protected Image hurtImage;
	protected Image deadImage;
	//攻击波
	protected EnergyBlast eb;
	//攻击目标
	protected ArrayList<Creature> targets = new ArrayList<Creature>();
	
	//状态[准备、攻击、受到攻击、死亡]
	protected int state;
	//血量
	protected int blood = 100;
	//是否寻找下一目标
	protected boolean nextTarget = false;
	//是否开始攻击
	protected boolean beginAttack = false; 
	
	public Creature(int x, int y) {
		super(x, y);
	}

	public abstract void run();
	
	public abstract boolean outOfBound();
	
	//初始化状态̬
	public void initState() {
		this.initBlood();
		this.nextTarget = false;
		this.beginAttack = false; 
		eb = new EnergyBlast(this.x(), this.y(), this);
		this.setState(aliveState);
	}
	
	public void setState(int state) {
		this.state = state;
		switch (state) {
		case aliveState:
			this.setImage(figureImage);
			this.eb.setEBvisible(false);
			break;
		case attackState:
			this.setImage(attackImage);
			this.eb.setEBvisible(true);
			break;
		case hurtState:
			this.setImage(hurtImage);
			break;
		case deadState:
			this.setImage(deadImage);
			this.eb.setEBvisible(false);
			break;
		default:
			break;
		}
	}
	
	public int getState() {
		return this.state;
	}
	
	//���㹥��Ŀ��������ƶ��ٶ�
	public void Attacks(Creature target) {
		if (target.isDead()) {
			nextTarget = true;
			return;
		}
		int speedX = eb.getSpeedX();
		int speedY;
		int distanceX = 0;
		int distanceY = 0;
		distanceX = target.x() - this.x();
		distanceY = target.y() - this.y();
		speedY = distanceY * speedX / distanceX;
		eb.setSpeedY(speedY);
		eb.setX(this.x());
		eb.setY(this.y());
		setState(attackState);
	}
	
	//若当前攻击目标死亡，寻找下一目标
	public void Attacks(ArrayList<Creature> targets, int beginIndex) {
		for (int i = 0; i < targets.size(); i++) {
			if (targets.get((i + beginIndex) % 7).isDead() == false) {
				this.Attacks(targets.get((i + beginIndex) % 7));
				break;
			}
		}
	}
	
	//判断攻击波是否击中敌人或超出边界
	public void hitEnemy(Creature target) {
		int x = eb.x();
		int y = eb.y();
		int width = eb.getImage().getWidth(null);
		int height = eb.getImage().getHeight(null);

		if (conflict(x + width, y, target) || conflict(x + width, y + height, target)) {

			eb.setX(this.x());
			eb.setY(this.y());
			target.beingAttacked(eb.getDamage());
			this.Attacks(target);
		}
		if(this.outOfBound()) {
			this.nextTarget = true;
		}
	}
	
	public void hitEnemy(ArrayList<Creature> targets,int beginIndex) {
		for (int i = 0; i < targets.size(); i++) {
			if (targets.get((i + beginIndex) % 7).isDead() == false) {
				if(this.nextTarget==true) {
					this.Attacks(targets.get((i + beginIndex) % 7));
					nextTarget = false;
				}
				this.hitEnemy(targets.get((i + beginIndex) % 7));
				break;
			}
		}
	}
	
	//受到攻击，血量减少
	public void beingAttacked(int damage) {
		if (this.isDead() == false) {
			blood -= damage;
			if (blood <= 0) 
				setState(deadState);
			else
				setState(hurtState);
		}
	}

	
	public void initBlood() {
		if(this instanceof Huluwa)
			this.blood = HuluwaBlood;
		else if(this instanceof Frog)
			this.blood = FrogBlood;
		else if(this instanceof Scorpion)
			this.blood = ScorpionBlood;
		else if(this instanceof Monster)
			this.blood = MonsterBlood;
	}
	
	public void beginAttack() {
		this.beginAttack = true;
	}
	
	public void endAttack() {
		setState(aliveState);
		eb.setX(this.x());
		eb.setY(this.y());
	}
	
	//设置攻击目标
	public void setEnemy(ArrayList<Creature> targets) {
		this.targets = targets;
	}
		
	public boolean isDead() {
		return this.state == deadState;
	}
	
	public boolean isAllEnemiesDead() {
		for (int i = 0; i < targets.size(); i++) {
			if (targets.get(i).isDead() == false)
				return false;
		}
		return true;
	}
	
	public EnergyBlast getEnergyBlast() {
		return eb;
	}
	
	//判断攻击波是否击中敌人
	public boolean conflict(int x, int y, Thing2D creature) {
		if (x > creature.x() && x < creature.x() + creature.getImage().getWidth(null) && y > creature.y()
				&& y < creature.y() + creature.getImage().getHeight(null))
			return true;
		return false;
	}

}
