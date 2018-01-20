import java.util.ArrayList;
import java.util.Random;

public class BadGuy {
	private ArrayList<Creature> enemy;
	public BadGuy() {
		enemy=new ArrayList<Creature>();
	}
	public void addOne(String name) {
		Creature temp=new Creature(name);
		enemy.add(temp);
	}
	public void removeOne() {
		enemy.remove(enemy.size()-1);
	}
	public int count() {
		return enemy.size();
	}
	public void setNumber(int n) {
		if(enemy.size()<n) {
			while(enemy.size()<n) {
				addOne("monster");
			}
		}
		else if(enemy.size()>n) {
			while(enemy.size()>n) {
				removeOne();
			}
		}
	}
	public void showEnemy() {
		for(Creature c:enemy) {
			c.report();
		}
	}
	public void formation(Map map, int x, int y) {
		strategy s;
		Random random=new Random();
		int choice=random.nextInt(5);
		switch(choice) {
		case 0:{
			s=new ArrowStrategy();
			setNumber(12);
			s.ourStrategy(map, enemy, x, y);
		}break;
		case 1:{
			s=new GooseStrategy();
			setNumber(5);
			s.ourStrategy(map, enemy, x, y);
		}break;
		case 2:{
			s=new WingStrategy();
			setNumber(7);
			s.ourStrategy(map, enemy, x, y);
		}break;
		case 3:{
			s=new ScaleStrategy();
			setNumber(10);
			s.ourStrategy(map, enemy, x, y);
		}break;
		case 4:{
			s=new SquareStrategy();
			setNumber(8);
			s.ourStrategy(map, enemy, x, y);
		}break;
		}
		
	}
}
