
public class Position {
	private Creature holder;
	private final int x,y;
	
	public Creature getHolder(){
		return holder;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	//�ɹ����÷���2�����򷵻ص�ǰ���������side
	public int setHolder(Creature holder){
		if(holder==null){
			this.holder=null;
			return 2;
		}
		else if(this.holder!=null)
			return this.holder.getSide();
		this.holder = holder;
		return 2;
	}
	
	public Position(int x,int y){
		this.x = x;
		this.y = y;
		this.holder = null;
	}
}
