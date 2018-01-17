package Calabashboy_v3;

//葫芦娃和爷爷的类
public class Calabash extends Creature {
	private Color col;
	
	//通过颜色构造葫芦娃
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
	
	//直接通过角色构造，爷爷只能这么构造
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
	
	//报告颜色
	public String Report_color() {
		switch(col) {
		case Red:		return "赤";
		case Orange: 	return "橙";
		case Yellow: 	return "黄";
		case Green:		return "绿";
		case Cyan:		return "青";
		case Blue: 		return "蓝";
		case Purple:	return "紫";
		default : 		return "未知";
		}
	}
	
	//报告身份
	public String Report_id() {
		switch(id) {
		case First_cala: 	return "大娃";
		case Second_cala: 	return "二娃";
		case Third_cala: 	return "三娃";
		case Fourth_cala: 	return "四娃";
		case Fifth_cala: 	return "五娃";
		case Sixth_cala: 	return "六娃";
		case Seventh_cala:	return "七娃";
		case Grandpa:		return "爷爷";
		default : return "葫芦娃";
		}
	}
	
}
