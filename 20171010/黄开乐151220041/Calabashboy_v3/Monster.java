package Calabashboy_v3;

//妖怪类
public class Monster extends Creature{
	//构造函数
	public Monster(Characters cre) {
		id = cre;
	}
	//报告身份
	public String Report_id() {
		switch(id) {
		case Snake_woman: 		return "蛇妖";
		case Scorpion_man: 	return "蝎精";
		case Toad:				return "蟾蜍";
		default:				return "妖怪";
		}
	}
}
