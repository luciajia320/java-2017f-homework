package Calabashboy_v3;

//���ڰ���ĵ�ͼ
public class Land {
	private Position[] map;
	private int Line;
	private int Col;
	
	//�����ͼ�������ͼ�Ĵ�С
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
	
	//��һ������ŵ�(i,j)��λ����ȥ
	public boolean Put_creature(Creature a, int i, int j) {
		if(i < 1 || j < 1)
			return false;
		if(map[(i-1)*Col + j-1].Having())
			return false;
		
		map[(i-1)*Col + j-1].Put_in(a);
		
		return true;
	}
	
	//�����ͼ��Ϣ
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
	
	//��յ�ͼ
	public void Clear_map() {
		for(int i = 0;i < Line;i++) {
			for(int j = 0;j < Col;j++) {
				map[i*Col+j].Pull_out();
			}
		}
	}
	
	//��ȡ��ͼ������
	public int Get_lines() {
		return Line;
	}
	
	//��ȡ��ͼ������
	public int Get_cols() {
		return Col;
	}
}
