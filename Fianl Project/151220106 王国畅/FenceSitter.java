import java.util.Vector;

public class FenceSitter {
	private Vector<Creature> Huluwa;
	private Vector<Creature> Monster;
	private Creature Laoyeye;
	private int HeroCount;
	private Field field;
	
	public void Record(Creature[] Huluwa,Creature[] Louluo,Creature Laoyeye,Field field){
		this.Huluwa = new Vector<Creature>();
		this.Monster = new Vector<Creature>();
		this.field = field;
		for(Creature i:Huluwa){
			this.Huluwa.add(i);
		}
		for(Creature i:Louluo){
			this.Monster.add(i);
		}
		int LaoyeyeNum = Laoyeye==null?0:1;
		HeroCount = this.Huluwa.size() + LaoyeyeNum;
	}
	
	public Creature SearchForLaoyeye(){
		return Laoyeye;
	}
	
	public Creature SearchForMonster(){
		if(Monster.size() == 0)
			return null;
		int i = new java.util.Random().nextInt(Monster.size());
		return Monster.get(i);
		
	}
	
	public Creature SearchForHuluwa(){
		if(Huluwa.size() == 0)
			return null;
		int i = new java.util.Random().nextInt(Huluwa.size());
		return Huluwa.get(i);
	}
	
	public void HearDeath(Creature d){
		if(d == Laoyeye)
			Laoyeye = null;
		else{
			Huluwa.removeElement(d);
			Monster.removeElement(d);
		}
		int LaoyeyeNum = Laoyeye==null?0:1;
		HeroCount = this.Huluwa.size() + LaoyeyeNum;
		if(HeroCount==0){
			field.setWinner(1);
			field.gameOver();
		}
		else if(this.Monster.size()==0){
			field.setWinner(0);
			field.gameOver();
		}
	}
	
	public synchronized void ViewBattle(Huluwa Hero,Creature Monster){
		int heroPower = Hero.getLuck()*10 + HeroCount*12;
		int MonsterPower = Monster.getLuck()*10 + this.Monster.size()*12;
		int r = new java.util.Random().nextInt(heroPower+MonsterPower);
		if(r < heroPower){
			Monster.getKilled();
			HearDeath(Monster);
			return;
		}
		else{
			Hero.getKilled();
			HearDeath(Hero);
			return;
		}
	}
}
