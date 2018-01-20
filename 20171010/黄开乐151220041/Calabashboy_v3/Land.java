package Calabashboy_v3;

//用于摆阵的地图
public class Land {
	private Position[] map;
	private int Line;
	private int Col;
	
	//构造地图，输入地图的大小
	public Land(int line, int col) {
		Line = line;
		Col = col;
		map = new Position[Line * Col];
		for(int i = 0;i < Line;i++) {
			for(int j = 0;j < Col;j++) {
				map[i*Col + j] = new Position(i,j);
			}
		}
	} 
	
	//把一个人物放到(i,j)的位置上去
	public boolean Put_creature(Creature a, int i, int j) {
		if(i < 1 || j < 1)
			return false;
		if(map[(i-1)*Col + j-1].Having())
			return false;
		
		map[(i-1)*Col + j-1].Put_in(a);
		
		return true;
	}
	
	//输出地图信息
	public void Print_map() {
		for(int i = 0;i < Line;i++) {
			for(int j = 0;j < Col;j++) {
				if(map[i*Col+j].Having() == false)
					System.out.print("\t");
				else
					System.out.print(map[i*Col+j].Get_it().Report_id()+'\t');
			}
			System.out.println();
		}
	}
	
	//清空地图
	public void Clear_map() {
		for(int i = 0;i < Line;i++) {
			for(int j = 0;j < Col;j++) {
				map[i*Col+j].Pull_out();
			}
		}
	}
	
	//获取地图的行数
	public int Get_lines() {
		return Line;
	}
	
	//获取地图的列数
	public int Get_cols() {
		return Col;
	}
}
