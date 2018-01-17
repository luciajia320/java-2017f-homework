public class Position{
	private Creature holder;
	private int x,y;
	private boolean ifBorder;

	public Position(int x,int y){
		this.holder = null;
		this.x = x;
		this.y = y;
		this.ifBorder = false;
	}

	public Creature getHolder(){
		return holder;
	}

	public void setHolder(Creature holder){
		this.holder = holder;
		if(holder != null){
			holder.setPosition(this);
		}
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void changePosition(Position position){
		Creature temp = position.holder;
		position.setHolder(this.holder);
		this.setHolder(temp);
	}

	public void setBorder(){
		this.ifBorder = true;
	}

	public boolean ifBorder(){
		return ifBorder;
	}
}