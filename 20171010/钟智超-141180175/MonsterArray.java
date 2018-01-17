public class MonsterArray extends Board{
	private MonsterBoss xieziMonster;
	private Monster[] weakMonster;

	public MonsterArray(){
		super(10,20);
		this.xieziMonster = new MonsterBoss();
		weakMonster = new Monster[20];
		for(int i = 0;i < 20;i++){
			this.weakMonster[i] = new Monster();
		}
		this.getPosition()[5][5].setHolder(xieziMonster);
	}

	public void setWingArray(){
		this.getPosition()[2][10].setHolder(weakMonster[1]);
		this.getPosition()[4][11].setHolder(weakMonster[2]);
		this.getPosition()[6][12].setHolder(weakMonster[3]);
		this.getPosition()[8][13].setHolder(weakMonster[4]);
		this.getPosition()[6][14].setHolder(weakMonster[5]);
		this.getPosition()[4][15].setHolder(weakMonster[6]);
		this.getPosition()[2][16].setHolder(weakMonster[7]);
	}

	public void setLineArray(){
		for(int i = 1;i < 6;i++){
			this.getPosition()[i][20 - 2 * i].setHolder(weakMonster[i]);
		}
	}

	public void setCrossArray(){
		for(int i = 0;i < 3;i++){
			this.getPosition()[2 * i + 2][11].setHolder(weakMonster[i + 1]);
		}
		for(int i = 0;i < 3;i++){
			this.getPosition()[2 * i + 3][15].setHolder(weakMonster[i + 4]);
		}
	}

	public void setFishArray(){
		this.getPosition()[5][10].setHolder(weakMonster[1]);
		this.getPosition()[4][11].setHolder(weakMonster[2]);
		this.getPosition()[5][12].setHolder(weakMonster[3]);
		this.getPosition()[2][13].setHolder(weakMonster[4]);
		this.getPosition()[4][13].setHolder(weakMonster[5]);
		this.getPosition()[6][13].setHolder(weakMonster[6]);
		this.getPosition()[3][14].setHolder(weakMonster[7]);
		this.getPosition()[5][14].setHolder(weakMonster[8]);
		this.getPosition()[4][15].setHolder(weakMonster[9]);
		this.getPosition()[5][16].setHolder(weakMonster[10]);
	}

	public void setSquareArray(){
		this.getPosition()[5][9].setHolder(weakMonster[1]);
		this.getPosition()[3][11].setHolder(weakMonster[2]);
		this.getPosition()[7][11].setHolder(weakMonster[3]);
		this.getPosition()[1][13].setHolder(weakMonster[4]);
		this.getPosition()[9][13].setHolder(weakMonster[5]);
		this.getPosition()[3][15].setHolder(weakMonster[6]);
		this.getPosition()[7][15].setHolder(weakMonster[7]);
		this.getPosition()[5][17].setHolder(weakMonster[8]);
	}

	public void setMoonArray(){
		for(int i = 1;i < 4;i++){
			for(int j = 1;j < 4;j++){
				this.getPosition()[i + 3][10 + 2 * j].setHolder(weakMonster[3 * i + j]);
			}
		}
		this.getPosition()[1][17].setHolder(weakMonster[10]);
		this.getPosition()[2][16].setHolder(weakMonster[11]);
		this.getPosition()[2][14].setHolder(weakMonster[12]);
		this.getPosition()[3][15].setHolder(weakMonster[13]);
		this.getPosition()[3][13].setHolder(weakMonster[14]);
		this.getPosition()[7][13].setHolder(weakMonster[15]);
		this.getPosition()[7][15].setHolder(weakMonster[16]);
		this.getPosition()[8][14].setHolder(weakMonster[17]);
		this.getPosition()[8][16].setHolder(weakMonster[18]);
		this.getPosition()[9][17].setHolder(weakMonster[19]);
	}

	public void setArrowArray(){
		for(int i = 1;i < 7;i++){
			this.getPosition()[i + 1][13].setHolder(weakMonster[i]);
		}
		this.getPosition()[5][10].setHolder(weakMonster[7]);
		this.getPosition()[4][11].setHolder(weakMonster[8]);
		this.getPosition()[3][12].setHolder(weakMonster[9]);
		this.getPosition()[3][14].setHolder(weakMonster[10]);
		this.getPosition()[4][15].setHolder(weakMonster[11]);
		this.getPosition()[5][16].setHolder(weakMonster[12]);
	}
}