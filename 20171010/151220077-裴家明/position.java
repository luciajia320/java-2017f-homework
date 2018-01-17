package javaH2;

public class position {
	private int x;
	private int y;
	private creature master;
	
	public position()
	{
		
	}
	
	public creature getMaster()
	{
		return master;
	}
	
	public void setMaster(creature A)
	{
		master=A;
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}
	
	public int getY()
	{
		return y;
	}

	public position(int x,int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
