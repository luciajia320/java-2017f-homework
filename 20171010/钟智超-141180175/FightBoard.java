public class FightBoard extends Board{
	public FightBoard(){
		super(22,22);
		for(int i = 0;i < 22;i++){
			this.getPosition()[0][i].setBorder();
			this.getPosition()[21][i].setBorder();
			this.getPosition()[i][0].setBorder();
			this.getPosition()[i][21].setBorder();
		}
	}

	public static void main(String[] args){
		FightBoard fightBoard = new FightBoard();
		//葫芦娃长蛇阵入场
		HuluboyArray huluBoard = new HuluboyArray();
		huluBoard.setGrandpa();
		huluBoard.setHuluboys();
		huluBoard.randomLine();
		fightBoard.boardInsertion(huluBoard,1,1);

		//妖怪鹤翼阵入场
		MonsterArray monsterBoard = new MonsterArray();
		monsterBoard.setWingArray();
		fightBoard.boardInsertion(monsterBoard,11,1);
		System.out.println("鹤翼阵：");
		fightBoard.output();
		//妖怪雁行阵入场
		monsterBoard = new MonsterArray();
		monsterBoard.setLineArray();
		fightBoard.boardInsertion(monsterBoard,11,1);
		System.out.println("雁行阵：");
		fightBoard.output();
		//妖怪冲厄阵入场
		monsterBoard = new MonsterArray();
		monsterBoard.setCrossArray();
		fightBoard.boardInsertion(monsterBoard,11,1);
		System.out.println("冲轭阵：");
		fightBoard.output();
		//妖怪方阵入场
		monsterBoard = new MonsterArray();
		monsterBoard.setSquareArray();
		fightBoard.boardInsertion(monsterBoard,11,1);
		System.out.println("方阵：");
		fightBoard.output();
		//妖怪鱼鳞阵入场
		monsterBoard = new MonsterArray();
		monsterBoard.setFishArray();
		fightBoard.boardInsertion(monsterBoard,11,1);
		System.out.println("鱼鳞阵：");
		fightBoard.output();
		//妖怪偃月阵入场
		monsterBoard = new MonsterArray();
		monsterBoard.setMoonArray();
		fightBoard.boardInsertion(monsterBoard,11,1);
		System.out.println("偃月阵：");
		fightBoard.output();
		//妖怪锋矢阵入场
		monsterBoard = new MonsterArray();
		monsterBoard.setArrowArray();
		fightBoard.boardInsertion(monsterBoard,11,1);
		System.out.println("锋矢阵：");
		fightBoard.output();
	}
}