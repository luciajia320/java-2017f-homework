package FinalProject;

public class Level {
	private static int level;
	private static Ground g;
	private static Stage stage;
	private static Step step;
	private static Huluwa huluwa;
	private static Shejing shejing;
	private static Xiezi xiezi;
	private static Louluo louluo;
	private static Devil devil;
	private static Redmonster red;
	private static Blackmonster black;
	private static Medicine [] medicine;
	private static int amount;
	private static Yeye yeye;
	public Level() {
		level = 0;
		level++;
		amount = 17;
		g = new Ground();
		set();
	}
	public static void restart() {
		level = 1;
		set();
	}
	public static void pause() {
		stage.interrupt();
	}
	public static int getlevel() {
		return level;
	}
	public static int getamount() {
		return amount;
	}
	public static void levelpass() {
		level++;
		Creature.resetamount();
		//System.out.println(Creature.getamount());
		set();
	}
	public static void setbase() { //set base here
		switch(level) {
		case 1:
			for(int i=0;i<Stage.xsize();i++)
				for(int j=0;j<Stage.ysize();j++)
					Stage.ground_below[i][j].settype(getlevel());
			for(int i=0;i<Stage.ysize();i++) {
				Stage.ground_mid[0][i].settype(101);
				Stage.ground_mid[Stage.xsize()-1][i].settype(101);
			}
			for(int i=0;i<Stage.xsize();i++) {
				Stage.ground_mid[i][0].settype(101);
				Stage.ground_mid[i][Stage.ysize()-1].settype(101);
			}
			//Stage.ground_mid[1][6].settype(101);
			//Stage.ground_mid[1][4].settype(101);
			Stage.ground_mid[18][1].settype(101);
			Stage.ground_mid[17][1].settype(101);
			Stage.ground_mid[16][1].settype(101);
			Stage.ground_mid[15][1].settype(101);
			Stage.ground_mid[15][9].settype(101);
			Stage.ground_mid[16][9].settype(101);
			Stage.ground_mid[17][9].settype(101);
			Stage.ground_mid[18][9].settype(101);
			Stage.ground_mid[17][8].settype(101);
			Stage.ground_mid[16][8].settype(101);
			Stage.ground_mid[15][9].settype(101);
			Stage.ground_mid[18][8].settype(101);
			Stage.ground_mid[17][7].settype(101);
			//Stage.ground_mid[18][6].settype(101);
			Stage.ground_mid[18][7].settype(101);
			Stage.ground_mid[17][2].settype(101);
			Stage.ground_mid[16][2].settype(101);
			Stage.ground_mid[15][1].settype(101);
			Stage.ground_mid[18][2].settype(101);
			Stage.ground_mid[17][3].settype(101);
			//Stage.ground_mid[18][4].settype(101);
			Stage.ground_mid[18][3].settype(101);
			Stage.ground_mid[7][2].settype(101);
			Stage.ground_mid[7][3].settype(101);
			Stage.ground_mid[8][2].settype(101);
			Stage.ground_mid[9][2].settype(101);
			Stage.ground_mid[10][2].settype(101);
			Stage.ground_mid[11][2].settype(101);
			Stage.ground_mid[7][5].settype(101);
			Stage.ground_mid[8][5].settype(101);
			Stage.ground_mid[9][5].settype(101);
			Stage.ground_mid[10][5].settype(101);
			Stage.ground_mid[11][5].settype(101);
			Stage.ground_mid[11][4].settype(101);
			Stage.ground_mid[11][6].settype(101);
			Stage.ground_mid[10][8].settype(101);
			Stage.ground_mid[11][8].settype(101);
			Stage.ground_mid[9][8].settype(101);
			Stage.ground_mid[8][8].settype(101);
			Stage.ground_mid[7][8].settype(101);
			Stage.ground_mid[7][7].settype(101);
			Stage.ground_mid[6][5].settype(101);
			Stage.ground_mid[6][3].settype(101);
			Stage.ground_mid[6][7].settype(101);
			break;
		case 2:
			for(int i=0;i<Stage.xsize();i++)
				for(int j=0;j<Stage.ysize();j++)
					Stage.ground_below[i][j].settype(getlevel());
			Stage.ground_mid[0][0].settype(115);
			Stage.ground_mid[0][Stage.ysize()-1].settype(115);
			Stage.ground_mid[Stage.xsize()-1][Stage.ysize()-1].settype(115);
			Stage.ground_mid[Stage.xsize()-1][0].settype(115);
			for(int i=1;i<Stage.xsize()-1;i++) {
				Stage.ground_mid[i][0].settype(118);
				Stage.ground_mid[i][Stage.ysize()-1].settype(112);
			}
			for(int i=1;i<Stage.ysize()-1;i++) {
				Stage.ground_mid[0][i].settype(116);
				Stage.ground_mid[Stage.xsize()-1][i].settype(114);
			}
			int k;
			k = 0;
			for(int j=0;j<3;j++)
				for(int i=0;i<3;i++) {
					k++;
					Stage.ground_mid[10+i][6+j].settype(110+k);
				}
			k = 0;
			for(int j=0;j<3;j++)
				for(int i=0;i<3;i++) {
					k++;
					Stage.ground_mid[10+i][2+j].settype(110+k);
				}
			k = 0;
			Stage.ground_mid[5][3].settype(111);
			Stage.ground_mid[6][3].settype(112);
			Stage.ground_mid[7][3].settype(112);
			Stage.ground_mid[8][3].settype(113);
			Stage.ground_mid[5][7].settype(117);
			Stage.ground_mid[6][7].settype(118);
			Stage.ground_mid[7][7].settype(118);
			Stage.ground_mid[8][7].settype(119);
			Stage.ground_mid[5][4].settype(114);
			Stage.ground_mid[6][3].settype(112);
			Stage.ground_mid[7][3].settype(112);
			Stage.ground_mid[8][4].settype(116);
			Stage.ground_mid[8][5].settype(116);
			Stage.ground_mid[8][6].settype(116);
			Stage.ground_mid[5][5].settype(114);
			Stage.ground_mid[5][6].settype(114);
			Stage.ground_mid[6][4].settype(115);
			Stage.ground_mid[6][5].settype(115);
			Stage.ground_mid[6][6].settype(115);
			Stage.ground_mid[7][4].settype(115);
			Stage.ground_mid[7][5].settype(115);
			Stage.ground_mid[7][6].settype(115);
			medicine = new Medicine[6];
			for(int i=0;i<6;i++) {
				medicine[i] = new Medicine();
				if(i<=2)
					medicine[i].setposition(1, 4-i);
				else
					medicine[i].setposition(1, 11-i);
			}
			break;
		case 3:
			break;
		}
	}
	public static void set() {  // set level items here
		switch(level) {
		case 1:
			stage = new Stage(20,11);
			setbase();
			Stage.setfirst();
			step = new Step();
			shejing = new Shejing(step);
			xiezi = new Xiezi(step);
			yeye = new Yeye(step);
			huluwa = new Huluwa(7,step);
			louluo = new Louluo(8,step);
			amount = 17;
			huluwa.setposition(2, 2, 0);
			yeye.setposition(1, 5);
			xiezi.setposition(17, 5);
			louluo.setposition(18, 5, 2);
			shejing.setposition(18, 5);
			stage.setstep(step);
			g.gamestart();
			Stage.show();
			break;
		case 2:
			stage = new Stage(20,11);
			setbase();
			step = new Step();
			yeye = new Yeye(step);
			huluwa = new Huluwa(7,step);
			devil = new Devil(2,step);
			red = new Redmonster(4,step);
			black = new Blackmonster(4,step);
			amount = 17;
			huluwa.setposition(2, 2, 0);
			yeye.setposition(1, 5);
			devil.setposition(17, 3, 4);
			black.setposition(17, 3, 4);
			red.setposition(17, 7, 4);
			stage.setstep(step);
			g.gamestart();
			Stage.show();
			break;
		case 3:
			System.out.println("all mission completed!");
			//Savefile.save();
		}
	}
	public static void start() {
		switch(level) {
		case 1:
			stage.start();
			huluwa.start();
			louluo.start();
			//yeye.start();
			xiezi.start();
			shejing.start();
			break;
		case 2:
			stage.start();
			huluwa.start();
			devil.start();
			red.start();
			black.start();
			break;
		}
	}
	public static void load() {
		g.gamestart(0);
	}
}
