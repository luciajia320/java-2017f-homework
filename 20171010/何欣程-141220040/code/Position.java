

public class Position extends Being {
	private boolean is_empty;
	private int x;
	private int y; 
	private CREATURE_TYPE c_type;
	private int id;
	public Position(int nx,int ny){
		is_empty=true;
		x=nx;
		y=ny;
		is_creature=false;
	}
	public void add_creature(CREATURE_TYPE nc_type,int nid){
		c_type=nc_type;
		id=nid;
		is_empty=false;
	}
	public void delate_creature(){
		is_empty=true;
	}
	
	public void show(){
		if(is_empty==true)
			System.out.print("¿Ú");
		else{
			int iorder=c_type.ordinal();
			switch(iorder){
			case 0:
				System.out.print("ÍÞ");
				break;
			case 1:
				System.out.print("Ò¯");
				break;
			case 2:
				System.out.print("Éß");
				break;
			case 3:
				System.out.print("±ø");
				break;
			case 4:
				System.out.print("Ð«");
				break;
			default:
				break;}
				
		}
	}
	public boolean get_empty(){
		return is_empty;
	}
	public int get_x(){
		return x;
	}
	public int get_y(){
		return y;
	}
	public CREATURE_TYPE get_ctype(){
		return c_type;
	}
	public int get_id(){
		return id;
	}
}

