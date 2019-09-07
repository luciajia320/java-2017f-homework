  package Calabashboy_v3;

  //��ͼ�ϵ�λ����Ϣ
public class Position {
	private Creature cre;
	private boolean is_in;
	private int line;
	private int col;
	
	//����λ��
	public Position(int l, int c) {
		line = l; col = c;
		cre = null; is_in = false;
	}
	
	public Position() {
		cre = null;
		is_in = false;
		line = col = 0;
	}
	
	//��λ���Ƿ�����
	public boolean Having() {
		return is_in;
	}
	
	//���˷Ž����λ��
	public void Put_in(Creature a) {
		if(is_in == false) {
			cre = a;is_in = true;
		} 
		else
			System.out.print("���λ���Ѿ�����");
	}
	
	//�����߳�λ��
	public void Pull_out() {
		is_in = false;
	}
	
	//����λ��������˵���Ϣ
	public Creature Get_it() {
		return cre;
	}
	
	//��ȡ��λ������һ��
	public int Get_line() {
		return line;
	}
	
	//��ȡ��λ������һ��
	public int Get_col() {
		return col;
	}
}
