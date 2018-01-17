  package Calabashboy_v3;

  //地图上的位置信息
public class Position {
	private Creature cre;
	private boolean is_in;
	private int line;
	private int col;
	
	//构造位置
	public Position(int l, int c) {
		line = l; col = c;
		cre = null; is_in = false;
	}
	
	public Position() {
		cre = null;
		is_in = false;
		line = col = 0;
	}
	
	//该位置是否有人
	public boolean Having() {
		return is_in;
	}
	
	//把人放进这个位置
	public void Put_in(Creature a) {
		if(is_in == false) {
			cre = a;is_in = true;
		} 
		else
			System.out.print("这个位置已经有人");
	}
	
	//把人踢出位置
	public void Pull_out() {
		is_in = false;
	}
	
	//反馈位置上面的人的信息
	public Creature Get_it() {
		return cre;
	}
	
	//获取该位置在哪一行
	public int Get_line() {
		return line;
	}
	
	//获取该位置在哪一列
	public int Get_col() {
		return col;
	}
}
