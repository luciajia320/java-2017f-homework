public class HuluboyArray extends Board{
	private People Grandpa;
	private Huluwa[] huluboys;

	public HuluboyArray(){
		super(10,20);
		this.Grandpa = new People();
		this.huluboys = new Huluwa[8];
		for(int i = 1;i < 8;i++){
			this.huluboys[i] = new Huluwa(COLOR.values()[i - 1],SENIORITY.values()[i - 1]);
		}
	}

	public void setGrandpa(){
		this.getPosition()[5][5].setHolder(Grandpa);
	}

	public void setHuluboys(){
		for(int i = 1;i < 8;i++){
			this.getPosition()[9 - i][13].setHolder(this.huluboys[i]);
		}
	}

	public void randomLine(){
		int random;
		for(int i = 1;i < 8;i++){
			random = (int)(Math.random() * 7 + 1);
			this.huluboys[i].getPosition().changePosition(this.huluboys[random].getPosition());
		}
	}
}