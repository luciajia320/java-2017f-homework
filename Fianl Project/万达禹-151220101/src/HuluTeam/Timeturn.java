package HuluTeam;

import Creature.Creature;

public class Timeturn {		//记录回合
	
	protected Creature turn;
	protected boolean whose;	//0为我方阵营回合， 1为敌方阵营回合
	protected BattleField game;
	int index;
	
	public Timeturn(BattleField f){
		game = f;
		index = 0;
		whose = false;
		turn = f.hulu.getCreature(index);
	}
	
	public void reset(){	
		index = 0;
		whose = false;
		turn = game.hulu.getCreature(index);
	}
	
	public Creature NextTurn() {
		if(turn == game.hulu.getCreature(game.hulu.hulubrothers.size()-1)) { //回合交替
			index=0;
			whose = true;
			turn = game.ene.getCreature(index);
		}
		else if(turn == game.ene.getCreature(game.ene.enemies.size()-1)) { //回合交替
			index=0;
			whose = false;
			turn = game.hulu.getCreature(index);
		}
		else if(whose == false) { //下回合仍是我方行动
			index++;
			turn = game.hulu.getCreature(index);
		}
		else{		//下回合仍是敌方行动
			index++;
			turn = game.ene.getCreature(index);
		}
		return turn;
	}
	
}
