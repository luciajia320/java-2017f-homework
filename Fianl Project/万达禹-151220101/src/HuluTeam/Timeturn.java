package HuluTeam;

import Creature.Creature;

public class Timeturn {		//��¼�غ�
	
	protected Creature turn;
	protected boolean whose;	//0Ϊ�ҷ���Ӫ�غϣ� 1Ϊ�з���Ӫ�غ�
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
		if(turn == game.hulu.getCreature(game.hulu.hulubrothers.size()-1)) { //�غϽ���
			index=0;
			whose = true;
			turn = game.ene.getCreature(index);
		}
		else if(turn == game.ene.getCreature(game.ene.enemies.size()-1)) { //�غϽ���
			index=0;
			whose = false;
			turn = game.hulu.getCreature(index);
		}
		else if(whose == false) { //�»غ������ҷ��ж�
			index++;
			turn = game.hulu.getCreature(index);
		}
		else{		//�»غ����ǵз��ж�
			index++;
			turn = game.ene.getCreature(index);
		}
		return turn;
	}
	
}
