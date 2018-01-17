
enum Color {
    RED,ORANGE,YELLOW,GREEN,CYAN,BULE,PURPLE
}

public class Creature extends Being{
	protected CREATURE_TYPE type;
	protected String name;
	protected int position_x;
	protected int position_y;
	public void change_position(int x,int y){
		position_x=x;
		position_y=y;
	}
	public CREATURE_TYPE get_type(){
		return type;
	}
	public String get_name(){
		return name;
	}
	public int get_x(){
		return position_x;
	}
	public int get_y(){
		return position_y;
	}
}

class GourdDoll extends Creature{
	private int gid;
	private Color color;
	public GourdDoll(int ngid,Color ncolor,String nname,int nx,int ny){
		gid=ngid;
		color=ncolor;
		name=nname;
		position_x=nx;
		position_y=ny;
		type=CREATURE_TYPE.GOURD;
		is_creature=true;
	}
	public int get_gid(){
		return gid;
	}
	public Color get_color(){
		return color;
	}
}

class Human extends Creature implements Action{
	public Human(int x,int y){
		position_x=x;
		position_y=y;
		type=CREATURE_TYPE.HUMAN;
		is_creature=true;
	}
	public void cheer(){
		System.out.println("爷爷说：葫芦娃加油！");
	}
}

class Snake extends Creature implements Action{
	public Snake(int x,int y){
		position_x=x;
		position_y=y;
		type=CREATURE_TYPE.SNAKE;
		is_creature=true;
	}
	public void cheer(){
		System.out.println("蛇精说：蝎子精加油！");
	}
}

class Scorpion extends Creature{
	public Scorpion(int x,int y){
		position_x=x;
		position_y=y;
		type=CREATURE_TYPE.SNAKE;
		is_creature=true;
	}
}

class Monster extends Creature{
	private int mid;
	public Monster(int nmid,int x,int y){
		mid=nmid;
		position_x=x;
		position_y=y;
		type=CREATURE_TYPE.MONSTER;
		is_creature=true;
	}
	public int get_mid(){
		return mid;
	}
}