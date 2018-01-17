import java.util.Random;
public class WarRole {
	static public int N=10;
	static public int monsternum=20;
	private Position position[][];
	private Human grandpa;
	private Snake snake;
	private Scorpion scorpion;
	private GourdDoll[] brothers;
	private Monster[] monsters;
	public WarRole() {
		// TODO Auto-generated constructor stub
		position=new Position[2*N][N];
		for(int i=0;i<2*N;i++)
			for(int j=0;j<N;j++)
				position[i][j]=new Position(i, j);
		brothers=new GourdDoll[8];
		monsters=new Monster[monsternum];
	}
	public void addDoll(int ngid,Color ncolor,String nname,int nx,int ny){
		brothers[ngid]=new GourdDoll(ngid, ncolor, nname, nx, ny);
		//position[nx][ny].add_creature(CREATURE_TYPE.GOURD, ngid);
	}
	public void DollsBorn(){
		int []randnumx=new int[8];
		int []randnumy=new int[8];
		Random randomx = new Random();
		Random randomy = new Random();
		/*=======给每个葫芦娃分发随机数，存储在数组中===*/
		for(int i=1;i<=7;i++){
			int randx=randomx.nextInt(N);
			int randy=randomy.nextInt(N);
			while(randx==0||randy==0||position[randx][randy].get_empty()==false){
				randx=randomx.nextInt(N);
				randy=randomy.nextInt(N);
			}
			randnumx[i]=randx;
			randnumy[i]=randy;
			position[randx][randy].add_creature(CREATURE_TYPE.GOURD, i);
		}
		addDoll(1, Color.RED, "红娃", randnumx[1], randnumy[1]);
		addDoll(2, Color.ORANGE, "橙娃", randnumx[2], randnumy[2]);
		addDoll(3, Color.YELLOW, "黄娃", randnumx[3], randnumy[3]);
		addDoll(4, Color.GREEN, "绿娃", randnumx[4], randnumy[4]);
		addDoll(5, Color.CYAN, "青娃", randnumx[5], randnumy[5]);
		addDoll(6, Color.BULE, "蓝娃", randnumx[6], randnumy[6]);
		addDoll(7, Color.PURPLE, "紫娃", randnumx[7], randnumy[7]);
	}
	public void show_shape(){
		for(int i=0;i<2*N;i++){
			for(int j=0;j<N;j++)
				position[i][j].show();
			System.out.println();
		}
	}
	public void DollChangeShape(){
		for(int i=0;i<N;i++)
			for(int j=1;j<N;j++)
				position[i][j].delate_creature();
		for(int i=1;i<=7;i++){
			brothers[i].change_position(10-i,5 );
			position[10-i][5].add_creature(CREATURE_TYPE.GOURD, i);
		}
	}
	public void monster_born(){
		for(int i=0;i<monsternum;i++)
			monsters[i]=new Monster(i,0,0);
	}
	public void scorpion_born(){
		scorpion=new Scorpion(0,0 );
	}
	public void snake_born(){
		snake=new Snake(0, 0);
	}
	public void grandpa_born(){
		grandpa=new Human(0, 0);
	}
	public void  HeYiShape_monster(){
		for(int i=N;i<2*N;i++)
			for(int j=1;j<N;j++)
				position[i][j].delate_creature();
		monsters[1].change_position(11, 2);
		monsters[2].change_position(12, 3);
		monsters[3].change_position(13, 4);
		scorpion.change_position(14, 5);
		monsters[4].change_position(13, 6);
		monsters[5].change_position(12, 7);
		monsters[6].change_position(11, 8);
		position[11][2].add_creature(CREATURE_TYPE.MONSTER, 1);
		position[12][3].add_creature(CREATURE_TYPE.MONSTER, 2);
		position[13][4].add_creature(CREATURE_TYPE.MONSTER, 3);
		position[14][5].add_creature(CREATURE_TYPE.SCORPION,0 );
		position[13][6].add_creature(CREATURE_TYPE.MONSTER, 4);
		position[12][7].add_creature(CREATURE_TYPE.MONSTER, 5);
		position[11][8].add_creature(CREATURE_TYPE.MONSTER, 6);
	}
	public void YanXing_monster(){
		for(int i=N;i<2*N;i++)
			for(int j=1;j<N;j++)
				position[i][j].delate_creature();
		monsters[1].change_position(16, 3);
		monsters[2].change_position(15, 4);
		scorpion.change_position(14, 5);
		monsters[4].change_position(13, 6);
		monsters[5].change_position(12, 7);
		position[16][3].add_creature(CREATURE_TYPE.MONSTER, 1);
		position[15][4].add_creature(CREATURE_TYPE.MONSTER, 2);
		position[14][5].add_creature(CREATURE_TYPE.SCORPION,0 );
		position[13][6].add_creature(CREATURE_TYPE.MONSTER, 4);
		position[12][7].add_creature(CREATURE_TYPE.MONSTER, 5);
	}
	public void Cheer(){
		grandpa.change_position(0, 5);
		position[0][5].add_creature(CREATURE_TYPE.HUMAN, 0);
		snake.change_position(19, 5);
		position[19][5].add_creature(CREATURE_TYPE.SNAKE, 0);
		grandpa.cheer();
		snake.cheer();
	}
	public void war_srart(){
		System.out.println("——————————葫芦娃登场————————");
		System.out.println();
		DollsBorn();
		show_shape();
		System.out.println();
		DollChangeShape();
		System.out.println("——————————葫芦娃变换队形————————");
		System.out.println();
		show_shape();
		System.out.println();
		monster_born();
		scorpion_born();
		System.out.println("——————————蝎子精带小兵登场————————");
		System.out.println();
		HeYiShape_monster();
		show_shape();
		System.out.println();
		grandpa_born();
		snake_born();
		System.out.println("——————————爷爷和蛇精登场助威————————");
		System.out.println();
		Cheer();
		System.out.println();
		show_shape();
		System.out.println();
		System.out.println("——————————蝎子精带小兵变换队形————————");
		System.out.println();
		YanXing_monster();
		show_shape();
	}

}
