import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Queue {
	public static final int N = 17;
	private Creature[][] creatures;
	private Position[][] positions;
	
	Queue(HuLuwa[] brothers)  {
		this.positions = new Position[N][N];
        this.creatures = new Creature[N][N];
        for (int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                this.positions[i][j] = new Position(i,j);
            }
        }
                
    }
	
    public Position[][] getPositions() {
        return positions;
    }

    public Creature[][] getCreatures() {
        return creatures;
    }
	
	public void rollCall() {
		for(int i = 0; i < 7;i++){
			if (this.positions[i+9][N/2].isCreature())
				this.positions[i+9][N/2].getHolder().print();
            System.out.println();
        }
	}
	
	public void print() {
		if (this.positions == null) return ;
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (this.positions[i][j].isCreature()) {
					this.positions[i][j].getHolder().print();
                    System.out.print("\t");
				}
				else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}
	
	public void putCreature(int x, int y, Creature creature) {
		this.positions[x][y].setHolder(creature);
		this.positions[x][y].setCreature(true);
		creature.setPosition(this.positions[x][y]);
		this.creatures[x][y] = creature;
	}

	public void shuffle() {
		System.out.println("");
		Random rnd = ThreadLocalRandom.current();
        for (int i = 6; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            index += 9;
            Position position = creatures[index][N/2].getPosition();
            creatures[index][N/2].setPosition(creatures[i+9][N/2].getPosition());
            creatures[i+9][N/2].setPosition(position);
        }
	}

	
	public static void main(String[] args) {
		HuLuwa[] brothers = new HuLuwa[7];
		for(int i=0; i < brothers.length; i++){
            brothers[i] = new HuLuwa(COLOR.values()[i], NUMBER.values()[i]);
        }
		
		Queue queue = new Queue(brothers);
		
		for(int i = 0; i < brothers.length; i++){
			queue.putCreature(i+9,  N/2, brothers[i]);
        }
		
		queue.rollCall();
		queue.shuffle();
		
		System.out.println("=======================================");
		System.out.println("长蛇阵乱序站队：");
		queue.rollCall();
		
		Creature[] enemies = new Creature[9]; 
		enemies[0] = new XieZijing();
		for (int i = 1; i < enemies.length - 2; i++) {
			enemies[i] = new Loluo();
		}
		enemies[7] = new SheJing();
		enemies[8] = new LaoYeye();
		
		System.out.println("=======================================");
		System.out.println("鹤翼阵对长蛇阵：");
		Formation.heyi(queue, brothers, enemies);
		queue.print();
		
		System.out.println("=======================================");
		System.out.println("锋失阵对长蛇阵：");
		Formation.fengshi(queue, brothers, enemies);
		queue.print();
		
	}
}
