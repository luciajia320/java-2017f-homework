package Calabashboy_v3;

//������
public class Monster extends Creature{
	//���캯��
	public Monster(Characters cre) {
		id = cre;
	}
	//�������
	public String Report_id() {
		switch(id) {
		case Snake_woman: 		return "����";
		case Scorpion_man: 	return "Ы��";
		case Toad:				return "���";
		default:				return "����";
		}
	}
}
