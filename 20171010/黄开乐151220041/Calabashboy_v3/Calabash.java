package Calabashboy_v3;

//��«�޺�үү����
public class Calabash extends Creature {
	private Color col;
	
	//ͨ����ɫ�����«��
	public Calabash(Color c) {
		col = c;
		switch(col) {
		case Red:		id = Characters.First_cala;	break;
		case Orange: 	id = Characters.Second_cala;	break;
		case Yellow: 	id = Characters.Third_cala;	break;
		case Green:		id = Characters.Fourth_cala;	break;
		case Cyan:		id = Characters.Fifth_cala;	break;
		case Blue: 		id = Characters.Sixth_cala;	break;
		case Purple:	id = Characters.Seventh_cala;	break;
		default : 		id = Characters.NULL;
		}
	}
	
	//ֱ��ͨ����ɫ���죬үүֻ����ô����
	public Calabash(Characters c) {
		id = c;
		switch(id) {
		case First_cala: 	col = Color.Red;
		case Second_cala: 	col = Color.Orange;
		case Third_cala: 	col = Color.Yellow;
		case Fourth_cala: 	col = Color.Green;
		case Fifth_cala: 	col = Color.Cyan;
		case Sixth_cala: 	col = Color.Blue;
		case Seventh_cala:	col = Color.Purple;
		case Grandpa:		col = Color.NULL;
		}
	}
	
	//������ɫ
	public String Report_color() {
		switch(col) {
		case Red:		return "��";
		case Orange: 	return "��";
		case Yellow: 	return "��";
		case Green:		return "��";
		case Cyan:		return "��";
		case Blue: 		return "��";
		case Purple:	return "��";
		default : 		return "δ֪";
		}
	}
	
	//�������
	public String Report_id() {
		switch(id) {
		case First_cala: 	return "����";
		case Second_cala: 	return "����";
		case Third_cala: 	return "����";
		case Fourth_cala: 	return "����";
		case Fifth_cala: 	return "����";
		case Sixth_cala: 	return "����";
		case Seventh_cala:	return "����";
		case Grandpa:		return "үү";
		default : return "��«��";
		}
	}
	
}
