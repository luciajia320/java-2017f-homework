import java.awt.Image;

public interface Creature extends Runnable{
	public String getName();
	
	public int setFieldPos(Position pos);
	
	public Position getFieldPos();
	
	public void move();
	
	public int getSide();
	
	public boolean dead();
	
	public void getKilled();
	
	public int getLuck();

	public Image getImage();
	
	public int getNum();
}
